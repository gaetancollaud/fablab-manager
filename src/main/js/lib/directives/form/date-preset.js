import angular from 'angular'
import moment from 'moment'


angular.module('Fablab').directive('datePreset', function ($translate, $timeout) {
	return {
		restrict: 'EA',
		scope: {
			dateChanged: '&',
			criteria: '='
		},
		templateUrl: 'lib/directives/form/date-preset.html',
		link: function (scope) {
			$timeout(function () {
				scope.selectPeriodPreset(scope.modes[2]);//this month
			});
		},
		controller: function ($scope) {
			$scope.criteria = {
				dateFrom: null,
				dateTo: null
			};

			$scope.delta = 0;
			$scope.modes = [{
					label: 'day',
					unit: 'day',
					delta: 'day'
				}, {
					label: 'week',
					unit: 'isoweek',
					delta: 'week'
				}, {
					label: 'month',
					unit: 'month',
					delta: 'month'
				}, {
					label: 'year',
					unit: 'year',
					delta: 'year'
				}];

			$scope.dateManuallyUpdated = function () {
				$scope.selectedMode = null;
				$scope.summary = "";
			};

			$scope.presetDelta = function (d) {
				$scope.delta += d;
				$scope.updatePreset();
			};

			$scope.updatePreset = function () {
				var start, end;
				var u = $scope.selectedMode.unit;
				var d = $scope.selectedMode.delta;

				start = moment().add($scope.delta, d).startOf(u);
				end = moment().add($scope.delta, d).endOf(u);

				$scope.criteria.dateFrom = start.toDate();
				$scope.criteria.dateTo = end.toDate();

				$scope.computeSummary();
				$scope.dateChanged({criteria: $scope.criteria});
			}

			$scope.selectPeriodPreset = function (mode) {
				$scope.delta = 0;
				$scope.selectedMode = mode;
				$scope.updatePreset();
			};

			$scope.computeSummary = function () {
				$scope.summary = "";
				if ($scope.selectedMode) {
					var fn = function (trad) {
						$scope.summary = trad;
					};
					if ($scope.delta > 0) {
						$translate('form.preset.' + $scope.selectedMode.label + '.next', {x: $scope.delta}).then(fn);
					} else if ($scope.delta < 0) {
						$translate('form.preset.' + $scope.selectedMode.label + '.ago', {x: -$scope.delta}).then(fn);
					} else {
						$translate('form.preset.' + $scope.selectedMode.label + '.current').then(fn);
					}
				}
			};

		}
	};
});