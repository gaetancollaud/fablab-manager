var ctrl = angular.module('Fablab');
ctrl.controller('GlobalUserEditController', function ($scope, $location, $filter, $q,
        UserService, GroupService, NotificationService, StaticDataService) {

    $scope.selected = {user: undefined};

    $scope.loadUser = function (id) {
        UserService.get(id, function (data) {
            $scope.user = data;
            if ($scope.user.birthdate) {
                $scope.user.birthdate = new Date($scope.user.birthdate);
            }
        });
    };

    $scope.save = function () {
        $scope.user.login = $scope.user.email.toString();
        var user = angular.copy($scope.user);
        delete user.subscriptions;
        delete user.balance;
        UserService.save(user, function (data) {
            $scope.user = data;
            NotificationService.notify("success", "user.notification.saved");
            $location.path("users");
        });
    };

    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);
    $scope.events =
            [
                {
                    date: tomorrow,
                    status: 'full'
                },
                {
                    date: afterTomorrow,
                    status: 'partially'
                }
            ];

    $scope.getDayClass = function (date, mode) {
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    };

    StaticDataService.loadMemberShipTypes(function (data) {
        $scope.membershipTypeList = data;
    });

    StaticDataService.loadGroups(function (data) {
        $scope.groups = data;
    });
}
);
ctrl.controller('UserNewController', function ($rootScope, $scope, $location, UserService, $controller) {
    $controller('GlobalUserEditController', {$scope: $scope});
    $scope.newUser = true;
    $scope.user = new Object();
}
);
ctrl.controller('UserEditController', function ($rootScope, $scope, $location, $routeParams, UserService, $controller) {
    $controller('GlobalUserEditController', {$scope: $scope});
    $scope.newUser = false;
    $scope.loadUser($routeParams.id);
}
);