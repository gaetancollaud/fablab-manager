(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('TicketService', function ($log, $resource, $http) {
        var ticket = $resource(App.API.TICKET_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.TICKET_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("TicketService: remove...");
                ticket.remove({id: id}, successFn);
            },
            save: function (ticketParam, successFn, errorFn) {
                $log.debug("TicketService: save...");
                var saved = ticket.save(ticketParam, successFn, errorFn);
                return saved;
            },
             softRemove: function (id, successFn) {
                $http.get(App.API.TICKET_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("TicketService: soft remove...");
            },
            get: function (id, successFn) {
                $log.debug("TicketService: get...");
                var ticketRes = ticket.get({id: id}, successFn);
                return ticketRes;
            },
            listByMachine: function (id, successFn) {
                $http.get(App.API.TICKET_API + "/listByMachine?id=" + id).success(successFn);
            }
        };
    });
}());

