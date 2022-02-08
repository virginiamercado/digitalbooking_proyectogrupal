import {BASE_URL} from '/modulosJs/constantes.js'

export const caracteristicas = function() {
    let seccionCaracteris = document.querySelectorAll(".secCaracteristicas");

seccionCaracteris.forEach(caract =>{
   fetch(BASE_URL + `productos/${caract.dataset.productoid}`)
.then(res => res.json())
.then(function (data){
            data.caracteristicas.forEach(crt =>{
                caract.innerHTML +=`
                <i class="${crt.icono}"></i>
                `
            })
})
})
}