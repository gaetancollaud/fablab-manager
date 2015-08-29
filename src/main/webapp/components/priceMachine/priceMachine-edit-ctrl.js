'use strict';
var app = angular.module('Fablab');
app.controller('GlobalPriceMachineEditController', function ($scope, $location,
    PriceMachineService, NotificationService, StaticDataService) {
    $scope.selected = {priceMachine: undefined};
    $scope.loadPriceMachine = function (id) {
        PriceMachineService.get(id, function (data) {
            $scope.priceMachine = data;
        });
    };
    $scope.save = function () {
        var priceMachineCurrent = angular.copy($scope.priceMachine);
        PriceMachineService.save(priceMachineCurrent, function (data) {
            $scope.priceMachine = data;
            NotificationService.notify("success", "priceMachine.notification.saved");
            $location.path("priceMachines");
        });
    };
    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });
    StaticDataService.loadMemberShipTypes(function (data) {
        $scope.membershipTypeList = data;
    });
}
);
app.controller('PriceMachineNewController', function ($scope, $controller) {
    $controller('GlobalPriceMachineEditController', {$scope: $scope});
    $scope.newPriceMachine = true;
    $scope.priceMachine = new Object();
}
);
    app.controller('PriceMachineEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalPriceMachineEditController', {$scope: $scope});
    $scope.newPriceMachine = false;
    $scope.loadPriceMachine($routeParams.id);
}
);

