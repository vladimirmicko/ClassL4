angular.module('DynFiles')
   .factory('CalculateService', ['$http', '$rootScope',
      function($http, $rootScope) {
         var service = {};

         var ajaxPrefix = $rootScope.ajaxHostPrefix;

         service.calculate = function(value, fileId) { // Calculate with input valued
            return $http.post(ajaxPrefix + 'calculate', {
               grossSalary: value,
               fileId: fileId
            });
         }

         return service;
      }
   ])