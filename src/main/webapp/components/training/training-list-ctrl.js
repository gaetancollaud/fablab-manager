'use strict';
var app = angular.module('Fablab');
app.controller('TrainingListController', function ($scope, $filter, $location, $rootScope,
        ngTableParams, TrainingService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('TRAINING_MANAGE');
    $scope.btnTitle =  $filter('translate')('certification.btnTitle');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    name: 'asc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.trainings) {
                params.total($scope.trainings.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.trainings, params.filter()) : $scope.trainings;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateTrainingList = function () {
        TrainingService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].trainingLevelLabel = ""; //initialization of new property 
                data[i].trainingLevelLabel = data[i].trainingLevel.label;  //set the data from nested obj into new property
                data[i].machineTypeName = ""; //initialization of new property 
                data[i].machineTypeName = data[i].machineType.name;  //set the data from nested obj into new property
            }
            $scope.trainings = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (training) {
        TrainingService.remove(training.id, function () {
            NotificationService.notify("success", "training.notification.removed");
            updateTrainingList();
        });
    };
    $scope.softRemove = function (training) {
        TrainingService.softRemove(training.id, function () {
            NotificationService.notify("success", "training.notification.removed");
            updateTrainingList();
        });
    };
    updateTrainingList();
});

