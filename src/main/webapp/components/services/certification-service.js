(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('CertificationService', function ($log, $resource, $http) {
        var certification = $resource(App.API.CERTIFICATION_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.CERTIFICATION_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("CertificationService: remove...");
                certification.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.CERTIFICATION_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("CertificationService: soft remove...");
            },
            save: function (certificationParam, successFn, errorFn) {
                $log.debug("CertificationService: save...");
                var saved = certification.save(certificationParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("CertificationService: get...");
                var certificationRes = certification.get({id: id}, successFn);
                return certificationRes;
            },
            getId: function (name, successFn) {
                $http.get(App.API.CERTIFICATION_API + "/getId?name=" + name.latinise()).success(successFn);
            },
            failedUser : function (certificationId, userIds, successFn) {
                $http.get(App.API.CERTIFICATION_API + "/failedUser?certificationId=" + certificationId + "&userIds=" + userIds).success(successFn);
            }
        };
    });
}());

