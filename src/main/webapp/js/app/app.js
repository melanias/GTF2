(function(angular) {
    "use strict";

    //angular.module("GTF", ["ngRoute", "ngResource"])
    angular.module("GTF", ["GTF.Service", "GTF.Controller", "ngRoute"])
            .config(["$routeProvider", "$locationProvider", function($routeProvider, $locationProvider) {
                    $locationProvider.html5Mode(true);

                    $routeProvider
                        .when("/", {
                            templateUrl : "index"
                        })

                        .when("/cor/list", {
                            templateUrl  : "cor/list",
                            controller   : "CorController",
                            controllerAs : "cor"
                        })

                        .when("/cor/add", {
                            templateUrl  : "cor/add",
                            controller   : "CorController",
                            controllerAs : "cor"
                        })

                        .when("/cor/:id", {
                            templateUrl  : "cor/edit",
                            controller   : "CorController",
                            controllerAs : "cor"

                            //templateUrl  : function(params) { return "cor/"+ params.id +"/edit"; },
                        })

                        .otherwise({ redirectTo : "/" });
                }]
            );
})(window.angular);