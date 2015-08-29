'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTrainingEditController', function ($scope, $location, $rootScope,
        TrainingService, NotificationService, StaticDataService) {
    $scope.currency = App.CONFIG.CURRENCY;

    $scope.showRole = $rootScope.hasAnyRole('TRAINING_MANAGE');
    $scope.selected = {training: undefined};
    $scope.loadTraining = function (id) {
        TrainingService.get(id, function (data) {
            $scope.training = data;
            setList();
        });
    };

    TrainingService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].name.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function () {
        if ($scope.newTraining) {
            var trainingCurrent = angular.copy($scope.training);
            TrainingService.save(trainingCurrent, function (data) {
                $scope.training = data;
                NotificationService.notify("success", "training.notification.saved");
                TrainingService.getId(data.name, function (withId) {
                    $location.path("trainings/training-edit/" + withId.id);
                });
            });
        } else {
            $scope.training.prerequisites = $scope.assignedPrerequisites;
            var trainingCurrent = angular.copy($scope.training);
            TrainingService.save(trainingCurrent, function (data) {
                $scope.training = data;
                NotificationService.notify("success", "training.notification.saved");
                $location.path("trainings");
            });
        }
    };
    StaticDataService.loadTrainingLevels(function (data) {
        $scope.trainingLevelList = data;
    });
    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });

    var setList = function () {
        TrainingService.list(function (trainings) {
            if ($scope.training) {
                var availableTrainings = [];
                for(var i = 0; i < trainings.length; i++){
                    if(trainings[i].id !== $scope.training.id){
                        availableTrainings.push(trainings[i]);
                    }
                }
                $scope.availablePrerequisites = availableTrainings;
                $scope.assignedPrerequisites = $scope.training.prerequisites;
            }
        });
    };

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };
}
);
app.controller('TrainingNewController', function ($scope, $controller) {
    $controller('GlobalTrainingEditController', {$scope: $scope});
    $scope.newTraining = true;
    $scope.training = new Object();
}
);
app.controller('TrainingEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTrainingEditController', {$scope: $scope});
    $scope.newTraining = false;
    $scope.loadTraining($routeParams.id);
}
);

