(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('SupplyUnityService', function ($log, $resource, $http) {
        var supplyUnity = $resource(App.API.SUPPLY_UNITY_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.SUPPLY_UNITY_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("SupplyUnityService: remove...");
                supplyUnity.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.SUPPLY_UNITY_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("SupplyUnityService: soft remove...");
            },
            save: function (supplyUnityParam, successFn, errorFn) {
                $log.debug("SupplyUnityService: save...");
                var saved = supplyUnity.save(supplyUnityParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("SupplyUnityService: get...");
                var supplyUnityRes = supplyUnity.get({id: id}, successFn);
                return supplyUnityRes;
            }
        };
    });
}());

