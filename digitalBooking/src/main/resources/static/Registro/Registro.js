
const BASE_URL="http://ec2-54-87-12-130.compute-1.amazonaws.com:8080/"
//const BASE_URL = "http://localhost:8080/"

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
});


console.log(localStorage.getItem('pathReserva'));
/*==============fUNCION PARA SELECCION DE PAISES====================== */
let slecPais = document.querySelector("#paises")
   fetch("https://restcountries.com/v2/all")
.then(res => res.json())
.then(function (data){
    data.forEach(pais => {
        slecPais.innerHTML +=`
        <option value="${pais.alpha3Code}" >${pais.name}</option> `
    });
}
)
.catch(err => console.log("Error:", err));

/*=============CAPTURANDO ELEMENTOS============= */
let formRegistro = document.querySelector(".formRegistro")
let botonCrearCuenta = document.querySelector(".botonCrearCuenta")
let inputNombre = document.querySelector("#nombre")
let inputApellido = document.querySelector("#apellido")
let inputCorreo = document.querySelector("#correo")
let inputNacimiento = document.querySelector("#fecha")
let paisNacimiento = document.querySelector("#paises")
let inputPassword = document.querySelector("#password")
let inputPasswordRep = document.querySelector("#passwordRep")

let botonCalendar = document.querySelector("btn-primary")

inputCorreo.addEventListener("focusout", () => {
    fetch(BASE_URL + `usuarios/emailUser/${inputCorreo.value}`)
   .then((resp) => resp.json())
   .then((data) => {
       if(data != null && data.error == null ) {
        inputCorreo.classList.add("errorInput")
        inputCorreo.placeholder=""
        inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
         inputCorreo.parentElement.childNodes[5].innerHTML =`
         <small>* Este email ya se encuentra registrado</small>
         `
        /*document.querySelector("#email").innerHTML += `
                                                        <div class="error">
                                                            <p>Este email ya se encuentra registrado</p>
                                                        </div>`*/
       }
       else {
                    
            /*=============VALIDACION FORMULARIO============= */
            formRegistro.addEventListener('submit',function (event) {

                event.preventDefault() 
            
            //if (correctos.length >= 6) {
            

                let datos = {
                    nombre : inputNombre.value,
                    username : inputCorreo.value,
                    apellido : inputApellido.value,
                    fechaNacimiento : inputNacimiento.value,
                    paisNacimiento : paisNacimiento.value,
                    email : inputCorreo.value,
                    password : inputPassword.value,
                    rol : {
                        id : 2
                    }
                }

                let config = {
                    method : "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body : JSON.stringify(datos)
                }
                let settings = (method, body) => {
                    return ({
                        method: method,
                        headers:{
                            'Content-Type':'application/json'
                        },
                        body: JSON.stringify(body)
                    })
                }

                let dataUsu={
                    "username":document.querySelector("#correo").value,
                    "password":document.querySelector("#password").value
                }


                    fetch(BASE_URL +`usuarios/registro`, config)
                    .then((respuesta) => {
                        respuesta.json()
                        if(respuesta.status === 201) {

                            console.log(dataUsu);
                            fetch(BASE_URL + "login", settings("POST", dataUsu))
                            .then(res => res.json())
                            .then((data) => {
                                localStorage.setItem("token",`${data}`)
                                
                                fetch(BASE_URL + `usuarios/findUser/${inputCorreo.value}`)
                                .then(res => res.json())
                                .then(function (data){
                                console.log(data);
                                
                                localStorage.setItem("userNombre", inputNombre.value.toLocaleUpperCase())
                                localStorage.setItem("userApellido", inputApellido.value.toLocaleUpperCase())
                                localStorage.setItem("userCorreo", inputCorreo.value)
                                localStorage.setItem("idUsuario", data.id)
                                
                                if (localStorage.getItem('pathReserva') == '' ||localStorage.getItem('pathReserva') == null) {
                                    window.location.pathname='/index.html'
                                    localStorage.setItem("sesion", "true")
                                }
                              else  if (localStorage.getItem('pathReserva') != '' ||localStorage.getItem('pathReserva') != null) {
                                   window.location.pathname='/Reserva/reservaProducto.html'  
                                   localStorage.setItem("sesion", "true")
                           }
                           else{
                            window.location.pathname='/index.html'
                            localStorage.setItem("sesion", "true")
                           }

                                })
                                
                            })

                        }
                        
                    })
                    .then((data) =>{
                    })
                    .catch((error) => {
                        console.log(error);
                        document.querySelector("#mensaje-error").classList.toggle("error")
                        document.querySelector("#mensaje-error").innerHTML = `<p>Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde</p>`
                        
                    });

            
            })
       }
   })
   .catch((error) => console.log(error));
})



