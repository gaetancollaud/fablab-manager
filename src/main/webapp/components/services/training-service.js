(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('TrainingService', function ($log, $resource, $http) {
        var training = $resource(App.API.TRAINING_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.TRAINING_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("TrainingService: remove...");
                training.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.TRAINING_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("TrainingService: soft remove...");
            },
            save: function (trainingParam, successFn, errorFn) {
                $log.debug("TrainingService: save...");
                var saved = training.save(trainingParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("TrainingService: get...");
                var trainingRes = training.get({id: id}, successFn);
                return trainingRes;
            }, 
            getId: function(name, successFn) {
                $http.get(App.API.TRAINING_API + "/getId?name=" + name.latinise()).success(successFn);
            }
        };
    });
}());

