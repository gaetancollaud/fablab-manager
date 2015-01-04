angular.module('User', ['ngResource'], function ($provide) {
	$provide.factory('UserService', ['$log', '$resource', '$http', function ($log, $resource, $http) {

			var Collaborateur = $resource(App.API.COLLABORATEUR_API + "/:id", {id: '@id'}, {
				update: {method: 'PUT'}
			});

			return {
				updatePassword: function (user, successFn) {
					$log.debug("UserService: update...");
					$http(
							{
								method: 'POST',
								url: App.API.COLLABORATEUR_API + "/password",
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
								url: App.API.COLLABORATEUR_API,
							}
					).success(function (data, status, headers, config) {
						successFn(data);
					}
					);
				},
				listRoles: function (successFn) {
					$log.debug("RoleService: list...");
					$http(
							{
								method: 'GET',
								url: App.API.ROLE_API,
								cache: true
							}
					).success(function (data, status, headers, config) {
						successFn(data);
					}
					);
				},
				remove: function (id, successFn) {
					$log.debug("UserService: remove...");
					Collaborateur.remove({id: id}, successFn);
					$log.debug("UserService: remove done.");
				},
				create: function (project, successFn, errorFn) {
					$log.debug("UserService: create...");
					var saved = Collaborateur.save(project, successFn, errorFn);
					$log.debug("UserService: create done.");
					return saved;
				},
				update: function (project, successFn, errorFn) {
					$log.debug("UserService: save...");
					var saved = Collaborateur.update(project, successFn, errorFn);
					$log.debug("UserService: save done.");
					return saved;
				},
				get: function (id, successFn) {
					$log.debug("UserService: get...");
					var prj = Collaborateur.get({id: id}, successFn);
					$log.debug("UserService: get done.");
					return prj;
				}
			};
		}]);
});
