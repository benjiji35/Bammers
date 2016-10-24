'use strict';

angular.module('myApp').factory('EmpSearchService', ['$http', '$q', function($http, $q){

var EMPLOYE_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/searchConseiller/';

var factory = {
		fetchCons : fetchCons
};
return factory;

function fetchCons(nom,prenom) {
    var deferred = $q.defer();
    $http.get(EMPLOYE_REST_SERVICE_URI+'nom-'+nom+'/prenom-'+prenom)
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