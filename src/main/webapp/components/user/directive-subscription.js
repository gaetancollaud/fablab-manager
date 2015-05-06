angular.module('Fablab').directive('fabUserSubscription', function ($filter, StaticDataService) {

	return {
		restrict: 'EA',
		scope: {
			user: '=?'
		},
		templateUrl: 'components/user/directive-subscription.html',
		controller: function ($rootScope, $scope) {


			$scope.visible = false;
			$scope.modalOpen = false;
			$scope.data = {
				user: null,
				epirationDate: null,
				dayLeft: null,
				price: null
			};
			$scope.$watch('user', function (newValue) {
				$scope.data.user = newValue;
				if (newValue) {
					$scope.visible = true;
					var myself = $rootScope.connectedUser !== undefined && $rootScope.connectedUser.connected
							&& newValue.id === $rootScope.connectedUser.user.id;
					$scope.who = myself ? 'myself' : 'user';
					if (newValue.subscriptions && newValue.subscriptions.length > 0) {
						var lastSub = $filter('orderBy')(newValue.subscriptions, 'dateSubscription', true)[0];
						var date = moment(lastSub.dateSubscription).add(1, 'y');//FIXME put in membership_type
						$scope.data.epirationDate = date.format('DD/MM/YYYY');
						$scope.data.dayLeft = $filter('number')(moment.duration(date.diff(moment())).asDays(), 0);
						//TODO diff
						if ($scope.data.dayLeft < 0) {
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

			$scope.openModal = function () {
				$scope.data.price = -1;
				var membershipTypeId = $scope.user.membershipType.id;
				StaticDataService.loadSubscriptionPrice(function (data) {
					angular.forEach(data, function (p) {
						if (p.membershipTypeId === membershipTypeId) {
							$scope.data.price = p.price;
						}
					});
				});
				$('#subscribeModal').modal();
			};
			$scope.cancel = function () {
				$('#subscribeModal').modal('hide');
			};
			$scope.confirmSubscription = function () {
				alert('toto');
				$('#subscribeModal').modal('hide');
			};

		}
	};
});