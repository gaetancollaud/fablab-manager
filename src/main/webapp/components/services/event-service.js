(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('EventService', function ($log, $resource, $http) {
        var event = $resource(App.API.EVENT_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.EVENT_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("EventService: remove...");
                event.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.EVENT_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("EventService: soft remove...");
            },
            save: function (eventParam, successFn, errorFn) {
                $log.debug("EventService: save...");
                var saved = event.save(eventParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("EventService: get...");
                var eventRes = event.get({id: id}, successFn);
                return eventRes;
            },
            getId: function (title, successFn) {
                $http.get(App.API.EVENT_API + "/getId?title=" + title.latinise()).success(successFn);
            }
        };
    });
}());

