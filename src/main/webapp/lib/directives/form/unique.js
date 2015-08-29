'use strict';
var app = angular.module('Fablab');
app.directive('duplicate', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                var duplicate = scope[attrs.duplicate];
                if (duplicate.indexOf(viewValue.toUpperCase()) !== -1) {
                    ctrl.$setValidity('duplicate', false);
                    return undefined;
                } else {
                    ctrl.$setValidity('duplicate', true);
                    return viewValue;
                }
            });
        }
    };
});