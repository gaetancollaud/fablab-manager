(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('ReservationService', function ($log, $resource, $http) {

        var reservation = $resource(App.API.RESERVATION_API + "/:id", {id: '@id'});

        return {
            search: function (criteria, successFn) {
                $http(
                        {
                            method: 'POST',
                            url: App.API.RESERVATION_API + "/search",
                            data: criteria
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("ReservationService: remove...");
                reservation.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.RESERVATION_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("ReservationService: soft remove...");
            },
            save: function (user, successFn, errorFn) {
                $log.debug("ReservationService: save...");
                var saved = reservation.save(user, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("ReservationService: get...");
                var prj = reservation.get({id: id}, successFn);
                return prj;
            },
            listByMachine: function (id, successFn) {
                $http.get(App.API.REVISION_API + "/listByMachine?id=" + id).success(successFn);
            }
        };
    });

}());