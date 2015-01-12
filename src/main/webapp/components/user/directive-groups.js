angular.module('User').directive('fabUserGroups', function () {
	return {
		restrict: 'EA',
		scope: {
			ngModel: '=',
			groups: '=',
		},
		templateUrl: 'components/user/directive-groups.html',
		controller: function ($scope) {
			$scope.selected = {};
			
			if(!$scope.ngModel){
				$scope.ngModel = [];
			}

			$scope.isSelected = function (group) {
				if ($scope.ngModel) {
					for (var k in $scope.ngModel) {
						if ($scope.ngModel[k].groupId === group.groupId) {
							return true;
						}
					}
				}
				return false;
			};

			$scope.toggleGroupSelect = function (group) {
				for (var k in $scope.ngModel) {
						if ($scope.ngModel[k].groupId === group.groupId) {
							//remove
							$scope.ngModel.splice(k, 1);
							return;
						}
					}
					//add
					$scope.ngModel[$scope.ngModel.length] = {
						groupId:group.groupId,
						name:group.name,
					};
			};

		}
	};
});