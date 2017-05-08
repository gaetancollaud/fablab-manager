(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('AuditListController', function ($scope, AuditService) {

		$scope.criteria = {
			dateFrom: null,
			dateTo: null,
			limit: 200
		};

		$scope.reloadAudit = function () {
			$scope.criteria.limit = 200;
			AuditService.search($scope.criteria, function (data) {
				$scope.history = data;
			});
		};
		
//		$scope.export = function () {
//			var from = moment($scope.criteria.dateFrom).format('X');
//			var to = moment($scope.criteria.dateTo).format('X');
//			window.location = App.API.AUDIT_API + "/export?dateFrom=" + from + "&dateTo=" + to;
//		};

	});
}());