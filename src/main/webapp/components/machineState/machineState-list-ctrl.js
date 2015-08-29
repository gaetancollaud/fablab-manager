'use strict';
var app = angular.module('Fablab');
app.controller('MachineStateListController', function ($scope, $filter, $location,
        ngTableParams, MachineStateService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.machineStates) {
                params.total($scope.machineStates.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.machineStates, params.filter()) : $scope.machineStates;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMachineStateList = function () {
        MachineStateService.list(function (data) {
            $scope.machineStates = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (machineState) {
        MachineStateService.remove(machineState.id, function () {
            NotificationService.notify("success", "machineState.notification.removed");
            updateMachineStateList();
        });
    };
    $scope.softRemove = function (machineState) {
        MachineStateService.softRemove(machineState.id, function () {
            NotificationService.notify("success", "machineState.notification.removed");
            updateMachineStateList();
        });
    };
    updateMachineStateList();
});

