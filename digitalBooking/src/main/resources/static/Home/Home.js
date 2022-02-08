import { caracteristicas} from '/modulosJs/caracteristicas.js';
import {card} from '/modulosJs/card.js';
import {cortarDescrip} from '/modulosJs/cortarDescrip.js';
import {estrellas} from '/modulosJs/estrellas.js';
import {verMas} from '/modulosJs/verMas.js';
import {sFavoritos} from '/modulosJs/selectFavoritos.js';
import {BASE_URL} from '/modulosJs/constantes.js'
import {listarFavoritos} from '/modulosJs/listarFavoritos.js'



window.addEventListener("load", () => {



fetch(BASE_URL + "productos")
.then(res => res.json())
.then(function (data){
    let listaProductos = document.querySelector("#myList");
    let carga = document.querySelectorAll(".carga")
    carga.forEach((li) => {
        listaProductos.removeChild(li)
    })

    data.forEach(producto =>{

        card(producto, document.querySelector("#myList"));   
        
       
    })

// FUNCION CORTAR DESCRIPCION

    cortarDescrip();

// FUNCION PONER ESTRELLAS

    estrellas();

// FUNCION PONER cARACTERISTICAS

    caracteristicas();
 
// FUNCION PARA MOSTRAR MAS

    verMas();

    listarFavoritos()

    sFavoritos()

// TERMINA EL THEN
})
.catch(err => console.log("Error:", err));


cortarDescrip()

  

/*==============fUNCION PARA MOSTRAR CATEGORIAS DESDE LA API====================== */

fetch(BASE_URL + "categorias")
.then(res => res.json())
.then(function (data){

    let seccionCategorias = document.querySelector(".seccionCategorias");
    let cardCategorias = document.querySelectorAll(".cargaC"); 
    cardCategorias.forEach((card) => {
        seccionCategorias.removeChild(card)
    })

    data.forEach(categoria => {
        seccionCategorias.innerHTML +=`
        <div class="cardCategoria" data-categoria="${categoria.titulo}">
            <img src="${categoria.imagen}" alt="" class="imagenCategoria">
            <h3 class="tituloCategoria">${categoria.titulo}</h3>
            <div class="descripcionCategoria">
            <p >${categoria.descripcion}</p>
            </div>
       </div>
        `
    });



    /*==============fUNCION PARA MOSTRAR PRODUCTOS SEGUN CATEGORIA====================== */
    let cardCat = document.querySelectorAll(".cardCategoria")
    let seccionRecomendaciones = document.querySelector("#myList");

    cardCat.forEach(categoria =>{

        categoria.addEventListener("click", function () {
            seccionRecomendaciones.innerHTML = `
                <li class="carga">
                <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>`
                
            fetch(BASE_URL + `productos/categoria/${categoria.dataset.categoria}`)
            .then(res => res.json())
            .then(function (data){
              
                let carga = document.querySelectorAll(".carga")
                carga.forEach((li) => {
                    seccionRecomendaciones.removeChild(li)
                })

                if(data.length == 0) {
                    
                    document.querySelector(".tituloRec").innerHTML = `Ups! no hay opciones disponibles para esta selección.`
                } else {
                    document.querySelector(".tituloRec").innerHTML=`${data[0].categoria.titulo}`

                    data.forEach(producto =>{

                        card(producto,document.querySelector("#myList"));
                    })
                
                    cortarDescrip();
    
            // FUNCION PONER ESTRELLAS
    
                    estrellas();
    
            // FUNCION PONER CARACTERISTICAS
    
                    caracteristicas();
    
            // FUNCION PARA MOSTRAR TEXTO
    
                    verMas();
                }
              

            })  
                //TERMINA THEN
            .catch(err => console.log("Error:", err));
        })

    })
  
})
//TERMINA EL THEN DE CATEGORIAS
.catch(err => console.log("Error:", err));

  

  //SI EL CHECKBOX DE "AUN NO SE LAS FECHAS" ESTA SELECCIONADO SE EJECUTA LA FUNCION 
let checkFechas = document.querySelector("#checkFechas")
checkFechas.addEventListener("click", () =>{
    checked()
})

 /*============== API PARA SELECT DE CIUDAD ====================== */

let inputCiudad = document.querySelector("#buscarCiudad");
let slecCiudad = document.querySelector(".select-ciudad")                      
fetch(BASE_URL + `ciudades`)
.then(res => res.json())
.then(function (data){
    let option = document.createElement("option")
    option.value = "Todas las ciudades"
    option.style.color="red"
    slecCiudad.append(option)
    data.forEach(pais => {
        let option2 = document.createElement("option")
        option2.value = pais.nombre; 
        
        slecCiudad.append(option2)

        //Buscar ciudad en tiempo real 
        
        inputCiudad.addEventListener("keyup", () => {
            fetch(BASE_URL + `ciudades/nombre`) 
  
        })

        
    })  
       
    

})
.catch(err => console.log("Error:", err));


//FUNCION FILTRAR DISPONIBLES POR FECHAS, POR CIUDAD O POR AMBAS
 
let formFechas = document.querySelector(".formBuscar");
formFechas.addEventListener("submit", (e) => {
    e.preventDefault();
    
    let fechaSeleccion = document.querySelector(".drp-selected").textContent,
        fechaIngreso = fechaSeleccion.slice(0, 10).split('/').reverse().join('-'),
        fechaEgreso = fechaSeleccion.slice(13, 23).split('/').reverse().join('-'),
        ciudad = inputCiudad.value,
        seccionRecomendaciones = document.querySelector("#myList");
        
        seccionRecomendaciones.innerHTML = `
                <li class="carga">
                <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>
                <li class="carga">
                    <div class="cardProducto skeleton"></div>
                </li>`

    if(fechaSeleccion != "" && (ciudad === "Todas las ciudades" || ciudad == "")) {
        
        fetch(BASE_URL + `productos/fechas/disponibles?fechaIngreso=${fechaIngreso}&fechaEgreso=${fechaEgreso}`)
        .then((resp) => resp.json())
        .then((data) => {
            
            let titulo = document.querySelector(".tituloRec");
            titulo.innerHTML = `Disponibles en tus fechas`;

            let carga = document.querySelectorAll(".carga")
                carga.forEach((li) => {
                    seccionRecomendaciones.removeChild(li)
                })

            if(data.length == 0) {
                titulo.innerHTML = "Ups! no hay opciones disponibles para esta seleccion"
            } 
            else {
                    
                data.forEach(producto =>{

                    card(producto,document.querySelector("#myList"));
                })

                cortarDescrip();

                estrellas();

                caracteristicas();

                verMas();


            }

        })

        .catch((error) => console.log(error))

    }

    else if(fechaSeleccion == "" && (ciudad !== "" || ciudad !== "Todas las ciudades")) {
        
        fetch(BASE_URL + `productos/ciudad/${ciudad}`)
        .then((respuesta) => respuesta.json())
        .then((data) => {
            let titulo = document.querySelector(".tituloRec");
            

            let carga = document.querySelectorAll(".carga")
                carga.forEach((li) => {
                    seccionRecomendaciones.removeChild(li)
                })

            if(data.length === 0) {
                titulo.innerHTML = "Ups! no hay opciones disponibles para esta seleccion"
            } 
            else {
                titulo.innerHTML = `${data[0].ciudad.nombre}`;
                    
                data.forEach(producto => {
                    card(producto,document.querySelector("#myList"));
                })

                cortarDescrip();

                estrellas();

                caracteristicas();

                verMas();
            }


        })
        .catch((error) => console.log(error))
    }

    else if(ciudad !== "Todas las ciudades" && ciudad != "" && fechaSeleccion != ""){
        fetch(BASE_URL + `productos/fechas/ciudad/disponibles?fechaIngreso=${fechaIngreso}&fechaEgreso=${fechaEgreso}&ciudad=${ciudad}`)
        .then((respuesta) => respuesta.json())
        .then((data) => {
            let titulo = document.querySelector(".tituloRec");
            

            let carga = document.querySelectorAll(".carga")
                carga.forEach((li) => {
                    seccionRecomendaciones.removeChild(li)
                })

            if(data.length == 0) {
                titulo.innerHTML = "Ups! no hay opciones disponibles para esta seleccion"
            } else {
                
                titulo.innerHTML = `${ciudad}`;
                data.forEach(producto => {
                    card(producto,document.querySelector("#myList"));
                })

                cortarDescrip();

                estrellas();

                caracteristicas();

                verMas();
            
            }

        })
    
        .catch((error) => console.log(error))
    
    }

    else {
                
        fetch(BASE_URL + "productos")
        .then(res => res.json())
        .then(function (data){

            let listaProductos = document.querySelector("#myList");
            let carga = document.querySelectorAll(".carga")
            carga.forEach((li) => {
                listaProductos.removeChild(li)
            })

            document.querySelector(".tituloRec").innerHTML= "Todos los alojamientos"

            data.forEach(producto =>{

                card(producto,document.querySelector("#myList"));   
            
            })

        // FUNCION CORTAR DESCRIPCION

            cortarDescrip();

        // FUNCION PONER ESTRELLAS

            estrellas();

        // FUNCION PONER cARACTERISTICAS

            caracteristicas();
        
        // FUNCION PARA MOSTRAR MAS

            verMas();

        // TERMINA EL THEN
        })
        .catch(err => console.log("Error:", err));


        cortarDescrip()

    }

})

})


