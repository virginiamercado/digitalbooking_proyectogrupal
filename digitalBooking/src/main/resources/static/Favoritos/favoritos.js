import {caracteristicas} from '/modulosJs/caracteristicas.js';
import {card} from '/modulosJs/card.js';
import {cortarDescrip} from '/modulosJs/cortarDescrip.js';
import {estrellas} from '/modulosJs/estrellas.js';
import {verMas} from '/modulosJs/verMas.js';
import {BASE_URL} from '/modulosJs/constantes.js'







window.addEventListener("load", () => {

    let idUsuario = localStorage.getItem("idUsuario")

    fetch(BASE_URL + `usuarios/favoritos/${idUsuario}`)
    .then((resp) => resp.json())
    .then((data) => {
        fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => resp.json())
    .then((usuario) => {

        if(usuario.rol.id != 1){

             if (data.length == 0) {
            document.querySelector("#misFavoritos").innerHTML=`
            <div class="noFavoritos">
            <h2>Parece que no tienes favoritos, buscamos algunos? </h2>
            <a href="/index.html"> <i class="fas fa-chevron-left"></i> Volver al Home</a>
            </div>
            `
        }
    data.forEach(producto =>{
        card(producto,document.querySelector("#misFavoritos"));   

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
             location.reload()
            })
            })
        
       
            
            
            


})



    })

// FUNCION CORTAR DESCRIPCION

    cortarDescrip();

// FUNCION PONER ESTRELLAS

    estrellas();

// FUNCION PONER cARACTERISTICAS

    caracteristicas();
 
// FUNCION PARA MOSTRAR MAS

    verMas();


   
    }
    
else{
    document.querySelector("#misFavoritos").innerHTML+=`
            <div class="noFavoritos">
                <h2>No puedes realizar esta acción </h2>
                </div>
            `
}
    })
})
    .catch((error) => console.log(error));



    
    

})