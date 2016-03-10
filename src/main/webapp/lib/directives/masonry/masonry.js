angular.module('Fablab').directive('masonry', function ($timeout) {
	var index = 0;
	return {
		restrict: 'EA',
		templateUrl: 'lib/directives/masonry/masonry.html',
		transclude:true,
		link: function (scope, element, attrs, ctrl, transclude)
		{
			scope.reload = function () {
				$timeout(function () {
					$(element.find('ng-transclude')).masonry({
						columnWidth: 300,
						isFitWidth: true,
						transitionDuration: "0.5s"
					});
				});
			};

			$timeout(function () {
				scope.reload();
			}, 200);
			
		},
		controller: function ($scope) {

			this.reload = function () {
				$scope.reload();
			};
		}
	};
});
