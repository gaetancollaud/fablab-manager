(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AuthService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};
		return {
			getCurrentUser: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.AUTH_API + "/current"
				}).then(getData(successFn));
			},
			login: function (data, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/login",
					data: data
				}).then(getData(successFn));
			},
			logout: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.AUTH_API + "/logout"
				}).then(getData(successFn));
			},
			signup: function (user, params, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/signup",
					data: user,
					params: params
				}).then(getData(successFn));
			},
			forgotPassword: function (params, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/forgotPassword",
					params: params
				}).then(getData(successFn));
			},
			changePassword: function (data, successFn) {
				$http({
					method: 'POST',
					url: App.API.AUTH_API + "/changePassword",
					data: data
				}).then(getData(successFn));
			}
		};
	});

}());