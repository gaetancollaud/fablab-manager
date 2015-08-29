(function () {
    'use strict';
    angular.module('Fablab').directive('userPaymentHistory', function (AccountingService, UserService, $filter) {
        return {
            restrict: 'EA',
            scope: {
                user: '=?',
                reload: '=?',
                editable: '=?',
                needReloadUser: '&'
            },
            templateUrl: 'components/payment/directive-user-payment-history.html',
            controller: function ($scope, AccountingService, UserService, $filter,
                    $location, ngTableParams) {
                $scope.currency = App.CONFIG.CURRENCY;
                $scope.tableParams = new ngTableParams(
                        angular.extend({
                            page: 1, // show first page
                            count: 25, // count per page
                            sorting: {
                                date: 'desc'
                            }
                        }, $location.search()), {
                    getData: function ($defer, params) {
                        if ($scope.history) {
                            params.total($scope.history.length);
                            $location.search(params.url());
                            var filteredData = params.filter() ? $filter('filter')($scope.history, params.filter()) : $scope.history;
                            var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                        }
                    }
                });
                $scope.reload = function () {
                    AccountingService.byUser($scope.user.id, function (data) {
                        $scope.history = data;
                        UserService.balance($scope.user.id, function (balance) {
                            $scope.balance = $filter('number')(balance, 2);
                        });
                        $scope.tableParams.reload();
                    });
                    
                };
                $scope.$watch('user', function (newValue) {
                    $scope.history = [];
                    if (newValue) {
                        $scope.user = newValue;
                        $scope.reload();
                    }
                });
            }
        };
    });
}());