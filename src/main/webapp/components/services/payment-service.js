(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('PaymentService', function ($log, $resource, $http) {
        return {
            history: function (userId, successFn) {
                $http({
                    method: 'GET',
                    url: App.API.PAYMENT_API + '/' + userId + '/history'
                }).success(successFn);
            },
            machinePrice: function (successFn) {
                $http({
                    method: 'GET',
                    url: App.API.PAYMENT_API + '/machine_price'
                }).success(successFn);
            },
            addUsage: function (usage, successFn) {
                $http({
                    method: 'POST',
                    data: usage,
                    url: App.API.PAYMENT_API + '/add_usage'
                }).success(successFn);
            },
            addPayment: function (payment, successFn) {
                $http({
                    method: 'POST',
                    data: payment,
                    url: App.API.PAYMENT_API + '/add_payment'
                }).success(successFn);
            },
            removeHistory: function (history, successFn) {
                $http({
                    method: 'POST',
                    data: history,
                    url: App.API.PAYMENT_API + '/delete_history'
                }).success(successFn);
            },
            subscriptionConfirmUser: function (userId, successFn) {
                $http({
                    method: 'GET',
                    url: App.API.PAYMENT_API + '/subscription/confirm/' + userId
                }).success(successFn);
            },
            subscriptionConfirmCurrentUser: function (successFn) {
                $http({
                    method: 'GET',
                    url: App.API.PAYMENT_API + '/subscription/confirm'
                }).success(successFn);
            },
            canUse: function (machineId, userId, successFn) {
                $http.get(App.API.USER_API + "/canUse?machineId=" + machineId +
                        "/&userId=" + userId).success(successFn);
            }, 
            getPrice: function (machineTypeId, userId, successFn) {
                $http.get(App.API.PAYMENT_API + "/getPrice?machineTypeId=" + machineTypeId +
                        "&userId=" + userId).success(successFn);
            }
        };
    });

}());