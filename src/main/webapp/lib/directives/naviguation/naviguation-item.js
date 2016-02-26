var naviguation = angular.module('Fablab');
naviguation.directive('fabNavItem', function () {
	return {
		restrict: 'EA',
		transclude:true,
		scope: {
			link: '@',
			icon: '@',
			label: '@',
			show: '='
		},
		template: '<a ng-show="show===undefined || show" href="#/{{link}}">'
				+ '	<span class="glyphicon glyphicon-{{icon}}"></span> {{label | translate}}'
				+ '</a><ng-transclude />'
	};
});