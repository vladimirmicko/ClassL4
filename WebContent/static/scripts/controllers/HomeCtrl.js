angular.module('DynFiles')
   .controller('HomeCtrl', ['$scope', 'HomeService',
      function($scope, HomeService) {
         var vm = this;
         /************/

         vm.files = []; //Array of object for files list
         vm.sCodeText = ''; // Code text to be sent

         var editedFile = {};

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
         vm.codeSubmit = function(codeText) { // Submit code text to server
            HomeService.sendTextCode(codeText).then(function(result) {
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ sendTextCode - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ sendTextCode - FAILED");
               }
            });
         }
         vm.startFile = function(fileId) { // Start file from list
            HomeService.executeFile(fileId).then(function(result) {
               console.log(result)
               $scope.testProperty = result.data.result
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ executeFile - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ executeFile - FAILED");
               }
            });
         }
         vm.deleteFile = function(fileId) { // Delete file form list
            HomeService.deleteFile(fileId).then(function(result) {
               vm.getFiles();
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteFile - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ deleteFile - FAILED");
               }
            });
         }
         vm.editFile = function(file) {
            HomeService.editFile(file).then(function(result) {
               vm.getFiles();
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ editFile - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ editFile - FAILED");
               }
            });
         }

         /*----------  Setters and Getters  ----------*/
         vm.setEditedFile = function(file) { // editeFile
            editedFile = angular.copy(file);
         }
         vm.getEditedFile = function() { // editeFile
            return editedFile;
         }


         /*----------  Construct  ----------*/
         init();
      }
   ]);