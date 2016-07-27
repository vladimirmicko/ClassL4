angular.module('DynFiles')
   .controller('UploadFileCtrl', ['$scope', '$rootScope', 'FileUploader', 'UploadFileService',
      function($scope, $rootScope, FileUploader, UploadFileService) {
         var vm = this;
         /************/

         vm.fileUploader = new FileUploader();
         vm.fileUploader.url = $rootScope.ajaxHostPrefix + "upload";

         bSuccessMessage = true; // Show success message for ajax calls
         bFailMessage = true; // Show faul message for ajax calls
         /*----------  Init function  ----------*/
         function init() {}


         vm.fileUploader.filters.push({
             name: 'customFilter',
             fn: function(item /*{File|FileLikeObject}*/, options) {
                 return this.queue.length < 10;
             }
         });

         // CALLBACKS

         vm.fileUploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
             console.info('onWhenAddingFileFailed', item, filter, options);
         };
         vm.fileUploader.onAfterAddingFile = function(fileItem) {
             console.info('onAfterAddingFile', fileItem);
         };
         vm.fileUploader.onAfterAddingAll = function(addedFileItems) {
             console.info('onAfterAddingAll', addedFileItems);
         };
         vm.fileUploader.onBeforeUploadItem = function(item) {
             console.info('onBeforeUploadItem', item);
         };
         vm.fileUploader.onProgressItem = function(fileItem, progress) {
             console.info('onProgressItem', fileItem, progress);
         };
         vm.fileUploader.onProgressAll = function(progress) {
             console.info('onProgressAll', progress);
         };
         vm.fileUploader.onSuccessItem = function(fileItem, response, status, headers) {
             console.info('onSuccessItem', fileItem, response, status, headers);
         };
         vm.fileUploader.onErrorItem = function(fileItem, response, status, headers) {
             console.info('onErrorItem', fileItem, response, status, headers);
         };
         vm.fileUploader.onCancelItem = function(fileItem, response, status, headers) {
             console.info('onCancelItem', fileItem, response, status, headers);
         };
         vm.fileUploader.onCompleteItem = function(fileItem, response, status, headers) {
             console.info('onCompleteItem', fileItem, response, status, headers);
         };
         vm.fileUploader.onCompleteAll = function() {
             console.info('onCompleteAll');
         };

         console.info('vm.fileUploader', vm.fileUploader);
   }]);