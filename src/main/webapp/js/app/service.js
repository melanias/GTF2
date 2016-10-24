(function(angular) {
    "use strict";

    var service = angular.module("GTF.Service", ["ngResource"]);

    //Cor
    service.factory("CorService", function($resource) {
        return $resource("cor/:id", {}, {
            "update" : { method: "PUT" }
        });
    });
})(window.angular);