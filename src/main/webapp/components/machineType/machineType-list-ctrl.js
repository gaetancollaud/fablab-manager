'use strict';
var app = angular.module('Fablab');
app.controller('MachineTypeListController', function ($scope, $filter, $location,
        ngTableParams, MachineTypeService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.machineTypes) {
                params.total($scope.machineTypes.length);
                $location.search(params.url()); // put params in url
                var filteredData = params.filter() ? $filter('filter')($scope.machineTypes, params.filter()) : $scope.machineTypes;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMachineTypeList = function () {
        MachineTypeService.list(function (data) {
            $scope.machineTypes = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (machineType) {
        MachineTypeService.remove(machineType.id, function () {
            NotificationService.notify("success", "machineType.notification.removed");
            updateMachineTypeList();
        });
    };
    $scope.softRemove = function (machineType) {
        MachineTypeService.softRemove(machineType.id, function () {
            NotificationService.notify("success", "machineType.notification.removed");
            updateMachineTypeList();
        });
    };
    updateMachineTypeList();
});

