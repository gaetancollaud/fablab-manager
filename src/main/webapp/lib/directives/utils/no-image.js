angular.module('Fablab').directive('ngImage', [function () {
	return {
		restrict: 'A',
		scope: {
			'ngImage': '='
		},
		link: function (scope, element, attrs) {
			var fallbakImg = './images/no-image-available.png';
			element.bind('error', function () {
				element.attr('src', fallbakImg);
			});
			scope.$watch('ngImage', function (newValue) {
				if (newValue) {
					element.attr('src', newValue);
				} else {
					element.attr('src', fallbakImg);

				}

			})
		}
	}
}]);