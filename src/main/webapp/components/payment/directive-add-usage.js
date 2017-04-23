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
						amount: 100,
						additionalCost: 0,
						directPaid: false,
						total: 0
					};
				};
				$scope.total = 0;
				$scope.currency = App.CONFIG.CURRENCY;
				StaticDataService.loadMachines(function (data) {
					$scope.machines = data;
				});
				$scope.unit = 'HOUR';
				var getAmount = function () {
					if ($scope.unit == 'HOUR') {
						var time = moment($scope.addUsage.time);
						return time.hours() + time.minutes() / 60.0;
					} else {
						return $scope.addUsage.amount;
					}
				};
				var updateTotalPrice = function () {
					if ($scope.addUsage.machine) {
						var membershipTypeId = $scope.user.membershipType.id;
						$scope.addUsage.total = -1;
						angular.forEach($scope.addUsage.machine.machineType.priceList, function (p) {
							if (p.membershipTypeId === membershipTypeId) {
								var add = $scope.addUsage.additionalCost;
								$scope.unit = p.unit;
								var total = eval(p.equation.replace('x', getAmount())) + add;
								$scope.addUsage.total = parseFloat($filter('number')(total, 2));
							}
						});
					} else {
						delete $scope.addUsage.total;
					}
				};
				$scope.$watchGroup(['addUsage.machine', 'addUsage.time', 'addUsage.amount', 'addUsage.additionalCost'], updateTotalPrice);
				$scope.paidDirectlyOptions = [
					{value: false, label: 'No, use its credit'},
					{value: true, label: 'Yes, he gives the money'}
				];
				$scope.execute = function () {
					var data = {
						user: $scope.user,
						machine: $scope.addUsage.machine,
						dateStart: $scope.addUsage.date,
						amount: getAmount(),
						additionalCost: $scope.addUsage.additionalCost,
						comment: $scope.addUsage.comment,
						directPaid: $scope.addUsage.directPaid
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