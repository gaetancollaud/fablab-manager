angular.module('Fablab').controller('ProjectListController', function ($scope, $filter, $timeout, ProjectService) {

	var updateProjectsList = function () {
		ProjectService.list(function (data) {
			$scope.projects = data;
		});
	};

	updateProjectsList();


});

	