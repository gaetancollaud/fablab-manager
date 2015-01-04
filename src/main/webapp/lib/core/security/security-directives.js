(function() {
	'use strict';

	var core = angular.module('core');

	core.directive('scSecured', ['securityService', function(securityService) {
		return {
			transclude: true,
			scope: {
				hasRole: '@',
				hasAnyRole: '@'
			},
			restrict: 'EA',
			template: '<div class="sc-secured" ng-show="authorized" ng-transclude></div>',
			link: function(scope, element, attrs) {
				var auth = false;
				if(scope.hasRole) {
					auth = securityService.hasRole(scope.hasRole);
				} else if(scope.hasAnyRole) {
					var roles = scope.hasAnyRole.split(',');
					auth = securityService.hasAnyRole(roles);
				}
				scope.authorized = auth;
			}
		};
	}]);
}());

