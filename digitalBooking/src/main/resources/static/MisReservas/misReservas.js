import {caracteristicas} from '/modulosJs/caracteristicas.js';
import {card} from '/modulosJs/card.js';
import {cortarDescrip} from '/modulosJs/cortarDescrip.js';
import {estrellas} from '/modulosJs/estrellas.js';
import {verMas} from '/modulosJs/verMas.js';
import {BASE_URL} from '/modulosJs/constantes.js'

window.addEventListener("load", () => {

    let idUsuario = localStorage.getItem("idUsuario")

    fetch(BASE_URL + `reservas/usuario/${idUsuario}`)
    .then((resp) => resp.json())
    .then((data) => {
        fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => resp.json())
    .then((usuario) => {
        
        if(usuario.rol.id != 1){
            if (data.length == 0) {
                document.querySelector("#misResevas").innerHTML=`
                <div class="noReservas">
                <h2>Parece que no tienes reservas, buscamos algunas? </h2>
                <a href="/index.html"> <i class="fas fa-chevron-left"></i> Volver al Home</a>
                </div>
                `
            }
            else{
        data.forEach(reserva =>{
            document.querySelector("#misResevas").innerHTML+=`
            <li>
    
            <div class="generalReserva">
            <div class="headerProducto">
            <section class="sectionInfo">
            <p>Informacion</p>
            <span id="fechaLlegada">Fecha de Llegada:<span> ${reserva.fechaLlegada.replace(" ", "").split('-').reverse().join('-')}</span></span>
            <span id="fechaPartida">Fecha de Salida:<span> ${reserva.fechaPartida.replace(" ", "").split('-').reverse().join('-')}</span></span>
            <span>Horario de Llegada: <span>${reserva.horaLlegada}</span></span>
        </section>
         <section class="secBtnCancelar">
            <button class="btnCancelarReserva" data-reservaId="${reserva.id}">Cancelar Reserva</button>
            <span class="precioCalculado">${calcularPrecio(reserva.fechaLlegada,reserva.fechaPartida) }</span>
        </section>
        </div>
    
            
            <div class="cardProducto" data-productoId="${reserva.id}">
                           <div class="cajaImgProducto">
                               <img src="${reserva.producto.imagenes[1].url}" alt="" class="imgProducto">
                               <i class="far fa-heart"></i>
                           </div>
                           <div class="secInformacion">
                           <section>
                               <section class="headerCardProducto">
                                   <div class="contTitulo">
                                       <span class="seccionEstrellas" data-puntaje="${reserva.producto.puntuacion}">
                                           ${reserva.producto.categoria.titulo}
                                       </span>
                                       <h3 class="tituloCardProducto">${reserva.producto.nombre}</h3>
                                    </div>
                                   <div class="contPuntaje">
                                       <p class="numeroPuntaje"> ${reserva.producto.puntuacion}</p>
                                       <div class="textPuntaje"></div>
                                   </div>
    
                               </section>
                               <span class="infoUbicacion">
                                   <i class="fas fa-map-marker-alt"></i> A 930m del centro
                                   <a href="${reserva.producto.ubicacion}" class="linkAMapa" target="_blank">  MOSTRAR EN EL MAPA</a>
                               </span>
                               <div class="secCaracteristicas" data-productoId="${reserva.producto.id}">
                           </div>
                           <span class="precio">Por noche $${reserva.producto.precio}</span>
                           </section>
                           <section>
                           <div class="contDescripcionProducto">
                           <p class="descripcionProducto" data-productoId="${reserva.producto.id}">
                            ${reserva.producto.descripcionCorta}
                           </p>
                               </div>
                               <a href="">
                               <button class="btnVerMasProducto" data-productoId="${reserva.producto.id}"> Ver Más</button>
                               </a>
                           </section>
                           </div>
                           </div>
                           </li>
    
            </div>
            `
             
          function calcularPrecio(fechaLlegada,fechaPartida){

                      
            let dateArrayDos = new Array();

            Date.prototype.addDays = function(days) {
                let dat = new Date(this.valueOf())
                dat.setDate(dat.getDate() + days);
                return dat;
            }

            let startDateDos =new Date(reserva.fechaLlegada)
            let stopDateDos = new Date(reserva.fechaPartida)
        
            let currentDateDos = startDateDos;
            while (currentDateDos <= stopDateDos) {
                dateArrayDos.push(`${currentDateDos.getDate()+2 <=10 ? "0" +(currentDateDos.getDate()+1) : (currentDateDos.getDate()+1)}`
              +"-" +
              `${currentDateDos.getMonth() <= 9 ? "0" +(currentDateDos.getMonth()+1) : (currentDateDos.getMonth()+1)}`
              + "-" +
              `${currentDateDos.getFullYear()}`)
              currentDateDos = currentDateDos.addDays(1);
            }
            if (dateArrayDos[0]==null) {
                return ``
            }
            else if(dateArrayDos.length <= 2){
                return `<span class="precioNumero"> 
                $${((dateArrayDos.length-1 )* reserva.producto.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-1} noches`
        }else if(dateArrayDos.length <= 3){
            return `<span class="precioNumero"> 
            $${((dateArrayDos.length-1 )* reserva.producto.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-1} noches`
        }
             
            else{
                return `<span class="precioNumero"> $${((dateArrayDos.length-2 )* reserva.producto.precio).toLocaleString('en-IN', { minimumFractionDigits: 2})} </span> por ${dateArrayDos.length-2} noches`}
        
        
        } 
       
        })
    
    // FUNCION CORTAR DESCRIPCION
    
        cortarDescrip();
    
    // FUNCION PONER ESTRELLAS
    
        estrellas();
    
    // FUNCION PONER cARACTERISTICAS
    
        caracteristicas();
     
    // FUNCION PARA MOSTRAR MAS
    
        verMas();
    
        document.querySelectorAll(".btnCancelarReserva").forEach(btnCancelar=>{
            btnCancelar.addEventListener('click',function () {
    
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
                    title : `Estás cancelando una reserva.
                    ¿Estás segur@ que quieres seguir?`,
                    showCancelButton: true,
                    confirmButtonText: 'Cancelar Reserva',
                    confirmButtonColor : '#F0572D',
                    cancelButtonText: 'Cancelar',
                    cancelButtonColor : '#a3a0a0'
                })
                .then((result) => {
                    if(result.isConfirmed) {
                        fetch(BASE_URL + `reservas/borrar/${btnCancelar.dataset.reservaid}`,settings("DELETE"))
                        .then((resp) => {resp.json()
                            if(resp.status != 204) {
                                Swal.fire({
                                    title : `Algo salio mal, vuelve a intentarlo más tarde`,
                                    confirmButtonText: 'Cerrar',
                                    confirmButtonColor : '#F0572D',
                                })
                             }
                        })
                        .then((data) => {
                            location.reload()
                        })
                    }
                })
                
            })
        })
        }  
    
        }
        else{
            document.querySelector("#misResevas").innerHTML+=`
            <div class="noReservas">
                <h2>No puedes realizar esta acción </h2>
                </div>
            `
        }
    
    })
    .catch(err => console.log("Error:", err));
     })  
    .catch((error) => console.log(error));


})
