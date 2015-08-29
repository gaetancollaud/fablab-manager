(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('EventTypeService', function ($log, $resource, $http) {
        var eventType = $resource(App.API.EVENT_TYPE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.EVENT_TYPE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("EventTypeService: remove...");
                eventType.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.EVENT_TYPE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("EventTypeService: soft remove...");
            },
            save: function (eventTypeParam, successFn, errorFn) {
                $log.debug("EventTypeService: save...");
                var saved = eventType.save(eventTypeParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("EventTypeService: get...");
                var eventTypeRes = eventType.get({id: id}, successFn);
                return eventTypeRes;
            }
        };
    });
}());

