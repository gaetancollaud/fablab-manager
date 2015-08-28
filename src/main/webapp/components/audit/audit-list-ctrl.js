(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('AuditListController', function ($scope, AuditService) {
		$scope.criteria = {
			dateFrom: null,
			dateTo: null
		};
		$scope.intervals = [{
				label: 'today',
				unit: 'day',
				delta: 0
			}, {
				label: 'yesterday',
				unit: 'day',
				delta: 1
			}, {
				label: 'thisMonth',
				unit: 'month',
				delta: 0
			}, {
				label: 'lastMonth',
				unit: 'month',
				delta: 1
			}, {
				label: 'thisYear',
				unit: 'year',
				delta: 0
			}, {
				label: 'lastYear',
				unit: 'year',
				delta: 1
			}];

		$scope.dateManuallyUpdated = function () {
			$scope.selectedInterval = null;
		};

		$scope.periodPreset = function (interval) {
			var start, end;
			var u = interval.unit;
			start = moment().startOf(u);
			end = moment().endOf(u);
			if (interval.delta !== 0) {
				start = start.subtract(interval.delta, u);
				end = end.subtract(interval.delta, u);
			}
			$scope.criteria.dateFrom = start.toDate();
			$scope.criteria.dateTo = end.toDate();
			$scope.selectedInterval = interval;
			$scope.reloadAudit();
		};

		
		$scope.reloadAudit = function () {
			AuditService.search($scope.criteria, function (data) {
				$scope.history = data;
			});
		};

//		$scope.export = function () {
//			var from = moment($scope.criteria.dateFrom).format('X');
//			var to = moment($scope.criteria.dateTo).format('X');
//			window.location = App.API.AUDIT_API + "/export?dateFrom=" + from + "&dateTo=" + to;
//		};

		$scope.periodPreset($scope.intervals[2]);//this month
	});
}());