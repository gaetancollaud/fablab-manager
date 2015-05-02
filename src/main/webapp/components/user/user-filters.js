(function () {
	'use strict';

	var app = angular.module('Fablab');
	
	app.filter('prettyUser', function () {
		return function (user) {
			if(!user){
				return '';
			}
			return user.firstname + " " + user.lastname;
		};
	});

	app.filter('lastSubscription', function ($filter, $translate) {
		var dateFormat;
		$translate('date.format.date').then(function (d) {
			dateFormat = d;
		});
		return function (subscriptions) {
			if (!subscriptions || subscriptions.length === 0) {
				return '<span class="text-danger text-bold">Not a member yet</span>';//FIXME
			}
			var ordered = $filter('orderBy')(subscriptions, 'dateSubscription', true);
			var sub = moment(ordered[0].dateSubscription);
			sub.add(1, 'year');//FIXME get from duration
			var diff = sub.diff(moment(), 'days');
			var diffText = diff > 0 ? diff + ' days left' : 'expired';
			var clazz = diff < 0 ? 'text-danger text-bold' : '';
			return '<span class="'+clazz+'">'+sub.format(dateFormat) + ' - ' + diffText+'</span>';
		};
	});

}());
