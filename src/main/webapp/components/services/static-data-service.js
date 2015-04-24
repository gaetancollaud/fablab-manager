angular.module('App').service('StaticDataService', function (AuthService, UserService, GroupService,
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
	this.loadMachinePrice = function(successFn){
		PaymentService.machinePrice(successFn);
	};
});
