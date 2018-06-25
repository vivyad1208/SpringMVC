
function validateLogin() {
	var errorMsg = document.getElementById("formError");
	errorMsg.innerHTML = "";
	var ids = ["formInputEmail","formInputPassword"];
	var msg = ["Email","Password"];
	for(var i=0; i<ids.length; i++) {
		var elem = document.getElementById(ids[i]);
		if(elem.value.trim()=="") {
			errorMsg.innerHTML = "Please enter '"+msg[i]+"'!";
			elem.focus();
			return false;
		}
	}
	return true;
}
