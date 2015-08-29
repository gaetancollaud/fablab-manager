var naviguation = angular.module('Fablab');
naviguation.directive('fabNavItemAf', function () {
	return {
		restrict: 'EA',
		scope: {
			link: '@',
			icon: '@',
			label: '@',
			show: '='
		},
		template: '<a ng-show="show===undefined || show" href="#/{{link}}">'
				+ '	<span class="fa fa-fw fa-{{icon}}"></span> {{label | translate}}'
				+ '</a>'
	};
});

