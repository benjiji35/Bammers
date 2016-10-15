'use strict';

angular.module('myApp').factory('EmployeService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/conseiller/';

var factory = {
		fetchConseiller : fetchConseiller,
		searchCons : searchCons
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
}]);