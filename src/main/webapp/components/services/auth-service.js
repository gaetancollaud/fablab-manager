angular.module('Auth', ['ngResource'], function ($provide) {
	$provide.factory('AuthService', ['$log', '$resource', '$http', function ($log, $resource, $http) {
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
				}
			};
		}]);
});
