(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('SupplyService', function ($log, $resource, $http) {
        var supply = $resource(App.API.SUPPLY_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.SUPPLY_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("SupplyService: remove...");
                supply.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.SUPPLY_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("SupplyService: soft remove...");
            },
            save: function (supplyParam, successFn, errorFn) {
                $log.debug("SupplyService: save...");
                var saved = supply.save(supplyParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("SupplyService: get...");
                var supplyRes = supply.get({id: id}, successFn);
                return supplyRes;
            },
            stock: function (successFn) {
                $http.get(App.API.SUPPLY_API + "/stock").success(successFn);
            },
            addQuantity : function (id, quantity, successFn) {
                $http.get(App.API.SUPPLY_API + "/addQuantity?id=" + id + "&quantity=" + quantity).success(successFn);
            },
            getById: function (id, successFn) {
                $http.get(App.API.SUPPLY_API + "/getById?id=" + id).success(successFn);
            }
        };
    });
}());

