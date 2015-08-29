'use strict';
var app = angular.module('Fablab');
app.controller('GlobalGroupEditController', function ($scope, $location,
        GroupService, NotificationService, RoleService) {
    $scope.showTechName = true;
    $scope.selected = {group: undefined};
    $scope.loadGroup = function (id) {
        GroupService.get(id, function (data) {
            $scope.group = data;
            if (data.technicalname.toUpperCase() === 'ADMIN') {
                $scope.showTechName = false;
            }
            setLists();
        });
    };
    GroupService.list(function (group) {
        var res = [];
        for (var i = 0; i < group.length; i++) {
            res.push(group[i].technicalname.toUpperCase());
        }
        $scope.existingValues = res;
    });
    RoleService.list(function (data) {
        $scope.roles = data;
    });
    $scope.save = function () {
        var groupCurrent = angular.copy($scope.group);
        GroupService.save(groupCurrent, function (data) {
            $scope.group = data;
            NotificationService.notify("success", "group.notification.saved");
            $location.path("groups");
        });
    };
    $scope.save = function () {
        if ($scope.newGroup) {
            var groupCurrent = angular.copy($scope.group);
            GroupService.save(groupCurrent, function (data) {
                $scope.group = data;
                NotificationService.notify("success", "group.notification.saved");
                GroupService.getId(data.name, function (withId) {
                    $location.path("groups/group-edit/" + withId.id);
                });
            });
        } else {
            $scope.group.roles = $scope.assignedRoles;
            var groupCurrent = angular.copy($scope.group);
            GroupService.save(groupCurrent, function (data) {
                $scope.group = data;
                NotificationService.notify("success", "group.notification.saved");
                $location.path("groups");
            });
        }
    };
    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };
    var setLists = function () {
        RoleService.list(function (roles) {
            if ($scope.group) {
                //Check if admin
                var availableRoles = [];
                if ($scope.group.technicalname.toUpperCase() === 'ADMIN') {
                    for (var i = 0; i < roles.length; i++) {
                        if (roles[i].technicalname.toUpperCase() !== 'ROLE_ADMIN') {
                            availableRoles.push(roles[i]);
                        }
                    }
                } else {
                    availableRoles = roles;
                }
                $scope.availableRoles = availableRoles;
                $scope.assignedRoles = $scope.group.roles;
            }
        });
    };
}
);
app.controller('GroupNewController', function ($scope, $controller) {
    $controller('GlobalGroupEditController', {$scope: $scope});
    $scope.newGroup = true;
    $scope.group = new Object();
}
);
app.controller('GroupEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalGroupEditController', {$scope: $scope});
    $scope.newGroup = false;
    $scope.loadGroup($routeParams.id);
}
);

