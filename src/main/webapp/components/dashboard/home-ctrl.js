(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('DashboardHomeController', function ($rootScope, $scope, $location, NotificationService) {
		$scope.callbacks = {};
		$scope.callbacks.updateUser = function (user) {
			$rootScope.updateCurrentUser();
		};
	});
}());