'use strict';

angular.module('myApp').factory('ClientService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/client/';

var factory = {
		fetchAllClients: fetchAllClients,
		createClient: createClient,
		srcClient : srcClient,
        updateClient : updateClient
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
        },
        function(errResponse){
            console.error('Error while creating User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

function srcClient(nom,prenom,cpte) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-'+nom+'/prenom-'+prenom+'/cpte-'+cpte)
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

}]);
