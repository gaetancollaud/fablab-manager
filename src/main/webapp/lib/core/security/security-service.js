(function() {
	'use strict';

	var core = angular.module('core');

	core.service('securityService', [function() {
		var instance = this;

		this.getSecurityConfiguration = function() {
			var sec = {};
			if(APP && APP.Constants && APP.Constants.Security) {
				sec = APP.Constants.Security;
			}
			return sec;
		};

		this.getRoles = function() {
			var roles = instance.getSecurityConfiguration().ROLES;
			if(!roles) {
				roles = [];
			}
			return roles;
		};

		this.getRolesObj = function() {
			var roles = instance.getSecurityConfiguration().ROLES;
			if(!roles) {
				roles = [];
			}
			var rs = {};
			for(var i = 0; i < roles.length; i++) {
				rs[roles[i]] = true;
			}
			return rs;
		};

		this.hasRole = function(role) {
			var roles = instance.getRolesObj();
			return roles[role] === true;
		};

		this.hasAnyRole = function(any) {
			for(var i = 0; i < any.length; i++) {
				if(instance.hasRole(any[i])) {
					return true;
				}
			}
			return false;
		};

		this.isAuthenticated = function() {
			return instance.getSecurityConfiguration().AUTHENTICATED;
		};

		this.getPrincipal = function() {
			return instance.getSecurityConfiguration().PRINCIPAL;
		};

	}]);

}());

