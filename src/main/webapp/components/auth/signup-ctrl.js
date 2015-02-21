var ctrl = angular.module('Auth');
ctrl.controller('AuthSignUpController', function ($rootScope, $filter, $scope,
		NotificationService, AuthService, StaticDataService) {
	$scope.user = {
		firstname: "test",
		lastname: "test",
		email: "test@test.com",
		password: "123"
	};
	StaticDataService.loadMemberShipTypes(function (data) {
		$scope.membershipTypeList = data;
		angular.forEach(data, function (v) {
			if (v.id === 2) {
				$scope.user.membershipType = v;
			}
		});
	});

	$scope.recaptchaReponse = false;
	$scope.captchaResponse = function (response) {
		$scope.recaptchaReponse = response;
	};

	$scope.signup = function () {
		if ($scope.recaptchaReponse) {
			AuthService.signup($scope.user, {
				recaptcha: $scope.recaptchaReponse
			}, function (result) {
				alert('ok');
			});
		}
	};
});
