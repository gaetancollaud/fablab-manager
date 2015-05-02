(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AuthService', function ($log, $resource, $http) {
		return {
			getCurrentUser: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.AUTH_API + "/current"
				}).success(successFn);
			},
			login: function (data, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/login",
					data: data
				}).success(successFn);
			},
			logout: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.AUTH_API + "/logout"
				}).success(successFn);
			},
			signup: function (user, params, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/signup",
					data: user,
					params: params
				}).success(successFn);
			},
			forgotPassword: function (params, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/forgotPassword",
					params: params
				}).success(successFn);
			}
		};
	});

}());