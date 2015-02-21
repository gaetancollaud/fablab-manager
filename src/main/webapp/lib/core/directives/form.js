

(function () {
	'use strict';

	var app = angular.module('core');

	app.directive('formGroup', [function () {
			return {
				restrict: "C",
				link: function (scope, element, attrs, ctrl) {
					var input = element.find('input[ng-model],select[ng-model]');
					if (input) {
						scope.$watch(function () {
							return input.hasClass('ng-invalid');
						}, function (isInvalid) {
							element.toggleClass('has-error', isInvalid);
						});
					}
				}
			};
		}]);

}());
