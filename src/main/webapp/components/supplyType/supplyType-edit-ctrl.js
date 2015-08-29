'use strict';
var app = angular.module('Fablab');
app.controller('GlobalSupplyTypeEditController', function ($scope, $location,
    SupplyTypeService, NotificationService) {
    $scope.selected = {supplyType: undefined};
    $scope.loadSupplyType = function (id) {
        SupplyTypeService.get(id, function (data) {
            $scope.supplyType = data;
        });
    };
    
    SupplyTypeService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
    
    $scope.save = function () {
        var supplyTypeCurrent = angular.copy($scope.supplyType);
        SupplyTypeService.save(supplyTypeCurrent, function (data) {
            $scope.supplyType = data;
            NotificationService.notify("success", "supplyType.notification.saved");
            $location.path("supplyTypes");
        });
    };
}
);
app.controller('SupplyTypeNewController', function ($scope, $controller) {
    $controller('GlobalSupplyTypeEditController', {$scope: $scope});
    $scope.newSupplyType = true;
    $scope.supplyType = new Object();
}
);
    app.controller('SupplyTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalSupplyTypeEditController', {$scope: $scope});
    $scope.newSupplyType = false;
    $scope.loadSupplyType($routeParams.id);
}
);

