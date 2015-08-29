(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('EventPersonService', function ($log, $resource, $http) {
        var eventPerson = $resource(App.API.EVENT_PERSON_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.EVENT_PERSON_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("EventPersonService: remove...");
                eventPerson.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.EVENT_PERSON_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("EventPersonService: soft remove...");
            },
            save: function (eventPersonParam, successFn, errorFn) {
                $log.debug("EventPersonService: save...");
                var saved = eventPerson.save(eventPersonParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("EventPersonService: get...");
                var eventPersonRes = eventPerson.get({id: id}, successFn);
                return eventPersonRes;
            },
            getId: function (email, successFn) {
                $http.get(App.API.EVENT_PERSON_API + "/getId?email=" + email.latinise()).success(successFn);
            },
            failedModules: function (eventPersonId, eventModuleId, successFn) {
                $http.get(App.API.EVENT_PERSON_API + "/failedModules?eventPersonId=" + eventPersonId + "&eventModuleId=" + eventModuleId).success(successFn);
            }
        };
    });
}());

