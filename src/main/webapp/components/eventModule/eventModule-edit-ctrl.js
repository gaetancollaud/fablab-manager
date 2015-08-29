'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventModuleEditController', function ($scope, $location, $rootScope,
        EventModuleService, NotificationService, StaticDataService) {
    $scope.selected = {eventModule: undefined};
    $scope.showRole = $rootScope.hasAnyRole('EVENT_MANAGE');
    $scope.loadEventModule = function (id) {
        EventModuleService.get(id, function (data) {
            $scope.eventModule = data;
            setList();
        });
    };
    $scope.save = function () {
        if ($scope.newEventModule) {
            var evmCurrent = angular.copy($scope.eventModule);
            EventModuleService.save(evmCurrent, function (data) {
                $scope.eventModule = data;
                NotificationService.notify("success", "training.notification.saved");
                EventModuleService.getId(data.name, function (withId) {
                    $location.path("eventModules/eventModule-edit/" + withId.id);
                });
            });
        } else {
            $scope.eventModule.prerequisites = $scope.assignedPrerequisites;
            var evmCurrent = angular.copy($scope.eventModule);
            EventModuleService.save(evmCurrent, function (data) {
                $scope.eventModule = data;
                NotificationService.notify("success", "training.notification.saved");
                $location.path("eventModules");
            });
        }
    };
    EventModuleService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].name.toUpperCase());
        }
        $scope.existingValues = res;
    });
    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });

    $scope.ereaseType = function () {
        $scope.eventModule.machineType = null;
    };

    var setList = function () {
        EventModuleService.list(function (evm) {
            if ($scope.eventModule) {
                var availableEvms = [];
                for(var i = 0; i < evm.length; i++){
                    if(evm[i].id !== $scope.eventModule.id){
                        availableEvms.push(evm[i]);
                    }
                }
                $scope.availablePrerequisites = availableEvms;
                $scope.assignedPrerequisites = $scope.eventModule.prerequisites;
            }
        });
    };

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };
}
);
app.controller('EventModuleNewController', function ($scope, $controller) {
    $controller('GlobalEventModuleEditController', {$scope: $scope});
    $scope.newEventModule = true;
    $scope.eventModule = new Object();
}
);
app.controller('EventModuleEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventModuleEditController', {$scope: $scope});
    $scope.newEventModule = false;
    $scope.loadEventModule($routeParams.id);
}
);

