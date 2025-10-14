function validateEmail() {
    const emailInput = document.getElementById("email");
    const email = emailInput.value.trim();
    const emailError = document.getElementById("emailError");
    const pattern = /^[A-Za-z0-9._%+-]+@gmail\.com$/;

    emailError.textContent = ""; // reset error

    if (email === "") {
        return false;
    }

    if (!pattern.test(email)) {
        emailError.textContent = "Please enter a valid Gmail address (e.g., user@gmail.com)";
        return false;
    }

    return true; // valid
}

function checkDoctorEmail() {
    const emailInput = document.getElementById("email");
    const email = emailInput.value.trim();
    const emailError = document.getElementById("emailError");

    // ✅ Stop here if field is empty
    if (email === "") {
        emailError.textContent = "";
        return; // No backend call
    }

    // ✅ Stop here if invalid Gmail
    const pattern = /^[A-Za-z0-9._%+-]+@gmail\.com$/;
    if (!pattern.test(email)) {
        emailError.textContent = "Please enter a valid Gmail address (e.g., user@gmail.com)";
        return;
    }

    // ✅ Encode and call backend safely
    const encodedEmail = encodeURIComponent(email);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", `http://localhost:8080/Chirashree_Hospital_Module/checkDoctorEmail/${encodedEmail}`, true);

    xhttp.onload = function () {
        const responseText = this.responseText.trim();
        emailError.textContent = responseText;
    };

    xhttp.send();
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
      let phoneInput = document.getElementById("phone");
      let error = document.getElementById("phoneError");
      let regex = /^[6-9][0-9]{9}$/;

      // Remove all non-digit characters
      phoneInput.value = phoneInput.value.replace(/\D/g, '');
      let phone = phoneInput.value;

      if (!regex.test(phone)) {
          error.textContent = "Phone number must start with 6-9 and be exactly 10 digits";
          return false;
      } else {
          error.textContent = "";
          return true;
      }
  }


  function CheckPhoneNumber() {
      const phoneInput = document.getElementById("phone");
      const phoneNumber = phoneInput.value.trim();
      const error = document.getElementById("phoneError");

      // ✅ Reset previous error
      error.textContent = "";

      // ✅ Step 1: Stop if empty
      if (phoneNumber === "") {
          return;
      }

      // ✅ Step 2: Validate phone (assuming 10-digit numbers)
      const pattern = /^[6-9]\d{9}$/;
      if (!pattern.test(phoneNumber)) {
          error.textContent = "Please enter a valid 10-digit phone number";
          return;
      }

      // ✅ Step 3: Encode and prepare request
      const encodedPhone = encodeURIComponent(phoneNumber);
      const url = "http://localhost:8080/Chirashree_Hospital_Module/CheckPhoneNumber/" + encodedPhone;

      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);
      xhttp.send();

      // ✅ Step 4: Handle response
      xhttp.onload = function () {
          const responseText = this.responseText.trim();
          if (responseText !== "") {
              error.textContent = responseText;
          } else {
              error.textContent = "";
          }
      };
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





