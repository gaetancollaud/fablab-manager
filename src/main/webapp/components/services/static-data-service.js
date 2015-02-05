angular.module('App').service('StaticDataService',
			function (UserService, GroupService) {
				//FIXME implement cache !
				this.loadMemberShipTypes = function (successFn) {
					UserService.membershipTypeList(successFn);
				};
				this.loadGroups = function (successFn) {
					GroupService.list(successFn);
				};
			});
