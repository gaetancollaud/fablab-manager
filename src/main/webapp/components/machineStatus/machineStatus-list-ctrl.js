'use strict';
var app = angular.module('Fablab');
app.controller('MachineStatusListController', function ($scope, $filter, $location,
        ngTableParams, MachineStatusService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.machineStatus) {
                params.total($scope.machineStatus.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.machineStatus, params.filter()) : $scope.machineStatus;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMachineStatusList = function () {
        MachineStatusService.list(function (data) {
            $scope.machineStatus = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (machineStatus) {
        MachineStatusService.remove(machineStatus.id, function () {
            NotificationService.notify("success", "machineStatus.notification.removed");
            updateMachineStatusList();
        });
    };
    $scope.softRemove = function (machineStatus) {
        MachineStatusService.softRemove(machineStatus.id, function () {
            NotificationService.notify("success", "machineStatus.notification.removed");
            updateMachineStatusList();
        });
    };
    updateMachineStatusList();
});

