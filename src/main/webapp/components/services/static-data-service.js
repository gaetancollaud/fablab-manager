(function () {
	'use strict';

	angular.module('Fablab').service('StaticDataService', function (PriceService, UserService, GroupService,
			MachineService, PaymentService) {

		//FIXME implement cache !
		this.loadMemberShipTypes = function (successFn) {
			UserService.membershipTypeList(successFn);
		};
		this.loadGroups = function (successFn) {
			GroupService.list(successFn);
		};
		this.loadMachines = function (successFn) {
			MachineService.list(successFn);
		};
	});

}());