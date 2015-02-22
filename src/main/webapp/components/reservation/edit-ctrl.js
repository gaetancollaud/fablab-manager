var reservation = angular.module('Reservation');
reservation.directive('datepickerPopup', function () {
	return {
		restrict: 'EAC',
		require: 'ngModel',
		link: function (scope, element, attr, controller) {
			//remove the default formatter from the input directive to prevent conflict
			controller.$formatters.shift();
		}
	}
});
reservation.controller('GlobalReservationEditController', function ($scope, $rootScope, $location, $filter, $q,
		ReservationService, MachineService, NotificationService) {
	$scope.dateFormat = 'dd MMMM yyyy';
	$scope.hstep = 1;
	$scope.mstep = 15;
	$scope.dateOptions = {
		formatYear: 'yy',
		startingDay: 1,
	};
	$scope.minDate = moment().format($scope.dateFormat);
	$scope.maxDate = moment().add(14, 'days').format($scope.dateFormat);

	$scope.disabled = function (date, mode) {
		return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
	};

	var extractDates = function () {
		$scope.reservationDate = new Date($scope.reservation.dateStart);
		$scope.startTime = new Date($scope.reservation.dateStart);
		$scope.endTime = new Date($scope.reservation.dateEnd);

	};

	$scope.loadReservation = function (id) {
		ReservationService.get(id, function (data) {
			$scope.reservation = data;
			extractDates();
		});
	};
	$scope.createNewReservation = function () {
		var now = moment().hour(18).minute(0).second(0);
		$scope.reservation = {
			dateStart: now,
			dateEnd: now.clone().add(1, 'hour'),
			user: $rootScope.connectedUser
		};
		extractDates();
	};

	$scope.open = function ($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.timeChanged = function () {
		$scope.timeError = moment($scope.startTime).unix() >= moment($scope.endTime).unix();
	};

	$scope.save = function () {
		if (!$scope.timeError && $scope.reservation.machine) {
			var day = moment($scope.reservationDate);
			var start = moment($scope.startTime);
			var end = moment($scope.endTime);
			$scope.reservation.dateStart = day.clone().hour(start.hour()).minute(start.minute());
			$scope.reservation.dateEnd = day.clone().hour(end.hour()).minute(end.minute());
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