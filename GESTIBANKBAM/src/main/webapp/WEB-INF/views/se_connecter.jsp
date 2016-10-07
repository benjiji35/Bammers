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
<!-- <header> <a href="index.html" class="navbar-left"><img -->
<%-- 	id="logo" src="<c:url value='img/logo.jpg'/>"</a> </header> --%>

<body ng-app="myApp">

	<ul class=" breadcrumb ">
		<li><a href="index.html">Accueil </a></li>
		<li class=" active ">Acceder a vos comptes</li>
	</ul>
	<br>
	<br>
	<br>

	<div id="formulaire">
		<form ng-controller="connectCtrl">
			<legend>BIENVENUE SUR L'ESPACE DE CONNEXION</legend>
			<p>
				<label for="n1"><h4>1. Saisissez votre identifiant</h4></label> <br>
			<div id="Identifiant">
				<input type="text" ng-model="id" name="username" class="form-inline"
					placeholder="Identifiant" required>&nbsp; &nbsp; &nbsp;
				&nbsp; <a href="url">Identifiant oublié?</a><br>
				<br> <input type="checkbox">&nbsp; Memoriser mon
				identifiant sur mon navigateur
			</div>
			</p>
			<br>
			<br>

			<p>
				<label for="n2"><h4>2. Saisissez votre mot de passe</h4></label> <br>
			<div id=MotdePasse>
				<input ng-model="mdp" placeholder="Mot de passe" class="form-inline"
					type="password" name="password" required> &nbsp; &nbsp;
				&nbsp; &nbsp; <a href="url">Mot de passe oublié?</a><br>
				<br>
				<button type="button" class="btn btn-danger  btn-xs">Effacer</button>
			</div>
			</p>
			<br>
			<br>
			<br>

			<p>
				<label for="n3"><h4>3. Accéder à votre espace</h4></label> <br>
				<button type="submit" ng-click="valid()"
					class="btn btn-success btn-sm">Accéder</button>
			</p>

		</form>
	</div>
</body>
</html>
