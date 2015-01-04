(function() {
  'use strict';

  var cmp = angular.module('core.ui.date', []);   
  cmp.directive('scDatePicker', ['$timeout', function($timeout) {
    
    return {
    restrict: 'EA',
    require: 'ngModel',
    replace: true,
    scope: {
      model: '=ngModel',
      startDate: '=startDate',
      endDate: '=endDate'
    },
    template: '<span class="sc-datepicker-core">'+
              '<div class="sc-datepicker-date">'+
              '<input class="form-control datepicker-core-data" type="text" value="" />' +
              '<input class="form-control datepicker-core-dp" readonly type="text" value="" />' +
              '<div class="sc-datepicker-core-buttons">'+
              '<button type="button" class="btn btn-success btn-xs" ng-click="showDP()">'+
              '<span class="glyphicon glyphicon-calendar"></span>'+
              '</button>'+
              '<button type="button" class="btn btn-danger btn-xs" ng-click="clearDP()">'+
              '<span class="glyphicon glyphicon-remove"></span>'+
              '</button>'+
              '</div>'+
              '</div>'+
              '</span>',
    link: function(scope, element, attrs, controller) {
          var modelFormat = 'YYYY-MM-DD'; // JSON model format
          var dpFormat = 'dd.mm.yyyy'; // View format
          var viewFormat = 'DD.MM.YYYY'; // View format
          var viewFormatSmall = 'DD.MM.YY'; // View format (with only 2 years digit  .)

          var inputHidden = element.find('input.datepicker-core-dp');
          var input = element.find('input.datepicker-core-data');

          var setDpFieldVisible = function(visible) {
            if (visible) {
              inputHidden.css('display', 'block');
              input.css('display', 'none');
            } else {
              inputHidden.css('display', 'none');
              input.css('display', 'block');
            }
          };
          setDpFieldVisible(false);

          var dpTarget = inputHidden;

          var formatModelDate = function(date) {
            if (date === undefined) {
              return null;
            }
            var formatted = moment(date).hour(12).format(modelFormat);
            return formatted;
          };
          var parseModelDate = function(dateString) {
            var date = null;
            if (dateString !== undefined && dateString !== '') {
              date = moment(dateString, modelFormat).toDate();
            }
            if (date !== undefined && isNaN(date.getTime())) {
              date = null;
            }
            return date;
          };

          var formatDate = function(date) {
            if (date === undefined) {
              return null;
            }
            var formatted = moment(date).format(viewFormat);
            return formatted;
          };
          var parseDate = function(dateString) {
            var date = null;
            if (dateString !== undefined && dateString !== '') {
              date = moment(dateString, viewFormat).hour(12).toDate();
              if (date.getFullYear() < 1000) {
                date = moment(dateString, viewFormatSmall).hour(12).toDate();
              }
            }
            if (date !== undefined && date !== null && isNaN(date.getTime())) {
              date = null;
            }
            return date;
          };

          var update = function() {
            input.val = inputHidden.val;
            getPicker();
            var dpDate = dpTarget.datepicker('getDate');
            if (dpDate) {
              $timeout(function() {
                var date = dpDate;
                var time = null;
                if (date !== null) {
                  time = date.getTime();
                }

                if (time !== null && isNaN(time)) {
                  time = null;
                } else {
                  time = formatModelDate(time);
                }

                if (scope.model !== time) {
                  scope.model = time;
                  controller.$setViewValue(scope.model);
                }
              });
            } else {
              $timeout(function() {
                if (scope.model !== null) {
                  scope.model = null;
                  controller.$setViewValue(scope.model);
                }
              });
            }
          };

          var createPicker = function() {
            var config = {
              format: dpFormat,
              autoclose: true,
              todayBtn: true,
              todayHighlight: true,
              orientation: 'auto',
              keyboardNavigation: true,
              language: 'fr'
            };

            if (scope.startDate) {
              config.startDate = parseModelDate(scope.startDate);
            }
            if (scope.endDate) {
              config.endDate = parseModelDate(scope.endDate);
            }

            var dp = dpTarget.datepicker(config);
            dp.on('hide', function() {
              setDpFieldVisible(false);
            });
            dp.on('changeDate', function() {
              update();
            });
            return dp;
          };

          var picker = null;
          /*
           *
           * @returns {DatePicker} create or get the date picker
           */
          var getPicker = function() {
            if (picker === null) {
              picker = createPicker();
            }
            return picker;
          };

          var pickerSet = function() {
            return picker !== null;
          };

          var updatePicker = function() {
            if (pickerSet()) {
              dpTarget.datepicker('update');
            }
          };



          var updateUserValue = function(withEnter) {
            $timeout(function() {
              var val = input.val();
              var date = null;
              var viewVal = '';
              var modelVal = null;
              if (val) {
                date = parseDate(val);
                viewVal = formatDate(date);
                modelVal = formatModelDate(date);
                // Update the datepicker
                if (pickerSet()) {
                  dpTarget.val(viewVal);
                  dpTarget.datepicker('update');
                }
              }
              if (scope.model !== modelVal) {
                scope.model = modelVal;
                controller.$setViewValue(scope.model);
                input.val(viewVal);
              }

              if (withEnter === true && stlForm) {
                // let's call the default action
                stlForm.submit();
              }
            });
          };

          var cls = $(element).attr('class');
          input.attr('class', input.attr('class') + ' ' + cls);
          var fill = function(data) {
            if (data !== undefined && data !== null) {
              var date = parseModelDate(data);
              if (Object.prototype.toString.call(date) === '[object Date]' && !isNaN(date.getTime())) {
                input.val(formatDate(date));
                inputHidden.val(formatDate(date));
                updatePicker();
              }
              else {
                input.val(null);
                inputHidden.val(null);
                updatePicker();
              }
            } else {
              input.val(null);
              inputHidden.val(null);
              updatePicker();
            }
          };
          fill(scope.model);

          scope.$watch('model', function(dt) {
            fill(dt);
          });

          scope.$watch('startDate', function(newSD, oldSD) {
            if (newSD !== oldSD /*|| div.datepicker('getDate').getTime() < parseModelDate(newSD).getTime()*/) {
              if (scope.model !== undefined && newSD !== undefined && scope.model < newSD) {
                scope.model = null;
              }
              if (pickerSet()) {
                if (newSD) {
                  dpTarget.datepicker('setStartDate', parseModelDate(newSD));
                } else {
                  dpTarget.datepicker('setStartDate', null);
                }
              }
            }
          });
          scope.$watch('endDate', function(newSD, oldSD) {
            if (newSD !== oldSD) {
              if (scope.model !== undefined && newSD !== undefined && scope.model > newSD) {
                scope.model = null;
              }
              if (pickerSet()) {
                if (newSD) {
                  dpTarget.datepicker('setEndDate', parseModelDate(newSD));
                } else {
                  dpTarget.datepicker('setEndDate', null);
                }
              }
            }
          });
          scope.clearDP = function() {
            scope.model = null;
            controller.$setViewValue(null);

            getPicker();
            dpTarget.datepicker('update', new Date());
          };
          scope.showDP = function() {
            setDpFieldVisible(true);
            dpTarget.focus();

            getPicker();
            dpTarget.datepicker('show');
          };

          // Event binding
          input.bind({
            blur: updateUserValue
          });
          input.keypress(function(event) {
            if (event.which === 13) {
              event.preventDefault();
              updateUserValue(true);
            }
          });
        }
    };
  }]);
}());