/**
 * I18N Module
 */
angular.module('I18n', [], function($provide) {
	$provide.factory('I18nService', ['$log', function($log) {
		var localize = function(key) {
			var message = App.MESSAGES[key];
			if (message == null) {
				message = key;
				$log.warn("I18nService: Could not find message for key " + key);
			}

			return message;
		};
		return {
			localize : localize,
			localizeEnum : function(enumClass, value) {
				var cl = enumClass;
				var val = value;			
				var key = "common.data.enum." + cl + "." + val;
				return localize(key);
			},
			getLocale : function(){
				return App.LOCALE;
			},
			/**
			 * Get a full list of localized enum object:
			 * v: {
			 * 		value: NAME,
			 * 		localized: Nom
			 * }
			 * 
			 * param name: The name of the class of the enum (TypeConduite)
			 */
			listEnum : function(name) {
				var enums = [];
				var loadedEnums = App.I18N_ENUMS[name];
				if (loadedEnums == null) {
					$log.error("I18nService: Could not find enum translation for Enum class name: " + name);
					return [];
				}
				if (loadedEnums.length == 0) {
					$log.error("I18nService: Empty translation for Enum class name: " + name);
					return [];
				}
				
				for (var i=0; i<loadedEnums.length; i++) {
					var e = {
							value: loadedEnums[i].value,
							localized: loadedEnums[i].localizedValue
					};
					enums.push(e);
				}
				return enums;
			}
		};
	}]);
});

var i18n = angular.module('I18n');
i18n.directive('i18nKey', ['I18nService', function(I18nService) {
	return {
	    transclude: true,
	    scope: {
	    	'i18nKey': '@'
	    },
		restrict: 'A',
		template : '{{message}} <span ng-transclude></span>',
		link: function(scope, element, attrs) {
			scope.$watch('i18nKey', function(value) {
				scope.message = I18nService.localize(value);
			});
		}
	};
}]);
i18n.directive('i18nAttributes', ['I18nService', function(I18nService) {
	return {
	    //transclude: true,
	    scope: {
	    	'i18nAttributes': '@'
	    },
		restrict: 'A',
		
		link: function(scope, element, attrs) {
			var attributes = eval(scope.i18nAttributes);
			for(var i = 0;i<attributes.length;i++){
				if(attrs[attributes[i]] == undefined) continue;
				var localized = I18nService.localize(attrs[attributes[i]]);
				element.attr(attributes[i],localized);
			}
		}
	};
}]);
/**
 * Fill the i18n element with the i18n translation
 * Don't use it in an option element !
 **/
i18n.directive('i18n', ['I18nService', function(I18nService) {
	return {
	    transclude: true,
	    scope: {
	    	'key': '@'
	    },
		restrict: 'E',
		template : '{{message}} <span ng-transclude></span>',
		link: function(scope, element, attrs) {
			scope.$watch('key', function(value) {
				scope.message = I18nService.localize(value);
			});
		}
	};
}]);
i18n.directive('i18nEnum', ['I18nService', function(I18nService) {
	return {
	    transclude: true,
	    scope: {
	    	'enumType': '@',
	    	'value': '='
	    },
		restrict: 'E',
		template : '{{message}} <span ng-transclude></span>',
		link: function(scope, element, attrs) {
			scope.$watch('value', function(value) {
				scope.message = I18nService.localizeEnum(scope.enumType, value);
			});
		}
	};
}]);