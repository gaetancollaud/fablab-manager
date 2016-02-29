angular.module('Fablab').controller('ProjetListController', function ($scope, $filter, $location, ProjectService) {

	var updateProjectsList = function () {
		AssetService.list(function (data) {
			$scope.assets = data;
			$scope.tableParams.reload();
		});
	};

	updateProjectsList();

});

	