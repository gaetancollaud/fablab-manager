'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventTypeEditController', function ($scope, $location,
    EventTypeService, NotificationService) {
    $scope.selected = {eventType: undefined};
    $scope.loadEventType = function (id) {
        EventTypeService.get(id, function (data) {
            $scope.eventType = data;
        });
    };
    $scope.save = function () {
        var eventTypeCurrent = angular.copy($scope.eventType);
        EventTypeService.save(eventTypeCurrent, function (data) {
            $scope.eventType = data;
            NotificationService.notify("success", "eventType.notification.saved");
            $location.path("eventTypes");
        });
    };
    EventTypeService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
}
);
app.controller('EventTypeNewController', function ($scope, $controller) {
    $controller('GlobalEventTypeEditController', {$scope: $scope});
    $scope.newEventType = true;
    $scope.eventType = new Object();
}
);
    app.controller('EventTypeEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventTypeEditController', {$scope: $scope});
    $scope.newEventType = false;
    $scope.loadEventType($routeParams.id);
}
);

