angular.module('DynFiles')
   .factory('CalculateService', ['$http', '$rootScope',
      function($http, $rootScope) {
         var service = {};

         var ajaxPrefix = $rootScope.ajaxHostPrefix;

         service.calculate = function(value, fileName) { // Calculate with input valued
            return $http.post(ajaxPrefix + 'calculate', {
               grossSalary: value,
               fileName: fileName
            });
         }

         return service;
      }
   ])