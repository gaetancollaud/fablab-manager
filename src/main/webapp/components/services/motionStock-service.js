(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('MotionStockService', function ($log, $resource, $http) {
        var motionStock = $resource(App.API.MOTION_STOCK_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.MOTION_STOCK_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("MotionStockService: remove...");
                motionStock.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.MOTION_STOCK_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("MotionStockService: soft remove...");
            },
            save: function (motionStockParam, successFn, errorFn) {
                $log.debug("MotionStockService: save...");
                var saved = motionStock.save(motionStockParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("MotionStockService: get...");
                var motionStockRes = motionStock.get({id: id}, successFn);
                return motionStockRes;
            }, 
            getById: function (id, successFn) {
                $http.get(App.API.MOTION_STOCK_API + "/getById?id=" + id).success(successFn);
            }
        };
    });
}());

