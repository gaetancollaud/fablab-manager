import angular from 'angular'
import moment from 'moment'

var app = angular.module('Fablab');

app.filter('prettyUser', function () {
	return function (user) {
		if (!user) {
			return '';
		}
		return user.firstname + " " + user.lastname;
	};
});


app.filter('lastSubscription', function ($filter) {
	return function (subscriptions) {
		if (subscriptions && subscriptions.length > 0) {
			var ordered = $filter('orderBy')(subscriptions, 'dateSubscription', true);
			return ordered[0];
		}
	};
});

app.filter('lastSubscriptionDetail', function ($filter, $translate) {
	var dateFormat;
	$translate('date.format.date').then(function (d) {
		dateFormat = d;
	});
	return function (subscriptions) {
		var sub = $filter('lastSubscription')(subscriptions);
		if (!sub) {
			return '<span class="text-danger text-bold">Not a member yet</span>';//FIXME
		}
		var subDate = moment(sub.dateSubscription);
		subDate.add(1, 'year');//FIXME get from duration
		var diff = subDate.diff(moment(), 'days');
		var diffText = diff > 0 ? diff + ' days left' : 'expired';
		var clazz = diff < 0 ? 'text-danger text-bold' : '';
		return '<span class="' + clazz + '">' + subDate.format(dateFormat) + ' - ' + diffText + '</span>';
	};
});

app.filter('lastSubscriptionDays', function ($filter, $translate) {
	return function (subscriptions) {
		var sub = $filter('lastSubscription')(subscriptions);
		if (!sub) {
			return 0;
		}
		var subDate = moment(sub.dateSubscription);
		subDate.add(1, 'year');//FIXME get from duration
		return subDate.diff(moment(), 'days');
	};
});

app.filter('lastSubscriptionDaysText', function ($filter, $translate) {
	return function (subscriptions) {
		var sub = $filter('lastSubscription')(subscriptions);
		if (!sub) {
			return '<span class="text-warning text-bold">Never confirm</span>';//FIXME
		}
		var diff = $filter('lastSubscriptionDays')(subscriptions);
		var clazz = diff < 0 ? 'text-danger text-bold' : 'text-success';
		return '<span class="' + clazz + '">' + diff + ' days </span>';
	};
});
