function validateEmail(){
    let email = document.getElementById("email").value;
    let error = document.getElementById("emailError");

    let pattern = /^[A-Za-z0-9._%+-]+@gmail\.com$/;

    if(!pattern.test(email)){
        error.textContent = "Please enter valid email :(eg: user@gmail.com)";
    } else {
        error.textContent = "";
    }
}

function checkEmail(){
let email = document.getElementById("email").value;
let emailError = document.getElementById("emailError");

const xhttp = new XMLHttpRequest();

xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/checkEmail/"+email);
xhttp.send();

xhttp.onload=function(){
emailError.innerHTML=this.responseText;
}

}