/*============== CALENDARIO ====================== */

$(function calendario() {
    $('input[name="daterange"]').daterangepicker({
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
              },
    },
    );
   document.querySelector(".btn-primary").addEventListener("click",function () {
       let fechaSelec = document.querySelector(".drp-selected").innerHTML
    localStorage.setItem("fecha",fechaSelec )
    
    document.querySelector(".input-calendario").setAttribute("placeholder",`${fechaSelec}`)
  })  

  //SI SE ELIGEN FECHAS SE DESELECCIONA EL CHECKBOX
    document.querySelector(".btn-primary").addEventListener("click", () => {
        let inputFechas = document.querySelector(".input-calendario").getAttribute("placeholder");
 
        if(inputFechas != " Check in - Check out") {
         checkFechas.checked = false;
        }
    })
  
  //FUNCION PARA PONER EL CALENDARIO ESTATICO
//$('input[name="daterange"]').data('daterangepicker').show();
//$('input[name="daterange"]').data('daterangepicker').hide = function () { };
  });



//FUNCION PARA BORRAR LA SELECCION DE FECHAS
function checked() {

    if(checkFechas.checked === true) {
      localStorage.setItem("fecha", " ")
      document.querySelector(".input-calendario").setAttribute("placeholder", " Check in - Check out")
      document.querySelector(".drp-selected").textContent = ""
    }
}

//modularizar
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




