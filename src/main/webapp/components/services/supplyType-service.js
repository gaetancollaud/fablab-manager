(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('SupplyTypeService', function ($log, $resource, $http) {
        var supplyType = $resource(App.API.SUPPLY_TYPE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.SUPPLY_TYPE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("SupplyTypeService: remove...");
                supplyType.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.SUPPLY_TYPE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("SupplyTypeService: soft remove...");
            },
            save: function (supplyTypeParam, successFn, errorFn) {
                $log.debug("SupplyTypeService: save...");
                var saved = supplyType.save(supplyTypeParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("SupplyTypeService: get...");
                var supplyTypeRes = supplyType.get({id: id}, successFn);
                return supplyTypeRes;
            }
        };
    });
}());

