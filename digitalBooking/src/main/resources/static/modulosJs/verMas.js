import {BASE_URL} from '/modulosJs/constantes.js'


export const verMas = function() {
    let spanMas = document.querySelectorAll(".spanMas");

   


spanMas.forEach(span =>{
    span.addEventListener("click", function () {
        fetch(BASE_URL +`productos/${span.dataset.productoid}`)
        .then(res => res.json())
        .then(function (data){
            if (span.parentElement.childNodes[1].innerText.length <= 110 && span.parentElement.childNodes[1].innerText.length > 80 && span.parentElement.childNodes[3].innerText !== "menos...") {
               span.parentElement.childNodes[1].innerText =`
            ${data.descripcionCorta}
            `
            span.parentElement.childNodes[3].innerText =`menos...`
            }
            else if (span.parentElement.childNodes[1].innerText.length >= 95 && span.parentElement.childNodes[3].innerText != "más...") {
                span.parentElement.childNodes[1].innerText =`
             ${data.descripcionCorta.slice(0,80)}
             `
             span.parentElement.childNodes[3].innerText =`más...`
             }
             else if (span.parentElement.childNodes[1].innerText.length <= 80) {
                span.parentElement.childNodes[1].innerText =`
             ${data.descripcionCorta.slice(0,100)}
             `
             span.parentElement.childNodes[3].innerText =``
             }
        })



    })
})

let productos= document.querySelectorAll(".btnVerMasProducto")

productos.forEach(producto=>{
    producto.addEventListener("click",function () {
        let idproducto = producto.dataset.productoid
        producto.parentElement.setAttribute("href",`/DetalleProducto/detalleProducto.html#${idproducto}`)
    })
})

$(document).ready(function () {
    let size_li = $("#myList li").length;

    let x=5;

    $('#myList li:lt('+x+')').show();

    if ($('#myList li:lt('+x+')').length >= size_li) {
        $('#loadMore').hide()
    }

$('#myList li').not(':lt('+x+')').hide();

    $('#loadMore').click(function () {
        x= (x+5 <= size_li) ? x+5 : size_li;
        $('#myList li:lt('+x+')').show();
        if ($('#myList li:lt('+x+')').length >= size_li) {
            $('#loadMore').hide()
        }
    });
});


}