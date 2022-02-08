//const BASE_URL="http://ec2-54-87-12-130.compute-1.amazonaws.com:8080/"
import {BASE_URL} from '/modulosJs/constantes.js'

function insertAfter(referenceNode, newNode) {
  referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

var el = document.getElementById("bloque-creacion-caracteristica");
var div = document.getElementById("div-container-prueba");
insertAfter(div, el);


 /*============== API PARA SELECT DE CATEGORÍA ====================== */

 let slecCategoria = document.querySelector("#categorias")
 fetch(BASE_URL +`categorias`)
 .then(res => res.json())
 .then(function (data){
     data.forEach(categoria => {
         slecCategoria.innerHTML +=`
         <option value="${categoria.id}">${categoria.titulo}</option> `
     })
 })
 .catch(err => console.log("Error:", err));


  /*============== API PARA SELECT DE CIUDAD ====================== */
  let slecCiudad = document.querySelector("#select-ciudad2")
  fetch(BASE_URL + `ciudades`)
  .then(res => res.json())
  .then(function (data){
      data.forEach(ciudad => {
          slecCiudad.innerHTML +=`
          <option value="${ciudad.id}" class="option-ciudad">${ciudad.nombre}</option> `
      })
  })
  .catch(err => console.log("Error:", err));

  /*============== API PARA LISTADO CARACTERISTICAS ====================== */

  let atributosListado = document.querySelector(".bloque-caracteristicas")
  fetch(BASE_URL + `caracteristicas`)
 .then(res => res.json())
 .then(function (data){
     data.forEach(caracteristica => {
        atributosListado.innerHTML +=`
        <div class="bloque-caracteristica">
        <input class="checkbox-caracteristicas"type="checkbox" id="${caracteristica.id}" name="${caracteristica.nombre}" value="${caracteristica.id}">
        <label class="nombre-caracteristica" for="${caracteristica.id}">${caracteristica.nombre}</label>
        <div>`

     })
 })
 .catch(err => console.log("Error:", err));

let formCreacionCaracteristica= document.querySelector("#form-creacion-caracteristica")
let nombreNuevaCaracteristica = document.querySelector("#nombre_nueva_caracteristica")
let iconoNuevaCaracteristica = document.querySelector("#icono_nueva_caracteristica")
let botonCrearCaracteristica = document.querySelector(".btn-agregar-caracteristica")
let data1 ={
    nombre: "",
    icono: "",
    }
botonCrearCaracteristica.addEventListener('click',function (event) {


let data1 ={
    nombre: nombreNuevaCaracteristica.value,
    icono: iconoNuevaCaracteristica.value,
    }

let settings1 = {
       method : "POST",
       headers: {
           'Content-Type': 'application/json'
       },
       body : JSON.stringify(data1)
   };

fetch(BASE_URL +`caracteristicas/registro`, settings1)
.then((respuesta) => {
   respuesta.json()
   if(respuesta.status === 200) {
       fetch(BASE_URL + `caracteristicas`)
      .then(res => res.json())
      .then(function (data){

           atributosListado.innerHTML= ""
          data.forEach(caracteristica => {
             atributosListado.innerHTML +=`
             <div class="bloque-caracteristica">
             <input class="checkbox-caracteristicas"type="checkbox" id="${caracteristica.id}" name="${caracteristica.nombre}" value="${caracteristica.id}">
             <label class="nombre-caracteristica" for="${caracteristica.id}">${caracteristica.nombre}</label>
             <div>`
          })

        $(document).ready(function() {new jBox('Notice', {
                                        content: ' ✓ Característica creada con éxito!',
                                        color: 'green',
                                        attributes: {
                                              x: 'right',
                                              y: 'bottom'
                                            },
                                            showCountdown:true,
                                            autoClose: 3000
                                      });})

      })
      .catch(err => console.log("Error:", err));
     }else{
     console.log("error")

     }
     })

.catch((error) => {
   console.log(error);
   document.querySelector("#mensaje-error").classList.toggle("error")
   document.querySelector("#mensaje-error").innerHTML = `<p>Error. Por favor intente más tarde</p>`
})

});






/*=============CAPTURANDO ELEMENTOS============= */

let formCreacionProducto = document.querySelector(".formCreacionProducto")
let botonCrearProducto = document.querySelector(".btn-crearProducto")
let inputNombreProducto = document.querySelector("#inputNombreProducto")
let categoria = document.querySelector("#categorias")
let inputUbicacion= document.querySelector("#inputUbicacion")
let ciudad = document.querySelector("#select-ciudad2")
let inputUbicacionGoogleMaps = document.querySelector("#inputUbicacionGoogleMaps")
let inputUbicacionGoogleMaps2 = document.querySelector("#inputUbicacionGoogleMaps2")
let descripcionCortaForm = document.querySelector("#text-area-descripcion-corta")
let descripcionLargaForm = document.querySelector("#text-area-descripcion-larga")
let normas = document.querySelector("#text-area-normas")
let politicasSalud = document.querySelector("#text-area-salud")
let politicasCancelacion = document.querySelector("#text-area-cancelacion")
let inputImagen1 = document.querySelector("#img-1")
let inputImagen2 = document.querySelector("#img-2")
let inputImagen3 = document.querySelector("#img-3")
let inputImagen4 = document.querySelector("#img-4")
let inputImagen5 = document.querySelector("#img-5")
let inputPrecio =document.querySelector("#inputPrecio")
let inputPuntuacion =document.querySelector("#inputPuntaje")


let correctos= []
let data = {
   nombre : "",
   descripcionCorta: "",
   descripcionLarga: "",
   ubicacion: "",
   ubicacion2: "",
   puntuacion:0,
   precio:0,
   direccion:"",
   puntuacion: "",
   normas: "",
   saludSeguridad: "",
   cancelacion: "",
   categoria: {
    id: null,
    },
   ciudad:{
    id: null,
    },
   imagenes: [
    {
    titulo:"",
    url:""
    },
    {
    titulo:"",
    url:""
    },
    {
    titulo:"",
    url:""
    },
    {
    titulo:"",
    url:""
    },
    {
    titulo:"",
    url:""
    }
    ],
    caracteristicas:[
]
}

formCreacionProducto.addEventListener('submit',function (event) {

event.preventDefault()

let arrayCaracteristicas = []
let checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
 for (var i = 0; i < checkboxes.length; i++){
 arrayCaracteristicas.push(checkboxes[i].value)
 }


let data = {
   nombre : inputNombreProducto.value,
   descripcionCorta: descripcionCortaForm.value,
   descripcionLarga: descripcionLargaForm.value,
   ubicacion: inputUbicacionGoogleMaps.value,
   ubicacion2: inputUbicacionGoogleMaps2.value,
   direccion:inputUbicacion.value,
   precio:inputPrecio.value,
   puntuacion:inputPuntuacion.value,
   normas: normas.value,
   saludSeguridad: politicasSalud.value,
   cancelacion: politicasCancelacion.value,
   categoria: {
    id: categoria.value
    },
   ciudad:{
    id: ciudad.value
    },
   imagenes:[
    {
    titulo: inputNombreProducto.value +1,
    url: inputImagen1.value
    },
    {
    titulo: inputNombreProducto.value +2,
    url: inputImagen2.value
    },
    {
    titulo: inputNombreProducto.value +2,
    url: inputImagen3.value
    },
    {
    titulo: inputNombreProducto.value +4,
    url: inputImagen4.value
    },
    {
    titulo:inputNombreProducto.value +5,
    url: inputImagen5.value
    }
    ],
   caracteristicas:[]
};

for( var i =0 ; i < arrayCaracteristicas.length; i++){
    let arrayAdentro = data.caracteristicas
    arrayAdentro.push({
id: arrayCaracteristicas[i]
})
}

console.log(data)

let settings = {
       method : "POST",
       headers: {
           'Content-Type': 'application/json'
       },
       body : JSON.stringify(data)
   };

fetch(BASE_URL +`productos/registro`, settings)
.then((respuesta) => {
   respuesta.json()
   if(respuesta.status === 200) {
     modal.style.display = "block";
     }else{
     document.querySelector("#titulo_modal").innerHTML = "Ups! algo salió mal";
                           document.querySelector("#mensaje_modal").innerHTML = "Lamentablemente no se ha podido crear la propiedad. Por favor, intente más tarde";
                           $("#imagen_modal").attr("src", "/img/fail.png");
                           modal.style.display = "block";
     }
     })

.catch((error) => {
   console.log(error);
   document.querySelector("#mensaje-error").classList.toggle("error")
   document.querySelector("#mensaje-error").innerHTML = `<p>Error. Por favor intente más tarde</p>`
})
});

// Configuración plugin para mensajes de información en Políticas
$(document).ready(function() {
new jBox('Tooltip', {
  attach: '.tooltip',
  width: 400,
});});

// Longitud máxima en text-areas

$(document).ready(function() {
var text_max = 100;
$('textarea_feedback').html(text_max +'  '+'  caracteres disponibles.');

$('#text-area-descripcion-corta').keyup(function() {
    var text_length = $('#text-area-descripcion-corta').val().length;
    var text_remaining = text_max - text_length;

    $('#textarea_feedback').html(text_remaining + ' caracteres disponibles.');
});

});


$(document).ready(function() {
var text_max = 250;
$('textarea_feedback2').html(text_max +'  '+'  caracteres disponibles.');

$('#text-area-descripcion-larga').keyup(function() {
    var text_length = $('#text-area-descripcion-larga').val().length;
    var text_remaining = text_max - text_length;

    $('#textarea_feedback2').html(text_remaining + ' caracteres disponibles.');
});

});

// Validación campos formulario

$(document).ready(function() {
  $("#profile_form").validate({
          rules: {
              inputUbicacion: "required",
              },
          messages: {
              inputUbicacion: "(requerido)",
                        }, highlight: function(element) {
              $(element).addClass('error');
          }, unhighlight: function(element) {
              $(element).removeClass('error');
          }
      });
});



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


    fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => {resp.json()
    if (resp.status !=200) {
        document.querySelector("main").innerHTML=`<h2 class="prohibido">No puedes realizar esta accion </h2>`
    }}
    )
    .then((usuario) => {
        if(usuario.rol.id != 1){       
            document.querySelector("main").innerHTML=`<h2 class="prohibido">No puedes realizar esta accion </h2>`
            
        }
    })