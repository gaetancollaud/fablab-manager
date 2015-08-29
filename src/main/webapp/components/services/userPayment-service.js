(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('UserPaymentService', function ($log, $resource, $http) {
        var userPayment = $resource(App.API.USER_PAYMENT_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.USER_PAYMENT_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("UserPaymentService: remove...");
                userPayment.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.USER_PAYMENT_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("UserPaymentService: soft remove...");
            },
            save: function (userPaymentParam, successFn, errorFn) {
                $log.debug("UserPaymentService: save...");
                var saved = userPayment.save(userPaymentParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("UserPaymentService: get...");
                var userPaymentRes = userPayment.get({id: id}, successFn);
                return userPaymentRes;
            }
        };
    });
}());

