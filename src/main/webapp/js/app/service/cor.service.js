(function() {
    "use strict";

    angular.module("cor")
           .factory("CorService", CorService);

   CorService.$inject = ["$http"];

   function CorService($http)
   {
       var root = ROOTURL + "/cor/cores";

       return {
           getCores : getCores
       };

       function getCores() {
           return $http.get(root);
       }
   }
})();