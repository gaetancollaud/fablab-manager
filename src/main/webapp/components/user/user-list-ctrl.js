var app = angular.module('Fablab');
app.controller('UserListController', function ($scope, $filter, $location,
        ngTableParams, UserService, NotificationService) {

    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    lastname: 'asc',
                    firstname: 'asc'
                }
            }, $location.search()), {
        //total: $scope.users.length, // length of data
        getData: function ($defer, params) {
            if ($scope.users) {
                params.total($scope.users.length);

                $location.search(params.url()); // put params in url

                var filteredData = params.filter() ? $filter('filter')($scope.users, params.filter()) : $scope.users;

                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });

    var updateUserList = function () {
        UserService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].membershipTypeName = ""; //initialization of new property 
                data[i].membershipTypeName = data[i].membershipType.name;  //set the data from nested obj into new property
            }
            $scope.users = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (user) {
        UserService.remove(user.id, function () {
            NotificationService.notify("success", "user.notification.removed");
            updateUserList();
        });
    };
    $scope.softRemove = function (user) {
        UserService.softRemove(user.id, function () {
            NotificationService.notify("success", "user.notification.removed");
            updateUserList();
        });
    };
    $scope.updateMailingList = function () {
        UserService.updateMailingList(function () {
            NotificationService.notify("success", "TODO Mailing list mise à jour");
        });
    };

    updateUserList();

});

	