'use strict';
var app = angular.module('Fablab');
app.controller('EventTypeListController', function ($scope, $filter, $location,
        ngTableParams, EventTypeService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.eventTypes) {
                params.total($scope.eventTypes.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.eventTypes, params.filter()) : $scope.eventTypes;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateEventTypeList = function () {
        EventTypeService.list(function (data) {
            $scope.eventTypes = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (eventType) {
        EventTypeService.remove(eventType.id, function () {
            NotificationService.notify("success", "eventType.notification.removed");
            updateEventTypeList();
        });
    };
    $scope.softRemove = function (eventType) {
        EventTypeService.softRemove(eventType.id, function () {
            NotificationService.notify("success", "eventType.notification.removed");
            updateEventTypeList();
        });
    };
    updateEventTypeList();
});