let correctos= []
let info = {
    nombre : "",
    apellido : "",
    edad : "",
    pais : "",
    correo : "",
    password : "",
    sesion : true,
}

 /*=============VALIDACION INPUT NOMBRE============= */
function validarNombre() {
    correctos.slice(0,1)
    inputNombre.classList.remove("errorInput")
    inputNombre.parentElement.childNodes[5].innerHTML =``
    inputNombre.parentElement.childNodes[5].classList.remove("errorSpan")

    if (inputNombre.value.length === 0) {
        inputNombre.classList.add("errorInput")
        inputNombre.placeholder=""
        inputNombre.parentElement.childNodes[5].classList.add("errorSpan")
         inputNombre.parentElement.childNodes[5].innerHTML =`
         <small>*El Nombre No Puede Estar Vacio</small>
         `
    }
    else if(inputNombre.value.length <=2) {
        inputNombre.classList.add("errorInput")
        inputNombre.placeholder=""
        inputNombre.parentElement.childNodes[5].classList.add("errorSpan")
         inputNombre.parentElement.childNodes[5].innerHTML =`
         <small>*El Nombre es Muy Corto</small>
        `
    }
    else if(inputNombre.value.length >15) {
        inputNombre.classList.add("errorInput")
        inputNombre.placeholder=""
        inputNombre.parentElement.childNodes[5].classList.add("errorSpan")
         inputNombre.parentElement.childNodes[5].innerHTML =`
         <small>*El Nombre es Muy Largo</small>
        `
    }
    else{
        correctos.push("|")
        info.nombre = inputNombre.value
    }
}


/*=============VALIDACION INPUT APELLIDO============= */
function validarApellido() {
    correctos.slice(0,1)
    inputApellido.classList.remove("errorInput")
    inputApellido.parentElement.childNodes[5].innerHTML =``
    inputApellido.parentElement.childNodes[5].classList.remove("errorSpan")


    if (inputApellido.value.length === 0) {
        inputApellido.classList.add("errorInput")
        inputApellido.placeholder=""
        inputApellido.parentElement.childNodes[5].classList.add("errorSpan")
         inputApellido.parentElement.childNodes[5].innerHTML =`
         <small>*El Apellido No Puede Estar Vacio</small> 
         `
    }
    else if(inputApellido.value.length <=2) {
        inputApellido.classList.add("errorInput")
        inputApellido.placeholder=""
        inputApellido.parentElement.childNodes[5].classList.add("errorSpan")
         inputApellido.parentElement.childNodes[5].innerHTML =`
         <small>*El Apellido es Muy Corto</small>
        `
    }
    else if(inputApellido.value.length >15) {
        inputApellido.classList.add("errorInput")
        inputApellido.placeholder=""
        inputApellido.parentElement.childNodes[5].classList.add("errorSpan")
         inputApellido.parentElement.childNodes[5].innerHTML =`
         <small>*El Apellido es Muy Largo</small>
        `
    }

    else{
        correctos.push("|")
        info.apellido = inputApellido.value
    }

}

/*============= INPUT CALENDARIO============= */

