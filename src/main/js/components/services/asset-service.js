(function () {
	'use strict';
	
	var app = angular.module('Fablab');
	app.factory('AssetService', function ($log, $resource, $http) {

		var getData = function(successFn){
			return function(response){
				successFn(response.data);
			}
		};


		return {
			upload: function (name, file, successFn) {
				$http({
					method: 'POST',
					url: App.API.ASSET_API + "/upload",
					data: {
						name:name,
						file: file
					}
				}).then(getData(successFn));
			},
			list: function (successFn) {
				$http({
					method: 'GET',
					url: App.API.ASSET_API,
				}).then(getData(successFn));
			},
			remove:function(asset, successFn){
				$http({
					method: 'DELETE',
					url: App.API.ASSET_API+"/"+asset.id,
				}).then(getData(successFn));
			}
		};
	});

}());
