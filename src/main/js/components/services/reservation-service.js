(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('ReservationService', function ($log, $resource, $http) {

		var Reservation = $resource(App.API.RESERVATION_API + "/:id", {id: '@id'});
		
		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};

		return {
			search: function (criteria, successFn) {
				$http({
					method: 'POST',
					url: App.API.RESERVATION_API + "/search",
					data: criteria
				}).then(getData(successFn));
			},
			remove: function (id, successFn) {
				$log.debug("ReservationService: remove...");
				Reservation.remove({id: id}, successFn);
			},
			save: function (user, successFn, errorFn) {
				$log.debug("ReservationService: save...");
				var saved = Reservation.save(user, successFn, errorFn);
				return saved;
			},
			get: function (id, successFn) {
				$log.debug("ReservationService: get...");
				var prj = Reservation.get({id: id}, successFn);
				return prj;
			}
		};
	});

}());