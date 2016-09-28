'use strict';
// declaration de module
var app = angular.module('myApp',['ngRoute']);

// déclaration de variables de contexte
/*
 * 0 = Espace public
 * 1 = Espace Client
 * 2 = Espace Conseiller
 * 3 = Espace Admin
 */
var role = 0;

//$scope.role = role;