$(function() {

    $('input[name="birthday"]').daterangepicker({
      singleDatePicker: true,
      showDropdowns: true,
      autoApply: true,
      locale: {
        format: 'DD/MM/YYYY',
        maxYear: parseInt(moment().format('YYYY'),10),
        applyLabel : "Buscar",
        cancelLabel : "Cancelar",
        daysOfWeek: ["Dom","Lun","Mar","Mie","Jue","Vie","Sáb",],
        monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
      },
  });
});
/*=============VALIDACION INPUT CALENDARIO============= */
function calcularEdad() {
    let inputFecha = document.querySelector("#fecha")

    correctos.slice(0,1)
    inputFecha.classList.remove("errorInput")
    inputFecha.parentElement.childNodes[5].innerHTML =``
    inputFecha.parentElement.childNodes[5].classList.remove("errorSpan")
    

    let hoy = new Date();
    let cumpleanos = new Date(inputFecha.value);
    let edad = parseInt(moment().format('YYYY'),10) - cumpleanos.getFullYear();
    let m = parseInt(moment().format('MM'),10) - cumpleanos.getMonth() + 1;
    let d = parseInt(moment().format('DD'),10) - cumpleanos.getDate() + 1;

    if ((parseInt(moment().format('YYYY'),10) - cumpleanos.getFullYear()) >= 18 && m >=0 && d >=0 ) {
        correctos.push("|")
        info.edad = edad
    }
else{
    inputFecha.classList.add("errorInput")
         inputFecha.placeholder=""
         inputFecha.parentElement.childNodes[5].classList.add("errorSpan")
          inputFecha.parentElement.childNodes[5].innerHTML =`
          <small>*Debes Ser Mayor de  18 Años</small>
         `
}
}

/*=============VALIDACION INPUT PAIS============= */
function validarPais() {
    correctos.slice(0,1)
    slecPais.classList.remove("errorInput")
    slecPais.parentElement.childNodes[5].innerHTML =``
    slecPais.parentElement.childNodes[5].classList.remove("errorSpan")

    if (slecPais.value == 1) {
        slecPais.classList.add("errorInput")
         slecPais.placeholder=""
         slecPais.parentElement.childNodes[5].classList.add("errorSpan")
          slecPais.parentElement.childNodes[5].innerHTML =`
          <small>*Debes Seleccionar Pais</small>
          `
    } else {
        correctos.push("|")
        info.pais = slecPais.value
    }
  
}





     /*=============VALIDACION INPUT CORREO============= */
function validarCorreo() {
    correctos.slice(0,1)
    inputCorreo.classList.remove("errorInput")
    inputCorreo.parentElement.childNodes[5].innerHTML =``
    inputCorreo.parentElement.childNodes[5].classList.remove("errorSpan")

     if (inputCorreo.value.length === 0) {
         inputCorreo.classList.add("errorInput")
         inputCorreo.placeholder=""
         inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
          inputCorreo.parentElement.childNodes[5].innerHTML =`
          <small>*El Correo No Puede Estar Vacio</small>
          `
     }
    else  if(inputCorreo.value.length <=7) {
         inputCorreo.classList.add("errorInput")
         inputCorreo.placeholder=""
         inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
          inputCorreo.parentElement.childNodes[5].innerHTML =`
          <small>*El Correo es Muy Corto</small>
         `
     }
    else  if(inputCorreo.value.length >50) {
         inputCorreo.classList.add("errorInput")
         inputCorreo.placeholder=""
         inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
          inputCorreo.parentElement.childNodes[5].innerHTML =`
          <small>*El Correo es Muy Largo</small>
         `
     }
    else if (inputPassword.length < 8) {
        inputCorreo.classList.add("errorInput")
        inputCorreo.placeholder=""
        inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
         inputCorreo.parentElement.childNodes[5].innerHTML =`
         <small>*La Contraseña Debe Contener Al Menos 8 Caracteres</small>
         `

    }
    else if (inputPassword.length > 16) {
        inputCorreo.classList.add("errorInput")
        inputCorreo.placeholder=""
        inputCorreo.parentElement.childNodes[5].classList.add("errorSpan")
         inputCorreo.parentElement.childNodes[5].innerHTML =`
         <small>* La Contraseña No Puede Tener Mas de 16 Caracteres </small>
         `

}
else{
    correctos.push("|")
    info.correo = inputCorreo.value
}
}





     /*=============VALIDACION INPUT PASSWORD============= */
