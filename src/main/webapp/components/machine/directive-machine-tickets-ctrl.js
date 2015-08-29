'use strict';
angular.module('Fablab').directive('machineTickets',
        function ($filter, ngTableParams, $location) {
            return {
                restrict: 'E',
                scope: {
                    machine: '@machine'
                },
                templateUrl: 'components/machine/directive-machine-tickets-view.html',
                controller: function ($scope, TicketService) {
                    $scope.tableParams = new ngTableParams(
                            angular.extend({
                                page: 1, // show first page
                                count: 25 // count per page
                            }, $location.search()), {
                        getData: function ($defer, params) {
                            if ($scope.tickets) {
                                params.total($scope.tickets.length);
                                $location.search(params.url());
                                var filteredData = params.filter() ? $filter('filter')($scope.tickets, params.filter()) : $scope.tickets;
                                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                            }
                        }
                    });
                    var updateTicketList = function () {
                        console.log("machine id " + $scope.machine);
                        TicketService.listByMachine($scope.machine, function (data) {
                            for (var i = 0; i < data.length; i++) {
                                data[i].statusLabel = ""; //initialization of new property 
                                data[i].statusLabel = data[i].status.label; //set the data from nested obj into new property
                            }
                            $scope.tickets = data;
                            $scope.tableParams.reload();
                        });
                    };
                    updateTicketList();
                }
            };
        });