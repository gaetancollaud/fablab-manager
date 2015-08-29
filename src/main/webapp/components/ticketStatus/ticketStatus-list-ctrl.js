'use strict';
var app = angular.module('Fablab');
app.controller('TicketStatusListController', function ($scope, $filter, $location,
        ngTableParams, TicketStatusService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.ticketStatus) {
                params.total($scope.ticketStatus.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.ticketStatus, params.filter()) : $scope.ticketStatus;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTicketStatusList = function () {
        TicketStatusService.list(function (data) {
            $scope.ticketStatus = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (ticketStatus) {
        TicketStatusService.remove(ticketStatus.id, function () {
            NotificationService.notify("success", "ticketStatus.notification.removed");
            updateTicketStatusList();
        });
    };
    $scope.softRemove = function (ticketstatus) {
        TicketStatusService.softRemove(ticketstatus.id, function () {
            NotificationService.notify("success", "ticketStatus.notification.removed");
            updateTicketStatusList();
        });
    };
    updateTicketStatusList();
});

