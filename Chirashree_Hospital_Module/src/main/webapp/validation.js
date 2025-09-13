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

//function checkEmail(){
//let email = document.getElementById("email").value;
//let emailError = document.getElementById("emailError");
//
//const xhttp = new XMLHttpRequest();
//
//xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/checkEmail/"+email);
//xhttp.send();
//
//xhttp.onload=function(){
//emailError.innerHTML=this.responseText;
//}
//
//}

function validateFirstName(){
let firstname = document.getElementById("firstName").value;
let error = document.getElementById("firstnameError");

if(firstname.length<3||firstname.length>20){
error.textContent = "name must be between the sizeb of 3 and 20"

}else{
error.textContent=" "
}

}

//function CheckFirstName(){
//let name = document.getElementById("firstName").value;
//let error = document.getElementById("firstnameError");
//
//const xhttp = new XMLHttpRequest();
//
//xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/CheckFirstName"+name);
//xhttp.send();
//
//xhttp.onload = function(){
//error.innerHTML = this.responseText;
//}
//}

 function validateLastName() {
        let lastname = document.getElementById("lastName").value.trim();
        let error = document.getElementById("lastnameError");

        if (lastname.length < 3 || lastname.length > 20) {
            error.textContent = "Last name must be between 3 and 20 characters";
        } else {
            error.textContent = "";
        }
    }

//function CheckLastName(){
//let name = document.getElementById("lastName").value;
//let error = document.getElementById("lastnameError");
//
//const xhttp = new XMLHttpRequest();
//
//xhttp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/CheckLastName"+name);
//xhttp.send();
//
//xhttp.onload = function(){
//error.innerHTML = this.responseText;
//}
//}

  function validatePhone() {
        let phone = document.getElementById("phone").value.trim();
        let error = document.getElementById("phoneError");
        let regex = /^[0-9]{10}$/;

        if (!regex.test(phone)) {
            error.textContent = "Phone number must be exactly 10 digits";
        } else {
            error.textContent = "";
        }
    }

      function validateExperience() {
            let exp = document.getElementById("experience").value;
            let error = document.getElementById("experienceError");

            if (exp === "" || exp < 0 || exp > 50) {
                error.textContent = "Experience must be between 0 and 50 years";
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
           let specialization = document.getElementById("specialization").value.trim();
           let error = document.getElementById("specializationError");

           if (specialization === "" || specialization.length < 3 || specialization.length > 50) {
               error.textContent = "Please select or enter a valid specialization (3–50 characters)";
           } else {
               error.textContent = "";
           }
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

function validateTiming() {
    let start = document.getElementById("timingStart").value;
    let end = document.getElementById("timingEnd").value;
    let error = document.getElementById("timingError");

    if (start === "" || end === "") {
        error.textContent = "Please select both start and end time";
        return false;
    }


    let startTime = new Date("1970-01-01T" + start + ":00");
    let endTime = new Date("1970-01-01T" + end + ":00");

    if (endTime <= startTime) {
        error.textContent = "End time must be later than start time";
        return false;
    }

    error.textContent = "";
    return true;
}

function validateProfilePicture() {
    let fileInput = document.getElementById("profilePicture");
    let error = document.getElementById("profilePictureError");
    let filePath = fileInput.value;

    if (filePath === "") {
        error.textContent = "Please upload a profile picture";
        return false;
    }

    // Allowed file extensions
    let allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;

    if (!allowedExtensions.exec(filePath)) {
        error.textContent = "Only JPG, JPEG, PNG, or GIF files are allowed";
        fileInput.value = ""; // reset the file input
        return false;
    }

    // Check file size (e.g., max 2MB)
    if (fileInput.files[0].size > 2 * 1024 * 1024) {
        error.textContent = "File size must be less than 2MB";
        fileInput.value = "";
        return false;
    }

    error.textContent = "";
    return true;
}



