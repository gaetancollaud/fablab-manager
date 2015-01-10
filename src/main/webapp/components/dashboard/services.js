angular.module('Dashboard', ['ngResource'], function ($provide) {
	$provide.factory('DashboardService', ['$log', '$resource', '$http', function ($log, $resource, $http) {
			return {
				getCurrentUser: function (successFn) {
					$log.debug("AuthService: getCurrentUser...");
					$http(
							{
								method: 'GET',
								url: App.API.AUTH_API + "/current",
							}
					).success(successFn);
				},
				login : function(data, successFn){
					$log.debug("AuthService: login");
					$http(
							{
								method: 'POST',
								url: App.API.AUTH_API + "/login",
								data:data
							}
					).success(successFn);
				}
			};
		}]);
});
