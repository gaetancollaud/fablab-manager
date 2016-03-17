angular.module('Fablab').controller('MachineEditController', function ($scope, 
	$filter, $routeParams, MachineService, AssetSelectService) {

	$scope.clickAssetImageUrl = function(){
		AssetSelectService.openAssetSelector(function(file){
			if(file){
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

	if ($routeParams.id) {
		loadMachine($routeParams.id);
	}else{
		$scope.machine = {
			name:'Machine name',
			description:'#Machine name'
		};
	}

});

	