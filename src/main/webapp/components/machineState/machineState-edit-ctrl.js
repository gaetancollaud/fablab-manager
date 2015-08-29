'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMachineStateEditController', function ($scope, $location,
        MachineStateService, NotificationService) {
    $scope.selected = {machineState: undefined};
    $scope.loadMachineState = function (id) {
        MachineStateService.get(id, function (data) {
            $scope.machineState = data;
        });
    };
    $scope.save = function () {
        var machineStateCurrent = angular.copy($scope.machineState);
        MachineStateService.save(machineStateCurrent, function (data) {
            $scope.machineState = data;
            NotificationService.notify("success", "machineState.notification.saved");
            $location.path("machineStates");
        });
    };

    MachineStateService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
}
);
app.controller('MachineStateNewController', function ($scope, $controller) {
    $controller('GlobalMachineStateEditController', {$scope: $scope});
    $scope.newMachineState = true;
    $scope.machineState = new Object();
}
);
app.controller('MachineStateEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMachineStateEditController', {$scope: $scope});
    $scope.newMachineState = false;
    $scope.loadMachineState($routeParams.id);
}
);

