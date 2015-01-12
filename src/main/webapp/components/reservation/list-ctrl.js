angular.module('Reservation').controller('ReservationListController', [
	'$scope',
	'$filter',
	'$location',
	'ngTableParams',
	'ReservationService',
	function ($scope, $filter, $location, ngTableParams, ReservationService) {
		$scope.criteria = {
			dateFrom:'2000-01-01T00:00:00',
			dateTo:'2020-01-01T00:00:00',
		}
		ReservationService.search($scope.criteria, function (data) {
			$scope.reservations = data;
			$scope.tableParams = new ngTableParams(
					angular.extend({
						page: 1, // show first page
						count: 25, // count per page
						sorting: {
							lastname: 'asc',
						}
					}, $location.search()), {
				total: data.length, // length of data
				getData: function ($defer, params) {

					$location.search(params.url()); // put params in url

					var filteredData = params.filter() ? $filter('filter')(data, params.filter()) : data;

					var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

					$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		});
	
	}
]);

	