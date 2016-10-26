(function(angular) {
    "use strict";

    var app = angular.module("GTF.Controllers", []);

    //Cor
    app.controller("CorController", function($scope, $state, $stateParams, CorService) {
        $scope.cores = CorService.query();
        $scope.cor = $stateParams.id === undefined ? {} : CorService.get({ id : $stateParams.id });

        //Erros de valição
        $scope.errors = [];

        $scope.updateColor = function() {
            $scope.cor.$update(function() {
                $state.go("cor");
            }, function(response) {
                $scope.errors = response.data.errors;
            });
        };
    });
})(window.angular);