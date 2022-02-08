/*============== HEADER ====================== */
let header = document.querySelector(".header")
let cajaIniciales= document.querySelector(".iniciales")


import {BASE_URL} from '/modulosJs/constantes.js'


/*============== CONDICIONALES PARA MODIFICAR HEADER ====================== */
if (localStorage.getItem("sesion") == "true" ) {
    header.innerHTML = `
<div class="container">
    <div class="logo">
        <a href="/index.html"><img  class="img-logo" src="/img/logo_2.png" alt="logo"></a>
    </div>
    <div class="menu_bar">
        <a href="#" class="bt-menu">
            <span class="icon-list2"><i class="fas fa-bars"></i></span>
        </a>
    </div>
        <nav class="menu" id="menu">
        <div class="header-responsive">
        <div class="textAdmin"></div>
            <button id="cerrar" class="cerrar">x</button>
        <div class="header-menu-responsive">
        <p>
        MENU
        </p>
        </div>
        <section class="seCerrarLetra">
            <span  class="cerrarLetra">X</span>
        </section>
        <div class="cajaGneralUsuario" id="menuUsu">

        
        <section class="seccionInfoUsuario">
        <div class="iniciales">
        <p> ${localStorage.getItem("userNombre").slice(0,1).toLocaleUpperCase() + localStorage.getItem("userApellido").slice(0,1).toLocaleUpperCase()}</p>
        </div>
        <div class="saludoUser">
            Hola, 
            <span> ${ localStorage.getItem("userNombre") +" "+localStorage.getItem("userApellido") }</span>
        </div>
        
    </section>
    <section>
        <span class="spanCerrarSesion">¿Deseas <span class="cerrar movil"   href="LogIn/LogIn.html">cerrar sesión</span>?</span>
        </section>

        

        </div>
        <ul class="opciones" id="despegable">     
        <a href="/MisReservas/misReservas.html"> <li id="desple1" > <i class="fas fa-hotel"></i> Mis Reservas</li></a> 
         <a href="/Favoritos/favoritos.html"> <li><i class="fas fa-heart"></i> Mis Favoritos</li></a> 
         </ul>
       
    </div>
            <div class="red-social-header">
            <a href="https://www.facebook.com/"<i class="fab fa-facebook"></i></a>
            <a href="https://www.linkedin.com/"<i class="fab fa-linkedin-in"></i></a>
            <a href="https://twitter.com/"<i class="fab fa-twitter"></i></a>
            <a href="https://www.instagram.com/"<i class="fab fa-instagram"></i></a>
        </div>
        </nav>
</div>
`



let desple =document.querySelector("#menuUsu")
let desplegable =document.querySelector("#despegable")
let menu =document.querySelector(".textAdmin")
fetch(BASE_URL + `usuarios/${localStorage.getItem("idUsuario")}`)
    .then((resp) => resp.json())
    .then((data) => {
        
        if(data.rol.id === 1){
            desplegable.innerHTML=`<a href="/CreacionProducto/creacionProducto.html"> <li><i class="fas fa-clipboard-list"></i> Agregar Producto </li></a> `

        desplegable.innerHTML+=`<a href="/listarProductos(Admin)/listarProductos.html"> <li> <i class="fas fa-edit"></i> Modificar Productos </li></a> `
        menu.innerHTML+=` <p> Administrador</p> `
        document.querySelector(".seCerrarLetra").style.marginTop = "-25px"
        }
    
    })
    .catch(err => console.log("Error:", err));

desple.addEventListener("click",function () {
	desplegable.classList.toggle('despegable')
})
desplegable.addEventListener('mouseleave',function () {
	desplegable.classList.remove('despegable')
})

}

