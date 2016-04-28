angular.module('Fablab').directive('assetList', function ($timeout, $translate, $location,
														  $filter, ngTableParams, AssetService, NotificationService) {
	var index = 0;
	return {
		restrict: 'EA',
		scope: {
			callback: '&?'
		},
		templateUrl: 'components/asset/asset-list-directive.html',
		controller: function ($scope) {

			$scope.uploadTarget = App.API.ASSET_API + '/upload';

			$scope.showSelectBtn = $scope.callback !== undefined

			$scope.tableParams = new ngTableParams(
				angular.extend({
					page: 1, // show first page
					count: 25, // count per page
					sorting: {
						dateUpload: 'asc',
					}
				}, $location.search()), {
					//total: $scope.assets.length, // length of data
					getData: function ($defer, params) {
						if ($scope.assets) {
							params.total($scope.assets.length);

							$location.search(params.url()); // put params in url

							var filteredData = params.filter() ? $filter('filter')($scope.assets, params.filter()) : $scope.assets;

							var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

							$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
						}
					}
				});

			var updateAssetList = function () {
				AssetService.list(function (data) {
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
	