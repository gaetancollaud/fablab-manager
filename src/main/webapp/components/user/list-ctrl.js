angular.module('Fablab').controller('UserListController', function ($scope, $filter, $location,
		ngTableParams, UserService, NotificationService) {

	$scope.details = {
		open: false
	};

	var resetDetails = function () {
		var reset = {
			balance: {
				positiv: {
					nb: 0,
					total: 0
				},
				neutral: {
					nb: 0
				},
				negativ: {
					nb: 0,
					total: 0
				}
			},
			subscription: {
				positiv: 0,
				negativ: 0,
				unconfirmed: 0
			},
			nb:0
		};
		angular.extend($scope.details, reset);
		return $scope.details;
	};

	var computeDetails = function (users) {
		var d = resetDetails();
		users.forEach(function (u) {
			d.nb++;
			
			//subscription
			if (u.subscriptions.length === 0) {
				d.subscription.unconfirmed++;
			} else {
				if ($filter('lastSubscriptionDays')(u.subscriptions) < 0) {
					d.subscription.negativ++;
				} else {
					d.subscription.positiv++;
				}
			}

			//balance
			if (u.balance.value == 0) {
				d.balance.neutral.nb++;
			} else if (u.balance.value < 0) {
				d.balance.negativ.nb++;
				d.balance.negativ.total += -u.balance.value;
			} else {
				d.balance.positiv.nb++;
				d.balance.positiv.total += u.balance.value;
			}
		});
	};

	$scope.tableParams = new ngTableParams(
			angular.extend({
				page: 1, // show first page
				count: 25, // count per page
				sorting: {
					lastname: 'asc',
					firstname: 'asc'
				}
			}, $location.search()), {
		//total: $scope.users.length, // length of data
		getData: function ($defer, params) {
			if ($scope.users) {
				params.total($scope.users.length);

				$location.search(params.url()); // put params in url

				var filteredData = params.filter() ? $filter('filter')($scope.users, params.filter()) : $scope.users;

				var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

				$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
			}
		}
	});

	var updateUserList = function () {
		UserService.list(function (data) {
			computeDetails(data);
			$scope.users = data;
			$scope.users.forEach(function (u) {
				u.balance.value = $filter('number')(u.balance.value, 2);
				u.subsriptionExpireDay = $filter('lastSubscriptionDays')(u.subscriptions);
				u.subsriptionExpireDayText = $filter('lastSubscriptionDaysText')(u.subscriptions);
			});
			$scope.tableParams.reload();
		});
	};
	$scope.remove = function (user) {
		UserService.remove(user.id, function () {
			NotificationService.notify("success", "user.notification.removed");
			updateUserList();
		});
	};
	$scope.updateMailingList = function () {
		UserService.updateMailingList(function () {
			NotificationService.notify("success", "TODO Mailing list mise à jour");
		});
	};

	$scope.export = function () {
		window.location = App.API.USER_API + "/export";
	};

	updateUserList();

});

	