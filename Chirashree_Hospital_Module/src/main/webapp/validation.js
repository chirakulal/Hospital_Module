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


function checkDoctorEmail(){
let email = document.getElementById("email").value;
let emailError = document.getElementById("emailError");

const xhttp = new XMLHttpRequest();

xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/checkDoctorEmail/"+email);
xhttp.send();

xhttp.onload=function(){
emailError.innerHTML=this.responseText;
}


}
function validateFirstName() {
    let firstname = document.getElementById("firstName");
    let error = document.getElementById("firstnameError");
     const pattern = /^[A-Z][A-Za-z\s-]*$/;

     firstname.value = firstname.value.replace(/[^A-Za-z\s-]/g, '');


    // Empty check
        if ( firstname.value === "") {
            error.textContent = "First name is required";
            return false;
        }

        // Regex check first
        if (!pattern.test( firstname.value)) {
            error.textContent = "Name should start with Capital letter and not contain numbers or special characters";
            return false;
        }

    if ( firstname.value.length < 3 ||  firstname.value.length > 20) {
        error.textContent = "Name must be between 3 and 20 characters";
        return false;
    }


    error.textContent = "";
    return true;
}




 function validateLastName() {
        let lastname = document.getElementById("lastName");
        let error = document.getElementById("lastnameError");

        const pattern = /^[A-Za-z\s-]*$/;

            lastname.value = lastname.value.replace(/[^A-Za-z\s-]/g, '');

        if(!pattern.test(lastname.value)){
        error.textContent = "Name should not contain numbers or special characters";
        return false;

        }

        if (lastname.value.length < 0 || lastname.value.length > 20) {
            error.textContent = "Last name must be between 3 and 20 characters";
            return false;
        }

        //if valid
        error.textContent ="";
        return true;
    }

//function CheckLastName(){
//let lastname = document.getElementById("lastName").value.trim();
//let error = document.getElementById("lastnameError");
//
//const xhttp = new XMLHttpRequest();
//
//xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/CheckLastName/"+lastname);
//xhttp.send();
//
//xhttp.onload = function(){
//error.innerHTML = this.responseText;
//}
//}

  function validatePhone() {
      let phone = document.getElementById("phone");
      let error = document.getElementById("phoneError");
      let regex = /^[6-9][0-9]{9}$/;  // starts with 6–9 and has total 10 digits

      // Remove all non-digit characters (if user types spaces, +91, etc.)
      phone.value = phone.value.replace(/\D/g, '');

      if (!regex.test(phone.value)) {
          error.textContent = "Phone number must start with 6-9 and be exactly 10 digits";
          return false;
      } else {
          error.textContent = "";
          return true;
      }
  }



    function CheckPhoneNumber(){
    let phoneNumber = document.getElementById("phone").value;
    let error = document.getElementById("phoneError");

    const xhttp = new XMLHttpRequest();

    xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/CheckPhoneNumber/"+phoneNumber);
    xhttp.send();

    xhttp.onload = function(){
    error.innerHTML = this.responseText;
    }
    }


      function validateExperience() {
            let exp = document.getElementById("experience").value;
            let error = document.getElementById("experienceError");

            if (exp === "" || exp < 0 || exp > 30) {
                error.textContent = "Experience must be between 0 and 30 years";
            } else {
                error.textContent = "";
            }
        }


        function validateAddress() {
            let address = document.getElementById("address").value.trim();
            let error = document.getElementById("addressError");

            if (address.length < 3 || address.length > 1000) {
                error.textContent = "Address must be between 3 and 1000 characters";
            } else {
                error.textContent = "";
            }
        }

       function validateSpecialization() {
           let specialization = document.getElementById("specialization").value;
           let error = document.getElementById("specializationError");

           if (specialization === "") {
               error.textContent = "Please select a specialization";
               return false;
           }

           error.textContent = "";
           return true;
       }

       function validateGender() {
           let male = document.getElementById("male");
           let female = document.getElementById("female");
           let error = document.getElementById("genderError");

           if (!male.checked && !female.checked) {
               error.textContent = "Please select gender";
           } else {
               error.textContent = "";
           }
       }

window.addEventListener('pageshow', function(event) {
    console.log("pageshow event fired");
    console.log("persisted:", event.persisted);
    if (event.persisted) {
        console.log(" Page loaded from cache, reloading...");
        window.location.reload();
    }
});





