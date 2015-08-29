'use strict';
var app = angular.module('Fablab');
app.controller('UsageListController', function ($scope, $filter, $location, $rootScope,
        ngTableParams, UsageService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.currentUserId = $rootScope.connectedUser.user.id;
    $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    dateStart: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.usages) {
                params.total($scope.usages.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.usages, params.filter()) : $scope.usages;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateUsageList = function () {
        UsageService.list(function (data) {
            if ($rootScope.hasAnyRole('PAYMENT_MANAGE')) {
                for (var i = 0; i < data.length; i++) {
                    data[i].userName = ""; //initialization of new property 
                    data[i].userName = $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
                    data[i].cashierName = ""; //initialization of new property 
                    data[i].cashierName = $filter('prettyUser')(data[i].cashier);//set the data from nested obj into new property
                    data[i].machineName = ""; //initialization of new property 
                    data[i].machineName = data[i].machine.name;  //set the data from nested obj into new property
                    data[i].membershipTypeName = ""; //initialization of new property 
                    data[i].membershipTypeName = data[i].membershipType.name;  //set the data from nested obj into new property
                }
                $scope.usages = data;
            } else {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    if (data[i].user.id === $rootScope.connectedUser.user.id) {
                        data[i].userName = ""; //initialization of new property 
                        data[i].userName = $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
                        data[i].cashierName = ""; //initialization of new property 
                        data[i].cashierName = $filter('prettyUser')(data[i].cashier);//set the data from nested obj into new property
                        data[i].machineName = ""; //initialization of new property 
                        data[i].machineName = data[i].machine.name;  //set the data from nested obj into new property
                        data[i].membershipTypeName = ""; //initialization of new property 
                        data[i].membershipTypeName = data[i].membershipType.name;  //set the data from nested obj into new property
                        res.push(data[i]);
                    }
                }
                $scope.usages = res;
            }
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (usage) {
        UsageService.remove(usage.id, function () {
            NotificationService.notify("success", "usage.notification.removed");
            updateUsageList();
        });
    };
    $scope.softRemove = function (usage) {
        UsageService.softRemove(usage.id, function () {
            NotificationService.notify("success", "usage.notification.removed");
            updateUsageList();
        });
    };
    updateUsageList();
});

