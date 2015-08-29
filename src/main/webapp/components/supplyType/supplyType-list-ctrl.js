'use strict';
var app = angular.module('Fablab');
app.controller('SupplyTypeListController', function ($scope, $filter, $location,
        ngTableParams, SupplyTypeService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.supplyTypes) {
                params.total($scope.supplyTypes.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.supplyTypes, params.filter()) : $scope.supplyTypes;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateSupplyTypeList = function () {
        SupplyTypeService.list(function (data) {
            $scope.supplyTypes = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (supplyType) {
        SupplyTypeService.remove(supplyType.id, function () {
            NotificationService.notify("success", "supplyType.notification.removed");
            updateSupplyTypeList();
        });
    };
    $scope.softRemove = function (supplyType) {
        SupplyTypeService.softRemove(supplyType.id, function () {
            NotificationService.notify("success", "supplyType.notification.removed");
            updateSupplyTypeList();
        });
    };
    updateSupplyTypeList();
});

