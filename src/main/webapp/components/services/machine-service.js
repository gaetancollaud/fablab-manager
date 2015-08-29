(function () {
    'use strict';
    var app = angular.module('Fablab');
    app.factory('MachineService', function ($log, $resource, $http) {
        var machine = $resource(App.API.MACHINE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.MACHINE_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("MachineStateService: remove...");
                machine.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.MACHINE_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("MachineService: soft remove...");
            },
            save: function (machineParam, successFn, errorFn) {
                $log.debug("MachineService: save...");
                var saved = machine.save(machineParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("MachineService: get...");
                var machineRes = machine.get({id: id}, successFn);
                return machineRes;
            },
            getStatusLabel: function (label, successFn) {
                $http.get(App.API.MACHINE_API + "/getByStatusLabel?label=" + label).success(successFn);
            },
            findSimpleByCode: function (code, successFn) {
                $http.get(App.API.MACHINE_API + "/findSimpleByCode?code=" + code).success(successFn);
            },
            saveStatus: function(machineId,machineStatusId, successFn){
                $http.get(App.API.MACHINE_API + "/saveStatus?machineId=" + machineId +
                        "&machineStatusId=" + machineStatusId).success(successFn);
            }
        };
    });
}());

