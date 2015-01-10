angular.module('User').controller('UserListController', [
	'$scope',
	'$filter',
	'$location',
	'ngTableParams',
	'UserService',
	function ($scope, $filter, $location, ngTableParams, UserService) {

		UserService.list(function (data) {
			$scope.users = data;
			$scope.tableParams = new ngTableParams(
					angular.extend({
						page: 1, // show first page
						count: 25, // count per page
						sorting: {
							lastname: 'asc',
							firstname: 'asc',
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

		$scope.$watch("searchUser", function () {
			if ($scope.tableParams) {
				$scope.tableParams.reload();
			}
		});

	}
]);

	