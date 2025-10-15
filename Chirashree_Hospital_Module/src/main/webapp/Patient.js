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

function checkPatientEmail() {
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
    xhttp.open("GET", `http://localhost:8080/Chirashree_Hospital_Module/checkPatientEmail/${encodedEmail}`, true);

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


     function CheckPatientPhoneNumber() {
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
         const url = "http://localhost:8080/Chirashree_Hospital_Module/CheckPatientPhoneNumber/" + encodedPhone;

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

function validateAge() {
    const ageInput = document.getElementById("age");
    const ageError = document.getElementById("ageError");
    const age = parseInt(ageInput.value, 10);

    ageError.textContent = ""; // reset error

    if (isNaN(age)) {
        ageError.textContent = "Please enter a valid age";
        return false;
    }

    if (age < 0 || age > 60) {
        ageError.textContent = "Age must be between 0 and 60 years";
        return false;
    }

    return true; // valid
}

function fetchDoctor(){
console.log("fetchDoctor() called ");
let doctorError=document.getElementById("doctorError");
doctorError.innerHTML="";
let specialization=document.getElementById("specialization").value;


    const xhhtp=new XMLHttpRequest();

    xhhtp.open("GET","http://localhost:8080/Chirashree_Hospital_Module/fetchDoctor/"+specialization);

    xhhtp.send();
    xhhtp.onload=function(){
    let doctorName=document.getElementById("doctor");

    doctorName.innerHTML = "";
        let defaultOption = document.createElement("option");
        defaultOption.textContent = "Select Doctor";
        defaultOption.value = "";
        defaultOption.disabled=true;
        defaultOption.selected=true;
        doctorName.appendChild(defaultOption);

    if(this.responseText==="No Doctors Found"||this.responseText==="No doctors"){
    doctorName.disabled=true;
    doctorError.innerHTML="No doctors found";

    }else{
    doctorError.innerHTML="";
        doctorName.disabled=false;
        let names=this.responseText.split(",");//ram|ram@gmail.com,amr|amar@hgmail.com,.|,
        for(let i=0;i<names.length;i++){
        let [name,email]=names[i].split("|");
        console.log(name,email)
               let option = document.createElement("option");
                 option.value = name;
                     option.textContent = name;
                     option.setAttribute("data-email", email);
                   doctorName.appendChild(option);
                   }
        }
    }
}
function fetchTimeSlot() {
    console.log("fetchTimeSlot() called ");

    let doctorSelect = document.getElementById("doctor");
    let selectedOption = doctorSelect.options[doctorSelect.selectedIndex];

    if (!selectedOption || !selectedOption.hasAttribute("data-email")) {
        console.warn("No doctor selected or missing email attribute");
        return;
    }

    let email = selectedOption.getAttribute("data-email");
    console.log("Fetching timeslot for email:", email);

    const xhhtp = new XMLHttpRequest();
    xhhtp.open("GET", "http://localhost:8080/Chirashree_Hospital_Module/getTimeSlotByEmail/" + encodeURIComponent(email));
    xhhtp.send();

    xhhtp.onload = function () {
        console.log("Timeslot response:", this.responseText);

        let slotSelect = document.getElementById("slot");
        let slotError = document.getElementById("slotError");

        // Clear previous options
        slotSelect.innerHTML = "";

        if (this.responseText === "No Time Slot Assigned") {
            // Disable select and show error
            let noOption = document.createElement("option");
            noOption.textContent = "No Slot Available";
            noOption.disabled = true;
            noOption.selected = true;
            slotSelect.appendChild(noOption);

            slotSelect.disabled = true;
            slotError.innerHTML = "No time slot assigned for this doctor";
        } else {
            // Enable select and populate slots
            slotSelect.disabled = false;
            slotError.innerHTML = "";

            // If response has multiple slots separated by commas, split them
            let slots = this.responseText.split(",");
            slots.forEach(slot => {
                let option = document.createElement("option");
                option.value = slot.trim();
                option.textContent = slot.trim();
                slotSelect.appendChild(option);
            });
        }
    }
}
