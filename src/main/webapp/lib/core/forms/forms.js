(function() {
  'use strict';

  var form = angular.module('core');

  form.directive('scFormGroup', ['$log', function($log) {

    return {
      restrict: 'EA',
      replace: true,
      transclude: true,
      template: '<div class="sc-form-group" ng-transclude><button type="submit" ng-click="doDefault()" ng-if="defaultAction != undefined">submit</button></div>',
      scope: {
        readOnly: '='
      },
      controller: ['$scope', function($scope) {
        var instance = this;

        this.components = [];
        this.listeners = [];

        var refreshListeners = function() {
          for(var i = 0; i < instance.listeners.length; i++) {
            var l = instance.listeners[i];
            l.readOnlyChanged($scope.readOnly);
          }
        };

        var setComponentReadOnly = function(ro, cmp) {
          if(cmp.setReadOnly) {
            cmp.setReadOnly(ro);
          }
        };

        this.setReadOnly = function(ro) {
          $log.debug('Form-group readonly: ' + ro);
          for(var i = 0; i < instance.components.length; i++) {
            var cmp = instance.components[i];
            setComponentReadOnly(ro, cmp);
          }
          refreshListeners();
        };

        $scope.$watch('readOnly', function(newVal, oldVal) {
          if(newVal !== oldVal) {
            var readOnly = !!newVal;
            instance.setReadOnly(readOnly);
          }
        });

        /**
         * Add a component to this form
         */
        this.registerComponent = function(cmp) {
          instance.components.push(cmp);
          setComponentReadOnly($scope.readOnly, cmp);
        };

        /**
         * Listener should have a readOnlyChanged(boolean) method
         */
        this.registerListener = function(listener) {
          instance.listeners.push(listener);

          listener.readOnlyChanged($scope.readOnly);
        };
      }]
    };
  }]);

  form.directive('scForm', ['$log', function($log) {

    return {
      restrict: 'EA',
      replace: true,
      transclude: true,
      template: '<form novalidate class="sc-form"><div ng-transclude></div><button style="visibility: hidden; position: absolute;" type="submit" ng-click="doDefault()" ng-if="defaultAction != undefined">submit</button></form>',
      scope: {
        defaultAction: '&'
      },
      require: '^scFormGroup',
      controller: ['$scope', function($scope) {
        var instance = this;

        $scope.doDefault = function() {
          if($scope.defaultAction) {
            $log.debug('scFormGroup: Doing default action...');
            $scope.defaultAction();
          }
        };
      }]
    };
  }]);


  form.directive('scReadWrite', [function() {
    return {
      restrict: 'EA',
      replace: true,
      transclude: true,
      template: '<span class="read-write">' +
        '<span ng-show="!readOnly" ng-transclude></span>' +
        '<span ng-class="css" class="read-write-disabled" ng-if="readOnly">{{disabledValue}}</span>' +
        '</span>',
      scope: {
        disabledValue: '=',
        css: '@'
      },
      require: '^scFormGroup',
      link: function($scope, element, attrs, formGroup) {
        $scope.readOnly = false;
        $scope.setReadOnly = function(ro) {
          $scope.readOnly = ro;
        };

        formGroup.registerComponent($scope);
      }
    };
  }]);
}());

