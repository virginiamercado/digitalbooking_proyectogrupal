
if (window.location.hash != "") {
    alerta()
}

function alerta() {

document.querySelector(".alerts").innerHTML=`
<div class="alert request">
<span class="alert-icon"><i class="fas fa-exclamation"></i></span>
<span class="alert-content">
  <span class="alert-close"><i class="fas fa-times"></i></span>
  <span class="alert-title">Debes Estar Logeado Para Reservar</span>
</span>
</div>
`

}

$(document).ready(function () {
    $( ".alert" ).click(function() {
      $( this ).find( ".little-list" ).slideToggle();
    });
    $( ".alert-close" ).click(function() {
      $( this ).parent().parent().fadeOut();
    });
  }); 