angular.module('Fablab').controller('ProjectListController', function ($scope, $filter, $timeout, ProjectService) {

	var updateProjectsList = function () {
		ProjectService.list(function (data) {
			$scope.projects = data;
			$timeout(function () {
				$('.grid').masonry({
					columnWidth: 180,
					isFitWidth: true
				});
			}, 100);
		});
	};

	updateProjectsList();


});

	