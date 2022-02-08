
import {BASE_URL} from '/modulosJs/constantes.js'


export const listarFavoritos = function(){

    let idUsuario = localStorage.getItem("idUsuario")

    fetch(BASE_URL + `usuarios/favoritos/${idUsuario}`)
    .then((resp) => resp.json())
    .then((data) => {
    data.forEach(producto =>{  
        document.querySelectorAll(".cardProducto").forEach(card=>{
            if (card.dataset.productoid == producto.id) {
                card.childNodes[1].childNodes[3].classList.add("fas")
                card.childNodes[1].childNodes[3].classList.remove("far")
            }


            let corazonesLike=document.querySelectorAll(".fa-heart.fas")
corazonesLike.forEach(corazonL=>{
    corazonL.addEventListener('click',function(){
     let idProducto = corazonL.parentElement.parentElement.dataset.productoid
     let idUsuario = localStorage.getItem("idUsuario")

     
     let settings =  {
             method : "PUT",
             headers: {
                 'Content-Type': 'application/json',
             },
     }
 
 fetch(BASE_URL + `usuarios/favoritos/quitar?idUsuario=${idUsuario}&idProducto=${idProducto}`, settings)
 .then((resp) => resp.json())
 .then((data) => {/*console.log(data)*/})
 .catch(err => console.log("Error:", err));
 
 corazonL.classList.toggle("fas")
 corazonL.classList.toggle("far")
})


})
    
})

    })
})







}