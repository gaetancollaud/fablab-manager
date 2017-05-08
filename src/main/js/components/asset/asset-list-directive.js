angular.module('Fablab').directive('assetList', function ($timeout, $translate, $location,
														  $filter, NgTableParams, AssetService, NotificationService) {

	return {
		restrict: 'EA',
		scope: {
			callback: '&?'
		},
		templateUrl: 'components/asset/asset-list-directive.html',
		controller: function ($scope) {

			$scope.uploadTarget = App.API.ASSET_API + '/upload';
			$scope.assets = [];

			$scope.showSelectBtn = $scope.callback !== undefined

			$scope.tableParams = new NgTableParams(
				angular.extend({
					page: 1, // show first page
					count: 10, // count per page
					sorting: {
						sortDate: 'desc',
					}
				}, $location.search()), {
					total: $scope.assets.length, // length of data
					getData: function (params) {
						if ($scope.assets) {
							params.total($scope.assets.length);

							var filteredData = params.filter() ? $filter('filter')($scope.assets, params.filter()) : $scope.assets;

							var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

							return orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
						}
					}
				});

			var updateAssetList = function () {
				AssetService.list(function (data) {
					data.forEach(function(a){
						a.sortDate = moment(a.dateUpload).unix();
					});
					$scope.assets = data;
					$scope.tableParams.reload();
				});
			};
			$scope.remove = function (asset) {
				AssetService.remove(asset.id, function () {
					NotificationService.notify("success", "asset.notification.removed");
					updateAssetList();
				});
			};

			$scope.getUrl = function (asset) {
				return App.API.ASSET_API + '/' + asset.id + '.' + asset.extension;
			};

			$scope.uploadDone = function () {
				updateAssetList();
			};

			$scope.select = function (asset) {
				if ($scope.callback) {
					$scope.callback({asset: asset, url: $scope.getUrl(asset)});
				}
			};

			$scope.remove = function (asset) {
				AssetService.remove(asset, function () {
					updateAssetList();
				});
			};

			updateAssetList();

		}
	};
});
	