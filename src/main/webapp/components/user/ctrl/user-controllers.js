var ctrl = angular.module('UserCtrls', []);
ctrl.controller('UserProfileController',
		[
			'$rootScope',
			'$scope',
			'NotificationService',
			'I18nService',
			'UserService',
			function ($rootScope, $scope, NotificationService, I18nService, UserService) {
				$scope.showInfo = function () {
					NotificationService.notify("INFO", "My title", "my data !");
				};
				$scope.showError = function () {
					NotificationService.notify("ERROR", "My title", "ERROR !");
				};

				$scope.user = {};

				$scope.updatePassword = function () {

					UserService.updatePassword($scope.user,
							//success
									function (data) {
										$location.path('/projects');
									},
									//error
											function (error) {
												$scope.saveBtnEnabled = true;
											}
									);
								};
					}
		]
				);
		ctrl.controller('UserListController', [
			'$scope',
			'$filter',
			'ngTableParams',
			'UserService',
			'I18nService',
			'ModalService',
			function ($scope, $filter, ngTableParams,
					UserService, I18nService, ModalService) {
				$scope.connectedUser = App.user;

				/**
				 * Update the user lsit
				 */
				$scope.search = function () {
					UserService.list(function (data) {
						$scope.users = data;
						$scope.tableParams.reload();
					});
				};

				var createTableParams = function () {
					$scope.tableParams = new ngTableParams({
						page: 1, // show first page
						count: 100, // count per page
						sorting: {
							lastName: 'asc', // initial sorting
							firstName: 'asc'
						}
					}, {
						total: 1,
						counts: [],
						getData: function ($defer, params) {
							var data = $scope.users;
							// use build-in angular filter

							var orderedData = params.sorting() ?
									$filter('orderBy')(data, params.orderBy()) :
									data;

							if (orderedData == null) {
								orderedData = [];
							}
							$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
						}
					});
				};

				/**
				 * Remove an user (display a modal confirmation box)
				 * @param User user user to remove
				 */
				$scope.remove = function (user) {
					if (user != null) {
						ModalService.confirmationModal({
							validateBtn: I18nService.localize("common.button.delete"),
							cancelBtn: I18nService.localize("common.button.cancel"),
							validateBtnCls: "btn-danger",
							cancelBtnCls: "btn-default",
							modalText: I18nService.localize("common.user.delete.confirmation")
						}, function () {
							UserService.remove(user.id, function () {
								$scope.search();
							});
						}, function () {
						});

					}
				};

				$scope.search();
				createTableParams();
			}
		]);

		ctrl.controller('GlobalUserEditController', [
			'$scope',
			'$location',
			'$filter',
			'$q',
			'ngTableParams',
			'UserService',
			'I18nService',
			'NotificationService',
			function ($scope, $location, $filter, $q, ngTableParams,
					UserService, I18nService, NotificationService) {

				$scope.saveBtnEnabled = true;
				$scope.connectedUser = App.user;
				$scope.roleSelected = 'NONE';

				//reload list of roles
				var rolePromise = $q.defer();
				UserService.listRoles(function (data) {
					$scope.rolesAvailable = data;
					rolePromise.resolve();
				}, rolePromise.reject);

				$scope.checkFields = function () {
					var ret = true;
					$scope.validation = {};

					$scope.validation.lastName = !$scope.user.lastName;
					$scope.validation.firstName = !$scope.user.firstName;

					for (var k in $scope.validation) {
						if ($scope.validation[k]) {
							ret = false;
							NotificationService.notify(
									"ERROR",
									I18nService.localize('common.form.validation.required.title'),
									I18nService.localize('common.form.validation.required.message'));
							break;
						}
					}

					return ret;
				};


				/**
				 * Reload an user
				 * @param userId userId
				 */
				$scope.loadUser = function (userId) {
					rolePromise.promise.then(function () {
						UserService.get(userId, function (data) {
							$scope.user = data;
							for (var k in $scope.user.roles) {
								var name = $scope.user.roles[k].name;
								if (name !== 'ROLE_VIEWER' || !$scope.roleSelected) {
									$scope.roleSelected = name;
								}
							}
							if (!$scope.roleSelected) {
								//if no roles selected, use viewer
								$scope.roleSelected = 'NONE';
							}

						});
					});

				};
				/**
				 * Save the current user
				 * @param boolean end if true, redirect to list
				 */
				$scope.save = function (end) {
					if ($scope.checkFields()) {
						$scope.saveBtnEnabled = false;
						//update roles list
						$scope.user.roles = Array();
						if ($scope.roleSelected !== 'NONE') {
							for (var k in $scope.rolesAvailable) {
								var r = $scope.rolesAvailable[k];
								if ($scope.roleSelected == r.name || r.name == 'ROLE_VIEWER') {
									//this role is selected or this is the viewer role
									$scope.user.roles[$scope.user.roles.length] = r;
								}
							}
						}

						var successFn = function (data) {
							$scope.saveBtnEnabled = true;
							$scope.user = data;
							if (end) {
								$location.path('/users');
							} else {
								$location.path('/users/edit/' + data.id);
							}
						};
						var errorFn = function () {
							$scope.saveBtnEnabled = true;
						};
						if ($scope.user.id != null) {
							UserService.update($scope.user, successFn, errorFn);
						} else {
							UserService.create($scope.user, successFn, errorFn);
						}
					}
				};
			}
		]);
		ctrl.controller('UserNewController',
				[
					'$rootScope',
					'$scope',
					'$location',
					'UserService',
					'$controller',
					function ($rootScope, $scope, $location, UserService, $controller) {
						$controller('GlobalUserEditController', {$scope: $scope});
						$scope.newUser = true;
						$scope.user = new Object();
					}
				]
				);
		ctrl.controller('UserEditController',
				[
					'$rootScope',
					'$scope',
					'$location',
					'$routeParams',
					'UserService',
					'$controller',
					function ($rootScope, $scope, $location, $routeParams, UserService, $controller) {

						$controller('GlobalUserEditController', {$scope: $scope});
						$scope.newUser = false;
						$scope.loadUser($routeParams.id);
					}
				]
				);