angular.module('DynFiles')
   .factory('UploadFileService', ['$http', '$rootScope',
      function($http, $rootScope) {
         var service = {};

         var ajaxPrefix = $rootScope.ajaxHostPrefix;

         service.addFile = function(fileName, fileUrl) {
            return $http.post(ajaxPrefix + 'addFile', {
               name: fileName,
               fileUrl: fileUrl
            })
         }

         return service;
      }
   ])