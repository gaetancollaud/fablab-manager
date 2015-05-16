(function () {
	'use strict';
	angular.module('Fablab').directive('reservationDay', function (ReservationService) {
		return {
			restrict: 'EA',
			scope: {
				machine: '=',
				date: '=',
				hourFrom: '=',
				hourTo: '=',
				oneConflict: '='
			},
			templateUrl: 'components/reservation/directive-reservation-day.html',
			controller: function ($scope, $filter) {
				$scope.value = {
					loading: false
				};

				$scope.reservations = [];

				var checkConflict = function () {
					var oneConflict = false;
					angular.forEach($scope.reservations, function (r) {
						r.conflict = false;
					});
					if ($scope.date && $scope.machine && $scope.hourFrom && $scope.hourFrom) {
						var hourFrom = moment($scope.hourFrom);
						var hourTo = moment($scope.hourTo);
						var start = moment($scope.date).hour(hourFrom.hour()).minute(hourFrom.minute())
						var end = moment($scope.date).hour(hourTo.hour()).minute(hourTo.minute())
						var rangeTest = moment().range(start, end);
						angular.forEach($scope.reservations, function (r) {
							r.conflict = false;
							if ($scope.machine.id === r.machine.id) {
								var rangeRes = moment().range(moment(r.dateStart), moment(r.dateEnd));
								if (rangeRes.overlaps(rangeTest)) {
									r.conflict = true;
									oneConflict = true;
								}
							}
						});
					}
					$scope.oneConflict = oneConflict;
				};

				$scope.$watch('date', function (newValue) {
					$scope.reservation = [];
					if (newValue) {
						$scope.dateTranslate = {date:moment(newValue).format('DD/MM/YYYY')}
						var criteria = {
							dateFrom: moment(newValue).startOf('day'),
							dateTo: moment(newValue).endOf('day')
						};
						ReservationService.search(criteria, function (data) {
							$scope.reservations = data;
							checkConflict();
						});
					}
				});

				$scope.$watchGroup(['machine', 'hourFrom', 'hourTo'], checkConflict);
			}
		};
	});
}());