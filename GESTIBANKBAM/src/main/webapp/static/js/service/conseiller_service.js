'use strict';

angular.module('myApp').factory('ConseillerUpdateService', ['$http', '$q', function($http, $q){

var CLIENT_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/UpdateConseiller/';

var factory = {
		UpdateConseiller : UpdateConseiller
};
return factory;

function UpdateConseiller(conseiller,id){
    var deferred = $q.defer();
    $http.put(CLIENT_REST_SERVICE_URI+id,conseiller)
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