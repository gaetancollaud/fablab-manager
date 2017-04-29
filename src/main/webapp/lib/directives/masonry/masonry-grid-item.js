angular.module('Fablab').directive('masonryGridItem', function ($timeout) {
	var index = 0;
	return {
		restrict: 'C',
		require: '^masonryGrid',
		link: function (scope, element, attr, masonryCtrl) {
			element.find('img').bind('load', function () {
				masonryCtrl.reload();
			});
			
			masonryCtrl.reload();

			scope.$on('$destroy', function () {
				masonryCtrl.reload();
			});
		}
	};
});
