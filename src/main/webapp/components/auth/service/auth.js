angular.module('Auth', ['ngResource'], function ($provide) {
	$provide.factory('AuthService', ['$log', '$resource', '$http', function ($log, $resource, $http) {
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
			};
		}]);
});
