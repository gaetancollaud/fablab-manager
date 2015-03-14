angular.module('App').service('StaticDataService', function (AuthService, UserService, GroupService,
		MachineService) {

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
