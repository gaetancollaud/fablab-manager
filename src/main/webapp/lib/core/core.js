(function() {
	'use strict';

	var core = angular.module('core', ['toaster', 'core.i18n']);
  
  
  /**
  * You need to set the configuration to the core service to use certain functionalities of the core lib
  * You can do that with the following in your "app.js":
  * coreService.init({
  *   libPath: 'PATH_TO_CORE_LIB'
  * });
  */
  core.service('coreService', ['$log', function($log) {
		var instance = this;
    var coreConfig = {};
    
    this.init = function(config) {
      coreConfig = config;
      $log.debug('coreService: Initialized.');
    };
		
    this.getResource = function(resourcePath) {
      var path = '.';
      if (coreConfig.libPath) {
        return coreConfig.libPath + resourcePath;
      } else {
        $log.warn('coreService: configuration.libPath is not set ! please set it with coreService.init({...})');
      }
    };
	}]);
  
}());
