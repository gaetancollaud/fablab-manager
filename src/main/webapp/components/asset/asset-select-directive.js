angular.module('Fablab').directive('assetSelect', function (AssetSelectService) {
	var index = 0;
	return {
		restrict: 'EA',
		scope: {
			callback: '&?'
		},
		templateUrl: 'components/asset/asset-select-directive.html',
		controller: function ($scope, $element) {
			var lastCallback = null;

			var modalAction = function (action) {
				$element.find('.modal').modal(action);
			};

			var modalFn = function (callback) {
				lastCallback = callback;
				modalAction('show');
			};

			$scope.select = function (asset, url) {
				lastCallback(url);
				modalAction('hide');
			};

			$scope.close = function () {
				lastCallback();
				modalAction('hide');
			};

			AssetSelectService.register(modalFn);

		}
	};
}).service('AssetSelectService', function () {
	var modalFn;

	this.register = function (modal) {
		this.modalFn = modal;
	};

	this.openAssetSelector = function (callback) {
		if (this.modalFn) {
			this.modalFn(callback);
		} else {
			$log.error("No modal registered");
		}
	};
});
	