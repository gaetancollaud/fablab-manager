'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTrainingLevelEditController', function ($scope, $location,
    TrainingLevelService, NotificationService) {
    $scope.selected = {trainingLevel: undefined};
    $scope.loadTrainingLevel = function (id) {
        TrainingLevelService.get(id, function (data) {
            $scope.trainingLevel = data;
        });
    };
    
    TrainingLevelService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
    
    $scope.save = function () {
        var trainingLevelCurrent = angular.copy($scope.trainingLevel);
        TrainingLevelService.save(trainingLevelCurrent, function (data) {
            $scope.trainingLevel = data;
            NotificationService.notify("success", "trainingLevel.notification.saved");
            $location.path("trainingLevels");
        });
    };
}
);
app.controller('TrainingLevelNewController', function ($scope, $controller) {
    $controller('GlobalTrainingLevelEditController', {$scope: $scope});
    $scope.newTrainingLevel = true;
    $scope.trainingLevel = new Object();
}
);
    app.controller('TrainingLevelEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTrainingLevelEditController', {$scope: $scope});
    $scope.newTrainingLevel = false;
    $scope.loadTrainingLevel($routeParams.id);
}
);

