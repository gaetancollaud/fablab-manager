'use strict';
var app = angular.module('Fablab');
app.controller('GlobalMachineTypeEditController', function ($scope, $location,
        MachineTypeService, NotificationService, MembershipTypeService,
        PriceMachineService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.selected = {machineType: undefined};

    MachineTypeService.list(function (mt) {
        var res = [];
        for (var i = 0; i < mt.length; i++) {
            res.push(mt[i].technicalname.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.loadMachineType = function (id) {
        MachineTypeService.get(id, function (data) {
            $scope.machineType = data;
        });
        updateMemberShipList();
    };
    MembershipTypeService.list(function (data) {
        $scope.membershipTypes = data;
    });
    $scope.save = function () {
        var machineTypeCurrent = angular.copy($scope.machineType);
        if ($scope.newMachineType) {
            MachineTypeService.save(machineTypeCurrent, function (data) {
                $scope.machineType = data;
                NotificationService.notify("success", "machineType.notification.saved");
                MachineTypeService.getId(data.technicalname, function (withId) {
                    $location.path("machineTypes/machineType-edit/" + withId.id);
                });
            });
        } else {
            MachineTypeService.save(machineTypeCurrent, function (data) {
                $scope.machineType = data;
                var prices = $scope.prices;
                var pmi;
                for (pmi = 0; pmi < prices.length; pmi++) {
                    var price = prices[pmi];
                    var priceMachineCurrent = angular.copy(price);
                    PriceMachineService.save(priceMachineCurrent, function (data) {
                        price = data;
                        NotificationService.notify("success", "machineType.notification.saved");
                        $location.path("machineTypes");
                    });
                }
            });
        }
    };

    $scope.updateTechnicalName = function () {
        if (!$scope.machineType.technicalname) {
            if ($scope.machineType.name) {
                $scope.machineType.technicalname = $scope.machineType.name.toUpperCase();
            }
        } else {
            if ($scope.machineType.name) {
                var oldName = $scope.machineType.name.toUpperCase().substring(0, $scope.machineType.name.length - 1);
                if ($scope.machineType.technicalname === oldName) {
                    $scope.machineType.technicalname = $scope.machineType.name.toUpperCase();
                }
            }
        }
    };

    var updateMemberShipList = function () {
        MembershipTypeService.list(function (mst) {
            $scope.membershipTypes = mst;
            PriceMachineService.list(function (pm) {
                $scope.priceMachines = pm;
                var msti, pmi;
                var getCellule = function (mstId) {
                    for (pmi = 0; pmi < pm.length; pmi++) {
                        if (pm[pmi].machineType.id === $scope.machineType.id &&
                                pm[pmi].membershipType.id === mstId) {
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
app.controller('MachineTypeNewController', function ($scope, $controller) {
    $controller('GlobalMachineTypeEditController', {$scope: $scope});
    $scope.newMachineType = true;
    $scope.machineType = {};
}
);
app.controller('MachineTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalMachineTypeEditController', {$scope: $scope});
    $scope.newMachineType = false;
    $scope.loadMachineType($routeParams.id);
}
);