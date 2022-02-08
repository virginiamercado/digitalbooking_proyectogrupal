export const card = function(producto, seccion) {
    
    seccion.innerHTML +=`
        <li>
         <div class="cardProducto" data-productoId="${producto.id}">
                        <div class="cajaImgProducto">
                            <img src="${producto.imagenes[1].url}" alt="" class="imgProducto">
                            <i class="far fa-heart"></i>
                        </div>
                        <div class="secInformacion">
                        <section>
                            <section class="headerCardProducto">
                                <div class="contTitulo">
                                    <span class="seccionEstrellas" data-puntaje="${producto.puntuacion}">
                                        ${producto.categoria.titulo}
                                    </span>
                                    <h3 class="tituloCardProducto">${producto.nombre}</h3>
                                 </div>
                                <div class="contPuntaje">
                                    <p class="numeroPuntaje"> ${producto.puntuacion}</p>
                                    <div class="textPuntaje"></div>
                                </div>

                            </section>
                            <span class="infoUbicacion">
                                <i class="fas fa-map-marker-alt"></i> A 930m del centro
                                <a href="${producto.ubicacion}" class="linkAMapa" target="_blank">  MOSTRAR EN EL MAPA</a>
                            </span>
                            <div class="secCaracteristicas" data-productoId="${producto.id}">
                        </div>
                        <span class="precio">Por noche $${producto.precio}</span>
                        </section>
                        <section class="secDescripcion">
                        
                        <div class="contDescripcionProducto">
                        <p class="descripcionProducto" data-productoId="${producto.id}">
                         ${producto.descripcionCorta}
                        </p>
                            </div>
                            <a href="">
                            <button class="btnVerMasProducto" data-productoId="${producto.id}"> Ver Más</button>
                            </a>
                        </section>
                        </div>
                        </div>
                        </li>

        `
}