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
			ReservationService, MachineService, NotificationService) {
		$scope.value = {};
		$scope.dateFormat = 'dd MMMM yyyy';
		$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
		};
		$scope.limit = {
			min: moment().format('YYYY-MM-DD'),
			max: moment().add(14, 'days').format('YYYY-MM-DD')
		};

		$scope.disabled = function (date, mode) {
			return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
		};

		var extractDates = function () {
			$scope.value.reservationDate = moment($scope.reservation.dateStart).toDate();
			$scope.value.startTime = moment($scope.reservation.dateStart).startOf('minute').toDate();
			$scope.value.endTime =  moment($scope.reservation.dateEnd).startOf('minute').toDate();

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
					NotificationService.notify("success", "Reservation enregistrée");
					$location.path("reservations");
				});
			}
		};

		MachineService.list(function (data) {
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