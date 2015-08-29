(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('UsageService', function ($log, $resource, $http) {
        var usage = $resource(App.API.USAGE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.USAGE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("UsageService: remove...");
                usage.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.USAGE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("UsageService: soft remove...");
            },
            save: function (usageParam, successFn, errorFn) {
                $log.debug("UsageService: save...");
                var saved = usage.save(usageParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("UsageService: get...");
                var usageRes = usage.get({id: id}, successFn);
                return usageRes;
            }
        };
    });
}());

