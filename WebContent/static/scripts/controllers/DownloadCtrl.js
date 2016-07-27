angular.module('DynFiles')
   .controller('DownloadCtrl', ['$scope', 'HomeService',
      function($scope, HomeService) {
         var vm = this;
         /************/

         vm.files = []; // Array of files for list

         vm.selectedFiles = [];
         vm.isLoaded = false;

         bSuccessMessage = false; // Show success message for ajax calls
         bFailMessage = false; // Show faul message for ajax calls
         
         /*----------  Init function  ----------*/
         function init() {
            vm.getFiles();
         }

         /*----------  Click methods  ----------*/
         vm.getFiles = function() { // Get all files for list
            HomeService.getFiles().then(function(result) {
               vm.files = result.data;
               vm.isLoaded = true;
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ getFiles - SUCCESSFUL");
               }
            }, function() {
            	vm.isLoaded = true;
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ getFiles - FAILED");
               }
            });
         }

         vm.onFileSelect = function(file) { // Select file from list
        	 var index = vm.selectedFiles.indexOf(file);
        	 if(index === -1) {
        		 vm.selectedFiles.push(file);
        		 $("[data-id='"+file.id+"']").addClass("active-green");
        	 } else {
        		 $("[data-id='"+file.id+"']").removeClass("active-green");
        		 vm.selectedFiles.splice(index, 1);
        	 }
         }
         
         vm.downloadFiles = function() {
        	 vm.isLoaded = false;
        	 HomeService.downloadFiles(vm.selectedFiles).then(function(data) {
        		 vm.isLoaded = true;
        		 vm.selectedFiles = [];
        		 if (bSuccessMessage) {
                     console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ downloadFiles - SUCCESSFUL");
                  }
        	 }, function() {
        		 vm.isLoaded = true;
        		 if (bFailMessage) {
                     console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ downloadFiles - FAILED");
                  }
        	 });
         }

         /*----------  Construct  ----------*/
         init();
      }
   ]);