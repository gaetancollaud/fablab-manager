'use strict';
var app = angular.module('Fablab');
app.controller('GlobalSupplyUnityEditController', function ($scope, $location,
        SupplyUnityService, NotificationService) {
    $scope.selected = {supplyUnity: undefined};
    $scope.loadSupplyUnity = function (id) {
        SupplyUnityService.get(id, function (data) {
            $scope.supplyUnity = data;
        });
    };

    SupplyUnityService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function () {
        var supplyUnityCurrent = angular.copy($scope.supplyUnity);
        SupplyUnityService.save(supplyUnityCurrent, function (data) {
            $scope.supplyUnity = data;
            NotificationService.notify("success", "supplyUnity.notification.saved");
            $location.path("supplyUnities");
        });
    };
}
);
app.controller('SupplyUnityNewController', function ($scope, $controller) {
    $controller('GlobalSupplyUnityEditController', {$scope: $scope});
    $scope.newSupplyUnity = true;
    $scope.supplyUnity = {
        floating: false
    };
}
);
app.controller('SupplyUnityEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalSupplyUnityEditController', {$scope: $scope});
    $scope.newSupplyUnity = false;
    $scope.loadSupplyUnity($routeParams.id);
}
);

