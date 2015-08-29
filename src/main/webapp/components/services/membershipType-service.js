(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('MembershipTypeService', function ($log, $resource, $http) {
        var membershipType = $resource(App.API.MEMBERSHIP_TYPE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.MEMBERSHIP_TYPE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("MembershipTypeService: remove...");
                membershipType.remove({id: id}, successFn);
            },
            save: function (membershipTypeParam, successFn, errorFn) {
                $log.debug("MembershipTypeService: save...");
                var saved = membershipType.save(membershipTypeParam, successFn, errorFn);
                return saved;
            },
             softRemove: function (id, successFn) {
                $http.get(App.API.MEMBERSHIP_TYPE_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("MembershipTypeService: soft remove...");
            },
            get: function (id, successFn) {
                $log.debug("MembershipTypeService: get...");
                var membershipTypeRes = membershipType.get({id: id}, successFn);
                return membershipTypeRes;
            }, 
            getPrices: function (id, successFn) {
                $http.get(App.API.MEMBERSHIP_TYPE_API + "/getPrices?id=" + id).success(successFn);
            },
            getId: function (name, successFn) {
                $http.get(App.API.MEMBERSHIP_TYPE_API + "/getId?name=" + name.latinise()).success(successFn);
            }
        };
    });
}());

