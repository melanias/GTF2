(function(angular) {
    "use strict";

    var service = angular.module("GTF.Services", []);

    //Cor
    service.factory("CorService", function($resource) {
        return $resource("cor/:id", { id: "@id" }, {
            "update" : {
                method: "PUT"
            }
        });
    });
})(window.angular);