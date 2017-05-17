import angular from 'angular'

angular.module('Fablab').directive('dropFile', function (Upload, $timeout) {
	return {
		restrict: 'EA',
		scope: {
			target: '=',
			mimeAllowed: '@',
			uploadDone:'&?'
		},
		transclude: true,
		templateUrl: 'lib/directives/form/dropfile.html',
		controller: function ($scope) {
			$scope.$watch('files', function () {
				$scope.upload($scope.files);
			});

			$scope.upload = function (files) {
				if (files && files.length) {
					for (var i = 0; i < files.length; i++) {
						var file = files[i];
						if (!file.$error) {
							Upload.upload({
								url: $scope.target,
								data: {
									file: file
								}
							}).then(function (resp) {
								$timeout(function () {
									$scope.log = 'file: ' +
											resp.config.data.file.name +
											', Response: ' + JSON.stringify(resp.data) +
											'\n' + $scope.log;
								});
								if($scope.uploadDone){
									$scope.uploadDone();
								}
							}, null, function (evt) {
								var progressPercentage = parseInt(100.0 *
										evt.loaded / evt.total);
								$scope.log = 'progress: ' + progressPercentage +
										'% ' + evt.config.data.file.name + '\n' +
										$scope.log;
							});
						}
					}
				}
			};


		}
	};
});