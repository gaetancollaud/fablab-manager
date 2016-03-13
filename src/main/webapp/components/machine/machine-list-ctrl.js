angular.module('Fablab').controller('MachineListController', function ($scope, $filter, $timeout,
		MachineService, MachineTypeService) {

	var allMachines = [];
	var machineTypesSelected = [];


	var updateMachineListFilter = function () {
		var ret = [];
		allMachines.forEach(function (m) {
			if ($scope.isMachineTypeSelected(m.machineType)) {
				ret.push(m);
			}
		});
		$scope.machines = ret;
	};

	var updateMachineTypeList = function () {
		MachineTypeService.list(function (data) {
			machineTypesSelected = angular.copy(data);
			$scope.machineTypes = data;
			updateMachineListFilter();
		});
	};

	var updateMachineList = function () {
		MachineService.list(function (data) {
			allMachines = data;
			updateMachineListFilter();
		});
	};

	$scope.isMachineTypeSelected = function (type) {
		var found = false;
		machineTypesSelected.forEach(function (mt) {
			if (type.id === mt.id) {
				found = true;
				return false;
			}
		});
		return found;
	};

	$scope.toggleMachineType = function (type) {
		var found = false;
		for (var k in machineTypesSelected) {
			if (machineTypesSelected[k].id === type.id) {
				machineTypesSelected.splice(k, 1);
				found = true;
				break;
			}
		}
		if (!found) {
			machineTypesSelected.push(type);
		}

		updateMachineListFilter();
	};

	updateMachineList();
	updateMachineTypeList();


});

	