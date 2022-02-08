import {BASE_URL} from '/modulosJs/constantes.js'

//TRAER ID DE PRODUCTO SELECCIONADO
let url =window.location.hash.slice(1,50);

/*
if (localStorage.getItem('pathReserva') != "" && localStorage.getItem('pathReserva') != null ) {
    url =localStorage.getItem('pathReserva')
    localStorage.setItem('pathReserva', '')
}
*/



fetch(BASE_URL + `productos/${url}`)
.then(res => res.json())
.then(function (data){

        let main = document.querySelector("#header_titulo_producto")
        main.innerHTML +=`

        <div class="headerDetalleProducto nombreHotel">
            <section class="tituloProducto">
                <p>${data.categoria.titulo.toUpperCase()}</p>
                <H2>${data.nombre}</H2>
            </section>
            <section class="flecha-header-nombre">
                <a href="/index.html">
                    <i id="flecha-icono" class="fas fa-chevron-left "></i>
                </a>
            </section>
        </div>
        `

        document.querySelector("#nombre").value = localStorage.getItem("userNombre");
        document.querySelector("#apellido").value = localStorage.getItem("userApellido");
        document.querySelector("#correo").value = localStorage.getItem("userCorreo");

        let detalle_reserva = document.querySelector("#detalle_reserva");
        detalle_reserva.innerHTML +=`
        <div class="div-img-reserva"><img class="img-reserva" src="${data.imagenes[0].url}" alt=""></div>`

        detalle_reserva.innerHTML +=`
            <p class="categoria_det_reserva">${data.categoria.titulo.toUpperCase()}</p>
            <H2>${data.nombre}</H2>`

        detalle_reserva.innerHTML +=`
            <div class="calificacion-producto">
                <span class="estrellas">
                </span>
            </div>
            <i class="fas fa-map-marker-alt"></i>${data.ciudad.nombre}, ${data.ciudad.nombre_pais}
            `

        let politicas = document.querySelector("#sectionPoliticas")

                politicas.innerHTML +=`
                <h3 class="tituloDescrip">Qué tenés que saber</h3>

                <div class="secPoliticas">
        
                <div>
                <h4>Normas de la casa</h4>
                <span>${data.normas.replace(/(?:\r\n|\r|\n)/g, '<br />')}</span>
                </div> 
            
                <div>
                <h4>Salud y Seguridad</h4>
                <span>${data.saludSeguridad.replace(/(?:\r\n|\r|\n)/g, '<br />')}</span>
                </div>
            
            
                <div>
                <h4>Politica de cancelación</h4>
                <span>${data.cancelacion.replace(/(?:\r\n|\r|\n)/g, '<br />')}</span>
                </div>
            
            </div>

                        </div>

                        `
                      
                    document.querySelector(".precioPorNoche").innerHTML=`$${data.precio} por noche`

            let numero = data.puntuacion

            if (numero <= 10 && numero > 8) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`

            }
            else if (numero <= 8 && numero > 6) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`

            }
            else if (numero <= 6 && numero > 5) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`
            }
            else if (numero <= 5 && numero > 3) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">`
            }
            else{
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">`
            }
    })


