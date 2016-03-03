(function () {
	'use strict';
	
	var app = angular.module('Fablab');
	app.factory('ProjectService', function ($log, $resource, $http) {

		return {
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.PROJECT_API,
				}).success(successFn);
			},
			get:function(projectId, successFn){
				$http({
					method: 'GET',
					url: App.API.PROJECT_API+'/'+projectId,
				}).success(successFn);
			}
		};
	});

}());
