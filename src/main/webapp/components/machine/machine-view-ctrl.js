angular.module('Fablab').controller('MachineViewController', function ($scope, $filter, $routeParams, MachineService,
																	   MembershipTypeService) {

	var loadMachine = function (machineId) {
		MachineService.get(machineId, function (data) {
			$scope.machine = data;
			MembershipTypeService.list(function (data) {
				$scope.membershipTypes = data;
			});
		});
	};

	$scope.getPriceForMembershipType = function (m) {
		var price = -1;
		$scope.machine.machineType.priceList.fo
		rEach(function(p){
			if(p.membershipTypeId==m.id){
				price = p.price;
			}
		});
		return price;
	};

	loadMachine($routeParams.id);

});

	