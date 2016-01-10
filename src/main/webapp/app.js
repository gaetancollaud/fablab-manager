(function () {
	'use strict';

	angular.module('Fablab', [
		//ext-lib
		'ngRoute', 'ngSanitize', 'ngResource', 'ui.bootstrap', 'btford.modal', 
		'pascalprecht.translate', 'ngTable', 'ui.calendar', 'ui.select', 
		// Core
		'Notification', 'Loader', 'httpInterceptor'
	]).config(function ($routeProvider, $httpProvider, $translateProvider) {
		$routeProvider.when('/', {
			templateUrl: './components/dashboard/home-view.html',
			controller: 'DashboardHomeController'
		}).when('/login', {
			templateUrl: './components/auth/login-view.html',
			controller: 'AuthLoginController'
		}).when('/logout', {
			templateUrl: './components/auth/logout-view.html',
			controller: 'AuthLogoutController'
		}).when('/signup', {
			templateUrl: './components/user/signup-view.html',
			controller: 'AuthSignUpController'
		}).when('/forgotPassword', {
			templateUrl: './components/auth/forgot-password-view.html',
			controller: 'AuthForgotPasswordController'
		}).when('/profil', {
			templateUrl: './components/user/profil-view.html',
			controller: 'UserProfilController'
		}).when('/assets', {
			templateUrl: './components/asset/list-view.html',
			controller: 'AssetListController'
		}).when('/users', {
			templateUrl: './components/user/list-view.html',
			controller: 'UserListController'
		}).when('/users/edit/:id', {
			templateUrl: './components/user/edit-view.html',
			controller: 'UserEditController'
		}).when('/users/edit', {
			templateUrl: './components/user/edit-view.html',
			controller: 'UserNewController'
		}).when('/reservations', {
			templateUrl: './components/reservation/list-view.html',
			controller: 'ReservationListController'
		}).when('/reservations/edit/:id', {
			templateUrl: './components/reservation/edit-view.html',
			controller: 'ReservationEditController'
		}).when('/reservations/edit', {
			templateUrl: './components/reservation/edit-view.html',
			controller: 'ReservationNewController'
		}).when('/payments', {
			templateUrl: './components/payment/by-user-view.html',
			controller: 'PaymentByUserController'
		}).when('/payments/:id', {
			templateUrl: './components/payment/by-user-view.html',
			controller: 'PaymentByUserController'
		}).when('/accounting', {
			templateUrl: './components/accounting/accounting-list-view.html',
			controller: 'AccountingListController'
		}).when('/audit', {
			templateUrl: './components/audit/audit-list-view.html',
			controller: 'AuditListController'
		}).otherwise({
			redirectTo: '/'
		});

		// HTTP Interceptor
		$httpProvider.interceptors.push('httpInterceptor');
		$translateProvider.preferredLanguage('en');
		$translateProvider.useSanitizeValueStrategy('escaped');
	}).run(function ($log, LoaderService, NotificationService, $rootScope, $location, AuthService) {
		App.interceptors.errorInterceptor.loaderService = LoaderService;
		App.interceptors.errorInterceptor.notificationService = NotificationService;

		$rootScope.updateCurrentUser = function (redirectCallback) {
			AuthService.getCurrentUser(function (data) {
				$rootScope.connectedUser = data;
				$rootScope.$broadcast('connectedUserChanged', data);
				if(redirectCallback){
					redirectCallback();
				}else{
					authRedirect();
				}
			});
		};

		$rootScope.connectedUser = App.connectedUser;

		var authRedirect = function () {
			if ($rootScope.connectedUser) {
				if (!$rootScope.isAuthenticated()) {
					//$location.path('/login');
				} else {
					if ($location.$$path === '/login') {
						$location.path('/');
					}
				}
			}
		};

		// register listener to watch route changes
		$rootScope.$on("$routeChangeStart", function (event, next, current) {
			authRedirect();

			var path = next.originalPath;
			if (path) {
				var secondSlah = path.indexOf('/', 2);
				if (secondSlah === -1) {
					secondSlah = path.length;
				}
				$rootScope.navModuleName = path.substring(1, secondSlah);

			}

		});

		$rootScope.isAuthenticated = function () {
			return $rootScope.connectedUser && $rootScope.connectedUser.connected;
		};

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
			if (!$rootScope.connectedUser || !$rootScope.connectedUser.roles) {
				return false;
			}
			role = 'ROLE_' + role;
			for (var k in $rootScope.connectedUser.roles) {
				if ($rootScope.connectedUser.roles[k] === role) {
					return true;
				}
			}
			return false;
		};
	});
}());