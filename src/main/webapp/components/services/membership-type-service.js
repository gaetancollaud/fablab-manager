(function () {
	'use strict';
	
	var app = angular.module('Fablab');
	app.factory('MembershipTypeService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};
		return {
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.USER_API + "/membershipType",
				}).then(getData(successFn));

			}
		};
	});
}());
