(function () {
	'use strict';

	var app = angular.module('User');
	
	app.filter('lastSubscription', function($filter){
		return function(subscriptions){
			if(!subscriptions || subscriptions.length==0){
				return '';
			}
			var ordered = $filter('orderBy')(subscriptions, 'dateSubscription', true);
			var now = new Date();
			var rest = 365-((now.getTime()-ordered[0].dateSubscription)/(3600*1000*24));
			//FIXME order
			return rest;
		}
	});

}());
