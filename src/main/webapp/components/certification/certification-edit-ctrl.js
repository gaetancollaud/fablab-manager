'use strict';
var app = angular.module('Fablab');
app.controller('GlobalCertificationEditController', function ($scope, $routeParams, $location,
        $rootScope, CertificationService, NotificationService, StaticDataService, UserService) {
    $scope.currency = App.CONFIG.CURRENCY;

    $scope.showRole = $rootScope.hasAnyRole('TRAINING_MANAGE');
    $scope.fromTraining = $routeParams.trainingId ? true : false;
    $scope.selected = {certification: undefined};
    $scope.loadCertification = function (id) {
        CertificationService.get(id, function (data) {
            $scope.certification = data;
            setLists();
        });
    };

    CertificationService.list(function (certif) {
        var res = [];
        for (var i = 0; i < certif.length; i++) {
            res.push(certif[i].name.toUpperCase());
        }
        $scope.existingValues = res;
    });

    $scope.save = function () {
        if ($scope.newCertification) {
            var certificationCurrent = angular.copy($scope.certification);
            CertificationService.save(certificationCurrent, function (data) {
                $scope.certification = data;
                NotificationService.notify("success", "certification.notification.saved");
                CertificationService.getId(data.name, function (withId) {
                    $location.path("certifications/certification-edit/" + withId.id);
                });
            });
        } else {
            $scope.certification.users = $scope.certifiedUsers;
            var userIds = [];
            var ui;
            if ($scope.certification.users) {
                for (ui = 0; ui < $scope.certification.users.length; ui++) {
                    userIds.push($scope.certification.users[ui].id);
                }
            }
            //Control of prerequisites 
            CertificationService.failedUser($scope.certification.id, userIds, function (failedUsers) {
                if (failedUsers.length !== 0) {
                    var fui;
                    var fuNames = "";
                    for (fui = 0; fui < failedUsers.length; fui++) {
                        fuNames += failedUsers[fui];
                        fuNames += ", ";
                    }
                    fuNames = fuNames.substring(0, parseInt(fuNames.length - 2));
                    NotificationService.notify("error", "certification.notification.failed", fuNames.toString());
                } else {
                    var certificationCurrent = angular.copy($scope.certification);
                    CertificationService.save(certificationCurrent, function (data) {
                        $scope.certification = data;
                        NotificationService.notify("success", "certification.notification.saved");
                        $location.path("certifications");
                    });
                }
            });


        }
    };

    $scope.updatePriceAndSetName = function () {
        if ($scope.certification) {
            if ($scope.certification.training) {
                $scope.certification.certificationPrice = $scope.certification.training.price;
            }
            if (!$scope.certification.name) {
                if ($scope.certification.training) {
                    $scope.certification.name = "Certification " + $scope.certification.training.name;
                }
            }
        }
    };

    $scope.settings = {
        bootstrap2: false,
        moveOnSelect: true,
        postfix: '_helperz',
        selectMinHeight: 200,
        filter: true,
        filterValues: true
    };

    StaticDataService.loadMachineTypes(function (data) {
        $scope.machineTypeList = data;
    });
    var setLists = function () {
        UserService.list(function (users) {
            if ($scope.certification) {
                $scope.availableUsers = users;
                $scope.certifiedUsers = $scope.certification.users;
            }
        });
    };

    $scope.minDate = new Date();
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
    StaticDataService.loadTrainings(function (data) {
        $scope.trainingList = data;
    });
}
);
app.controller('CertificationNewController', function ($scope, $controller) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = true;
    $scope.certification = {
        certificationDate: new Date()
    };
}
);
app.controller('CertificationEditController', function ($scope, $routeParams, $controller) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = false;
    $scope.loadCertification($routeParams.id);
}
);
app.controller('CertificationNewWithTrainingController', function ($scope, $routeParams, $controller, TrainingService) {
    $controller('GlobalCertificationEditController', {$scope: $scope});
    $scope.newCertification = true;
    $scope.certification = {
        certificationDate: new Date()
    };
    TrainingService.get($routeParams.trainingId, function (data) {
        $scope.certification.training = data;
        $scope.certification.certificationPrice = data.price;
    });
}
);

