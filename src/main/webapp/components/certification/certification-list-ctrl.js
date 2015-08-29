'use strict';
var app = angular.module('Fablab');
app.controller('CertificationListController', function ($scope, $filter, $location, $rootScope,
        ngTableParams, CertificationService, NotificationService) {
    $scope.currency = App.CONFIG.CURRENCY;
    $scope.showRole = $rootScope.hasAnyRole('TRAINING_MANAGE');
    $scope.tableParams = new ngTableParams(
            angular.extend({
                page: 1, // show first page
                count: 25, // count per page
                sorting: {
                    certificationDate: 'desc'
                }
            }, $location.search()), {
        getData: function ($defer, params) {
            if ($scope.certifications) {
                params.total($scope.certifications.length);
                $location.search(params.url());
                var filteredData = params.filter() ? $filter('filter')($scope.certifications, params.filter()) : $scope.certifications;
                var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
            }
        }
    });
    var updateCertificationList = function () {
        CertificationService.list(function (data) {
            for (var i = 0; i < data.length; i++) {
                data[i].trainingName = ""; //initialization of new property 
                data[i].trainingName = data[i].training.name;  //set the data from nested obj into new property
            }
            $scope.certifications = data;
            $scope.tableParams.reload();
        });
    };
    $scope.remove = function (certification) {
        CertificationService.remove(certification.id, function () {
            NotificationService.notify("success", "certification.notification.removed");
            updateCertificationList();
        });
    };
    $scope.softRemove = function (certification) {
        CertificationService.softRemove(certification.id, function () {
            NotificationService.notify("success", "certification.notification.removed");
            updateCertificationList();
        });
    };
    updateCertificationList();
});

