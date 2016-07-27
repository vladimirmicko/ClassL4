angular.module('DynFiles')
   .controller('CalculateCtrl', ['$scope', 'CalculateService', 'HomeService',
      function($scope, CalculateService, HomeService) {
         var vm = this;
         /************/

         vm.files = []; // Array of files for list

         vm.selectedFile = {};
         vm.calculateResult;

         bSuccessMessage = true; // Show success message for ajax calls
         bFailMessage = true; // Show faul message for ajax calls
         /*----------  Init function  ----------*/
         function init() {
            vm.getFiles();
         }

         /*----------  Click methods  ----------*/
         vm.getFiles = function() { // Get all files for list
            HomeService.getFiles().then(function(result) {
               vm.files = result.data;
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ getFiles - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ getFiles - FAILED");
               }
            });
         }

         vm.calculate = function(value, fileId) {
            CalculateService.calculate(value, fileId).then(function(result) {
               vm.calculateResult = result.data.result;
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ calculate - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ calculate - FAILED");
               }
            });
         }

         vm.selectFile = function(file) { // Select file from list
            vm.selectedFile = angular.copy(file);
         }

         /*----------  Construct  ----------*/
         init();
      }
   ]);