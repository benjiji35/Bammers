'use strict';

angular.module('myApp').factory('EmployeService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/conseiller/';

var factory = {
		fetchConseiller : fetchConseiller,
		searchCons : searchCons,
		createCons: createCons
};
return factory;

function fetchConseiller() {
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
    function searchCons(id) {
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
    function createCons(cons) {
        var deferred = $q.defer();
        $http.post(CLIENTS_REST_SERVICE_URI, cons)
            .then(
            function (response) {
                deferred.resolve(response.data);
                $window.alert("Le conseiller a bien été créé");
            },
            function(errResponse){
                console.error('Error while creating Employe');
                $window.alert("Erreur lors de la création du conseiller");
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);