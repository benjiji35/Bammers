'use strict';

angular.module('myApp').factory('ConseillerUpdateService', ['$http', '$q','$window', function($http, $q,$window){

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
            $window.alert("Le conseiller a bien été mis à jour");
        },
        function(errResponse){
            console.error('Error while updating User');
            $window.alert("Erreur lors de la mise à jour du conseiller");
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}   

}]);