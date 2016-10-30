(function(angular) {
    "use strict";

    var app = angular.module("GTF.Controllers", []);

    //Cor
    app.controller("CorController", function($scope, $state, $stateParams,$mdToast, CorService) {
        $scope.cores = CorService.query();
        $scope.cor = $stateParams.id === undefined ? new CorService() : CorService.get({ id: $stateParams.id });

        //Erros de validação
        $scope.errors = [];

        $scope.addColor = function() {
            $scope.cor.$save(function() {
                $state.go("^.list");
                $mdToast.show(
                    $mdToast.simple()
                            .textContent("Cor cadastrada com sucesso.")
                            .position("bottom right")
                            .hideDelay(3000)
                );
            }, function(response) {
                //$scope.errors = response.data.errors;
                var errors = [];

                angular.forEach(response.data.errors, function(e, k) {
                    this.push(e.message);
                }, errors);

                //alert(errors.join("\n"));

                $mdToast.show(
                    $mdToast.simple()
                            .textContent(errors.join(""))
                            .position("bottom right")
                            .hideDelay(3000)
                );
            });
        };

        $scope.updateColor = function() {
            $scope.cor.$update(function() {
                $state.go("^.list");
                //$state.go("editarCor", { id: $scope.cor.id });
            }, function(response) {
                //$scope.errors = response.data.errors;
                var errors = [];

                angular.forEach(response.data.errors, function(e, k) {
                    this.push(e.message);
                }, errors);

                //alert(errors.join("\n"));

                $mdToast.show(
                    $mdToast.simple()
                            .textContent(errors.join(""))
                            .position("bottom right")
                            .hideDelay(3000)
                );
            });
        };
    });
})(window.angular);