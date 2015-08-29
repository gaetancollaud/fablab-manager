'use strict';
var app = angular.module('Fablab');
app.controller('ConfigurationListController', function ($scope, $filter, $location,
        ngTableParams, ConfigurationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    key: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.configurations) {
                params.total($scope.configurations.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.configurations, params.filter()) : $scope.configurations;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateConfigurationList = function () {
        ConfigurationService.list(function (data) {
            $scope.configurations = data;
            $scope.tableParams.reload();
        });
    };
    updateConfigurationList();
});

