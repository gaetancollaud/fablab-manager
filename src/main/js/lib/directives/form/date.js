angular.module('Fablab').directive('filterDate', function () {
	return {
		restrict: 'EA',
		scope: {
			ngModel: '=',
			required: '='
		},
		templateUrl: 'lib/directives/form/date.html',
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