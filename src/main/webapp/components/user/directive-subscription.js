angular.module('Fablab').directive('fabUserSubscription', function ($filter) {
	return {
		restrict: 'EA',
		scope: {
			user: '=?'
		},
		templateUrl: 'components/user/directive-subscription.html',
		controller: function ($rootScope, $scope) {
			$scope.visible = false;
			$scope.data = {
				user:null,
				epirationDate:null,
				dayLeft:null
			};
			$scope.$watch('user', function (newValue) {
				$scope.data.user = newValue;
				if (newValue) {
					$scope.visible = true;
					var myself = $rootScope.connectedUser!==undefined && $rootScope.connectedUser.connected
							&& newValue.id === $rootScope.connectedUser.user.id;
					$scope.who = myself ? 'myself' : 'user';
					if (newValue.subscriptions && newValue.subscriptions.length > 0) {
						var lastSub = $filter('orderBy')(newValue.subscriptions, 'dateSubscription', true)[0];
						var date = moment(lastSub.dateSubscription).add(1, 'y');//FIXME put in membership_type
						$scope.data.epirationDate = date.format('DD/MM/YYYY');
						$scope.data.dayLeft = $filter('number')(moment.duration(date.diff(moment())).asDays(), 0);
						//TODO diff
						if ($scope.data.dayLeft<0) {
							$scope.status = 'expired';
						} else {
							$scope.status = 'ok';
						}
					} else {
						$scope.status = 'never';
					}
				} else {
					$scope.visible = false;
				}
			});
		}
	};
});