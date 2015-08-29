(function () {
    'use strict';
    var app = angular.module('Fablab');
    app.factory('RevisionService', function ($log, $resource, $http) {
        var revision = $resource(App.API.REVISION_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.REVISION_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("RevisionService: remove...");
                revision.remove({id: id}, successFn);
            },
            save: function (revisionParam, successFn, errorFn) {
                $log.debug("RevisionService: save...");
                var saved = revision.save(revisionParam, successFn, errorFn);
                return saved;
            },
             softRemove: function (id, successFn) {
                $http.get(App.API.REVISION_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("RevisionService: soft remove...");
            },
            get: function (id, successFn) {
                $log.debug("RevisionService: get...");
                var revisionRes = revision.get({id: id}, successFn);
                return revisionRes;
            }, 
            listByMachine: function (id, successFn) {
                $http.get(App.API.REVISION_API + "/listByMachine?id=" + id).success(successFn);
            }
        };
    });
}());

