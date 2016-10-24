/**
 * 
 */
'use strict';

angular.module('myApp').factory('UploadFileService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/singleUpload/';

var factory = {
		uploadFile : uploadFile
};

return factory;

function uploadFile(fd, sid, key) {
	var deferred = $q.defer();
	$http.post(CLIENTS_REST_SERVICE_URI+"s-"+sid+"/k-"+key, 
			fd, {
				withCredentials: true,
				headers: {'Content-Type': undefined },
				transformRequest: angular.identity})
				.then(
						function (response) {
							deferred.resolve();
						},
						function(errResponse){
							console.error('Error while uploading file');
							deferred.reject(errResponse);
						}
				);
    	return deferred.promise;

//	Upload.upload({
//        url: CLIENTS_REST_SERVICE_URI+"s-"+sid,
//        file: fd,
//        progress: function(evt){
//        	file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
//        }
//      }).then(function(data, status, headers, config) {
//        // file is uploaded successfully
//        console.log(data, status, headers, config);
//      });

}
}]);