<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="charset=utf-8" />
<meta http-equiv="Content-Language" content="fr" />
<link rel="stylesheet" type="text/css" href="<c:url value="static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="static/style/css/se_connecter.css"/>"/>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="<c:url value='static/bower_components/angular/angular.min.js'/>"></script>
<script src="<c:url value='static/bower_components/jquery/dist/jquery.js'/>"></script>
<script src="<c:url value='static/bower_components/bootstrap/dist/js/bootstrap.js'/>"></script>
<script src="<c:url value='static/bower_components/angular-route/angular-route.js'/>"></script>

<script src="<c:url value='static/js/app.js'/>"></script>
<script src="<c:url value='static/js/service/validation_service.js'/>"></script>
<script src="<c:url value='static/js/controller/validation_controller.js'/>"></script>
<script src="<c:url value='static/js/config-route.js'/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de connexion</title>
</head>


<header>
	<a href="index.html" class="navbar-left"><img id ="logo" src="img/logo.jpg"></a>
	<!-- barre de navigation -->
	<nav class="navbar">
	  <div class="container-fluid">
	    <div class="navbar-header">
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="index.html">Accueil</a></li>
	    </ul>
	    </div>
	  </div>
	</nav>
	<!-- boutons -->
	<div id="connexion" class ="navbar">
		<input type="button" value="Devenir client" class ="btn btn - primary btn -xs"   onclick="javascript:location.href='newclient.html'">
		<input type="button" value="Connexion" class ="btn btn - primary btn -xs"   onclick="javascript:location.href='se_connecter.html'">
	</div>
</header>
<body ng-app="myApp">
		
		<ul class =" breadcrumb ">
            <li ><a href ="index.html">Accueil </a></li >
            <li class =" active " >Acceder a vos comptes</li >
        </ul >
		<br>
		
	<div id="formulaire">
	<form ng-controller="connectCtrl" >
		<legend>BIENVENUE SUR L'ESPACE DE CONNEXION</legend>
<!-- 			<h3 ng-show="con==false"><font color="red"><b> -->
<!-- 			Erreur de mot de passe ou d'identifiant -->
<!-- 		</b></font></h3> -->
		<p>
			<label for="n1"><h4>1. Saisissez votre identifiant</h4></label>
			<div id="Identifiant">
				<input type="text" ng-model="id" name="username" class= "form-inline" placeholder="Identifiant" required>&nbsp; &nbsp; &nbsp; &nbsp; 
				<a href="url">Identifiant oublié?</a><br><br>
				<input type="checkbox">&nbsp; Memoriser mon identifiant sur mon navigateur
			</div>
		</p>
		<br>

		<p>
			<label for="n2"><h4>2. Saisissez votre mot de passe</h4></label>
			<div id=MotdePasse>
				<input ng-model="mdp" placeholder="Mot de passe" class= "form-inline" type="password" name="password"  required> &nbsp; &nbsp; &nbsp; &nbsp;
				<a href="url">Mot de passe oublié?</a><br><br>
				<button type="button" class="btn btn-danger  btn-xs" ng-click = "mdp=null">Effacer</button>
			</div>
		</p>
		<br>

		<p>
			<label for="n3"><h4>3. Accéder à votre espace</h4></label>
			<button type="submit"  ng-click= "valid()" class= "btn btn-success btn-sm">Accéder</button>
		</p>

	</form>
	</div>
</body>
</html>
