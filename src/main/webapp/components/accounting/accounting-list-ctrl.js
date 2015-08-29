(function () {
    'use strict';
    var app = angular.module('Fablab');
    app.controller('AccountingListController', function ($scope, $location,$filter,
            AccountingService, ngTableParams) {
        $scope.filename = "FabLab-Manager_Acounting_export";
        $scope.tableParams = new ngTableParams(
                angular.extend({
                    page: 1, // show first page
                    count: 25, // count per page
                }, $location.search()), {
            getData: function ($defer, params) {
                if ($scope.history) {
                    params.total($scope.history.length);
                    $location.search(params.url());
                    var filteredData = params.filter() ? $filter('filter')($scope.history, params.filter()) : $scope.history;
                    var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            }
        });

        $scope.criteria = {
            dateFrom: null,
            dateTo: null
        };
        $scope.intervals = [{
                label: 'today',
                unit: 'day',
                delta: 0
            }, {
                label: 'yesterday',
                unit: 'day',
                delta: 1
            }, {
                label: 'thisMonth',
                unit: 'month',
                delta: 0
            }, {
                label: 'lastMonth',
                unit: 'month',
                delta: 1
            }, {
                label: 'thisYear',
                unit: 'year',
                delta: 0
            }, {
                label: 'lastYear',
                unit: 'year',
                delta: 1
            }];


        $scope.dateManuallyUpdated = function () {
            $scope.selectedInterval = null;
        };

        $scope.periodPreset = function (interval) {
            var start, end;
            var u = interval.unit;
            start = moment().startOf(u);
            end = moment().endOf(u);
            if (interval.delta !== 0) {
                start = start.subtract(interval.delta, u);
                end = end.subtract(interval.delta, u);
            }
            $scope.criteria.dateFrom = start.toDate();
            $scope.criteria.dateTo = end.toDate();
            $scope.selectedInterval = interval;
            $scope.updateAccounting();
        };



        var computeInAndOut = function () {
            var moneyIn = 0;
            var sell = 0;
            angular.forEach($scope.history, function (h) {
                if (h.amount > 0) {
                    moneyIn += h.amount;
                } else {
                    sell -= h.amount;
                }
            });
            $scope.results = {
                moneyIn: moneyIn,
                sell: sell,
                delta: moneyIn - sell
            };
        };

        $scope.updateAccounting = function () {
            AccountingService.search($scope.criteria.dateFrom, $scope.criteria.dateTo, function (data) {
                $scope.history = data;
                computeInAndOut();
                $scope.tableParams.reload();
            });
        };

        $scope.export = function () {
            var hist = $scope.history;
            var tabRet = [];
            for (var i = 0; i < hist.length; i++) {
                tabRet.push({
                    date: moment(hist[i].date).format(),
                    type: hist[i].type,
                    accountCredit: hist[i].account_CREDIT,
                    accountDebit: hist[i].account_DEBIT,
                    credit: hist[i].amount > 0 ? hist[i].amount : "",
                    debit: hist[i].amount <= 0 ? hist[i].amount : "",
                    user: hist[i].user.lastname.toUpperCase() + " " + hist[i].user.firstname,
                    detail: hist[i].detail.latinise(),
                    comment: hist[i].comment.latinise()
                });
            }
            return tabRet;
        };

        $scope.periodPreset($scope.intervals[2]);//this month



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
        $scope.updateAccounting();
    });

}());