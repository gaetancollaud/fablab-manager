(function () {
	'use strict';
	var app = angular.module('Fablab');
	app.filter('lowercase', function () {
		return function (texte) {
			if(!texte){
				return '';
			}
                        var firstLetter = texte.substring(0, 1).toUpperCase();
                        var rest = texte.substring(1, texte.length).toLowerCase();
			return firstLetter + rest;
		};
	});
}());