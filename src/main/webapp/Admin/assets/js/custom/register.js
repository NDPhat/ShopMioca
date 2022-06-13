const password = document.getElementById("password-register");
const confirmPassword = document.getElementById("repassword-register");
function checkPasswordMatch() {
    if (password.value != confirmPassword.value)
    {
        confirmPassword.setCustomValidity("Passwords Don't Match");
    } else {
        confirmPassword.setCustomValidity('');
    }
}

password.onchange = checkPasswordMatch;
confirmPassword.onkeyup = checkPasswordMatch;