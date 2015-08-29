(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('TrainingLevelService', function ($log, $resource, $http) {
        var trainingLevel = $resource(App.API.TRAINING_LEVEL_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.TRAINING_LEVEL_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("TrainingLevelService: remove...");
                trainingLevel.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.TRAINING_LEVEL_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("TrainingLevelService: soft remove...");
            },
            save: function (trainingLevelParam, successFn, errorFn) {
                $log.debug("TrainingLevelService: save...");
                var saved = trainingLevel.save(trainingLevelParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("TrainingLevelService: get...");
                var trainingLevelRes = trainingLevel.get({id: id}, successFn);
                return trainingLevelRes;
            }
        };
    });
}());

