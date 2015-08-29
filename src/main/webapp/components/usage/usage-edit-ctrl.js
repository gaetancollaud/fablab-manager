'use strict';
var app = angular.module('Fablab');
app.controller('GlobalUsageEditController', function ($scope, $location, $rootScope, $window, $filter,
        UsageService, NotificationService, StaticDataService, PaymentService, UserService) {
    $scope.selected = {usage: undefined};
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');
    $scope.loadUsage = function (id) {
        UsageService.get(id, function (data) {
            $scope.usage = data;
            if ($scope.usage.dateStart) {
                $scope.usage.dateStart = new Date($scope.usage.dateStart);
            }
            if ($scope.usage.minutes) {
                $scope.usage.minutes = new Date($scope.usage.minutes);
            }
            UserService.canUse($scope.usage.machine.machineType.id, $scope.usage.user.id, function (data) {
                $scope.canUse = data;
            });
        });
    };
    $scope.save = function () {
        $scope.usage.minutes = $scope.getMinutes();
        var usageCurrent = angular.copy($scope.usage);
        UsageService.save(usageCurrent, function (data) {
            $scope.usage = data;
            NotificationService.notify("success", "usage.notification.saved");
            $location.path("usages");
        });
    };
    $scope.miniDate = new Date();
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
    StaticDataService.loadCashiers(function (data) {
        $scope.cashierList = data;
    });
    StaticDataService.loadUsers(function (data) {
        $scope.userList = data;
    });
    StaticDataService.loadMachineriesDispo(function (data) {
        $scope.machines = data;
    });
    $scope.init = function () {
        UserService.canUse($scope.usage.machine.machineType.id, $rootScope.connectedUser.user.id, function (data) {
            $scope.canUse = data;
            if ($scope.usage.machine) {
                PaymentService.getPrice($scope.usage.machine.machineType.id, $scope.usage.user.id, function (price) {
                    $scope.usage.pricePerHour = price;
                    $scope.updatePrice();
                });
            }
        });


    };

    $scope.getMinutes = function () {
        var time = moment($scope.usage.minutes);
        return time.hours() * 60 + time.minutes();
    };
    $scope.updatePrice = function () {
        if ($scope.usage.machine) {
            $scope.usage.total = -1;
            var machineTypeId = $scope.usage.machine.machineType.id;
            PaymentService.getPrice(machineTypeId, $scope.usage.user.id, function (price) {
                var interTotal = parseFloat(price * $scope.getMinutes() / 60);
                if ($scope.usage.additionalCost) {
                    var add = $scope.usage.additionalCost;
                    interTotal += add;
                }
                if (!$scope.usage.discount) {
                    //0.05 cts ceil
                    var val = $window.Math.ceil(interTotal * 20) / 20;
                    $scope.usage.total = parseFloat($filter('number')(val, 2));
                } else {
                    if ($scope.usage.discountPercent) {
                        var discountInter = parseFloat(interTotal) * (parseFloat($scope.usage.discount) / parseFloat(100));
                        var total = parseFloat(interTotal) - parseFloat(discountInter);
                        //0.05 cts ceil
                        var val = $window.Math.ceil(total * 20) / 20;
                        $scope.usage.total = parseFloat($filter('number')(val, 2));
                    } else {
                        var total = parseFloat(interTotal) - parseFloat($scope.usage.discount);
                        //0.05 cts ceil
                        var val = $window.Math.ceil(total * 20) / 20;
                        $scope.usage.total = parseFloat($filter('number')(val, 2));
                    }
                }
                $scope.maxMoney = $scope.usage.total;
            });
        } else {
            delete $scope.usage.total;
        }
    };


    $scope.firstPercent = App.CONFIG.FIRST_PERCENT.toUpperCase() === "PERCENT";

    $scope.optionsPercent = [{
            name: "%",
            value: true
        }, {
            name: App.CONFIG.CURRENCY,
            value: false
        }];

    $scope.hstep = 1;
    $scope.mstep = 5;
    $scope.minDate = moment().subtract(App.CONFIG.ACCOUNTING_EDIT_HISTORY_LIMIT, 'days').format('YYYY-MM-DD');
}
);
app.controller('UsageNewController', function ($scope, $rootScope, $controller) {
    $controller('GlobalUsageEditController', {$scope: $scope});
    $scope.newUsage = true;
    $scope.canUse = true;
    $scope.paidDirectly = false;
    $scope.editable = true;
    $scope.usage = {
        dateStart: new Date(),
        user: $rootScope.connectedUser.user,
        minutes: new Date(0, 0, 0, 1, 0, 0),
        membershipType: $rootScope.connectedUser.user.membershipType
    };
}
);
app.controller('UsageEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalUsageEditController', {$scope: $scope});
    $scope.newUsage = false;
    $scope.editable = false;
    $scope.loadUsage($routeParams.id);
}
);

