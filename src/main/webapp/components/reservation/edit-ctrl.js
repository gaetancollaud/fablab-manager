var ctrl = angular.module('Reservation');
ctrl.controller('GlobalReservationEditController', [
	'$scope',
	'$location',
	'$filter',
	'$q',
	'ReservationService',
	'MachineService',
	'NotificationService',
	function ($scope, $location, $filter, $q,
			ReservationService, MachineService, NotificationService) {

		$scope.dateFormat = 'dd-MMMM-yyyy';
		$scope.hstep = 1;
		$scope.mstep = 15;
		$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
		};
		$scope.minDate = moment().format($scope.dateFormat);
		$scope.maxDate = moment().add(14, 'days').format($scope.dateFormat);
		
		$scope.disabled = function (date, mode) {
			return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
		};

		var extractDates = function (reservation) {
			$scope.date = new Date(reservation.dateStart);
			$scope.startTime = new Date(reservation.dateStart);
			$scope.endTime = new Date(reservation.dateEnd);

		};

		$scope.loadReservation = function (id) {
			ReservationService.get(id, function (data) {
				$scope.reservation = data;
				extractDates(data);
			});
		};
		$scope.open = function ($event) {
			$event.preventDefault();
			$event.stopPropagation();

			$scope.opened = true;
		};

		$scope.save = function () {
			ReservationService.save($scope.reservation, function (data) {
				$scope.reservation = data;
				extractDates(data);
				NotificationService.notify("success", "Reservation enregistrée");
				$location.path("reservations");
			});
		};

		MachineService.list(function (data) {
			$scope.machines = data;
		});

	}
]);
ctrl.controller('ReservationNewController',
		[
			'$rootScope',
			'$scope',
			'$location',
			'ReservationService',
			'$controller',
			function ($rootScope, $scope, $location, ReservationService, $controller) {
				$controller('GlobalReservationEditController', {$scope: $scope});
				$scope.newReservation = true;
				$scope.user = new Object();
			}
		]
		);
ctrl.controller('ReservationEditController',
		[
			'$rootScope',
			'$scope',
			'$location',
			'$routeParams',
			'ReservationService',
			'$controller',
			function ($rootScope, $scope, $location, $routeParams, ReservationService, $controller) {
				$controller('GlobalReservationEditController', {$scope: $scope});
				$scope.newReservation = false;
				$scope.loadReservation($routeParams.id);
			}
		]
		);