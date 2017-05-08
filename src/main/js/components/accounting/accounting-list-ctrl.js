(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('AccountingListController', function ($scope, AccountingService) {
		
		$scope.criteria = {};

		var computeInAndOut = function () {
			var moneyIn = 0;
			var sell = 0;
			angular.forEach($scope.history, function (h) {
				if (h.amount > 0) {
					moneyIn += h.amount;
				} else {
					sell -= h.amount;
				}
			});
			$scope.results = {
				moneyIn: moneyIn,
				sell: sell,
				delta: moneyIn - sell
			};
		};

		$scope.updateAccounting = function () {
			AccountingService.search($scope.criteria.dateFrom, $scope.criteria.dateTo, function (data) {
				$scope.history = data;
				computeInAndOut();
			});
		};

		$scope.export = function () {
			var format = 'DD-MM-YYYY hh:mm:ss'
			var from = moment($scope.criteria.dateFrom).format('X');
			var to = moment($scope.criteria.dateTo).format('X');
			window.location = App.API.ACCOUNTING_API + "/export?dateFrom=" + from + "&dateTo=" + to;
		};

	});
}());