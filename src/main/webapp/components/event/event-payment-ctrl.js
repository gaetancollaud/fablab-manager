'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventPaymentEditController', function ($scope, $location, $window, $rootScope, $filter,
        UserPaymentService, NotificationService, StaticDataService, AccountingService, EventService) {
    $scope.selected = {userPayment: undefined};
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('PAYMENT_MANAGE');
    $scope.btnTitle = $filter('translate')('userPayment.btnTitle');
    $scope.save = function () {
        $scope.userPayment.accountCredit = 'CAISSE_POSTE_BANQUE';
        $scope.userPayment.accountDebit = 'HONORAIRES';
        $scope.userPayment.note = $scope.event.place ? $scope.event.place : '' + ' | ' +
                $scope.event.theme ? $scope.event.theme : '' + ' | ' +
                $scope.event.description ? $scope.event.description : '';
        $scope.userPayment.user = $scope.event.supervisor;
        $scope.userPayment.payedForFabLab = true;
        var userPaymentCurrent = angular.copy($scope.userPayment);
        console.log(userPaymentCurrent);
        UserPaymentService.save(userPaymentCurrent, function (data) {
            $scope.userPayment = data;
            NotificationService.notify("success", "eventPayment.saved");
            $location.path("userPayments");
        });
    };

    EventService.list(function (data) {
        $scope.eventList = data;
    });

    $scope.updatePrice = function () {
        if ($scope.event) {
            $scope.userPayment.amount = parseFloat($scope.event.price);
            $scope.userPayment.label = $scope.event.title;
        }
        var interTotal = parseFloat($scope.userPayment.amount);
        if (!$scope.userPayment.discount) {
            $scope.userPayment.total = interTotal;
        } else {
            if ($scope.userPayment.discountPercent) {
                var discountInter = parseFloat(interTotal) * (parseFloat($scope.userPayment.discount) / parseFloat(100));
                var total = parseFloat(interTotal) - parseFloat(discountInter);
                //0.05 cts ceil
                var val = $window.Math.ceil(total * 20) / 20;
                $scope.userPayment.total = parseFloat($filter('number')(val, 2));
            } else {
                var total = parseFloat(interTotal) - parseFloat($scope.userPayment.discount);
                //0.05 cts ceil
                var val = $window.Math.ceil(total * 20) / 20;
                $scope.userPayment.total = parseFloat($filter('number')(val, 2));
            }
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
    StaticDataService.loadUsers(function (data) {
        $scope.userList = data;
    });
    StaticDataService.loadCashiers(function (data) {
        $scope.cashierList = data;
    });

    AccountingService.getAccounts(function (data) {
        $scope.accounts = [];
        for (var i = 0; i < data.length; i++) {
            $scope.accounts.push({
                name: data[i],
                value: data[i]
            });
        }
    });

}
);
app.controller('EventPaymentNewController', function ($scope, $controller, $rootScope) {
    $controller('GlobalEventPaymentEditController', {$scope: $scope});
    $scope.newUserPayment = true;
    $scope.paidDirectly = false;
    $scope.editable = true;
    $scope.userPayment = {
        datePayment: new Date(),
        user: $rootScope.connectedUser.user,
        cashier: $rootScope.connectedUser.user,
        payedForFabLab: true,
        event:true
    };
}
);

