'use strict';

angular.module('myApp').factory('ClientServiceNew', ['$http', '$q','$window', function($http, $q, $window){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/newClient/';

var factory = {
		fetchNewClients : fetchNewClients,
		srcClient : srcClient,
		updateUser : updateUser
};
return factory;

function fetchNewClients() {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI)
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

function updateUser(idcl,idcons) {
    var deferred = $q.defer();
    console.log("Blabla");
    $http.put(CLIENTS_REST_SERVICE_URI+'idcl-'+idcl+'/idcons-'+idcons)
        .then(
        function (response) {
            deferred.resolve(response.data);
            $window.alert("Le client a bien été mis à jour");
            $window.location.reload();
        },
        function(errResponse){
            console.error('Error while updating User');
            $window.alert("Erreur lors de la mise à jour du client");
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;

}
function srcClient(nom) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+ 'nom-'+nom)
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

}]);