function validatePassword() {
    correctos.slice(0,1)
    inputPassword.parentElement.parentElement.childNodes[5].innerHTML =``
    inputPassword.placeholder=""
    inputPassword.parentElement.classList.remove("errorInput")
    inputPassword.parentElement.parentElement.childNodes[5].classList.remove("errorSpan")
    let re = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

    if (inputPassword.value.length < 8) {
        inputPassword.parentElement.classList.add("errorInput")
        inputPassword.parentElement.parentElement.childNodes[5].classList.add("errorSpan")
         inputPassword.parentElement.parentElement.childNodes[5].innerHTML +=`
         <br>
         <small>*La Contraseña Debe Contener Al Menos 8 Caracteres</small>
         `
    }
    
    if (inputPassword.value.search(/[A-ZÁÉÍÓÚÜÑ]/) < 0) {
        inputPassword.parentElement.classList.add("errorInput")
        inputPassword.parentElement.parentElement.childNodes[5].classList.add("errorSpan")
         inputPassword.parentElement.parentElement.childNodes[5].innerHTML +=`
         <br>
         <small>*La Contraseña Debe Contener Una Mayuscula</small>
         `
    }
    if (inputPassword.value.search(/(?=.*?[a-z])/) < 0) {
        inputPassword.parentElement.classList.add("errorInput")
        inputPassword.parentElement.parentElement.childNodes[5].classList.add("errorSpan")
         inputPassword.parentElement.parentElement.childNodes[5].innerHTML +=`
         <br>
         <small>*La Contraseña Debe Contener Una Minuscula</small>
         `
    }

    if (inputPassword.value.search(/[0-9]/) < 0) {
        inputPassword.parentElement.classList.add("errorInput")
        inputPassword.parentElement.parentElement.childNodes[5].classList.add("errorSpan")
         inputPassword.parentElement.parentElement.childNodes[5].innerHTML +=`
         <br>
         <small>*La Contraseña Debe Contener Un Numero</small>
         `
    }
   
   else if(re.test(inputPassword.value) == false) {
        inputPassword.parentElement.classList.add("errorInput")
        inputPassword.parentElement.parentElement.childNodes[5].classList.add("errorSpan")
         inputPassword.parentElement.parentElement.childNodes[5].innerHTML +=`
         <br>
         <small>*La Contraseña Debe Contener Al Menos Un Caracter Especial</small>
         `
    }
    else{
        correctos.push("|")
        info.password=inputPassword.value
    }

}


     /*=============VALIDACION CONFIRMAR PASSWORD============= */
     function confirmarPassword() {
        correctos.slice(0,1)
        inputPasswordRep.parentElement.childNodes[5].innerHTML =``
        inputPasswordRep.placeholder=""
        inputPasswordRep.classList.remove("errorInput")
        inputPasswordRep.parentElement.childNodes[5].classList.remove("errorSpan")

        if(inputPasswordRep.value == "") {
        inputPasswordRep.classList.add("errorInput")
        inputPasswordRep.placeholder=""
        inputPasswordRep.parentElement.childNodes[5].classList.add("errorSpan")
         inputPasswordRep.parentElement.childNodes[5].innerHTML =`
         <small>* La Contraseña No Puede Estar Vacia</small>
         `
        }
       else if(inputPassword.value != inputPasswordRep.value) {
            inputPasswordRep.classList.add("errorInput")
        inputPasswordRep.placeholder=""
        inputPasswordRep.parentElement.childNodes[5].classList.add("errorSpan")
         inputPasswordRep.parentElement.childNodes[5].innerHTML =`
         <small>* Las Contraseñas Deben Coincidir </small>
         `
        }
        else{
            correctos.push("|")
        }
    }

     /*=============FUNCION QUE PERMITE SOLO LETRAS============= */
function soloLetras(e) {
    let key = e.keyCode || e.which,
      tecla = String.fromCharCode(key).toLowerCase(),
      letras = " áéíóúabcdefghijklmnñopqrstuvwxyz",
      especiales = [8, 37, 39, 46],
      tecla_especial = false;

    for (let i in especiales) {
      if (key == especiales[i]) {
        tecla_especial = true;
        break;
      }
    }

    if (letras.indexOf(tecla) == -1 && !tecla_especial) {
      return false;
    }
  }




