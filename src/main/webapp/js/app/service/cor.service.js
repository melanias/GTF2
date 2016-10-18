(function() {
    "use strict";

    angular.module("cor")
           .factory("corService", corService);

   corService.$inject = ["$resource"];

   function corService($resource)
   {
       return $resource(ROOTURL + "/cor/:params", {}, {});
   }
})();