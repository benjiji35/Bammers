    function save() {
        
        var name = document.getElementById("username");
        //var pwd = document.getElementById("password");

        var myName = name.value;
        //var myPwd = pwd.value;

        console.log ("Name"+ myName);
        //console.log ("password"+myPwd);

        sessionStorage.setItem("username",myName);
        //sessionStorage.setItem("password",myPwd);

       		if (myName.startsWith("C")){
       			if (true){
       			$(location).attr('href', 'accueil.html')}
       			else
       			$(location).attr('href', 'se_connecter.html')	
       		}

       		else if (myName.startsWith("X")) {
       			if (true){
       			$(location).attr('href', 'accueil_ag.html')}
       			else
       			$(location).attr('href', 'se_connecter.html')
       		}

       		else if (myName.startsWith("A")) {
       			if (true){
       			$(location).attr('href', 'accueil_ad.html')
       			}
       			else
       			$(location).attr('href', 'se_connecter.html')
       		}

}