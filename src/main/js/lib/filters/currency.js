(function () {
	'use strict';

	var cmp = angular.module('Fablab');

	cmp.filter('currency', function ($filter) {
		return function (value) {
			if (value===undefined || value===null) {
				return '-';
			}
			var round = $filter('number')(value, 2);
			return round +' '+ App.CONFIG.CURRENCY;
		};
	});

}());
