(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('UserService', function ($log, $resource, $http) {

        var User = $resource(App.API.USER_API + "/:id", {id: '@id'});

        return {
            updatePassword: function (user, successFn) {
                $http({
                    method: 'POST',
                    url: App.API.USER_API + "/password",
                    data: user
                }).success(function (data, status, headers, config) {
                    successFn(data);
                });
            },
            list: function (successFn) {
                $http({
                    method: 'GET',
                    url: App.API.USER_API
                }).success(function (data, status, headers, config) {
                    successFn(data);
                });
            },
            remove: function (id, successFn) {
                User.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.USER_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("UserService: soft remove...");
            },
            save: function (user, successFn, errorFn) {
                var saved = User.save(user, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                var prj = User.get({id: id}, successFn);
                return prj;
            },
            updateMailingList: function (successFn) {
                $http({
                    method: 'GET',
                    url: App.API.USER_API + "/updateMailingList"
                }).success(successFn);
            },
            canUse: function (machineTypeId, userId, successFn) {
                $http.get(App.API.USER_API + "/canUse?machineTypeId=" + machineTypeId +
                        "&userId=" + userId).success(successFn);
            },
            hasRole: function (userId, role, successFn) {
                $http.get(App.API.USER_API + "/hasRole?userId=" + userId +
                        "&role=" + role).success(successFn);
            },
            balance: function (userId, successFn) {
                $http.get(App.API.USER_API + "/balance?userId=" + userId).success(successFn);
            }
        };
    });
}());
