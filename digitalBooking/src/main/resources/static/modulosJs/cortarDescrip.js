export const cortarDescrip = function() {
    // FUNCION CORTAR DESCRIPCION

    let descripciones = document.querySelectorAll(".descripcionProducto");


        descripciones.forEach(descrp =>{
            if (descrp.innerHTML.length >= 110) {
                let descrpCorta = descrp.innerHTML.slice(0,110)
                descrp.innerHTML =`
                ${descrpCorta}

                `
                descrp.parentElement.innerHTML +=`
                <span class="spanMas" data-productoId="${descrp.dataset.productoid}" >más...</span>
                `
            }
        })
}