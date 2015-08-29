'use strict';
var app = angular.module('Fablab');
app.controller('UserPaymentListController', function ($scope, $filter, $location,$rootScope,
        ngTableParams, UserPaymentService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    datePayment: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.userPayments) {
                params.total($scope.userPayments.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.userPayments, params.filter()) : $scope.userPayments;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateUserPaymentList = function () {
        UserPaymentService.list(function (data) {
            if ($rootScope.hasAnyRole('PAYMENT_MANAGE')) {
                for (var i = 0; i < data.length; i++) {
                    data[i].userFirstname = ""; //initialization of new property 
                    data[i].userFirstname = $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
                }
                $scope.userPayments = data;
            } else {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    if (data[i].user.id === $rootScope.connectedUser.user.id) {
                        data[i].userFirstname = ""; //initialization of new property 
                        data[i].userFirstname = $filter('prettyUser')(data[i].user);  //set the data from nested obj into new property
                        res.push(data[i]);
                    }
                }
                $scope.userPayments = res;
            }
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (userPayment) {
        UserPaymentService.remove(userPayment.id, function () {
            NotificationService.notify("success", "userPayment.notification.removed");
            updateUserPaymentList();
        });
    };
    $scope.softRemove = function (userPayment) {
        UserPaymentService.softRemove(userPayment.id, function () {
            NotificationService.notify("success", "userPayment.notification.removed");
            updateUserPaymentList();
        });
    };
    updateUserPaymentList();
});

