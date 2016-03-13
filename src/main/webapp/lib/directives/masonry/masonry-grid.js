angular.module('Fablab').directive('masonryGrid', function ($timeout, $log) {
	var index = 0;
	return {
		restrict: 'C',
		link: function (scope, element, attrs, ctrl, transclude)
		{
			scope.reload = function () {
				$timeout(function () {
					$(element).masonry({
						columnWidth: 300,
						isFitWidth: true,
						transitionDuration: "0.5s"
					});

					$(element).masonry('reloadItems');
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

//
//
////angular.module('Fablab').directive('masonry', function ($timeout) {
//	var index = 0;
//	return {
//		restrict: 'EA',
//		templateUrl: 'lib/directives/masonry/masonry.html',
//		transclude:true,
//		link: function (scope, element, attrs, ctrl, transclude)
//		{
//			element.find('span').replaceWith(transclude());
//		 
//			scope.reload = function () {
//				$timeout(function () {
//					$(element).masonry({
//						columnWidth: 300,
//						isFitWidth: true,
//						transitionDuration: "0.5s"
//					});
//				});
//			};
//
//			$timeout(function () {
//				scope.reload();
//			}, 200);
//			
//		},
//		controller: function ($scope) {
//
//			this.reload = function () {
//				$scope.reload();
//			};
//		}
//	};
//});
