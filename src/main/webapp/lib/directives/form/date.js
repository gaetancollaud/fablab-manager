angular.module('Fablab').directive('filterDate', function () {
	return {
		restrict: 'EA',
		scope: {
			ngModel: '='
		},
		templateUrl: 'lib/directives/filters/date.html',
		controller: function ($scope) {
			$scope.opened = false;
			
			$scope.open = function ($event) {
				$event.preventDefault();
				$event.stopPropagation();

				$scope.opened = true;
			};


			$scope.clear = function () {
				$scope.ngModel = null;
			};
		}
	};
});