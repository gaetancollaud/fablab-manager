angular.module('Payment', ['ngResource'], function ($provide) {
	$provide.factory('PaymentService', ['$log', '$resource', '$http', function ($log, $resource, $http) {

			var User = $resource(App.API.RESERVATION_API + "/:id", {id: '@id'});

			return {
				search: function (criteria, successFn) {
					$http(
							{
								method: 'POST',
								url: App.API.RESERVATION_API+"/search",
								data:criteria
							}
					).success(function (data, status, headers, config) {
						successFn(data);
					}
					);
				},
				remove: function (id, successFn) {
					$log.debug("ReservationService: remove...");
					User.remove({id: id}, successFn);
				},
				save: function (user, successFn, errorFn) {
					$log.debug("ReservationService: save...");
					var saved = User.save(user, successFn, errorFn);
					return saved;
				},
				get: function (id, successFn) {
					$log.debug("ReservationService: get...");
					var prj = User.get({id: id}, successFn);
					return prj;
				},
			};
		}]);

});
