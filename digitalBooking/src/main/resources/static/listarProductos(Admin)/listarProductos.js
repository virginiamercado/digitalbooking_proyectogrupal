
import {BASE_URL} from '/modulosJs/constantes.js'

fetch(BASE_URL + "productos")
.then(res => res.json())
.then(function (data){
let main =document.querySelector(".content")

data.forEach(producto => {
     main.innerHTML+=`
    <div class="numeroPro"># ${producto.id} </div>
    <div class="nombrePro">${producto.nombre}</div>
    <div class="accionPro"> <button class="btnModificar" data-productoId="${producto.id}"> <i class="fas fa-edit"></i> </button> <button class="btnCancelar" data-productoId="${producto.id}"><i class="fas fa-trash"></i></button></div>

    `
});
       
document.querySelectorAll(".btnCancelar").forEach(btnEliminar=>{

    btnEliminar.addEventListener('click',function () {

        let token =localStorage.getItem("token")
        let settings = (method) => {
            return ({
                method: method,
                headers:{
                    'Content-Type':'application/json',
                    'Authorization':`${token}`
                 }
            })
        }
        Swal.fire({
            title : "¿Deseas eliminar esta propiedad?",
            showCancelButton: true,
            confirmButtonText: 'Eliminar',
            confirmButtonColor : '#F0572D',
            cancelButtonText: 'Cancelar',
            cancelButtonColor : '#a3a0a0'
        })
        .then((result) => {
            if(result.isConfirmed) {
         fetch(BASE_URL + `productos/borrar/${btnEliminar.dataset.productoid}`,settings("DELETE"))
        .then(res => res.json())
        .then(function (data){})
        location.reload()
            }
        })
        
    })
})

document.querySelectorAll(".btnModificar").forEach(btnModificar=>{
    btnModificar.addEventListener('click',function () {
        let idproducto = btnModificar.dataset.productoid
        window.location.href=`/modificarProducto/modificarProducto.html#${idproducto}`
         
        //export {idproducto}
    })
})


})


fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => {resp.json()
        if (resp.status != 200) {
            document.querySelector("main").innerHTML=`<h2 class="prohibido">No puedes realizar esta acción. </h2>`
        }})
    .then((usuario) => {
        if(usuario.rol.id != 1){        
            document.querySelector("main").innerHTML=`<h2 class="prohibido">No puedes realizar esta acción. </h2>`
            
        }
    })
