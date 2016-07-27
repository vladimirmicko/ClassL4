angular.module('DynFiles')
   .factory('HomeService', ['$http', '$rootScope',
      function($http, $rootScope) {
         var service = {};

         var ajaxPrefix = $rootScope.ajaxHostPrefix;

         service.getFiles = function() { // Get all files for list
            return $http.get(ajaxPrefix + 'files');
         }
         service.sendTextCode = function(data) { // Submit code text
            return $http.post(ajaxPrefix + 'executeTextArea', {
               codeText: data
            });
         }
         service.executeFile = function(fileId) { // Start command for file from list
            return $http.post(ajaxPrefix + 'executeFile/' + fileId);
         }
         service.deleteFile = function(fileId) { // Delete file from list
            return $http.post(ajaxPrefix + 'delete/' + fileId);
         }
         service.editFile = function(file) { // Edit file from list
            return $http.post(ajaxPrefix + 'editFile', file);
         }
         service.downloadFiles = function(arr) {
        	 return $http.post(ajaxPrefix + 'download', arr);
         }

         return service;
      }
   ])