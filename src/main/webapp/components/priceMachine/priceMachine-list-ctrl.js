'use strict';
var app = angular.module('Fablab');
app.controller('PriceMachineListController', function ($scope, $filter, $location,
        ngTableParams, PriceMachineService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    machineTypeName:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.priceMachines) {
                params.total($scope.priceMachines.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.priceMachines, params.filter()) : $scope.priceMachines;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updatePriceMachineList = function () {
        PriceMachineService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].machineTypeName = ""; //initialization of new property 
                data[i].machineTypeName = data[i].machineType.name;  //set the data from nested obj into new property
                data[i].membershipTypeName = ""; //initialization of new property 
                data[i].membershipTypeName = data[i].membershipType.name;  //set the data from nested obj into new property
            }
            $scope.priceMachines = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (priceMachine) {
        PriceMachineService.remove(priceMachine.id, function () {
            NotificationService.notify("success", "priceMachine.notification.removed");
            updatePriceMachineList();
        });
    };
    $scope.softRemove = function (priceMachine) {
        PriceMachineService.softRemove(priceMachine.id, function () {
            NotificationService.notify("success", "priceMachine.notification.removed");
            updatePriceMachineList();
        });
    };
    updatePriceMachineList();
});

