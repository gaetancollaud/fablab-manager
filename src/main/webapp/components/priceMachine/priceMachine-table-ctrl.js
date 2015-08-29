'use strict';
var app = angular.module('Fablab');
app.controller('PriceMachineTableController', function ($scope, $location,$filter, 
        PriceMachineService, MachineTypeService, MembershipTypeService, NotificationService) {
            $scope.currency = App.CONFIG.CURRENCY + " " + $filter('translate')('priceMachine.hour');
    $scope.save = function () {
        var mt = $scope.prices.machineTypes;
        var mti, msti;
        for (mti = 0; mti < mt.length; mti++) {
            var mst = mt[mti].membershipTypes;
            for (msti = 0; msti < mst.length; msti++) {
                var price = mst[msti].price;
                var priceMachineCurrent = angular.copy(price);
                PriceMachineService.save(priceMachineCurrent, function (data) {
                    price = data;
                    NotificationService.notify("success", "priceMachine.notification.saved");
                    $location.path("priceMachines/table");
                });
            }
        }
    };

    var updatePriceMachineList = function () {
        MachineTypeService.list(function (mt) {
            $scope.machineTypes = mt;
            MembershipTypeService.list(function (mst) {
                $scope.membershipTypes = mst;
                PriceMachineService.list(function (pm) {
                    $scope.priceMachines = pm;
                    var mti, msti, pmi;
                    var getCellule = function (mtId, mstId) {
                        for (pmi = 0; pmi < pm.length; pmi++) {
                            if (pm[pmi].machineType.id === mtId &&
                                    pm[pmi].membershipType.id === mstId) {
                                return pm[pmi];
                            }
                        }
                        return "";
                    };
                    var mtArray = [];
                    var prices = {};
                    for (mti = 0; mti < mt.length; mti++) {
                        var machineType = {};
                        machineType.machineType = mt[mti];
                        mtArray.push(machineType);
                        var mstArray = [];
                        for (msti = 0; msti < mst.length; msti++) {
                            var membershipType = {};
                            membershipType.price = getCellule(mt[mti].id, mst[msti].id);
                            mstArray.push(membershipType);
                        }
                        machineType.membershipTypes = mstArray;
                    }
                    prices.machineTypes = mtArray;
                    $scope.prices = prices;
                });
            });
        });
    };
    updatePriceMachineList();
});

