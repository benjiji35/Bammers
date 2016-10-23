'use strict';
// declaration de module


app.factory(
				'NotificationService',
				[
						'$http',
						'$q',
						function($http, $q) {

							var NOTIFEMPLOYE_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/notif/';

							var factory = {
									createEmployeNotif : createEmployeNotif,
									notif: notif
							};
							return factory;

							function createEmployeNotif(pub) {
							    var deferred = $q.defer();
							    $http.post(REST_SERVICE_URI, pub)
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
							function notif(pub) {
							    var deferred = $q.defer();
							    $http.post(REST_SERVICE_URI, pub)
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
						}]);
							