(function() {
    "use strict";

    angular.module("cor")
           .controller("CorController", CorController);

    CorController.$inject = ["corService"];

    function CorController(corService)
    {
        var cc       = this;
            cc.cor   = {};
            cc.cores = [];
            cc.url   = ROOTURL;

        var init = function() {
            corService.query(function(data) {
                cc.cores = data;
            });
        }();
    }
})();