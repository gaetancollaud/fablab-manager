(function () {
	'use strict';
	
	var app = angular.module('Fablab');
	app.factory('AssetService', function ($log, $resource, $http) {


		return {
			upload: function (name, file, successFn) {
				$http({
					method: 'POST',
					url: App.API.ASSET_API + "/upload",
					data: {
						name:name,
						file: file
					}
				}).success(function (data, status, headers, config) {
					successFn(data);
				});
			},
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.ASSET_API,
				}).success(successFn);
			},
			remove:function(asset, successFn){
				$http({
					method: 'DELETE',
					url: App.API.ASSET_API+"/"+asset.id,
				}).success(successFn);
			}
		};
	});

}());
