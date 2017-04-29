(function () {
	'use strict';
	
	var app = angular.module('Fablab');
	app.factory('UserService', function ($log, $resource, $http) {

		var User = $resource(App.API.USER_API + "/:id", {id: '@id'});

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};

		return {
			updatePassword: function (user, successFn) {
				$http({
					method: 'POST',
					url: App.API.USER_API + "/password",
					data: user
				}).then(getData(successFn));
			},
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.USER_API,
				}).then(getData(successFn));
			},
			remove: function (id, successFn) {
				User.remove({id: id}, successFn);
			},
			save: function (user, successFn, errorFn) {
				var saved = User.save(user, successFn, errorFn);
				return saved;
			},
			get: function (id, successFn) {
				var prj = User.get({id: id}, successFn);
				return prj;
			},
			updateMailingList: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.USER_API + "/updateMailingList",
				}).then(getData(successFn));
			},
			membershipTypeList: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.USER_API + "/membershipType",
				}).then(getData(successFn));

			}
		};
	});

	app.factory('GroupService', function ($log, $resource, $http) {

		var Group = $resource(App.API.GROUP_API + "/:id", {id: '@id'});
		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};

		return {
			list: function (successFn) {
				$http(
						{
							method: 'GET',
							url: App.API.GROUP_API,
						}
				).then(getData(successFn));
			},
//				remove: function (id, successFn) {
//					$log.debug("UserService: remove...");
//					User.remove({id: id}, successFn);
//					$log.debug("UserService: remove done.");
//				},
//				save: function (user, successFn, errorFn) {
//					$log.debug("UserService: save...");
//					var saved = User.save(user, successFn, errorFn);
//					$log.debug("UserService: save done.");
//					return saved;
//				},
//				get: function (id, successFn) {
//					$log.debug("UserService: get...");
//					var prj = User.get({id: id}, successFn);
//					$log.debug("UserService: get done.");
//					return prj;
//				},
		};
	});
}());
