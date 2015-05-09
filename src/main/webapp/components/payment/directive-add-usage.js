(function () {
	'use strict';
	angular.module('Fablab').directive('userPaymentAddUsage', function (PaymentService, NotificationService, StaticDataService) {
		return {
			restrict: 'EA',
			scope: {
				user: '=',
				minDate: '=',
				callback: '&'
			},
			templateUrl: 'components/payment/directive-add-usage.html',
			controller: function ($scope, $filter) {
				var resetValues = function () {
					$scope.addUsage = {
						date: new Date(),
						time: new Date(0, 0, 0, 1, 0, 0),
						additionalCost: 0,
						directPaid: false,
						total: 0
					};
				};
				$scope.total = 0;
				$scope.currency = App.CONFIG.CURRENCY;
				var marchinePrice = [];
				StaticDataService.loadMachines(function (data) {
					$scope.machines = data;
				});
				StaticDataService.loadMachinePrice(function (data) {
					marchinePrice = data;
					updateTotalPrice();
				});
				var getMinutes = function () {
					var time = moment($scope.addUsage.time);
					return time.hours() * 60 + time.minutes();
				};
				var updateTotalPrice = function () {
					if ($scope.addUsage.machine) {
						var membershipTypeId = $scope.user.membershipType.id;
						var machineTypeId = $scope.addUsage.machine.machineType.id;
						$scope.addUsage.total = -1;
						angular.forEach(marchinePrice, function (p) {
							if (p.machineTypeId === machineTypeId && p.membershipTypeId === membershipTypeId) {
								var add = $scope.addUsage.additionalCost;
								var total = p.price * getMinutes() / 60 + add;
								$scope.addUsage.total = parseFloat($filter('number')(total, 2));
							}
						});
					} else {
						delete $scope.addUsage.total;
					}
				};
				$scope.$watchGroup(['addUsage.machine', 'addUsage.time', 'addUsage.additionalCost'], updateTotalPrice);
				$scope.paidDirectlyOptions = [
					{value: false, label: 'No, use its credit'},
					{value: true, label: 'Yes, he gives the money'}
				];
				$scope.execute = function () {
					var data = {
						user: $scope.user,
						machine: $scope.addUsage.machine,
						dateStart: $scope.addUsage.date,
						minutes: getMinutes(),
						additionalCost: $scope.addUsage.additionalCost,
						comment: $scope.addUsage.comment,
					};
					PaymentService.addUsage(data, function () {
						NotificationService.notify("success", "payment.notification.usageAdded");
						$scope.callback();
						resetValues();
					});
				};
				resetValues();
			}
		};
	});
}());