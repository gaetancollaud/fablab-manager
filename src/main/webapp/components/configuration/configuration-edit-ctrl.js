'use strict';
var app = angular.module('Fablab');
app.controller('GlobalConfigurationEditController', function ($scope,$route, $location,
        ConfigurationService, NotificationService) {
    $scope.selected = {configuration: undefined};
    $scope.loadConfiguration = function (id) {
        ConfigurationService.get(id, function (data) {
            $scope.configuration = data;
        });
    };
    $scope.save = function () {
        var configurationCurrent = angular.copy($scope.configuration);
        ConfigurationService.save(configurationCurrent, function (data) {
            $scope.configuration = data;
            NotificationService.notify("success", "configuration.notification.saved");
            $route.reload();
            $location.path("configurations");
        });
    };
}
);
app.controller('ConfigurationEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalConfigurationEditController', {$scope: $scope});
    $scope.newConfiguration = false;
    $scope.loadConfiguration($routeParams.id);
}
);

