
import {BASE_URL} from '/modulosJs/constantes.js'
//import {idproducto} from '/listarProductos(Admin)/listarProductos.js'

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
 let slecCiudad = document.querySelector("#select-ciudad")
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




let url =window.location.hash.slice(1,50)
console.log(url);
fetch(BASE_URL + `productos/${url}`)
.then(res => res.json())
.then(function (data){
    console.log(data);
    localStorage.setItem("puntuacion",`${data.puntuacion}`)
    document.querySelector("#inputNombreProducto").value = data.nombre

    $(`#categorias > option[value='${data.categoria.id}']`).attr("selected",true);

    document.querySelector("#inputUbicacion").value = data.direccion

    $(`#select-ciudad > option[value='${data.ciudad.id}']`).attr("selected",true);

    document.querySelector("#inputUbicacionGoogleMaps").value = data.ubicacion

    document.querySelector("#inputUbicacionGoogleMaps2").value = data.ubicacion2
    
    document.querySelector("#inputPrecio").value = data.precio

    document.querySelector("#inputPuntaje").value = data.puntuacion

    document.querySelector("#text-area-descripcion-corta").value = data.descripcionCorta

    document.querySelector("#text-area-descripcion-larga").value = data.descripcionLarga

    
    data.caracteristicas.forEach(caract => {
        document.querySelectorAll(".nombre-caracteristica").forEach(check=>{
            if (caract.nombre == check.innerText) {
                check.parentElement.childNodes[1].setAttribute('checked', 'checked')
            }
        })
    });
    
    document.querySelector("#text-area-normas").value = data.normas

    document.querySelector("#text-area-salud").value = data.saludSeguridad
    
    document.querySelector("#text-area-cancelacion").value = data.cancelacion

    document.querySelector("#img-1").value = data.imagenes[0].url
    document.querySelector("#img-2").value = data.imagenes[1].url
    document.querySelector("#img-3").value = data.imagenes[2].url
    document.querySelector("#img-4").value = data.imagenes[3].url
    document.querySelector("#img-5").value = data.imagenes[4].url
})


/*=============CAPTURANDO ELEMENTOS============= */

let formCreacionProducto = document.querySelector(".formCreacionProducto")
let botonCrearProducto = document.querySelector(".btn-crearProducto")
let inputNombreProducto = document.querySelector("#inputNombreProducto")
let categoria = document.querySelector("#categorias")
let inputUbicacion= document.querySelector("#inputUbicacion")
let ciudad = document.querySelector("#select-ciudad")
let inputUbicacionGoogleMaps = document.querySelector("#inputUbicacionGoogleMaps")
let inputUbicacionGoogleMaps2 = document.querySelector("#inputUbicacionGoogleMaps2")
let inputPrecio =document.querySelector("#inputPrecio")
let inputPuntuacion =document.querySelector("#inputPuntaje")
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
let arrayCaracteristicas = []
let checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
 for (var i = 0; i < checkboxes.length; i++){
 array.push(checkboxes[i].value)
 }

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
   categoria: {
    id: null,
    },
   ciudad:{
    id: null,
    },
   imagenes: [
    {
    titulo:"",
    url:""},
    {
    titulo:"",
    url:""},
    {
    titulo:"",
    url:""},
    {
    titulo:"",
    url:""},
    {
    titulo:"",
    url:""},
    ],
    caracteristicas:[
    {id: "",
    },
    {id: "",
    },
    {id: "",
    },
    {id: "",
}]
}

formCreacionProducto.addEventListener('submit',function (event) {

    event.preventDefault()

    let arrayCaracteristicas = []
    let checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
     for (var i = 0; i < checkboxes.length; i++){
     arrayCaracteristicas.push(checkboxes[i].value)
     }
    
    let data = {
        id:window.location.hash.slice(1,50),
       nombre : inputNombreProducto.value,
       descripcionCorta: descripcionCortaForm.value,
       descripcionLarga: descripcionLargaForm.value,
       ubicacion: inputUbicacionGoogleMaps.value,
       ubicacion2: inputUbicacionGoogleMaps2.value,
       direccion:inputUbicacion.value,
       precio:inputPrecio.value,
       puntuacion:inputPuntuacion.value,
       //puntuacion: Math.floor(Math.random()*(10 - 4)+4),
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
       caracteristicas:[
        {
            id: arrayCaracteristicas[0],
        },
        {
            id: arrayCaracteristicas[1],
        },
        {
            id: arrayCaracteristicas[2],
        },
        {
            id: arrayCaracteristicas[3],
        }]
    };

console.log(data);
let token =localStorage.getItem("token")
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

fetch(BASE_URL +`productos/modificar`, settings("PUT", data))
.then((respuesta) => {
   respuesta.json()
   if(respuesta.status === 200) {
    console.log("enviado")
    modal.style.display = "block"
     }else{
     document.querySelector("#titulo_modal").innerHTML = "Ups! algo salió mal";
                           document.querySelector("#mensaje_modal").innerHTML = "Lamentablemente la propiedad no ha podido modificarse. Por favor, intente más tarde";
                           $("#imagen_modal").attr("src", "/img/fail.png");
                           modal.style.display = "block";
     }

     })

.catch((error) => {
   console.log(error);
//    document.querySelector("#mensaje-error").classList.toggle("error")
//    document.querySelector("#mensaje-error").innerHTML = `<p>Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde</p>`
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