'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMachineEditController', function ($scope, $location, $routeParams,
        MachineService, NotificationService, StaticDataService) {
    $scope.selected = {machine: undefined};
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.machineId = $routeParams.id;
    $scope.loadMachine = function (id) {
        MachineService.get(id, function (data) {
            $scope.machine = data;
            if ($scope.machine.acquisitionDate) {
                $scope.machine.acquisitionDate = new Date($scope.machine.acquisitionDate);
            }
        });
    };

    MachineService.list(function (mt) {
        var res = [];
        for (var i = 0; i < mt.length; i++) {
            res.push(mt[i].code.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function () {
        var machineCurrent = angular.copy($scope.machine);
        MachineService.save(machineCurrent, function (data) {
            $scope.machine = data;
            NotificationService.notify("success", "machine.notification.saved");
            $location.path("machines");
        });
    };
    $scope.minDate = new Date();
    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);
    $scope.events =
            [
                {
                    date: tomorrow,
                    status: 'full'
                },
                {
                    date: afterTomorrow,
                    status: 'partially'
                }
            ];

    $scope.getDayClass = function (date, mode) {
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    };

    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });
    StaticDataService.loadMachineStatus(function (data) {
        $scope.machineStatusList = data;
    });
    StaticDataService.loadMachineStates(function (data) {
        $scope.machineStateList = data;
    });
}
);
app.controller('MachineNewController', function ($scope, $controller) {
    $controller('GlobalMachineEditController', {$scope: $scope});
    $scope.newMachine = true;
    $scope.machine = new Object();
}
);
app.controller('MachineEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMachineEditController', {$scope: $scope});
    $scope.newMachine = false;
    $scope.loadMachine($routeParams.id);
}
);

