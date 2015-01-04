var filters = angular.module('UserFilters', [])

filters.filter('roleName', function () {
	return function (input) {
		switch(input){
			case 'ROLE_ADMIN':
				return 'Admin';
			case 'ROLE_VIEWER':
				return 'Lecture';
			case 'ROLE_DEC':
				return 'DEC';
			case 'ROLE_DET':
				return 'DET';
			case 'ROLE_DEX':
				return 'DEX';
			case 'ROLE_DVM':
				return 'DVM';
			case 'ROLE_DAF':
				return 'DAF';
		}
		return input;
	};
})