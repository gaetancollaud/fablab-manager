'use strict';
var app = angular.module('Fablab');
app.controller('GlobalRevisionEditController', function ($scope, $location, $routeParams,
        RevisionService, NotificationService, StaticDataService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.fromMachine = $routeParams.machineId ? true : false;
    $scope.selected = {revision: undefined};
    $scope.loadRevision = function (id) {
        RevisionService.get(id, function (data) {
            $scope.revision = data;
            if ($scope.revision.revisionDate) {
                $scope.revision.revisionDate = new Date($scope.revision.revisionDate);
            }
            if ($scope.revision.revisionTime) {
                $scope.revision.revisionTime = new Date($scope.revision.revisionTime);
            }
        });
    };
    $scope.save = function () {
        var revisionCurrent = angular.copy($scope.revision);
        RevisionService.save(revisionCurrent, function (data) {
            $scope.revision = data;
            NotificationService.notify("success", "revision.notification.saved");
            $location.path("revisions");
        });
    };

    StaticDataService.loadMachineries(function (data) {
        $scope.machineList = data;
    });

    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);
    $scope.events =
            [
                {
                    date: tomorrow,
                    status: 'full'
                },
                {
                    date: afterTomorrow,
                    status: 'partially'
                }
            ];

    $scope.getDayClass = function (date, mode) {
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    };

    $scope.hstep = 1;
    $scope.mstep = 15;
});
app.controller('RevisionNewController', function ($scope, $controller, $rootScope) {
    $controller('GlobalRevisionEditController', {$scope: $scope});
    $scope.newRevision = true;
    $scope.revision = {
        revisionDate: new Date(),
        revisionTime: new Date(),
        user: $rootScope.connectedUser.user
    };
}
);
app.controller('RevisionEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalRevisionEditController', {$scope: $scope});
    $scope.newRevision = false;
    $scope.loadRevision($routeParams.id);
}
);
app.controller('RevisionNewWithMachineController', function ($scope, $routeParams, $controller, MachineService, $rootScope) {
    $controller('GlobalRevisionEditController', {$scope: $scope});
    $scope.newRevision = true;
    $scope.revision = {
        revisionTime: new Date(),
        revisionDate: new Date(),
        user: $rootScope.connectedUser.user
    };
    MachineService.get($routeParams.machineId, function (data) {
        $scope.revision.machine = data;
    });
}
);

