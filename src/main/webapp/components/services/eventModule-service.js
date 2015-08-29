(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('EventModuleService', function ($log, $resource, $http) {
        var eventModule = $resource(App.API.EVENT_MODULE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.EVENT_MODULE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("EventModuleService: remove...");
                eventModule.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.EVENT_MODULE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("EventModuleService: soft remove...");
            },
            save: function (eventModuleParam, successFn, errorFn) {
                $log.debug("EventModuleService: save...");
                var saved = eventModule.save(eventModuleParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("EventModuleService: get...");
                var eventModuleRes = eventModule.get({id: id}, successFn);
                return eventModuleRes;
            }, 
            getId: function(name, successFn) {
                $http.get(App.API.EVENT_MODULE_API + "/getId?name=" + name.latinise()).success(successFn);
            }
        };
    });
}());

