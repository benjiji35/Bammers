'use strict';

angular.module('myApp').factory('ClientService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/client/';

var factory = {
		fetchAllClients: fetchAllClients,
		searchClient: searchClient,
		createClient: createClient,
		srcClient : srcClient
//		srcClientPrenom : srcClientPrenom,
//		srcClientNomPrenom : srcClientNomPrenom
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

///*recherche de tous les nouveaux utilisateurs */
//function fetchAllNewsClients() {
//    var deferred = $q.defer();
//    $http.get(CLIENTS_REST_SERVICE_URI'/news/')
//        .then(
//        function (response) {
//            deferred.resolve(response.data);
//        },
//        function(errResponse){
//            console.error('Error while fetching Users');
//            deferred.reject(errResponse);
//        }
//    );
//    return deferred.promise;
//}

function searchClient(id) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+id)
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
//function srcClient(nom,prenom,cpte) {
//    var deferred = $q.defer();
//    $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-'+nom +'/prenom-'+prenom+'/cpte-'+cpte)
//        .then( 
//        function (response) {
//            deferred.resolve(response.data);
//        },
//        function(errResponse){
//            console.error('Error while finding User');
//            deferred.reject(errResponse);
//        }
//    );
//    return deferred.promise;
//
//}

function srcClient(nom) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-'+client.nom+'/prenom-'+client.prenom+'/cpte'+client.cpte)
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
//function srcClientPrenom(prenom) {
// var deferred = $q.defer();
//   $http.get(CLIENTS_REST_SERVICE_URI+ 'prenom-' +prenom)
//       .then(
//       function (response) {
//            deferred.resolve(response.data);
//        },
//       function(errResponse){
//            console.error('Error while finding User');
//            deferred.reject(errResponse);
//        }
//    );
//    return deferred.promise;
//
//}
//
//
//function srcClientNomPrenom(nom,prenom) {
//  var deferred = $q.defer();
//    $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-' +nom+ '/prenom-' +prenom)
//        .then(
//        function (response) {
//           deferred.resolve(response.data);
//       },
//        function(errResponse){
//           console.error('Error while finding User');
//            deferred.reject(errResponse);
//       }
//    );
//}


}]);
