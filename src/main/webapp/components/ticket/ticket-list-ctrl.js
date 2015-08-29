'use strict';
var app = angular.module('Fablab');
app.controller('TicketListController', function ($scope, $filter, $location,
        ngTableParams, TicketService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    creationDate: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.tickets) {
                params.total($scope.tickets.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.tickets, params.filter()) : $scope.tickets;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTicketList = function () {
        TicketService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].machineName = ""; //initialization of new property 
                data[i].machineName = data[i].machine.name;  //set the data from nested obj into new property
                data[i].statusLabel = ""; //initialization of new property 
                data[i].statusLabel = data[i].status.label;  //set the data from nested obj into new property
            }
            $scope.tickets = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (ticket) {
        TicketService.remove(ticket.id, function () {
            NotificationService.notify("success", "ticket.notification.removed");
            updateTicketList();
        });
    };
    $scope.softRemove = function (ticket) {
        TicketService.softRemove(ticket.id, function () {
            NotificationService.notify("success", "ticket.notification.removed");
            updateTicketList();
        });
    };
    updateTicketList();
});

