angular.module('Fablab').controller('MachineEditController', function ($scope, $filter, $routeParams, $location,
																	   MachineService, AssetSelectService) {

	$scope.clickAssetImageUrl = function () {
		AssetSelectService.openAssetSelector(function (file) {
			if (file) {
				$scope.machine.image_url = file;
			}
		});
	};

	var loadMachine = function (projectId) {
		MachineService.get(projectId, function (data) {
			$scope.machine = data;
			//TODO check rights
		});
	};

	$scope.save = function(){
		MachineService.save($scope.machine, function(){
			$location.path('/machines');
		});
	};

	$scope.cancel = function () {
		$location.path('/machines');
	};

	if ($routeParams.id) {
		loadMachine($routeParams.id);
	} else {
		$scope.machine = {
			name: 'Machine name',
			description: '#Machine name'
		};
	}

});

	