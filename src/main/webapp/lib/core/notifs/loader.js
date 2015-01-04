(function () {
  'use strict';

  var core = angular.module('core');

  core.directive('scLoaderIndicator', function($rootScope) {
    return {
      restrict: 'E',
      scope: true,
      transclude: true,
      template: '<div class="loader-panel" ng-if="isShown" ng-transclude></div>',
      link: function($scope, $element, $attrs) {
        console.log($rootScope);
        $rootScope.$on('core.loaderService:isHidden', function(event, isHidden) {
          $scope.isShown = !isHidden;
        });
      }
    };
  });

  core.factory('loaderService', ['$rootScope', function($rootScope) {
    var showCounter = 0;

    return {
      show: show,
      hide: hide,
      close: close
    };

    function show() {
      showCounter += 1;
      if(showCounter > 0) {
        $rootScope.$emit('core.loaderService:isHidden', false);
      }
    }

    function hide() {
      showCounter -= 1;
      if(showCounter <= 0) {
        showCounter = 0;
        $rootScope.$emit('core.loaderService:isHidden', true);
      }
    }

    function close() {
      showCounter = 0;
      $rootScope.$emit('core.loaderService:isHidden', true);
    }
  }]);

}());

