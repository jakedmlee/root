var strength = {
  0: "Worst",
  1: "Bad",
  2: "Weak",
  3: "Good",
  4: "Strong"
};

var password = document.getElementById("password");
var meter = document.getElementById("password-strength-meter");
var msg = document.getElementById("password-strength-text");

function showFeedback() {
    var val = password.value;
    var result = zxcvbn(val);

    // Update the password strength meter
    meter.value = result.score;

    // Update the text indicator
    if (val !== "") {
      msg.innerText = strength[result.score];
    } else {
      msg.innerText = "";
    }
}

password.addEventListener('change', showFeedback);
password.addEventListener('keyup', showFeedback);