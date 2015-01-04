(function() {
  'use strict';

  var cmp = angular.module('core.filter.date', []);

  cmp.constant('scDateConstant', {
    dateModelFormat: 'YYYY-MM-DD',
	dateUserFormat: 'DD.MM.YYYY',
	dateTimeModelFormat: 'YYYY-MM-DD[T]HH:mm:ss',
	dateTimeUserFormat: 'DD.MM.YYYY HH:mm:ss'
  });
  cmp.filter('scDate', ['scDateConstant', function(scDateConstant) {
    return function(dateStr) {
      // convert
	  if (!dateStr) {
        return '';
	  }
	  return moment(dateStr, scDateConstant.dateModelFormat).format(scDateConstant.dateUserFormat);
    };
  }]);
  // Filters
  cmp.filter('scDateTime', ['scDateConstant', function(scDateConstant) {
    return function(dateStr) {
      // convert
	  if (!dateStr) {
        return '';
	  }
	  var localDate = moment.utc(dateStr, scDateConstant.dateTimeModelFormat).toDate();
      return moment(localDate).format(scDateConstant.dateTimeUserFormat);
    };
  }]);
  // Filters
  cmp.filter('scDateTimeUtc', ['scDateConstant', function(scDateConstant) {
    return function(dateStr) {
      // convert
	  if (!dateStr) {
        return '';
	  }
	  return moment.utc(dateStr, scDateConstant.dateTimeModelFormat).format(scDateConstant.dateTimeUserFormat);
    };
  }]);

  cmp.filter('scTimestamp', ['scDateConstant', function (scDateConstant) {
    return function (input, format) {
      if (input == null) {
        return "";
      }
      if (!format) {
        format = scDateConstant.dateUserFormat;
      }

      return moment(input).format(format);
    };
  }])

}());
