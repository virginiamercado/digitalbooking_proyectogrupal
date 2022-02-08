export const estrellas = function() {
    let seccionEstrellas = document.querySelectorAll(".seccionEstrellas");

    seccionEstrellas.forEach(secEstrella =>{
        let cajaTextPuntaje = secEstrella.parentNode.parentNode.childNodes[3].childNodes[3]
       if (secEstrella.dataset.puntaje <= 10 && secEstrella.dataset.puntaje > 8) {
        secEstrella.innerHTML +=`
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">`
        cajaTextPuntaje.innerHTML =`Excelente `

    }
   else if (secEstrella.dataset.puntaje <= 8 && secEstrella.dataset.puntaje > 6) {
    secEstrella.innerHTML +=`
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">`
        cajaTextPuntaje.innerText =`Muy Bueno `

    }
    else if (secEstrella.dataset.puntaje <= 6 && secEstrella.dataset.puntaje > 5) {
        secEstrella.innerHTML+=`
        <i class="fas fa-star">
        <i class="fas fa-star">
        <i class="fas fa-star">`
        cajaTextPuntaje.innerText =`Bueno `

    }
    else  if (secEstrella.dataset.puntaje <= 5 && secEstrella.dataset.puntaje > 3) {
        secEstrella.innerHTML+=`
        <i class="fas fa-star">
        <i class="fas fa-star">`
        cajaTextPuntaje.innerText =`Regular `
    }
    else{
        secEstrella.innerHTML+=`
        <i class="fas fa-star">`
        cajaTextPuntaje.innerText=`Malo `
    }
    })
}