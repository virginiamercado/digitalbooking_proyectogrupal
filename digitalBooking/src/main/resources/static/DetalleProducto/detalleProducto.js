//TRAER ID DE PRODUCTO SELECCIONADO
let url =window.location.hash.slice(1,2);

import {BASE_URL} from '/modulosJs/constantes.js'


fetch(BASE_URL + `productos/${url}`)
.then(res => res.json())
.then(function (data){
        let main= document.querySelector("main")
        main.innerHTML +=`
        <section>
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
        
        <div class="headerDetalleProducto headerDos-producto">
            <section class="ubicacion-header">
                <i class="fas fa-map-marker-alt"></i> 
                <div>
                <p> ${data.ciudad.nombre}, ${data.ciudad.nombre_pais}</p>
                <p class="texto-ubicacion">${data.direccion}</p>
            </div> 
            </section>
            <section class="calificacion-header-producto">
                <div class="calificacion-producto">
                    <p>Muy Bueno</p>
                    <span class="estrellas">
                    </span>
                </div>
                <p class="puntaje-numero">
                    ${data.puntuacion}
                </p>
            </section>
        </div>
        
        <div class="headerDetalleProducto compartir">
            <!-- <i class="st-toggle fas fa-share-alt"></i> -->
            <i class="far fa-heart"></i>
            <div id="compartir" class="sharethis-inline-share-buttons"></div>
        </div>
        </section>
        
        
        <section class="galeriaImgs">
        <img class="Img principal" src="${data.imagenes[0].url}" alt="">
        
        
        <img class="Img secundaria cuatro" src="${data.imagenes[1].url}" alt="">
        
        
        <img class="Img secundaria uno" src="${data.imagenes[2].url}" alt="">
        
        
        <img class="Img secundaria dos" src="${data.imagenes[3].url}" alt="">
        
        
        <img class="Img secundaria tres" src="${data.imagenes[4].url}" alt="">
        
        <div class="carousel desaparecer">
        <div id="imagen"></div>
    </div>

        <span class="verMasImg" onclick="aparecerGaleria()">Ver Más</span>
        
        </section>




        <section class="infoProducto">
        <h3 class="tituloDescrip tFecha"> Alójate en el corazón de ${data.ciudad.nombre}, ${data.ciudad.nombre_pais}</h3>
        <div class="content-descripcion">
        <p class="textoDescrip">
          ${data.descripcionLarga}
        </p>
       
        </div>
        
        <h3 class="tituloDescrip">¿Qué ofrece este lugar?</h3>
        
        <seccion class="conteinerIconos">
        <div>
        </div>
        </seccion>
        
    <section id="bloque-fechas-disponibles">
        <h2 id="fechas-disponibles-titulo" class="tituloDescrip tFecha">Fechas disponibles</h2>

        <div id="bloque-disponibilidad">
            <div id="div_fechas"></div>
            <input class="input-calendario fa" id="calendarioLine" type="text" name="daterange"
                        onclick="calendario()"
                       placeholder="&#xf073; Check in - Check out"
                       style="font-family:Arial, FontAwesome;display:none" autocomplete="off"/>
            <div id="informacion-reserva">
                <h5 id="texto-iniciar-reserva"></h5>
                <a id="button-iniciar-reserva" data-productoId="${data.id}">Iniciar reserva</a>
            </div>
        </div>
    </section>
        
        <section class="secMapa">
            <h3 class="tituloDescrip">¿Dónde vas a estar?</h3>
            <span class="mapaCiudad">${data.ciudad.nombre}, ${data.ciudad.nombre_pais}</span>
            <div class="mapa"> 
            ${data.ubicacion2}
            </div>
        
           
        
        </section>
        
        
        
        
        <section class="sectionPoliticas">
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
        </section>


        </section>
        `

    direccionarReserva();

    //ABRE EL CARRUSEL DE IMAGENES DEL PRODUCTO
    document.querySelectorAll(".Img").forEach((img) => {
        img.addEventListener("click", () => aparecerGaleria())
    })


    //FUNCION PARA PONER Y SACAR FAVORITOS

    let idUsuario = localStorage.getItem("idUsuario")
    if (idUsuario != null) {

    

    fetch(BASE_URL + `usuarios/favoritos/${idUsuario}`)
    .then((resp) => resp.json())
    .then((dataF) => {

        if (dataF[0]!=null) {
             dataF.forEach(producto=>{

        if (producto.id == data.id) {
            document.querySelector(".fa-heart.far").classList.add("fas")
            document.querySelector(".fa-heart.far").classList.remove("far")

        let corazon=document.querySelectorAll(".fas.fa-heart")
        corazon[1].addEventListener('click',function(){
             let idUsuario = localStorage.getItem("idUsuario")
             let settings =  {
                     method : "PUT",
                     headers: {
                         'Content-Type': 'application/json',
                     },
             }
         
         fetch(BASE_URL + `usuarios/favoritos/quitar?idUsuario=${idUsuario}&idProducto=${producto.id}`, settings)
         .then((resp) => resp.json())
         .then((data) => {/*console.log(data)*/})
         .catch(err => console.log("Error:", err));
         
                   corazon[1].classList.remove("fas")
                   corazon[1].classList.add("far")


        })

    }
    
    })
   }else{
        console.log("hola");
        let corazonDos=document.querySelector(".far.fa-heart")
corazonDos.addEventListener('click',function(){
     let idUsuario = localStorage.getItem("idUsuario")
     let settings =  {
             method : "PUT",
             headers: {
                 'Content-Type': 'application/json',
             },
     }
 
 fetch(BASE_URL + `usuarios/favoritos/agregar?idUsuario=${idUsuario}&idProducto=${data.id}`, settings)
 .then((resp) => resp.json())
 .then((data) => {/*console.log(data)*/})
 .catch(err => console.log("Error:", err));
 
           corazonDos.classList.add("fas")
           corazonDos.classList.remove("far")
})

    }
})
}






//FUNCION CALENDARIO ESTATICO

fetch(BASE_URL + `reservas/producto/${url}`)
   .then(res =>  res.json())
   .then(function (data){


        var dateArray = new Array();
        if (data[0]== undefined) {
            data.push('2000-01-01')
        }

       data.forEach(reserva => {
             Date.prototype.addDays = function(days) {
            var dat = new Date(this.valueOf())
            dat.setDate(dat.getDate() + days);
            return dat;
        }
        
        let startDate =new Date(reserva.fechaLlegada)
        let stopDate = new Date(reserva.fechaPartida)


        var currentDate = startDate;
        while (currentDate <= stopDate) {
          dateArray.push(`${currentDate.getDate()+2 <=10 ? "0" +(currentDate.getDate()+1) : (currentDate.getDate()+1)}`
          +"-" +
          `${currentDate.getMonth() <= 9 ? "0" +(currentDate.getMonth()+1) : (currentDate.getMonth()+1)}`
          + "-" +
          `${currentDate.getFullYear()}`)
          currentDate = currentDate.addDays(1);
        }
        
       
        
 



var date = new Date();
var currentMonth = date.getMonth();
var currentDate = date.getDate();
var currentYear = date.getFullYear();

   //Array fechas bloqueadas
   var some_date_range = dateArray



     $(function calendario() {
        $('input[name="daterange"]').daterangepicker({
            minDate: new Date(currentYear, currentMonth, currentDate),
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

       //FUNCION PARA PONER EL CALENDARIO ESTATICO
       $('input[name="daterange"]').data('daterangepicker').show();
       $('input[name="daterange"]').data('daterangepicker').hide = function () { };
         });


// FUNCION TRAER FECHAS EN LOCAL

    if (localStorage.getItem("fecha") == "" || localStorage.getItem("fecha") == null &&
    document.querySelectorAll(".disabled.in-range").length == 0 ) {
    document.querySelector("#texto-iniciar-reserva").innerHTML=`
    Agregá tus fechas de viaje para obtener precios exactos.
    `
    } 
    else if (localStorage.getItem("fecha") != "" ) {
    document.querySelector("#texto-iniciar-reserva").innerHTML=`
    Fechas previamente seleccionadas
    <br>
    ${localStorage.getItem("fecha")}
    `
    }

var datesInitial;
$( document ).ready(function() {
    jQuery("#div_fechas").append(jQuery(".daterangepicker"));
    datesInitial = $(".drp-selected").text();
});

window.setInterval(function(){
    if(datesInitial!=$(".drp-selected").text()){
        document.querySelector("#button-iniciar-reserva").style.display="inline-block"
        var dates = jQuery(".drp-selected").text().replace(" ", "");
        document.querySelector("#texto-iniciar-reserva").innerHTML=`
        Fechas Seleccionadas
    <br>
    ${dates}
    `
    localStorage.setItem("fecha",`${dates}`)
    }
  }, 500);

  window.setInterval(function(){
    if(document.querySelectorAll(".disabled.in-range").length != 0){
        document.querySelector("#button-iniciar-reserva").style.display="none"
        document.querySelector("#texto-iniciar-reserva").innerText=`* Seleccionaste fechas no disponibles`
    }
  }, 500);


    }) 
    


})




    // let fechaSelec = document.querySelector(".drp-selected").innerHTML
    // document.querySelector("#texto-iniciar-reserva").innerHTML=`
    // Fechas Previamente Seleccionadas
    // <br>
    // ${fechaSelec}`

//FUNCION PARA PONER PUNTAJES AUTOMATICOS
        function puntajes(numero) {
            numero = data.puntuacion
            
            if (numero <= 10 && numero > 8) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`
                document.querySelector(".calificacion-producto").childNodes[1].innerHTML=`
                Excelente `
                
            }    
            else if (numero <= 8 && numero > 6) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`
                document.querySelector(".calificacion-producto").childNodes[1].innerHTML=`
                Muy Bueno `
                
            } 
            else if (numero <= 6 && numero > 5) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">
                <i class="fas fa-star">`
                document.querySelector(".calificacion-producto").childNodes[1].innerHTML=`
                Bueno `
                
            } 
            else if (numero <= 5 && numero > 3) {
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">
                <i class="fas fa-star">`
                document.querySelector(".calificacion-producto").childNodes[1].innerHTML=`
                Regular `
            } 
            else{
                document.querySelector(".estrellas").innerHTML=`
                <i class="fas fa-star">`
                document.querySelector(".calificacion-producto").childNodes[1].innerHTML=`
                Malo `
            }


            }
        puntajes()
        //FUNCION PARA PONER CARACTERISTICAS AUTOMATICOS
        function caracteristicas() {

           let contentIconos = document.querySelector(".conteinerIconos").childNodes[1]
            data.caracteristicas.forEach(caract =>{
                contentIconos.innerHTML +=`
                <p class="servicio"><i class="${caract.icono}"></i> ${caract.nombre} </p>
                `
            })
        
        
            }
        caracteristicas()


        //FUNCION PARA PONER IMAGENES DEL DISPLAY
        data.imagenes.forEach(img=>{
             document.querySelector(".slider-content").innerHTML+=`
        <div class="slider-single">
                           <img class="slider-single-image" src="${img.url}" alt="1" />
                       </div>
        `

//RESERVAS
function direccionarReserva(){
    let buttonReserva = document.querySelector("#button-iniciar-reserva");
    
            let idproducto = buttonReserva.dataset.productoid
            buttonReserva.setAttribute("href",`/Reserva/reservaProducto.html#${idproducto}`)
    }
    direccionarReserva()


        })
    
//TERMINA EL THEN




//FUNCIONES PARA DISPLAY DE GALERIA
const repeat = false;
const noArrows = false;
const noBullets = false;


const container = document.querySelector('.slider-container');
var slide = document.querySelectorAll('.slider-single');
var slideTotal = slide.length - 1;
var slideCurrent = -1;

function initBullets() {
    if (noBullets) {
        return;
    }
    const bulletContainer = document.createElement('div');
    bulletContainer.classList.add('bullet-container')
    slide.forEach((elem, i) => {
        const bullet = document.createElement('div');
        bullet.classList.add('bullet')
        bullet.id = `bullet-index-${i}`
        bullet.addEventListener('click', () => {
            goToIndexSlide(i);
        })
        bulletContainer.appendChild(bullet);
        elem.classList.add('proactivede');
    })
    container.appendChild(bulletContainer);
}

function initArrows() {
    if (noArrows) {
        return;
    }
    const leftArrow = document.createElement('a')
    const iLeft = document.createElement('i');
    iLeft.classList.add('fas')
    iLeft.classList.add('fa-chevron-left')
    leftArrow.classList.add('slider-left')
    leftArrow.appendChild(iLeft)
    leftArrow.addEventListener('click', () => {
        slideLeft();
    })
    const rightArrow = document.createElement('a')
    const iRight = document.createElement('i');
    iRight.classList.add('fas')
    iRight.classList.add('fa-chevron-right')
    rightArrow.classList.add('slider-right')
    rightArrow.appendChild(iRight)
    rightArrow.addEventListener('click', () => {
        slideRight();
    })
    container.appendChild(leftArrow);
    container.appendChild(rightArrow);
}

function slideInitial() {
    initBullets();
    initArrows();
    setTimeout(function () {
        slideRight();
    }, 500);
}

function updateBullet() {
    if (!noBullets) {
        document.querySelector('.bullet-container').querySelectorAll('.bullet').forEach((elem, i) => {
            elem.classList.remove('active');
            if (i === slideCurrent) {
                elem.classList.add('active');
            }
        })
    }
    checkRepeat();
}

function checkRepeat() {
    if (!repeat) {
        if (slideCurrent === slide.length - 1) {
            slide[0].classList.add('not-visible');
            slide[slide.length - 1].classList.remove('not-visible');
            if (!noArrows) {
                document.querySelector('.slider-right').classList.add('not-visible')
                document.querySelector('.slider-left').classList.remove('not-visible')
            }
        }
        else if (slideCurrent === 0) {
            slide[slide.length - 1].classList.add('not-visible');
            slide[0].classList.remove('not-visible');
            if (!noArrows) {
                document.querySelector('.slider-left').classList.add('not-visible')
                document.querySelector('.slider-right').classList.remove('not-visible')
            }
        } else {
            slide[slide.length - 1].classList.remove('not-visible');
            slide[0].classList.remove('not-visible');
            if (!noArrows) {
                document.querySelector('.slider-left').classList.remove('not-visible')
                document.querySelector('.slider-right').classList.remove('not-visible')
            }
        }
    }
}

function slideRight() {
    if (slideCurrent < slideTotal) {
        slideCurrent++;
    } else {
        slideCurrent = 0;
    }

    if (slideCurrent > 0) {
        var preactiveSlide = slide[slideCurrent - 1];
    } else {
        var preactiveSlide = slide[slideTotal];
    }
    var activeSlide = slide[slideCurrent];
    if (slideCurrent < slideTotal) {
        var proactiveSlide = slide[slideCurrent + 1];
    } else {
        var proactiveSlide = slide[0];

    }

    slide.forEach((elem) => {
        var thisSlide = elem;
        if (thisSlide.classList.contains('preactivede')) {
            thisSlide.classList.remove('preactivede');
            thisSlide.classList.remove('preactive');
            thisSlide.classList.remove('active');
            thisSlide.classList.remove('proactive');
            thisSlide.classList.add('proactivede');
        }
        if (thisSlide.classList.contains('preactive')) {
            thisSlide.classList.remove('preactive');
            thisSlide.classList.remove('active');
            thisSlide.classList.remove('proactive');
            thisSlide.classList.remove('proactivede');
            thisSlide.classList.add('preactivede');
        }
    });
    preactiveSlide.classList.remove('preactivede');
    preactiveSlide.classList.remove('active');
    preactiveSlide.classList.remove('proactive');
    preactiveSlide.classList.remove('proactivede');
    preactiveSlide.classList.add('preactive');

    activeSlide.classList.remove('preactivede');
    activeSlide.classList.remove('preactive');
    activeSlide.classList.remove('proactive');
    activeSlide.classList.remove('proactivede');
    activeSlide.classList.add('active');

    proactiveSlide.classList.remove('preactivede');
    proactiveSlide.classList.remove('preactive');
    proactiveSlide.classList.remove('active');
    proactiveSlide.classList.remove('proactivede');
    proactiveSlide.classList.add('proactive');

    updateBullet();
}

function slideLeft() {
    if (slideCurrent > 0) {
        slideCurrent--;
    } else {
        slideCurrent = slideTotal;
    }

    if (slideCurrent < slideTotal) {
        var proactiveSlide = slide[slideCurrent + 1];
    } else {
        var proactiveSlide = slide[0];
    }
    var activeSlide = slide[slideCurrent];
    if (slideCurrent > 0) {
        var preactiveSlide = slide[slideCurrent - 1];
    } else {
        var preactiveSlide = slide[slideTotal];
    }
    slide.forEach((elem) => {
        var thisSlide = elem;
        if (thisSlide.classList.contains('proactive')) {
            thisSlide.classList.remove('preactivede');
            thisSlide.classList.remove('preactive');
            thisSlide.classList.remove('active');
            thisSlide.classList.remove('proactive');
            thisSlide.classList.add('proactivede');
        }
        if (thisSlide.classList.contains('proactivede')) {
            thisSlide.classList.remove('preactive');
            thisSlide.classList.remove('active');
            thisSlide.classList.remove('proactive');
            thisSlide.classList.remove('proactivede');
            thisSlide.classList.add('preactivede');
        }
    });

    preactiveSlide.classList.remove('preactivede');
    preactiveSlide.classList.remove('active');
    preactiveSlide.classList.remove('proactive');
    preactiveSlide.classList.remove('proactivede');
    preactiveSlide.classList.add('preactive');

    activeSlide.classList.remove('preactivede');
    activeSlide.classList.remove('preactive');
    activeSlide.classList.remove('proactive');
    activeSlide.classList.remove('proactivede');
    activeSlide.classList.add('active');

    proactiveSlide.classList.remove('preactivede');
    proactiveSlide.classList.remove('preactive');
    proactiveSlide.classList.remove('active');
    proactiveSlide.classList.remove('proactivede');
    proactiveSlide.classList.add('proactive');

    updateBullet();
}

function goToIndexSlide(index) {
    const sliding = (slideCurrent > index) ? () => slideRight() : () => slideLeft();
    while (slideCurrent !== index) {
        sliding();
    }
}

slideInitial();




let cerrarGImagenes = document.querySelector(".cerrarGaleria")
let seccionGImagenes = document.querySelector(".galeriaDeImagenes")

cerrarGImagenes.addEventListener("click", function () {

    seccionGImagenes.classList.toggle("aparecer")
    let body =document.querySelector("body")
    body.classList.toggle("overflowBody")
})


function aparecerGaleria() {
    let seccionGImagenes = document.querySelector(".galeriaDeImagenes")
    seccionGImagenes.classList.toggle("aparecer")
    let body =document.querySelector("body")
    body.classList.toggle("overflowBody")


}
//FUNCIONES PARA DISPLAY DE GALERIA



});
//TERMINA EL THEN


