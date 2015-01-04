(function() {
	'use strict';

	var core = angular.module('core');

	core.factory('notificationService', ['$log', 'toaster', function($log, toaster) {

    return {
      clear: clear,
      notify: notify,
      error: error,
      success: success,
      info: info,
      warn: warn
    };

    function error(title, message) {
      $log.error('Error: ' + title, message);
      toaster.pop('error', title, message, 5000, 'trustedHtml');
    }

    function success(title, message) {
      $log.info('Success: ' + title, message);
      toaster.pop('success', title, message, 5000, 'trustedHtml');
    }

    function info(title, message) {
      $log.info('Info: ' + title, message);
      toaster.pop('info', title, message, 5000, 'trustedHtml');
    }

    function warn(title, message) {
      $log.warn('Warn: ' + title, message);
      toaster.pop('warning', title, message, 15000, 'trustedHtml');
    }

    function notify(level, title, html) {
      html = html || "";
      switch(level) {
        case 'SUCCESS':
          success(title, html);
          break;
        case 'ERROR':
          error(title, html);
          break;
        case 'INFO':
          info(title, html);
          break;
        case 'WARN':
          warn(title, html);
          break;
      }
    }

    function clear() {
      toaster.clear();
    }
	}]);
}());