document.querySelector(".form_reserva").addEventListener('submit',function (event) {
            event.preventDefault() 

            let inputHoraLlegada = document.querySelector("#hora").value.slice(0,2) + ":00"
            let inputCheckIn =  document.querySelector("#checkin").value.replace(" ", "").split('/').reverse().join('-')
            let inputcheckout = document.querySelector("#checkout").value.replace(" ", "").split('/').reverse().join('-')
            let usuarioId = localStorage.getItem("idUsuario")
            let productoId = window.location.hash.slice(1,50)

            document.querySelector("#checkin").value.slice(0, 10).split('/').reverse().join('-')

    let token =localStorage.getItem("token")
    console.log(token);
    let settings = (method, body) => {
        return ({
            method: method,
            headers:{
                'Content-Type':'application/json',
                'Authorization':`${token}`
             },
            body: JSON.stringify(body)
        })
    }

            let datareserva ={
                "horaLlegada" : inputHoraLlegada,
                "fechaLlegada" : inputCheckIn,
                "fechaPartida" : inputcheckout,
                "usuario" : {
                    "id" : usuarioId
                },
                "producto" : {
                    "id" : productoId
                }
            }
console.log(datareserva);

                
    fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => resp.json())            
    .then((usuario) => {
        if(usuario.status != 400 && usuario.rol.id === 1){        
            document.querySelector("#titulo_modal").innerHTML = "No puedes realizar esta acción";
            document.querySelector("#mensaje_modal").innerHTML = "Lamentablemente no puedes realizar esta acción";
            $("#imagen_modal").attr("src", "/img/fail.png");
            modal.style.display = "block";
            
        }else{
            fetch(BASE_URL + "reservas/registro", settings("POST", datareserva))
                
                .then((respuesta) => {
                    respuesta.json()
            if(respuesta.status === 403) {
                        window.location.pathname='/LogIn/LogIn.html'
                        localStorage.setItem("pathReserva",`${window.location.hash.slice(1,50)}`)
                    }
                    else if(respuesta.status === 500) {
                       window.location.pathname='/LogIn/LogIn.html'
                        localStorage.setItem("pathReserva",`${window.location.hash.slice(1,50)}`)
                    }
                   else if (respuesta.status == 200) {
                      modal.style.display = "block";
                   } 
                   else {
                      document.querySelector("#titulo_modal").innerHTML = "Ups! algo salió mal";
                      document.querySelector("#mensaje_modal").innerHTML = "Lamentablemente la reserva no ha podido realizarse. Por favor, intente más tarde";
                      $("#imagen_modal").attr("src", "/img/fail.png");
                      modal.style.display = "block";
                   }
                })
        }
    
                    
                })

   })


   fetch(BASE_URL + `reservas/producto/${url}`)
   .then(res =>  res.json())
   .then(function (data){

        let dateArray = new Array();
        if (data[0]== undefined) {
            data.push('2000-01-01')
        }

       data.forEach(reserva => {
             Date.prototype.addDays = function(days) {
            let dat = new Date(this.valueOf())
            dat.setDate(dat.getDate() + days);
            return dat;
        }
        
        let startDate =new Date(reserva.fechaLlegada)
        let stopDate = new Date(reserva.fechaPartida)


        let currentDate = startDate;
        while (currentDate <= stopDate) {
          dateArray.push(`${currentDate.getDate()+2 <=10 ? "0" +(currentDate.getDate()+1) : (currentDate.getDate()+1)}`
          +"-" +
          `${currentDate.getMonth() <= 9 ? "0" +(currentDate.getMonth()+1) : (currentDate.getMonth()+1)}`
          + "-" +
          `${currentDate.getFullYear()}`)
          currentDate = currentDate.addDays(1);
        }
        


        let date = new Date();
        let currentMonth = date.getMonth();
        let currentDat = date.getDate();
        let currentYear = date.getFullYear();

        //Array fechas bloqueadas
        let some_date_range = dateArray



            $(function calendario() {
                $('input[name="daterange"]').daterangepicker({
                    minDate: new Date(currentYear, currentMonth, currentDat),
                    isInvalidDate: function(date){
                        for(var ii = 0; ii < some_date_range.length; ii++){
                        if (date.format('DD-MM-YYYY') == some_date_range[ii]){
                            return true;
                        }
                        }},
                opens: 'left',
                autoUpdateInput: false,
                    locale: {
                    alwaysShowCalendars: true,
                    showDropdowns: true,
                                applyLabel : "Buscar",
                                cancelLabel : "Cancelar",
                                format: 'DD/MM/YYYY',
                                daysOfWeek: ["Dom","Lun","Mar","Mie","Jue","Vie","Sáb",],
                                monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
            
                                }
                        },
                );
            document.querySelector(".btn-primary").addEventListener("click",function () {
                let fechaSelec = document.querySelector(".drp-selected").innerHTML
                localStorage.setItem("fecha",fechaSelec )
            
                document.querySelector(".input-calendario").setAttribute("placeholder",`${fechaSelec}`)
            })

            //FUNCION PARA PONER EL CALENDARIO ESTATICO
            $('input[name="daterange"]').data('daterangepicker').show();
            $('input[name="daterange"]').data('daterangepicker').hide = function () { };
                });



            let datesInitial;
            $( document ).ready(function() {
                jQuery("#div_fechas").append(jQuery(".daterangepicker"));
                datesInitial = $(".drp-selected").text();
            });

            if (localStorage.getItem("fecha") != "" && localStorage.getItem("fecha") != null) {
                document.querySelector("#checkin").value = localStorage.getItem("fecha").slice(0,10)
                document.querySelector("#checkout").value = localStorage.getItem("fecha").slice(12,25)
            }

            let intervalId = window.setInterval(function(){
            if(datesInitial!=$(".drp-selected").text()){
                let dates = jQuery(".drp-selected").text().replace(" ", "");
                localStorage.setItem("fecha",`${dates}`)
                let arrayDeFechas = dates.split("-");
                jQuery("#checkin").val(arrayDeFechas[0]);
                jQuery("#checkout").val(arrayDeFechas[1]);
            }
            }, 500);

            let disFechas = window.setInterval(function(){
                if(document.querySelectorAll(".disabled.in-range").length != 0){
                    document.querySelector(".error_fechas").innerText=`* Seleccionaste fechas no disponibles`;
                    document.querySelector("#enviar_reserva").style.display = "none";
                }
                else{
                    document.querySelector(".error_fechas").innerText=``;
                    document.querySelector("#enviar_reserva").style.display = "inline-block";
                }
            }, 500);



        

            }) 
            


})

    // Get the modal
    let modal = document.getElementById("modal_reserva_exitosa");

    // Get the <span> element that closes the modal
    let span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
      modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }

    window.setInterval(function(){

    fetch(BASE_URL + `productos/${url}`)
.then(res => res.json())
.then(function (data){

                       
    let dateArrayDos = new Array();
    let startDateDos =new Date(document.querySelector("#checkin").value.replace(" ", "").split('/').reverse().join('-'))
    let stopDateDos = new Date(document.querySelector("#checkout").value.replace(" ", "").split('/').reverse().join('-'))

    let currentDateDos = startDateDos;
    while (currentDateDos <= stopDateDos) {
        dateArrayDos.push(`${currentDateDos.getDate()+2 <=10 ? "0" +(currentDateDos.getDate()+1) : (currentDateDos.getDate()+1)}`
      +"-" +
      `${currentDateDos.getMonth() <= 9 ? "0" +(currentDateDos.getMonth()+1) : (currentDateDos.getMonth()+1)}`
      + "-" +
      `${currentDateDos.getFullYear()}`)
      currentDateDos = currentDateDos.addDays(1);
    }
    if (dateArrayDos[0]==null) {
        document.querySelector(".precioCalculado").innerHTML=``
    }
    else if(dateArrayDos.length <= 2){
        document.querySelector(".precioCalculado").innerHTML=`<span class="precioNumero"> 
        $${((dateArrayDos.length-1 )* data.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-1} noches`
}else if(dateArrayDos.length <= 3){
    document.querySelector(".precioCalculado").innerHTML=`<span class="precioNumero"> 
    $${((dateArrayDos.length-1 )* data.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-1} noches`
}
     
    else{
    document.querySelector(".precioCalculado").innerHTML=`<span class="precioNumero"> $${((dateArrayDos.length-2 )* data.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-2} noches`}

})
    }, 500)