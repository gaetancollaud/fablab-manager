'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTicketStatusEditController', function ($scope, $location,
    TicketStatusService, NotificationService) {
    $scope.selected = {ticketStatus: undefined};
    $scope.loadTicketStatus = function (id) {
        TicketStatusService.get(id, function (data) {
            $scope.ticketStatus = data;
        });
    };
    
    TicketStatusService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].label.toUpperCase());
        }
        $scope.existingValues = res;
    });
    
    $scope.save = function () {
        var ticketStatusCurrent = angular.copy($scope.ticketStatus);
        TicketStatusService.save(ticketStatusCurrent, function (data) {
            $scope.ticketStatus = data;
            NotificationService.notify("success", "ticketStatus.notification.saved");
            $location.path("ticketStatuss");
        });
    };
}
);
app.controller('TicketStatusNewController', function ($scope, $controller) {
    $controller('GlobalTicketStatusEditController', {$scope: $scope});
    $scope.newTicketStatus = true;
    $scope.ticketStatus = new Object();
}
);
    app.controller('TicketStatusEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTicketStatusEditController', {$scope: $scope});
    $scope.newTicketStatus = false;
    $scope.loadTicketStatus($routeParams.id);
}
);

