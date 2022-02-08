import {BASE_URL} from '/modulosJs/constantes.js'

/*==============TRAE OBJETOS DEL HTML====================== */
let inputPass = document.querySelector(".inputPass")
let divInputPass = document.querySelector(".divInputPass")
let iconEye  = document.querySelector("#ojoPass")



/*==============fUNCION PARA MOSTRAR CONTRASEÑA====================== */
iconEye.addEventListener("click", function(){
if (inputPass.type =="password") {
    inputPass.type ="text";
} else {
    inputPass.type ="password";
}

iconEye.classList.toggle("fa-eye")
iconEye.classList.toggle("fa-eye-slash")
})

/*=============CAPTURANDO ELEMENTOS============= */

let inputCorreo = document.querySelector("#inputCorreoLog")
let inputPassword = document.querySelector("#inputPassLog")
let formLogeo = document.querySelector(".formLogeo")


    let settings = (method, body) => {
        return ({
            method: method,
            headers:{
                'Content-Type':'application/json'
             },
            body: JSON.stringify(body)
        })
    }

   


/*=============EVENTO SUBMIT FORM LOGEO============= */

formLogeo.addEventListener('submit',function (event) {

    event.preventDefault() 
    let dataUsu={
        "username":inputCorreo.value,
        "password":inputPassword.value
    }

    fetch(BASE_URL + "login", settings("POST", dataUsu))
    .then(res => res.json())
    .then((data) => {
            localStorage.setItem("token",`${data}`)

            fetch(BASE_URL + `usuarios/findUser/${dataUsu.username}`)
            .then(res => res.json())
            .then(function (data){
                
                  localStorage.setItem("userNombre", data.nombre.toLocaleUpperCase())
                  localStorage.setItem("userApellido", data.apellido.toLocaleUpperCase())
                  localStorage.setItem("userCorreo", data.email)
                  localStorage.setItem("sesion", "true")
                  localStorage.setItem("idUsuario", data.id)
                  
                       
           if (window.location.hash =="" || window.location.hash  === null) {
                 window.location.pathname='/index.html'
                 localStorage.setItem("sesion", "true")
             }
           else  if (window.location.hash != '' && window.location.hash != null) {
                let id =localStorage.getItem('pathReserva')
                window.location.pathname='/Reserva/reservaProducto.html'
                
        }
                })
           
         
        })
        
    .catch(err => {
        document.querySelector("#mensaje-error").classList.add("error")
        document.querySelector("#mensaje-error").innerHTML = `<p>Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde</p>`
    });



})