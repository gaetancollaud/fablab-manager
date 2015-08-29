'use strict';
var app = angular.module('Fablab');
app.controller('MembershipTypeListController', function ($scope, $filter, $location,
        ngTableParams, MembershipTypeService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.membershipTypes) {
                params.total($scope.membershipTypes.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.membershipTypes, params.filter()) : $scope.membershipTypes;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateMembershipTypeList = function () {
        MembershipTypeService.list(function (data) {
            $scope.membershipTypes = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (membershipType) {
        MembershipTypeService.remove(membershipType.id, function () {
            NotificationService.notify("success", "membershipType.notification.removed");
            updateMembershipTypeList();
        });
    };
    $scope.softRemove = function (membershipType) {
        MembershipTypeService.softRemove(membershipType.id, function () {
            NotificationService.notify("success", "membershipType.notification.removed");
            updateMembershipTypeList();
        });
    };
    updateMembershipTypeList();
});

