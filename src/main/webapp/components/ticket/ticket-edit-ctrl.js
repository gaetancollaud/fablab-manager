'use strict';
var app = angular.module('Fablab');
app.controller('GlobalTicketEditController', function ($scope,
        $filter, $rootScope, $location, $routeParams,
        TicketService, NotificationService, StaticDataService, TicketStatusService,
        MachineStatusService, MachineService) {
    $scope.fromMachine = $routeParams.machineId ? true : false;
    $scope.showRole = $rootScope.hasAnyRole('TICKET_MANAGE');
    $scope.selected = {ticket: undefined};
    $scope.loadTicket = function (id) {
        TicketService.get(id, function (data) {
            $scope.ticket = data;
        });

    };

    TicketService.list(function (mstate) {
        var res = [];
        for (var i = 0; i < mstate.length; i++) {
            res.push(mstate[i].title.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function (ticketId) {
        if (!$scope.ticket.closeDate) {
            MachineStatusService.getByLabel("Indisponible", function (data) {
                MachineService.saveStatus($scope.ticket.machine.id, data.id, function (status) {
                    $scope.ticket.machine.machineStatus = status;
                });
            });
        } else {
            TicketService.list(function (tickets) {
                var res = false;
                for (var ti = 0; ti < tickets.length; ti++) {
                    if (tickets[ti].id !== $scope.ticket.id) {
                        //if res = false then alll ticket closed for this machine
                        //Maintient de l'état vrai
                        res = res || (tickets[ti].closeDate === null && tickets[ti].machine.id === $scope.ticket.machine.id);
                    }
                }
                if (!res) {
                    MachineStatusService.getByLabel("Disponible ", function (data) {
                        MachineService.saveStatus($scope.ticket.machine.id, data.id, function (status) {
                            $scope.ticket.machine.machineStatus = status;
                        });
                    });
                }
            });
        }
        var ticketCurrent = angular.copy($scope.ticket);
        TicketService.save(ticketCurrent, function (data) {
            $scope.ticket = data;
            NotificationService.notify("success", "ticket.notification.saved");
            if ($rootScope.hasAnyRole('TICKET_MANAGE')) {
                if (ticketId) {
                    //re-open the ticket
                    $location.path("tickets/ticket-edit/" + ticketId);
                } else {
                    $location.path("tickets");
                }
            } else {
                $location.path("");
            }
        });
    };

    $scope.closeTicket = function () {
        if (!$scope.newTicket) {
            $scope.ticket.closeDate = new Date();
            $scope.ticket.closeUser = $rootScope.connectedUser.user;
            TicketStatusService.findByLabel("CLOS", function (data) {
                $scope.ticket.status = data;
                $scope.save();
            });
        }
    };

    $scope.reOpenTicket = function (ticketId) {
        if (!$scope.newTicket) {
            if (ticketId) {
                //re-open the ticket and save info in note
                $scope.ticket.description += "\n" + $filter('translate')('ticket.reopenDescr') +
                        moment($scope.ticket.closeDate).format('DD.MM.YYYY') + $filter('translate')('ticket.by') +
                        $filter('prettyUser')($scope.ticket.closeUser) + ";";
                $scope.ticket.description += "\n" + $filter('translate')('ticket.reopenDescr2') +
                        moment(new Date()).format('DD.MM.YYYY') + $filter('translate')('ticket.by') +
                        $filter('prettyUser')($rootScope.connectedUser.user) + ";";
            }
            $scope.ticket.closeDate = null;
            $scope.ticket.closeUser = null;
            TicketStatusService.findByLabel("OUVERT", function (data) {
                $scope.ticket.status = data;
                $scope.save(ticketId);
            });
        }
    };

    StaticDataService.loadTicketStatus(function (data) {
        $scope.ticketStatusList = data;
    });
    StaticDataService.loadMachineries(function (data) {
        $scope.machines = data;
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

    $scope.minDate = new Date();
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

}
);
app.controller('TicketNewController', function ($scope, $controller, $rootScope, TicketStatusService) {
    $controller('GlobalTicketEditController', {$scope: $scope});
    $scope.newTicket = true;
    $scope.ticket = {
        creationDate: new Date(),
        creationUser: $rootScope.connectedUser.user
    };
    TicketStatusService.findByLabel("OUVERT", function (data) {
        $scope.ticket.status = data;
    });
}
);
app.controller('TicketNewWithMachineController', function ($scope, $controller,
        $rootScope, $routeParams, TicketStatusService, MachineService) {
    $controller('GlobalTicketEditController', {$scope: $scope});
    $scope.newTicket = true;
    $scope.ticket = {
        creationDate: new Date(),
        creationUser: $rootScope.connectedUser.user
    };
    TicketStatusService.findByLabel("OUVERT", function (data) {
        $scope.ticket.status = data;
    });
    MachineService.get($routeParams.machineId, function (data) {
        $scope.ticket.machine = data;
    });
}
);
app.controller('TicketEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalTicketEditController', {$scope: $scope});
    $scope.newTicket = false;
    $scope.loadTicket($routeParams.id);
}
);

