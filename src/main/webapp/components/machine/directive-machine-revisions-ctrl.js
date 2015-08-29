(function () {
    'use strict';
    angular.module('Fablab').directive('machineRevisions',
            function ($filter, ngTableParams, $location) {
                return {
                    restrict: 'E',
                    scope: {
                        machine: '@machine'
                    },
                    templateUrl: 'components/machine/directive-machine-revisions-view.html',
                    controller: function ($scope, RevisionService) {
                        $scope.currency = App.CONFIG.CURRENCY;
                        $scope.tableParams = new ngTableParams(
                                angular.extend({
                                    page: 1, // show first page
                                    count: 25 // count per page
                                }, $location.search()), {
                            getData: function ($defer, params) {
                                if ($scope.revisions) {
                                    params.total($scope.revisions.length);
                                    $location.search(params.url());
                                    var filteredData = params.filter() ? $filter('filter')($scope.revisions, params.filter()) : $scope.revisions;
                                    var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                                }
                            }
                        });
                        var updateRevisionList = function () {
                            RevisionService.listByMachine($scope.machine, function (data) {
                                $scope.revisions = data;
                                $scope.tableParams.reload();
                            });
                        };
                        updateRevisionList();
                    }
                };
            });
}());