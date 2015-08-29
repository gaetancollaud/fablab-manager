'use strict';
var app = angular.module('Fablab');
app.controller('TrainingLevelListController', function ($scope, $filter, $location,
        ngTableParams, TrainingLevelService, NotificationService) {
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    label:'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.trainingLevels) {
                params.total($scope.trainingLevels.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.trainingLevels, params.filter()) : $scope.trainingLevels;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTrainingLevelList = function () {
        TrainingLevelService.list(function (data) {
            $scope.trainingLevels = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (trainingLevel) {
        TrainingLevelService.remove(trainingLevel.id, function () {
            NotificationService.notify("success", "trainingLevel.notification.removed");
            updateTrainingLevelList();
        });
    };
    $scope.softRemove = function (trainingLevel) {
        TrainingLevelService.softRemove(trainingLevel.id, function () {
            NotificationService.notify("success", "trainingLevel.notification.removed");
            updateTrainingLevelList();
        });
    };
    updateTrainingLevelList();
});

