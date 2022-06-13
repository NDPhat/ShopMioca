const forgotPassword = (event) => {
    event.preventDefault();
    document.getElementById("forgot-passwd").click();
    document.getElementById("forgot-passwd").style.display = 'block';
    // document.getElementById("lg").style.display = 'none';
    // document.getElementById("reg").style.display = 'none';
}

const lg_regClick = () => {
    if (document.getElementById("forgot-passwd").style.display === 'block') {
        document.getElementById("forgot-passwd").style.display = 'none';
    }
}

const isRegister = () => {
    if (document.getElementById("forgot-passwd").style.display === 'block') {
        document.getElementById("forgot-passwd").style.display = 'none';
    }
}

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

setTimeout(() => {
    let err = document.getElementById("errorMessage");
    if (err!= null) {
        err.style.display = "none";
    }
},3500);
const passwordforget = document.getElementById("passwordforget");
const confirmPasswordforget = document.getElementById("repasswordforget");
function checkPasswordforgetMatch() {
    if (passwordforget.value != confirmPasswordforget.value)
    {
        confirmPasswordforget.setCustomValidity("Passwords Don't Match");
    } else {
        confirmPasswordforget.setCustomValidity('');
    }
}

passwordforget.onchange = checkPasswordforgetMatch;
confirmPasswordforget.onkeyup = checkPasswordforgetMatch;