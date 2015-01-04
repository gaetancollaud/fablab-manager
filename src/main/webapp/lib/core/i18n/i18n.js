(function() {
  'use strict';

  var i18n = angular.module('core.i18n', []);

  i18n.factory('i18nService', ['$log', function($log) {
    var enums = {};
    
    function initEnums() {
      for (var key in APP.Constants.Messages) {
        var msg = APP.Constants.Messages[key];
        if (key.indexOf('common.data.enum') === 0) {
          // We have an enum...
          var splitted = key.split('.');
          var name = splitted[3];
          var value = splitted[4];
          var order = splitted[5];
          if (!enums[name]) {
            enums[name] = [];
          }
          enums[name].splice(order-1, 0, {value: value, text: msg});
        }
      }
    }
    initEnums();
    
    return {
      localize: localize,
      localizeWithParams: localizeWithParams,
      exists: exists,
      getLocale: function() {
        return APP.LOCALE;
      },
      listEnum: listEnum,
      localizeEnum: localizeEnum
    };

    function localize(key) {
      var message = APP.Constants.Messages[key];
      if(!message) {
        message = key;
        $log.warn('i18nService: Could not find message for key ' + key);
      }
      return message;
    }

    function exists(key) {
      var message = APP.Constants.Messages[key];
      return !(!message);
    }

    function localizeWithParams(key, params) {
      var message = APP.Constants.Messages[key];
      if(!message) {
        message = key;
        $log.warn('i18nService: Could not find message for key ' + key);
      }
      message = message.replace('{}', params);
      return message;
    }
    
    /**
     * List an enumeration. Return an object {value, text}
     */
    function listEnum(name) {
      if (!enums[name]) {
        $log.warn('i18nService: could not find enum with name ' + name);
        return [];
      }
      return enums[name];
    }
    /**
     * List an enumeration. Return an object {value, text}
     */
    function localizeEnum(name, value) {
      var values = listEnum(name);
      for (var i=0; i<values.length; i++) {
        if (value === values[i].value) {
          return values[i].text;
        }
      }
      $log.warn('i18nService: Could not find enum name ' + name + ' and value ' + value);
      return value;
    }
  }]);


  i18n.directive('i18nKey', ['i18nService', function(i18nService) {
    return {
      transclude: true,
      scope: {
        'i18nKey': '@'
      },
      restrict: 'A',
      template: '{{message}} <span ng-transclude></span>',
      link: function(scope, element, attrs) {
        scope.$watch('i18nKey', function(value) {
          scope.message = i18nService.localize(value);
        });
      }
    };
  }]);

  i18n.directive('i18n', ['i18nService', function(i18nService) {
    return {
      transclude: true,
      scope: {
        'key': '@',
        'params': '@'
      },
      restrict: 'E',
      template: '{{message}} <span ng-transclude></span>',
      link: function(scope, element, attrs) {

        scope.$watch('key', function(value) {
          scope.key = value;
          if(scope.params && scope.key) {
            scope.message = i18nService.localizeWithParams(scope.key, scope.params);
          } else {
            scope.message = i18nService.localize(value);
          }
        });

        scope.$watch('params', function(value) {
          scope.params = value;
        });
      }
    };
  }]);
  
  i18n.directive('i18nEnum', ['i18nService', function(i18nService) {
    return {
      transclude: true,
      scope: {
        'enumName': '@',
        'enumValue': '='
      },
      restrict: 'E',
      template: '{{message}} <span ng-transclude></span>',
      link: function(scope, element, attrs) {
        if (scope.enumValue) {
          scope.message = i18nService.localizeEnum(scope.enumName, scope.enumValue);
        }
        scope.$watch('enumValue', function(newVal, oldVal) {
          if (newVal !== oldVal) {
            scope.message = '';
            if (newVal) {
              scope.message = i18nService.localizeEnum(scope.enumName, newVal);
            }
          }
        });
      }
    };
  }]);
}());

