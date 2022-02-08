
import {BASE_URL} from '/modulosJs/constantes.js'
import {listarFavoritos} from '/modulosJs/listarFavoritos.js'

listarFavoritos()


export const sFavoritos = function(){

   let corazones=document.querySelectorAll(".far.fa-heart")

   corazones.forEach(corazon=>{
    corazon.addEventListener('click',function(){

        let idProducto = corazon.parentElement.parentElement.dataset.productoid
        let idUsuario = localStorage.getItem("idUsuario")
        
        let settings =  {
                method : "PUT",
                headers: {
                    'Content-Type': 'application/json',
                },
        }
    
    fetch(BASE_URL + `usuarios/favoritos/agregar?idUsuario=${idUsuario}&idProducto=${idProducto}`, settings)
    .then((resp) => resp.json())
    .then((data) => {/*console.log(data)*/})
    .catch(err => console.log("Error:", err));
    
                corazon.classList.toggle("fas")
                corazon.classList.toggle("far")
   })


})



}
