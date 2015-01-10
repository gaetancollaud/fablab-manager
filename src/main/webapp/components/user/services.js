angular.module('User', ['ngResource'], function ($provide) {
	$provide.factory('UserService', ['$log', '$resource', '$http', function ($log, $resource, $http) {

			var User = $resource(App.API.USER_API + "/:id", {id: '@id'});

			return {
				updatePassword: function (user, successFn) {
					$log.debug("UserService: update...");
					$http(
							{
								method: 'POST',
								url: App.API.USER_API + "/password",
								data: user
							}
					).success(function (data, status, headers, config) {
						successFn(data);
						$log.debug("UserService: password change success");
					}
					);
				},
				list: function (successFn) {
					$log.debug("UserService: list...");
					$http(
							{
								method: 'GET',
								url: App.API.USER_API,
							}
					).success(function (data, status, headers, config) {
						successFn(data);
					}
					);
				},
				remove: function (id, successFn) {
					$log.debug("UserService: remove...");
					User.remove({id: id}, successFn);
					$log.debug("UserService: remove done.");
				},
				save: function (user, successFn, errorFn) {
					$log.debug("UserService: save...");
					var saved = User.save(user, successFn, errorFn);
					$log.debug("UserService: save done.");
					return saved;
				},
				get: function (id, successFn) {
					$log.debug("UserService: get...");
					var prj = User.get({id: id}, successFn);
					$log.debug("UserService: get done.");
					return prj;
				}
			};
		}]);
});
