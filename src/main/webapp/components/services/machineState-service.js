(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('MachineStateService', function ($log, $resource, $http) {
        var machineState = $resource(App.API.MACHINE_STATE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.MACHINE_STATE_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("MachineStateService: remove...");
                machineState.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.MACHINE_STATE_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("MachineStateService: soft remove...");
            },
            save: function (machineStateParam, successFn, errorFn) {
                $log.debug("MachineStateService: save...");
                var saved = machineState.save(machineStateParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("MachineStateService: get...");
                var machineStateRes = machineState.get({id: id}, successFn);
                return machineStateRes;
            }
        };
    });
}());

