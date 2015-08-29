'use strict';
var app = angular.module('Fablab');
app.controller('EventPersonListController', function ($scope, $filter, $location,
        ngTableParams, EventPersonService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    lastname:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.eventPersons) {
                params.total($scope.eventPersons.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.eventPersons, params.filter()) : $scope.eventPersons;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateEventPersonList = function () {
        EventPersonService.list(function (data) {
            $scope.eventPersons = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (eventPerson) {
        EventPersonService.remove(eventPerson.id, function () {
            NotificationService.notify("success", "eventPerson.notification.removed");
            updateEventPersonList();
        });
    };
    $scope.softRemove = function (eventPerson) {
        EventPersonService.softRemove(eventPerson.id, function () {
            NotificationService.notify("success", "eventPerson.notification.removed");
            updateEventPersonList();
        });
    };
    updateEventPersonList();
});

