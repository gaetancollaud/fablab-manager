(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('RoleService', function ($log, $resource, $http) {
        var role = $resource(App.API.ROLE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.ROLE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("RoleService: remove...");
                role.remove({id: id}, successFn);
            },
            save: function (roleParam, successFn, errorFn) {
                $log.debug("RoleService: save...");
                var saved = role.save(roleParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("RoleService: get...");
                var roleRes = role.get({id: id}, successFn);
                return roleRes;
            }
        };
    });
}());

