
(function () {
    'use strict';
    var app = angular.module('Fablab');
    app.directive('formGroup', [function () {
            return {
                restrict: "C",
                link: function (scope, element, attrs, ctrl) {
                    var input = element.find('input[ng-model],select[ng-model]');
                    if (input) {
                        scope.$watch(function () {
                            return input.hasClass('ng-invalid');
                        }, function (isInvalid) {
                            element.toggleClass('has-error', isInvalid);
                        });
                    }
                }
            };
        }]);
    app.directive('btnCancel', [function () {
            return {
                restrict: "EA",
                template: '<a href="{{link}}" class="btn btn-default btn-glyph">'
                        + '  <span translate="button.cancel"></span>'
                        + '  <span class="glyphicon glyphicon-remove"></span>'
                        + '</a>',
                scope: {
                    link: '@'
                }
            };
        }]);
    app.directive('toggleButton', [function ($filter) {
            return {
                restrict: 'A',
                replace: true,
                transclude: true,
                require: 'ngModel',
                scope: {'ngModel': '='},
                template: '<button type="button" class="btn ng-class:{\'btn-primary\':ngModel, \'btn-default\':!ngModel}" data-toggle="button" ng-click="ngModel = !ngModel" ng-transclude></button>'
            };
        }]);
    app.directive('btnShow', [function () {
            return {
                restrict: "EA",
                template: '<a href="{{link}}" class="btn btn-default btn-sm">'
                        + '  <span class="glyphicon glyphicon-search"></span>'
                        + '</a>',
                scope: {
                    link: '@'
                }
            };
        }]);
    app.directive('btnCreate', [function () {
            return {
                restrict: "EA",
                template: '<a href="{{link}}" class="btn btn-success btn-glyph">'
                        + '  <span translate="button.create"> </span>'
                        + '  <span class="fa fa-fw fa-plus"></span>'
                        + '</a>',
                scope: {
                    link: '@'
                }
            };
        }]);
    app.directive('btnReturn', [function () {
            return {
                restrict: "EA",
                template: '<a href="{{link}}" class="btn btn-default btn-glyph">'
                        + '  <span class="glyphicon glyphicon-menu-left"></span>'
                        + '  <span translate="button.return"></span>'
                        + '</a>',
                scope: {
                    link: '@'
                }
            };
        }]);
    app.directive('btnSubmit', [function () {
            return {
                restrict: "EA",
                template: '<button type="submit" class="btn btn-success btn-glyph" ng-disabled="form.$invalid">'
                        + '  <span translate="{{label}}"></span>'
                        + '  <span class="glyphicon glyphicon-{{icon}}"></span>'
                        + '</button>',
                scope: {
                    form: '=',
                    label: '@',
                    icon: '@'
                },
                controller: function ($scope) {
                    if (!$scope.label) {
                        $scope.label = 'button.save';
                    }
                    if (!$scope.icon) {
                        $scope.icon = 'floppy-disk';
                    }
                }
            };
        }]);
}());