(function () {
	'use strict';

	var cmp = angular.module('Fablab');

	cmp.filter('fileSize', function ($filter) {
		return function (bytes) {
			var list = ['o', 'Ko', 'Mo', 'Go'];
			for(var k in list){
				if(bytes<1024){
					var t = list[k];
					return $filter('number')(bytes, 1)+t;
				}
				bytes/=1024;
			}
			return 'too big';
		};
	});

}());