function aparecerGaleria() {
    let seccionGImagenes = document.querySelector(".galeriaDeImagenes")
    seccionGImagenes.classList.toggle("aparecer")
    let body =document.querySelector("body")
    body.classList.toggle("overflowBody")


}





//Cambio de n° de meses responsive
// var debounce;
//   $(window).resize(function() {
//     clearTimeout(debounce);
//     if ($(window).width() < 800) {
//       debounce = setTimeout(function() {
//         debounceDatepicker(1);
//       }, 100);
//     } else {
//       debounce = setTimeout(function() {
//         debounceDatepicker(2)
//       }, 100);
//     }
//   }).trigger('resize');

//   function debounceDatepicker(no) {
//     $("#datepick").datepick("option", "monthsToShow", no);
//   }




//FUNCION PARA FOTOS AUTOMATICAS EN MOVIL
fetch(BASE_URL + `productos/${url}`)
.then(res => res.json())
.then(function (data){

    let img =[];

    data.imagenes.forEach(mg =>{
       img.push(mg.url) 
    })
    
    const IMAGENES = img;
    const TIEMPO_INTERVALO_MILESIMAS_SEG = 3000;
    let posicionActual = 0;
    let $imagen = document.querySelector('#imagen');
    let intervalo;
    
    
    
    function pasarFoto() {
       if(posicionActual >= IMAGENES.length - 1) {
           posicionActual = 0;
       } else {
           posicionActual++;
       }
       renderizarImagen();
    }
    
    
    function renderizarImagen () {
        $imagen.style.backgroundImage = `url(${IMAGENES[posicionActual]})`;
        document.querySelector(".indexImagen").innerHTML=`${posicionActual+1 + "/" + IMAGENES.length}`
    }
    
    
    function playIntervalo() {
       intervalo = setInterval(pasarFoto, TIEMPO_INTERVALO_MILESIMAS_SEG);
    }
    playIntervalo()
    
    renderizarImagen(); 

})







/*===========================fUNCION PARA EL BOTON DE SUBIR============================== */

$(document).ready(function(){
        
        $('.Subir').click(function(){
            $('body, html').animate({
                scrollTop: '0px'
            }, 300);
        });
    
        $(window).scroll(function(){
            if( $(this).scrollTop() > 0 ){
                $('.Subir').slideDown(300);
            } else {
                $('.Subir').slideUp(300);
            }
        });

});



//RESERVAS
function direccionarReserva(){
    let buttonReserva = document.querySelector("#button-iniciar-reserva");
    
           let idproducto = buttonReserva.dataset.productoid
            buttonReserva.setAttribute("href",`/Reserva/reservaProducto.html#${idproducto}`)
    }