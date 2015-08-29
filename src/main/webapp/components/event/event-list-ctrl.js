'use strict';
var app = angular.module('Fablab');
app.controller('EventListController', function ($scope, $filter, $location, $rootScope,
        ngTableParams, EventService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('EVENT_MANAGE');
    $scope.btnClone = $filter('translate')('event.btnTitle');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    dateStart: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.events) {
                params.total($scope.events.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.events, params.filter()) : $scope.events;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateEventList = function () {
        EventService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].eventTypeLabel = ""; //initialization of new property 
                data[i].eventTypeLabel = data[i].eventType.label;  //set the data from nested obj into new property
            }
            $scope.events = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (event) {
        EventService.remove(event.id, function () {
            NotificationService.notify("success", "event.notification.removed");
            updateEventList();
        });
    };
    $scope.softRemove = function (event) {
        EventService.softRemove(event.id, function () {
            NotificationService.notify("success", "event.notification.removed");
            updateEventList();
        });
    };
    updateEventList();

    $scope.clone = function (eventId) {
        console.log("here");
        EventService.get(eventId, function (data) {
            var event = angular.copy(data);
            //obligatory for adding. Save instead
            event.id = null;
            event.dateStart = new Date();
            event.dateEnd = new Date();
            event.timeStart = new Date();
            event.timeEnd = new Date();
            event.title = "Cloné de " + data.title;
            event.organizers = null;
            event.participants = null;
            event.modules = null;
            if (!data.supervisor.active || !data.eventType.active) {
                NotificationService.notify("error", "event.notification.clonedFailed");
            } else {
                EventService.save(event, function (newEvent) {
                    event = newEvent;
                    NotificationService.notify("success", "event.notification.cloned");
                    updateEventList();
                });
            }
        });
    };
});

