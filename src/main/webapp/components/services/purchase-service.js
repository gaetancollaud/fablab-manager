(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('PurchaseService', function ($log, $resource, $http) {
        var purchase = $resource(App.API.PURCHASE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.PURCHASE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("PurchaseService: remove...");
                purchase.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.PURCHASE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("PurchaseService: soft remove...");
            },
            save: function (purchaseParam, successFn, errorFn) {
                $log.debug("PurchaseService: save...");
                var saved = purchase.save(purchaseParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("PurchaseService: get...");
                var purchaseRes = purchase.get({id: id}, successFn);
                return purchaseRes;
            }
        };
    });
}());

