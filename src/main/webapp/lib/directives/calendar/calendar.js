angular.module('Fablab').directive('fablabCalendar', function () {
	return {
		restrict: 'EA',
		scope: {
			ngModel: '='
		},
		templateUrl: 'lib/directives/calendar/calendar.html',
		controller: function ($scope) {

			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();

			/* event source that pulls from google.com */
			$scope.dataSourceGoogleAgenda = {
				url: "https://www.google.com/calendar/feeds/nulu08ntleed9c5peukoeaifl8%40group.calendar.google.com/public/basic",
				className: 'gcal-event',
				currentTimezone: 'Europe/Zurich',
				color: '#B2E0FF',
				textColor: 'black'
			};

			$scope.dataSourceReservation = {
				events: $scope.ngModel,
				color: '#FFEBCC',
				textColor: 'black'
			};

			var eventRender = function (event, element) {
				element.on('click', function (e) {
					alert('coucou');
					e.preventDefault();
				});
			};

			/* config object */
			$scope.uiConfig = {
				calendar: {
					googleCalendarApiKey: App.CONFIG.GOOGLE_CALENDAR_API_KEY,
					height: 450,
					editable: false,
					firstDay: 1,
					header: {
						left: 'title',
						center: 'month,agendaWeek,agendaDay',
						right: 'today prev,next'
					},
					eventRender: eventRender
				}
			};

			/* event sources array*/
			$scope.eventSources = [$scope.dataSourceGoogleAgenda, $scope.dataSourceReservation];
		}
	};
});