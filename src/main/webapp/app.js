angular.module('App', [
	//ext-lib
	'ngRoute', 'ngSanitize', 'ngResource', 'ngAnimate', 'ngToast', 'ui.bootstrap', 'btford.modal',
	'pascalprecht.translate',
	// Core
	'Notification', 'Loader', 'httpInterceptor',
	// Table
	//'core', 'core.ui.table',
	// Controllers
	'UserCtrls',
	// Filters
	'UserFilters',
	// Services
	'Auth', 'User',
			// Directives

]).config(['$routeProvider', '$httpProvider', '$translateProvider',
	function ($routeProvider, $httpProvider, $translateProvider) {
		$routeProvider.when('/', {
			redirectTo: '/users'
		}).when('/login', {// PROJECTS
			templateUrl: './components/auth/view/login.html',
			controller: 'AuthLoginController'
		}).when('/projects', {// PROJECTS
			templateUrl: App.BASE_ROUTE_PROVIDER_URL
					+ '/partials/project/project-list.html',
			controller: 'ProjectListController'
		}).when('/profil', {// USER
			templateUrl: App.BASE_ROUTE_PROVIDER_URL
					+ '/partials/user/profil-edit.html',
			controller: 'UserProfilController'
		}).when('/users', {// USER
			templateUrl: App.BASE_ROUTE_PROVIDER_URL
					+ '/partials/user/user-list.html',
			controller: 'UserListController'
		}).when('/users/edit/:id', {
			templateUrl: App.BASE_ROUTE_PROVIDER_URL
					+ '/partials/user/user-edit.html',
			controller: 'UserEditController'
		}).when('/users/edit', {
			templateUrl: App.BASE_ROUTE_PROVIDER_URL
					+ '/partials/user/user-edit.html',
			controller: 'UserNewController'
		}).otherwise({
			redirectTo: '/'
		});

		// HTTP Interceptor
		$httpProvider.interceptors.push('httpInterceptor');
		$translateProvider.preferredLanguage('en');
	}])
		.run(['LoaderService', 'NotificationService', '$rootScope', '$location', 'AuthService',
			function (LoaderService, NotificationService, $rootScope, $location, AuthService) {
				App.interceptors.errorInterceptor.loaderService = LoaderService;
				App.interceptors.errorInterceptor.notificationService = NotificationService;

				$rootScope.updateCurrentUser = function () {
					AuthService.getCurrentUser(function (data) {
						$rootScope.connectedUser = data;
					});
				};
				
				$rootScope.updateCurrentUser();

				// register listener to watch route changes
				$rootScope.$on("$routeChangeStart", function (event, next, current) {
					//FIXME check rights on page

					var path = next.originalPath;
					var secondSlah = path.indexOf('/', 2);
					if (secondSlah === -1) {
						secondSlah = path.length;
					}
					$rootScope.navModuleName = path.substring(1, secondSlah);

				});

				/**
				 * Has the current user any of this roles
				 * @param String role...
				 * @returns Boolean true if any role found
				 */
				$rootScope.hasAnyRole = function () {
					for (var i = 0; i < arguments.length; i++) {
						if ($rootScope.hasRole(arguments[i])) {
							return true;
						}
					}
					return false;
				};

				/**
				 * Has the current user this role
				 * @param String role role to test
				 * @returns Boolean true if he has the role, false otherwise
				 */
				$rootScope.hasRole = function (role) {
					if (!$rootScope.user) {
						return false;
					}

					role = 'ROLE_' + role;
					for (var k in $rootScope.user.roles) {
						var value = $rootScope.user.roles[k];
						if (k === role) {
							return value;
						}
					}
					$log.error("Unkonwn role " + role);
					return false;
				};

			}]);