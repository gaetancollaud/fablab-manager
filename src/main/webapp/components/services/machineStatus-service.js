(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('MachineStatusService', function ($log, $resource, $http) {
        var machineStatus = $resource(App.API.MACHINE_STATUS_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.MACHINE_STATUS_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("MachineStatusService: remove...");
                machineStatus.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.MACHINE_STATUS_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("MachineStatusService: soft remove...");
            },
            save: function (machineStatusParam, successFn, errorFn) {
                $log.debug("MachineStatusService: save...");
                var saved = machineStatus.save(machineStatusParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("MachineStatusService: get...");
                var machineStatusRes = machineStatus.get({id: id}, successFn);
                return machineStatusRes;
            },
            getByLabel: function (label, successFn) {
                $http.get(App.API.MACHINE_STATUS_API + "/getByLabel?label=" + label).success(successFn);
            }
        };
    });
}());

