(function () {
	'use strict';

	var app = angular.module('Fablab');
	app.factory('AccountingService', function ($log, $resource, $http) {
		return {
			search: function (dateFrom, dateTo, successFn) {
				$http({
					method: 'POST',
					data:{
						dateFrom:dateFrom,
						dateTo:dateTo
					},
					url: App.API.ACCOUNTING_API + '/search'
				}).success(successFn);
			},
                        byUser : function(userId, successFn){
                            $http.get(App.API.ACCOUNTING_API + '/byUser?userId=' + userId).success(successFn);
                        },
                        getName : function(name, successFn){
                            $http.get(App.API.ACCOUNTING_API + '/getName?name=' + name).success(successFn);
                        },
                        getAccounts: function(successFn){
                            $http.get(App.API.ACCOUNTING_API + '/getAccounts').success(successFn);
                        }
		};
	});

}());