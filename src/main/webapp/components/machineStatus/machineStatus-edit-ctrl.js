'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMachineStatusEditController', function ($scope, $location,
        MachineStatusService, NotificationService) {
    $scope.selected = {machineStatus: undefined};
    $scope.loadMachineStatus = function (id) {
        MachineStatusService.get(id, function (data) {
            $scope.machineStatus = data;
        });
    };

    MachineStatusService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function () {
        var machineStatusCurrent = angular.copy($scope.machineStatus);
        MachineStatusService.save(machineStatusCurrent, function (data) {
            $scope.machineStatus = data;
            NotificationService.notify("success", "machineStatus.notification.saved");
            $location.path("machineStatus");
        });
    };

}
);
app.controller('MachineStatusNewController', function ($scope, $controller) {
    $controller('GlobalMachineStatusEditController', {$scope: $scope});
    $scope.newMachineStatus = true;
    $scope.machineStatus = new Object();
}
);
app.controller('MachineStatusEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMachineStatusEditController', {$scope: $scope});
    $scope.newMachineStatus = false;
    $scope.loadMachineStatus($routeParams.id);
}
);

