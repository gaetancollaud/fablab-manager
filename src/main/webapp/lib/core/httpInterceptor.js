(function() {
  'use strict';

  angular.module('core.httpInterceptor', [
    'core'
  ], ['$provide', function($provide) {
    $provide.factory('scHttpInterceptor', ['$rootScope', '$q', 'loaderService', 'i18nService', 'notificationService',
      function(scope, $q, scLoaderService, i18nService, notificationService) {
        return {
          request: function(config) {
            scLoaderService.show();
            return config || $q.when(config);
          },
          response: function(response) {
            responseSuccessHandler(response);
            return response || $q.when(response);
          },
          responseError: function(rejection) {
            responseErrorHandler(rejection);
            return $q.reject(rejection);
          }
        };

        function responseSuccessHandler(response) {
          scLoaderService.hide();
          return response;
        }

        function responseErrorHandler(response) {
          scLoaderService.hide();

          if(response.data && response.data.businessError) {
            var err = response.data;

            var msg = null;
            if(i18nService.exists('common.error.business.' + err.errorCode + '.msg')) {
              msg = i18nService.localize('common.error.business.' + err.errorCode + '.msg');
            }

            notificationService.error(
              i18nService.localize('common.error.business.' + err.errorCode),
              msg);
          } else {
            var status = response.status;
            if(status === 404) {
              notificationService.error(
                i18nService.localize('common.error.404'),
                null);
            } else if(status === 401) {
              showAjaxErrorMessage(status);
            } else if(status === 400) {
              notificationService.error(
                i18nService.localize('common.error.validation.title'),
                null);
            } else if(status === 403) {
              window.location = APP.Constants.Configuration.BASE_URL + '/403';
            } else if(status === 409) { // Opt locking
              showOptimisticLock('common.error.optLocking.title');
            } else {
              notificationService.error(
                i18nService.localize('common.error.title'),
                null);
            }
          }
        }

        function showAjaxErrorMessage(status) {
          notificationService.notify('ERROR', i18nService.localize('common.error.ajax.message'),
              i18nService.localize('common.error.ajax.status') + ': ' + status);
        }

        function showOptimisticLock(message) {
          var msg = message +
            '<br /><br />' +
            '<a type="button" class="btn btn-default btn-glyph" onclick="window.location.reload()" style="color: black;">' +
            i18nService.localize('common.error.optLocking.reload') +
            '<span class="glyphicon glyphicon-refresh"></span></a>';
          notificationService.notify('WARN', null, msg);
        }
      }]);
  }]);
}());

