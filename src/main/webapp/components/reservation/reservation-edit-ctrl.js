(function () {
    'use strict';

    var reservation = angular.module('Fablab');
    reservation.directive('datepickerPopup', function () {
        return {
            restrict: 'EAC',
            require: 'ngModel',
            link: function (scope, element, attr, controller) {
                //remove the default formatter from the input directive to prevent conflict
                controller.$formatters.shift();
            }
        };
    });
    reservation.controller('GlobalReservationEditController', function ($scope, $rootScope, $location, $filter, $q,
            ReservationService, StaticDataService, NotificationService, ConfigurationService) {
        $scope.value = {};
        $scope.dateFormat = 'dd MMMM yyyy';
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
        $scope.minDate = new Date();
        $scope.maxDate = offsetDay(App.CONFIG.OFFSET_RESERVATION);
        
        function offsetDay(offset) {
            var ret = new Date();
            if (offset === 0) {
                ret.setYear(2500);
                return new Date(ret);
            } else {
                ret.setDate(ret.getDate() + parseInt(offset));
                return new Date(ret);
            }
        }
        

        $scope.limit = {
            min: moment().format('DD.MM.YYYY'),
            max: moment().add(14, 'days').format('DD.MM.YYYY')
        };

        $scope.disabled = function (date, mode) {
            return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
        };

        var extractDates = function () {
            $scope.value.reservationDate = moment($scope.reservation.dateStart).toDate();
            $scope.value.startTime = moment($scope.reservation.dateStart).startOf('minute').toDate();
            $scope.value.endTime = moment($scope.reservation.dateEnd).startOf('minute').toDate();

        };

        $scope.loadReservation = function (id) {
            ReservationService.get(id, function (data) {
                $scope.reservation = data;
                $scope.value.reservationUser = data.user;
                extractDates();
            });
        };
        $scope.createNewReservation = function () {
            var now = moment().hour(18).startOf('hour');
            $scope.value.reservationUser = $rootScope.connectedUser.user;
            $scope.reservation = {
                dateStart: now,
                dateEnd: now.clone().add(1, 'hour')
            };
            extractDates();
        };

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.timeChanged = function () {
            $scope.timeError = !moment($scope.value.endTime).isAfter(moment($scope.value.startTime));
        };

        $scope.save = function () {
            if (!$scope.timeError && $scope.reservation.machine) {
                var day = moment($scope.value.reservationDate);
                var start = moment($scope.value.startTime);
                var end = moment($scope.value.endTime);
                $scope.reservation.dateStart = day.clone().hour(start.hour()).minute(start.minute()).startOf('minute');
                $scope.reservation.dateEnd = day.clone().hour(end.hour()).minute(end.minute()).startOf('minute');
                ReservationService.save($scope.reservation, function (data) {
                    NotificationService.notify("success", "reservation.notification.saved");
                    $location.path("reservations");
                });
            }
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

        StaticDataService.loadMachineriesDispo(function (data) {
            $scope.machines = data;
        });

    });
    reservation.controller('ReservationNewController', function ($rootScope, $scope, $location, ReservationService, $controller) {
        $controller('GlobalReservationEditController', {$scope: $scope});
        $scope.newReservation = true;
        $scope.createNewReservation();
    });
    reservation.controller('ReservationEditController', function ($rootScope, $scope, $location, $routeParams, ReservationService, $controller) {
        $controller('GlobalReservationEditController', {$scope: $scope});
        $scope.newReservation = false;
        $scope.loadReservation($routeParams.id);
    });
}());