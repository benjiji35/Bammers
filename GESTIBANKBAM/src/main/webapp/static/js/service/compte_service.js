'use strict';

angular.module('myApp').factory('CompteService', ['$http', '$q','$window', function($http, $q,$window){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/compte/';
var CLIENT_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/comptes/';

var factory = {
		fetchCompte: fetchCompte,
		virer: virer,
		commanderChequier: commanderChequier
};
return factory;
/*recherche de tous les utilisateurs */
function fetchCompte(cpt) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+cpt)
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while fetching Users');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

function virer(cpt1, cpt2, mont) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+cpt1+"/"+cpt2+"/"+mont)
        .then(
        function (response) {
            deferred.resolve(response.data);
            $window.alert("Votre virement de "+mont+" vers le compte "+cpt2+" a bien été effectué");
            window.location.reload();
        },
        function(errResponse){
            console.error('Error while transfer');
            $window.alert("Votre virement n'a pas été effectué merci de vérifier votre solde et de rééditer votre demande");
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

function commanderChequier(cpt) {
    var deferred = $q.defer();
    $http.get(CLIENT_REST_SERVICE_URI+cpt)
        .then(
        function (response) {
            deferred.resolve(response.data);
            $window.alert("Le chéquier a bien été commandé pour le compte n°"+cpt);
        },
        function(errResponse){
            console.error('Error while fetching Users');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}
}]);
