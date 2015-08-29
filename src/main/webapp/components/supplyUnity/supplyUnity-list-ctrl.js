'use strict';
var app = angular.module('Fablab');
app.controller('SupplyUnityListController', function ($scope, $filter, $location,
        ngTableParams, SupplyUnityService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.supplyUnities) {
                params.total($scope.supplyUnities.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.supplyUnities, params.filter()) : $scope.supplyUnities;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateSupplyUnityList = function () {
        SupplyUnityService.list(function (data) {
            $scope.supplyUnities = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (supplyUnity) {
        SupplyUnityService.remove(supplyUnity.id, function () {
            NotificationService.notify("success", "supplyUnity.notification.removed");
            updateSupplyUnityList();
        });
    };
    $scope.softRemove = function (supplyUnity) {
        SupplyUnityService.softRemove(supplyUnity.id, function () {
            NotificationService.notify("success", "supplyUnity.notification.removed");
            updateSupplyUnityList();
        });
    };
    updateSupplyUnityList();
});

