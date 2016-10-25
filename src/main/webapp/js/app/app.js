(function(angular) {
    "use strict";

    angular.module("GTF", ["ui.router", "ngResource", "GTF.Services", "GTF.Controllers"]);

    angular.module("GTF").config(["$stateProvider",
        function($stateProvider) {
            $stateProvider.state("index", {
                url: "/",
                templateUrl: "index"
            }).state("cor", {
                url: "/cor",
                templateUrl: "cor/list",
                controller: "CorController"
            }).state("novaCor", {
                url: "/cor/add",
                templateUrl: "cor/add",
                controller: "CorController"
            }).state("editarCor", {
                url: "/cor/:id/edit",
                templateUrl: "cor/edit",
                controller: "CorController"
            });
        }
    ]).run(function($state){
        $state.go("index");
    });
})(window.angular);