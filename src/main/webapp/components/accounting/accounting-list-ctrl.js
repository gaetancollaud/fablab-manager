(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('AccountingListController', function ($rootScope, $scope, $location, NotificationService) {
		$scope.intervals = [
			{
				label:'today'
			},
			{
				label:'yesterday'
			},
			{
				label:'thisMonth'
			},
			{
				label:'lastMonth'
			},
			{
				label:'thisYear'
			},
			{
				label:'lastYear'
			}
		]
	});
}());