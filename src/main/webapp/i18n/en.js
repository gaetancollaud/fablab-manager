angular.module('App').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		error:{
			internal:'Internal error',
			ajax: {
				unauthorized:'Unauthorized',
				global:'Ajax error'
			}
		},
		menu:{
			home:'Home',
			users:'Users',
			reservation:'Reservation',
		},
		loading:{
			title:'Loading',
			text:'Loading...'
		},
		auth:{
			result:{
				ok:'Login Successful',
				unknownUserPassword:'Login and/or password incorrect'
			},
			pleaseSignIn:'Please sign in',
			email:'Email address',
			password:'Password',
			signIn:'Sign In'
		},
		user:{
			firstname:'Firstname',
			lastname:'Lastname',
			email:'Email',
			balance:'Balance',
			membership:'Membership',
		}
	});
});
