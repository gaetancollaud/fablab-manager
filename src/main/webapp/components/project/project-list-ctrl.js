angular.module('Fablab').controller('ProjectListController', function ($scope, $filter, $timeout, ProjectService) {

	var updateProjectsList = function () {
		ProjectService.list(function (data) {
			$scope.projects = data;
			$timeout(function () {
				$("#masonry-test").masonry({
					columnWidth: 300,
					isFitWidth: true,
					transitionDuration: "0.5s"
				});
			}, 200);
		});
	};

	updateProjectsList();


});

	