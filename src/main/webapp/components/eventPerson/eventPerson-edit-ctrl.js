'use strict';
var app = angular.module('Fablab');
app.controller('GlobalEventPersonEditController', function ($scope, $location, UserService,
        EventModuleService, EventPersonService, NotificationService) {
    $scope.selected = {eventPerson: undefined};
    $scope.loadEventPerson = function (id) {
        EventPersonService.get(id, function (data) {
            $scope.eventPerson = data;
            setLists();
        });
    };

    $scope.save = function () {
        if ($scope.newEventPerson) {
            var eventPersonCurrent = angular.copy($scope.eventPerson);
            EventPersonService.save(eventPersonCurrent, function (data) {
                $scope.eventPerson = data;
                NotificationService.notify("success", "eventPerson.notification.saved");
                EventPersonService.getId(data.email, function (withId) {
                    $location.path("eventPersons/eventPerson-edit/" + withId.id);
                });
            });
        } else {

            $scope.eventPerson.acquiredModules = $scope.acquiredModules;
            var moduleIds = [];
            var ui;
            if ($scope.acquiredModules) {
                for (ui = 0; ui < $scope.acquiredModules.length; ui++) {
                    moduleIds.push($scope.acquiredModules[ui].id);
                }
            }
            //Control of prerequisites 
            EventPersonService.failedModules($scope.eventPerson.id, moduleIds, function (failedacquiredModules) {
                if (failedacquiredModules.length !== 0) {
                    var fui;
                    var fuNames = "";
                    for (fui = 0; fui < failedacquiredModules.length; fui++) {
                        fuNames += failedacquiredModules[fui];
                        fuNames += ", ";
                    }
                    fuNames = fuNames.substring(0, parseInt(fuNames.length - 2));
                    NotificationService.notify("error", "eventPerson.notification.failed", fuNames.toString());
                } else {
                    var eventPersonCurrent = angular.copy($scope.eventPerson);
                    console.log(eventPersonCurrent);
                    EventPersonService.save(eventPersonCurrent, function (data) {
                        $scope.eventPerson = data;
                        NotificationService.notify("success", "eventPerson.notification.saved");
                        $location.path("eventPersons");
                    });
                }
            });
        }
    };

    EventPersonService.list(function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            res.push(data[i].email.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };

    var setLists = function () {
        EventModuleService.list(function (availableModules) {
            if ($scope.eventPerson) {
                $scope.availableModules = availableModules;
                $scope.acquiredModules = $scope.eventPerson.acquiredModules;
            }
        });
    };
}
);
app.controller('EventPersonNewController', function ($scope, $controller) {
    $controller('GlobalEventPersonEditController', {$scope: $scope});
    $scope.newEventPerson = true;
    $scope.eventPerson = new Object();
}
);
app.controller('EventPersonEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalEventPersonEditController', {$scope: $scope});
    $scope.newEventPerson = false;
    $scope.loadEventPerson($routeParams.id);
}
);

