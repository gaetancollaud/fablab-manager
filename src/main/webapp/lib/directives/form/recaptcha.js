angular.module('Fablab').directive('recaptcha', function ($timeout) {
	var index = 0;
	return {
		restrict: 'EA',
		scope: {
			onSuccess: '&?'
		},
		templateUrl: 'lib/directives/form/recaptcha.html',
		controller: function ($scope) {

			$scope.containerId = "recaptcha" + (index++);

			var captchaCallback = function (response) {
				if ($scope.onSuccess) {
					$timeout(function () {
						$scope.onSuccess({response: response});
					});
				}
			};

			var loadCaptcha = function () {
				var el = document.getElementById($scope.containerId);
				if (el) {
					grecaptcha.render(el, {
						'sitekey': App.CONFIG.RECAPTCHA_SITE_KEY,
						'callback': captchaCallback
					});
				} else {
					$timeout(loadCaptcha, 100);
				}
			};

			setTimeout(loadCaptcha, 300);
		}
	};
});