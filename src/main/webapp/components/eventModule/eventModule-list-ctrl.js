'use strict';
var app = angular.module('Fablab');
app.controller('EventModuleListController', function ($scope, $filter, $location,$rootScope,
        ngTableParams, EventModuleService, NotificationService) {
    $scope.showRole = $rootScope.hasAnyRole('EVENT_MANAGE');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.eventModules) {
                params.total($scope.eventModules.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.eventModules, params.filter()) : $scope.eventModules;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateEventModuleList = function () {
        EventModuleService.list(function (data) {
            $scope.eventModules = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (eventModule) {
        EventModuleService.remove(eventModule.id, function () {
            NotificationService.notify("success", "eventModule.notification.removed");
            updateEventModuleList();
        });
    };
    $scope.softRemove = function (eventModule) {
        EventModuleService.softRemove(eventModule.id, function () {
            NotificationService.notify("success", "eventModule.notification.removed");
            updateEventModuleList();
        });
    };
    updateEventModuleList();
});

