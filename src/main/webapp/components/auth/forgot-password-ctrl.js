(function () {
	'use strict';

	var ctrl = angular.module('Fablab');
	ctrl.controller('AuthForgotPasswordController', function ($rootScope, $location, $filter, $scope,
			NotificationService, AuthService, StaticDataService) {

		$scope.siteKey = App.Constants.RECAPTCHA_SITE_KEY;
		$scope.email = "";
		$scope.recaptchaReponse = false;
		$scope.captchaResponse = function (response) {
			$scope.recaptchaReponse = response;
		};

		$scope.forgotPassword = function () {
			if ($scope.recaptchaReponse) {
				AuthService.forgotPassword({
					email: $scope.email,
					recaptcha: $scope.recaptchaReponse
				}, function (result) {
					NotificationService.notify("success", "TODO email envoyé");
					$location.path("login");
				});
			}
		};
	});

}());