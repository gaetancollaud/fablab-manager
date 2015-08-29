'use strict';
var app = angular.module('Fablab');
app.controller('MotionStockListController', function ($scope, $filter, $location,
        ngTableParams, MotionStockService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    motionDate: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.motionStocks) {
                params.total($scope.motionStocks.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.motionStocks, params.filter()) : $scope.motionStocks;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMotionStockList = function () {
        MotionStockService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].supplyLabel = ""; //initialization of new property 
                data[i].supplyLabel = data[i].supply.label + data[i].supply.code;  //set the data from nested obj into new property
                data[i].userName = ""; //initialization of new property 
                data[i].userName = $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
            }
            $scope.motionStocks = data;
            $scope.tableParams.reload();
        });
    };
    updateMotionStockList();
});

