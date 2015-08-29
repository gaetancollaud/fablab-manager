(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('ConfigurationService', function ($log, $resource, $http) {
        var configuration = $resource(App.API.CONFIGURATION_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.CONFIGURATION_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("ConfigurationService: remove...");
                configuration.remove({id: id}, successFn);
            },
            save: function (configurationParam, successFn, errorFn) {
                $log.debug("ConfigurationService: save...");
                var saved = configuration.save(configurationParam, successFn, errorFn);
                return saved;
            },
            findByKey: function(key, successFn){
              $http.get(App.API.CONFIGURATION_API + "/findByKey?key=" + key).success(successFn);  
            },
            get: function (id, successFn) {
                $log.debug("ConfigurationService: get...");
                var configurationRes = configuration.get({id: id}, successFn);
                return configurationRes;
            }
        };
    });
}());

