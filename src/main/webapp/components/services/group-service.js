'use strict';
var app = angular.module('Fablab');
app.factory('GroupService', function ($log, $resource, $http) {
    var group = $resource(App.API.GROUP_API + "/:id", {id: '@id'});
    return {
        list: function (successFn) {
            $http(
                    {
                        method: 'GET',
                        url: App.API.GROUP_API
                    }
            ).success(successFn);
        },
        save: function (groupParam, successFn, errorFn) {
            $log.debug("GroupService: save...");
            var saved = group.save(groupParam, successFn, errorFn);
            return saved;
        },
        softRemove: function (id, successFn) {
            $http.get(App.API.GROUP_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("GroupService: soft remove...");
        },
        get: function (id, successFn) {
            $log.debug("GroupService: get...");
            var groupRes = group.get({id: id}, successFn);
            return groupRes;
        },
        getId: function (technicalname, successFn) {
                $http.get(App.API.GROUP_API + "/getId?technicalname=" + technicalname.latinise()).success(successFn);
            }
    };
});


