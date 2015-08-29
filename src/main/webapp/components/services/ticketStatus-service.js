(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('TicketStatusService', function ($log, $resource, $http) {
        var ticketStatus = $resource(App.API.TICKET_STATUS_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.TICKET_STATUS_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("TicketStatusService: remove...");
                ticketStatus.remove({id: id}, successFn);
            },
             softRemove: function (id, successFn) {
                $http.get(App.API.TICKET_STATUS_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("TicketStatusService: soft remove...");
            },
            save: function (ticketStatusParam, successFn, errorFn) {
                $log.debug("TicketStatusService: save...");
                var saved = ticketStatus.save(ticketStatusParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("TicketStatusService: get...");
                var ticketStatusRes = ticketStatus.get({id: id}, successFn);
                return ticketStatusRes;
            },
            findByLabel: function (label, successFn) {
                $http.get(App.API.TICKET_STATUS_API + "/findByLabel?label=" + label).success(successFn);
            }        
        };
    });
}());

