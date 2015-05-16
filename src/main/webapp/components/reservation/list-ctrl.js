(function () {
	'use strict';
	
	angular.module('Fablab').controller('ReservationListController', function ($scope, $filter,
		$location, ngTableParams, ReservationService) {
	$scope.criteria = {
		dateFrom: moment().startOf('month').toDate(),
		dateTo: moment().endOf('month').toDate()
	};

	$scope.tableParams = new ngTableParams(
			angular.extend({
				page: 1, // show first page
				count: 25, // count per page
				sorting: {
					dateStart: 'asc'
				}
			}, $location.search()), {
		getData: function ($defer, params) {
			if ($scope.reservations) {
				var filteredData = params.filter() ? $filter('filter')($scope.reservations, params.filter()) : $scope.reservations;
				var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
				$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
			}
		}
	});
	

	$scope.updateReservationList = function () {
		var criteria = {
			dateFrom:$filter('cropToDate')($scope.criteria.dateFrom),
			dateTo:$filter('cropToDate')($scope.criteria.dateTo)
		};
		ReservationService.search(criteria, function (data) {
			$scope.reservations = data;
			$scope.tableParams.reload();
			updateCalendarEvent();
		});
	};

	var updateCalendarEvent = function () {
		$scope.events.length = 0;
		for (var i = 0; i < $scope.reservations.length; i++) {
			var r = $scope.reservations[i];
			$scope.events[$scope.events.length] = {
				title: r.machine.name,
				start: new Date(r.dateStart),
				end: new Date(r.dateEnd)};
		}
	};

	$scope.remove = function (reservation) {
		ReservationService.remove(reservation.reservationId, function () {
			$scope.updateReservationList();
		});
	};

	$scope.updateReservationList();















	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	/* event source that pulls from google.com */
	$scope.dataSourceGoogleAgenda = {
		url: "https://www.google.com/calendar/feeds/nulu08ntleed9c5peukoeaifl8%40group.calendar.google.com/public/basic",
		className: 'gcal-event',
		currentTimezone: 'Europe/Zurich',
		color: 'yellow',
		textColor: 'black'
	};
	/* event source that contains custom events on the scope */
	$scope.events = [
		{title: 'All Day Event', start: new Date(y, m, 1)},
		{title: 'Long Event', start: new Date(y, m, d - 5), end: new Date(y, m, d - 2)},
		{id: 999, title: 'Repeating Event', start: new Date(y, m, d - 3, 16, 0), allDay: false},
		{id: 999, title: 'Repeating Event', start: new Date(y, m, d + 4, 16, 0), allDay: false},
		{title: 'Birthday Party', start: new Date(y, m, d + 1, 19, 0), end: new Date(y, m, d + 1, 22, 30), allDay: false},
		{title: 'Click for Google', start: new Date(y, m, 28), end: new Date(y, m, 29), url: 'http://google.com/'}
	];

	$scope.dataSourceReservation = {
		events: $scope.events,
		color: 'red'
	};

	/* config object */
	$scope.uiConfig = {
		calendar: {
			googleCalendarApiKey: 'AIzaSyB6WpKyvia6OD1ePfE6JGlYXBWIjBJGOLk',
			height: 450,
			editable: false,
			firstDay: 1,
			header: {
				left: 'title',
				center: 'month,agendaWeek,agendaDay',
				right: 'today prev,next'
			}
		}
	};

	/* event sources array*/
	$scope.eventSources = [$scope.dataSourceGoogleAgenda, $scope.dataSourceReservation];


});

	
}());