(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.controller('DashboardHomeController', function ($rootScope, $scope, $location, NotificationService) {

		$scope.selected = {
			user:null
		};
		
		if($rootScope.connectedUser.connected){
			$scope.selected.user = $rootScope.connectedUser;
		}
		
		$rootScope.$on('connectedUserChanged', function(event, data){
			$scope.selected.user = data.connected ? data : null;
		});

	});
}());