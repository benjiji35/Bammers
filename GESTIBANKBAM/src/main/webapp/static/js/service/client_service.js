'use strict';

angular.module('myApp').factory('ClientService', ['$http', '$q','$window', function($http, $q, $window){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/client/';
var CLIENT_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/clients/';

var factory = {
		fetchAllClients: fetchAllClients,
		createClient: createClient,
		srcClient : srcClient,
        updateClient : updateClient,
        OpenNewCompte: OpenNewCompte
};
return factory;
/*recherche de tous les utilisateurs */
function fetchAllClients() {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI)
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


function createClient(client) {
    var deferred = $q.defer();
    $http.post(CLIENTS_REST_SERVICE_URI, client)
        .then(
        function (response) {
            deferred.resolve(response.data);
            $window.alert("Votre demande a bien été enregistrée et sera traitée dans les meileurs délais");
            window.location.reload();
        },
        function(errResponse){
            console.error('Error while creating User');
            $window.alert("Une erreur s'est produite merci de vérifier vos informations et de réessayer votre demande");
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

function srcClient(nom,prenom) {
    var deferred = $q.defer();
        $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-'+nom+'/prenom-'+prenom)
        .then( 
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while finding User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

function updateClient(c,id) {
    var deferred = $q.defer();
    $http.put(REST_SERVICE_URI+id, c)
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while updating User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}
function OpenNewCompte(clt, mont) {
    var deferred = $q.defer();
    $http.get(CLIENT_REST_SERVICE_URI+clt+"/"+mont)
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
}]);
