(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.controller('AccountingUserController', function ($scope, UserService,$routeParams, $rootScope,$location, $log) {
 $scope.selected = {user: undefined};
        $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');

        $scope.minDate = moment().subtract(App.CONFIG.ACCOUNTING_EDIT_HISTORY_LIMIT, 'days').format('YYYY-MM-DD');

        $scope.loadUser = function (userId) {
            UserService.get(userId, function (data) {
                $scope.selected.user = data;
            });
        };
        $scope.onSelectUser = function (user) {
            $location.path('accounting/user-account/' + user.id);
            $log.info(user);
        };


        UserService.list(function (data) {
            $log.info("reload user");
            $scope.users = data;
            if ($routeParams.id) {
                for (var k in data) {
                    if (data[k].id === $routeParams.id) {
                        $scope.onSelectUser(data[k]);
                        break;
                    }
                }
            }
        });

        $scope.updateUser = function () {
            if ($routeParams.id) {
                $scope.loadUser($routeParams.id);
            }
        };

        $scope.updateUser();

    });
}());

app.controller('AccountingUserEditController', function ($scope, $routeParams, $controller) {
    $controller('AccountingUserController', {$scope: $scope});
    $scope.loadUser($routeParams.id);
}
);