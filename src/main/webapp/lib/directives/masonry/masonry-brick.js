angular.module('Fablab').directive('masonryBrick', function ($timeout) {
	var index = 0;
	return {
		restrict: 'EA',
		replace: true,
		templateUrl: 'lib/directives/masonry/masonry-brick.html',
		require: '^masonry',
		scope: {
			image: "=",
			width: "@?"
		},
		link: function (scope, element, attr, masonryCtrl) {
			if (!scope.width) {
				scope.width = 200;
			}
			scope.style = "width:" + scope.width + "px";

			element.find('img').bind('load', function () {
				masonryCtrl.reload();
			});
		}
	};
});
