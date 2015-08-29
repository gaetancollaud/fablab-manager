'use strict';
var app = angular.module('Fablab');
app.controller('MachineTestListController', function ($scope, $resource, $filter, $location,
        ngTableParams, MachineTypeService, NotificationService, DTOptionsBuilder) {

    /*
     MachineTypeService.list(function (machinesTypes) {
     $scope.machineTypes = machinesTypes;
     });
     
     $resource('data.json').query().$promise.then(function(persons) {
     vm.persons = persons;
     });*/



    var updateMachineTypeList = function () {
        $scope.dtOptions = DTOptionsBuilder.fromFnPromise(function () {
            return MachineTypeService.list();
        }).withPaginationType('full_numbers')
             .withColumnFilter({
             aoColumns: [{
             type: 'text',
             bRegex: true,
             bSmart: true
             }, {
             type: 'text',
             bRegex: true,
             bSmart: true
             }]
             });
        
        /*
        MachineTypeService.list(function (data) {
           // $scope.machineTypes = data;
            /*$scope.dtOptions = DTOptionsBuilder.$scope.dtOptions = DTOptionsBuilder.fromFnPromise(function () {
                return MachineTypeService.list();
            }).withBootstrap(); //*/
            /*.withPaginationType('full_numbers')
             .withColumnFilter({
             aoColumns: [{
             type: 'text',
             bRegex: true,
             bSmart: true
             }, {
             type: 'text',
             bRegex: true,
             bSmart: true
             }]
             });


        });
        */
    };


    $scope.softRemove = function (machineTypeId) {
        MachineTypeService.softRemove(machineTypeId, function () {
            NotificationService.notify("success", "machineType.notification.removed");
            updateMachineTypeList();
        });
    };
    updateMachineTypeList();
});

