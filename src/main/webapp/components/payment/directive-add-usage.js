(function () {
    'use strict';
    angular.module('Fablab').directive('userPaymentAddUsage', function (PaymentService, MachineService,
            MachineTypeService, NotificationService, StaticDataService, TrainingService, UserService) {
        return {
            restrict: 'EA',
            scope: {
                user: '=',
                minDate: '=',
                callback: '&'
            },
            templateUrl: 'components/payment/directive-add-usage.html',
            controller: function ($scope, $filter) {
                var resetValues = function () {
                    $scope.canUse = true;
                    $scope.addUsage = {
                        date: new Date(),
                        time: new Date(0, 0, 0, 1, 0, 0),
                        additionalCost: 0,
                        directPaid: false,
                        total: 0
                    };
                };
                $scope.total = 0;
                $scope.currency = App.CONFIG.CURRENCY;
                StaticDataService.loadMachineriesDispo(function (data) {
                    $scope.machines = data;
                });
                var getMinutes = function () {
                    var time = moment($scope.addUsage.time);
                    return time.hours() * 60 + time.minutes();
                };
                var updateTotalPrice = function () {
                    if ($scope.addUsage.machine) {
                        $scope.addUsage.total = -1;
                        var machineTypeId = $scope.addUsage.machine.machineType.id;
                        PaymentService.getPrice(machineTypeId, $scope.user.id, function (price) {
                            var add = $scope.addUsage.additionalCost;
                            var interTotal = price * getMinutes() / 60 + add;
                            if ($scope.addUsage.discount === undefined || !$scope.addUsage.discount) {
                                $scope.addUsage.total = interTotal;
                            } else {
                                if ($scope.purchase.discountPercent) {
                                    var discountInter = parseFloat(interTotal) * (parseFloat($scope.purchase.discount) / parseFloat(100));
                                    var discountFinal = parseFloat(interTotal) - parseFloat(discountInter);
                                    $scope.addUsage.total = (Math.ceil(discountFinal * 20) / 20).toFixed(2);
                                } else {
                                    $scope.addUsage.total = parseFloat(interTotal) - parseFloat($scope.purchase.discount);
                                }
                            }
                            $scope.addUsage.total = parseFloat($filter('number')(total, 2));
                            $scope.maxMoney = parseFloat($filter('number')(total, 2));
                        });
                    } else {
                        delete $scope.addUsage.total;
                    }
                };
                $scope.canUse = true;

                $scope.canUseMachinery = function () {
                    UserService.canUse($scope.addUsage.machine.machineType.id, App.connectedUser.user.id, function (data) {
                        $scope.canUse = data;
                    });
                };

                var updateMachineList = function () {
                    MachineService.getByStatusLabel("Disponible", function (data) {
                        $scope.machines = data;
                        $scope.tableParams.reload();
                    });
                };
                $scope.$watchGroup(['addUsage.machine', 'addUsage.time', 'addUsage.additionalCost'], updateTotalPrice);
                $scope.paidDirectlyOptions = [
                    {value: false, label: 'No, use its credit'},
                    {value: true, label: 'Yes, he gives the money'}
                ];
                $scope.execute = function () {
                    var data = {
                        user: $scope.user,
                        machine: $scope.addUsage.machine,
                        dateStart: $scope.addUsage.date,
                        minutes: getMinutes(),
                        additionalCost: $scope.addUsage.additionalCost,
                        comment: $scope.addUsage.comment,
                        directPaid: $scope.addUsage.directPaid
                    };
                    PaymentService.addUsage(data, function () {
                        NotificationService.notify("success", "payment.notification.usageAdded");
                        $scope.callback();
                        resetValues();
                    });
                };
                resetValues();

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
                $scope.firstPercent = App.CONFIG.FIRST_PERCENT.toUpperCase() === "PERCENT";

                $scope.optionsPercent = [{
                        name: "%",
                        value: true
                    }, {
                        name: App.CONFIG.CURRENCY,
                        value: false
                    }];

            }
        };

    });
    angular.module('Fablab').filter('objectToArray',
            [function () {
                    return function (object) {
                        var array = [];
                        angular.forEach(object, function (element)
                        {
                            array.push(element);
                        });
                        return array;
                    };
                }]);
}());