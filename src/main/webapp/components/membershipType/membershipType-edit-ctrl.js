'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMembershipTypeEditController', function ($scope, $location,
        MembershipTypeService, NotificationService, MachineTypeService, PriceMachineService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.selected = {membershipType: undefined};
    $scope.loadMembershipType = function (id) {
        MembershipTypeService.get(id, function (data) {
            $scope.membershipType = data;
        });
        updateMachineTypeList();
    };

    MembershipTypeService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].name.toUpperCase());
        }
        $scope.existingValues = res;
    });

    MachineTypeService.list(function (mt) {
        $scope.machineTypes = mt;
    });

    $scope.save = function () {
        var membershipTypeCurrent = angular.copy($scope.membershipType);
        if ($scope.newMembershipType) {
            MembershipTypeService.save(membershipTypeCurrent, function (data) {
                $scope.machineType = data;
                NotificationService.notify("success", "membershipType.notification.saved");
                MembershipTypeService.getId(data.name, function (withId) {
                    $location.path("membershipTypes/membershipType-edit/" + withId.id);
                });
            });
        } else {
            MembershipTypeService.save(membershipTypeCurrent, function (data) {
                $scope.membershipType = data;
                var prices = $scope.prices;
                var pmi;
                for (pmi = 0; pmi < prices.length; pmi++) {
                    var price = prices[pmi];
                    var priceMembershipTypeCurrent = angular.copy(price);
                    PriceMachineService.save(priceMembershipTypeCurrent, function (data) {
                        price = data;
                        NotificationService.notify("success", "membershipType.notification.saved");
                        $location.path("membershipTypes");
                    });
                }
            });
        }
    };
    var updateMachineTypeList = function () {
        MachineTypeService.list(function (mst) {
            $scope.machineTypes = mst;
            PriceMachineService.list(function (pm) {
                $scope.priceMachines = pm;
                var msti, pmi;
                var getCellule = function (mtId) {
                    for (pmi = 0; pmi < pm.length; pmi++) {
                        if (pm[pmi].machineType.id === mtId &&
                                pm[pmi].membershipType.id === $scope.membershipType.id) {
                            return pm[pmi];
                        }
                    }
                    return null;
                };
                var mstArray = [];
                for (msti = 0; msti < mst.length; msti++) {
                    mstArray.push(getCellule(mst[msti].id));
                }
                $scope.prices = mstArray;
            });
        });
    };
}
);
app.controller('MembershipTypeNewController', function ($scope, $controller) {
    $controller('GlobalMembershipTypeEditController', {$scope: $scope});
    $scope.newMembershipType = true;
    $scope.membershipType = {};
}
);
app.controller('MembershipTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMembershipTypeEditController', {$scope: $scope});
    $scope.newMembershipType = false;
    $scope.loadMembershipType($routeParams.id);
}
);

