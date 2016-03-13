angular.module('Fablab').controller('MachineListController', function ($scope, $filter, $timeout, MachineService) {

	var updateMachineList = function () {
		MachineService.list(function (data) {
			$scope.machines = data;
		});
	};

	updateMachineList();


});

	