(function(angular) {
    "use strict";

    var app = angular.module("GTF.Controller", []);

    //Cor
    app.controller("CorController", ["$routeParams" , "$scope", "CorService", function($routeParams, $scope, CorService) {
            var self        = this;
                self.cor    = $routeParams.id === undefined ? {} : CorService.get({ id : $routeParams.id });
                self.cores  = CorService.query();

            self.update = function() {
                CorService.update({ id : self.cor.id }, angular.toJson(self.cor));
            };
    }]);
})(window.angular);
