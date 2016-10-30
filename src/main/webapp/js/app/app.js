(function(angular) {
    "use strict";

    angular.module("GTF", ["ui.router", "ngAnimate", "ngResource", "ngMaterial", "GTF.Services", "GTF.Controllers"]);

    angular.module("GTF").config(["$stateProvider", "$mdThemingProvider",
        function($stateProvider, $mdThemingProvider) {
            $stateProvider
                .state("index", {
                    url: "/",
                    templateUrl: "index",
                    data: {
                        title: "Página principal"
                    }
                })

                .state("cor", {
                    url: "/cor",
                    abstract: true,
                    templateUrl: "cor/main",
                    controller: "CorController",
                    data: {
                        title: "Cor"
                    }
                })
                .state("cor.list", {
                    url: "/list",
                    templateUrl: "cor/list",
                    controller: "CorController",
                    data: {
                        subTitle: "Lista de cores"
                    }
                })
                .state("cor.add", {
                    url: "/add",
                    templateUrl: "cor/add",
                    controller: "CorController",
                    data: {
                        subTitle: "Cadastrar cor"
                    }
                })
                .state("cor.edit", {
                    url: "/:id/edit",
                    templateUrl: "cor/edit",
                    controller: "CorController",
                    data: {
                        subTitle: "Editar cor"
                    }
                });

            //Tema principal
            $mdThemingProvider.theme("gtf-theme").primaryPalette("blue-grey").accentPalette("light-blue").warnPalette("red");
            $mdThemingProvider.setDefaultTheme("gtf-theme");
        }
    ]).run(function($state, $rootScope) {
        $state.go("index");

        $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
            $rootScope.title = toState.data.title;

            if (toState.name !== "index") {
                $rootScope.subTitle = toState.data.subTitle;
            }
        });
    });
})(window.angular);