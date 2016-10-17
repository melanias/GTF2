(function() {
    "use strict";

    angular.module("cor")
           .controller("CorController", CorController);

    CorController.$inject = ["$window", "CorService"];

    function CorController($window, CorService) {
        var cc = this;
        cc.cor = {};
        cc.cores = [];

        var init = function() {
            CorService.getCores().success(function(data) {
                cc.cores = data;
            });
        }();
    }
})();