else if (document.querySelector("h2") != null && document.querySelector("h2").innerText === "Iniciar sesión") {
    console.log(document.querySelector("h2"))
    header.innerHTML = `
    <div class="container">
    <div class="logo">
        <a href="/index.html"><img  class="img-logo" src="/img/logo_2.png" alt="logo"></a>
    </div>
    <div class="menu_bar">
        <a href="#" class="bt-menu">
            <span class="icon-list2"><i class="fas fa-bars"></i></span>
        </a>
    </div>
        <nav class="menu" id="menu">
        <div class="header-responsive">
            <button id="cerrar" class="cerrar">x</button>
        <div class="header-menu-responsive">
        <p>
        MENU
        </p>
        </div>
            <ul>
                
                <a class="btn-header" href="/Registro/Registro.html"><li>Crear cuenta</li></a>
            </ul>
        </div>
        
            
            <div class="red-social-header">
                <a href="https://www.facebook.com/"<i class="fab fa-facebook"></i></a>
                <a href="https://www.linkedin.com/"<i class="fab fa-linkedin-in"></i></a>
                <a href="https://twitter.com/"<i class="fab fa-twitter"></i></a>
                <a href="https://www.instagram.com/"<i class="fab fa-instagram"></i></a>
            </div>
        </nav>
</div>
`
}
else if (document.querySelector("h2") != null && document.querySelector("h2").innerText === "Crear Cuenta") {
    console.log(document.querySelector(".tituloRegistro").value);
    console.log(document.querySelector("h2"))
    header.innerHTML = `
    <div class="container">
    <div class="logo">
        <a href="/index.html"><img  class="img-logo" src="/img/logo_2.png" alt="logo"></a>
    </div>
    <div class="menu_bar">
        <a href="#" class="bt-menu">
            <span class="icon-list2"><i class="fas fa-bars"></i></span>
        </a>
    </div>
        <nav class="menu" id="menu">
        <div class="header-responsive">
            <button id="cerrar" class="cerrar">x</button>
        <div class="header-menu-responsive">
        <p>
        MENU
        </p>
        </div>
            <ul>
                
            <a class="btn-header" href="/LogIn/LogIn.html"><li>Iniciar sesión</li></a>
            </ul>
        </div>
            <div class="red-social-header">
            <a href="https://www.facebook.com/"<i class="fab fa-facebook"></i></a>
            <a href="https://www.linkedin.com/"<i class="fab fa-linkedin-in"></i></a>
            <a href="https://twitter.com/"<i class="fab fa-twitter"></i></a>
            <a href="https://www.instagram.com/"<i class="fab fa-instagram"></i></a>
        </div>
        </nav>
</div>
`
}
else{
    header.innerHTML= `
<div class="container">
    <div class="logo">
        <a href="/index.html"><img  class="img-logo" src="/img/logo_2.png" alt="logo"></a>
    </div>
    <div class="menu_bar">
        <a href="#" class="bt-menu">
            <span class="icon-list2"><i class="fas fa-bars"></i></span>
        </a>
    </div>
        <nav class="menu" id="menu">
        <nav class="menu" id="menu">
        <div class="header-responsive">
            <button id="cerrar" class="cerrar">x</button>
        <div class="header-menu-responsive">
        <p>
        MENU
        </p>
        </div>
            <ul>
                <a class="btn-header" href="/Registro/Registro.html"><li>Crear cuenta</li></a>
                <a class="btn-header" href="/LogIn/LogIn.html"><li>Iniciar sesión</li></a>
            </ul>
            </div>
                <div class="red-social-header">
                <a href="https://www.facebook.com/"<i class="fab fa-facebook"></i></a>
                <a href="https://www.linkedin.com/"<i class="fab fa-linkedin-in"></i></a>
                <a href="https://twitter.com/"<i class="fab fa-twitter"></i></a>
                <a href="https://www.instagram.com/"<i class="fab fa-instagram"></i></a>
            </div>
        </nav>
</div>
`
}



/*============== FUNCION PARA CERRAR SESION CON EL HEADER ====================== */

if (document.querySelector(".cerrarLetra")!=null) {
    document.querySelector(".cerrarLetra").addEventListener('click',function(){
    Swal.fire({
        title : "¿Deseas cerrar sesión?",
        showCancelButton: true,
        confirmButtonText: 'Cerrar sesión',
        confirmButtonColor : '#F0572D',
        cancelButtonText: 'Cancelar',
        cancelButtonColor : '#a3a0a0'
    })
    .then((result) => {
        if(result.isConfirmed) {
            localStorage.clear()
            window.location.href = '/index.html'
        }
    })
})
}

if (document.querySelector(".movil")!=null) {
    document.querySelector(".movil").addEventListener('click',function(){
    Swal.fire({
        title : "¿Deseas cerrar sesión?",
        showCancelButton: true,
        confirmButtonText: 'Cerrar sesión',
        confirmButtonColor : '#F0572D',
        cancelButtonText: 'Cancelar',
        cancelButtonColor : '#a3a0a0'
    })
    .then((result) => {
        if(result.isConfirmed) {
            localStorage.clear()
            window.location.href = '/index.html'
        }
    })
})
}





/*============== JQUERY PARA EL DESPLEGABLE LATERAL EN MOVIL ====================== */
$(document).ready(maine);

let contador = 1;

function maine(){
	$('.menu_bar').click(function(){
		// $('nav').toggle(); 
		if(contador == 1){
			$('nav').animate({
				right: '0'
			});
			contador = 0;
		} else {
			contador = 1;
			$('nav').animate({
				right: '-100%'
			});
		}
	});

};

$(document).ready(maindos);

let contadore = 1;
function maindos(){
	$('#cerrar').click(function(){
		// $('nav').toggle(); 
		if(contadore == 1){
			$('nav').animate({
				right: '-100%'
			});
			contadore = 1;
		} else {
			contadore = 1;
			$('nav').animate({
				right: '0',
			});
		}
	});
};




/*============== FOOTER ====================== */

let footer = document.querySelector(".footer");
footer.innerHTML = `
<div class="pie-de-pagina">
<div class="box">
    <figure>
            <p>© 2021 Digital Booking</p>
    </figure>
</div>
<div class="box">
    <div class="red-social">
        <a href="https://www.facebook.com/"<i class="fab fa-facebook"></i></a>
        <a href="https://www.linkedin.com/"<i class="fab fa-linkedin-in"></i></a>
        <a href="https://twitter.com/"<i class="fab fa-twitter"></i></a>
        <a href="https://www.instagram.com/"<i class="fab fa-instagram"></i></a>
    </div>
</div>
</div>
`









