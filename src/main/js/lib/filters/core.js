(function () {
	'use strict';

	var cmp = angular.module('Fablab');
	
	cmp.filter('inlineList', function(){
		return function(list, separator, param){
			if(!separator){
				separator = ', ';
			}
			
			var first = true;
			var ret = "";
			for(var k in list){
				if(!first){
					ret += separator;
				}
				first = false;
				if(param){
					ret += list[k][param];
				}else{
					ret += list[k];
				}
			}
			return ret;
		};
	});

	//new line to <br />
	cmp.filter('nl2br', ['$sce', '$sanitize', function ($sce, $sanitize) {
			return function (msg) {
				var breakTag = '<br />';
				var msg = (msg + '').replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '$1' + breakTag + '$2');
				msg = $sanitize(msg);
				return $sce.trustAsHtml(msg);
			};
		}]);

	//compute the "size" of an object (could be usefull if object is used like an array in a ng-repeat)
	cmp.filter("objectSize", function () {
		return function (obj) {
			if (obj) {
				var size = 0, key;
				for (key in obj) {
					if (obj.hasOwnProperty(key))
						size++;
				}
				return size;
			}
			return 0;
		};
	});

	//same thing as orderBy from angular but for objects
	cmp.filter('orderObjectBy', function () {
		return function (items, field, reverse) {
			var filtered = [];
			angular.forEach(items, function (item) {
				filtered.push(item);
			});
			filtered.sort(function (a, b) {
				return (a[field] > b[field] ? 1 : -1);
			});
			if (reverse)
				filtered.reverse();
			return filtered;
		};
	});
	
	cmp.filter('stringify', function () {
		return function (user) {
			return JSON.stringify(user, null, 2);
		};
	});
	cmp.filter('emailLink', function () {
		return function (email) {
			if(email){
				return '<a href="mailto:'+email+'">'+email+"</a>";
			}
		};
	});
	cmp.filter('highlight', function () {
		return function (value, search) {
			//TODO
			return value;
		};
	});

}());
