var ctrl = angular.module('Auth');
ctrl.controller(function ($rootScope, $scope, $location, NotificationService, AuthService) {
	AuthService.logout(function () {
		$rootScope.updateCurrentUser();
		$location.path('/');
	});
});
