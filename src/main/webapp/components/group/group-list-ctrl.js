'use strict';
var app = angular.module('Fablab');
app.controller('GroupListController', function ($scope, $filter, $location,
        ngTableParams, GroupService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    technicalname:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.groups) {
                params.total($scope.groups.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.groups, params.filter()) : $scope.groups;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateGroupList = function () {
        GroupService.list(function (data) {
            $scope.groups = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (group) {
        GroupService.remove(group.id, function () {
            NotificationService.notify("success", "group.notification.removed");
            updateGroupList();
        });
    };
    $scope.softRemove = function (group) {
        GroupService.softRemove(group.id, function () {
            NotificationService.notify("success", "group.notification.removed");
            updateGroupList();
        });
    };
    updateGroupList();
});

