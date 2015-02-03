angular.module('App').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		error: {
			internal: 'Internal error',
			ajax: {
				unauthorized: 'Unauthorized',
				global: 'Ajax error'
			}
		},
		menu: {
			home: 'Home',
			users: 'Users',
			reservation: 'Reservation',
			payment: 'Payment',
		},
		loading: {
			title: 'Loading',
			text: 'Loading...'
		},
		button: {
			cancel: 'Cancel',
			save: 'Save',
			create: 'Create',
			export: 'Export',
			search: 'Search'
		},
		panel: {
			search:'Filters'
		},
		filter:{
			from:'From',
			to:'To',
		},
		auth: {
			result: {
				ok: 'Login Successful',
				unknownUserPassword: 'Login and/or password incorrect'
			},
			pleaseSignIn: 'Please sign in',
			email: 'Email address',
			password: 'Password',
			signIn: 'Sign In'
		},
		user: {
			firstname: 'Firstname',
			lastname: 'Lastname',
			email: 'Email',
			balance: 'Balance',
			membership: 'Membership',
			newpassword: 'New password',
			phone: 'Phone',
			address: 'Address',
			rfid: 'RFID',
			groups: 'Groups'
		},
		reservation: {
			day: 'Day',
			start: 'Start',
			end: 'End',
			user: 'User',
			machine: 'Machine',
			date: 'Date',
			startTime: 'Heure de début',
			endTime: 'Heure de fin',
			edit: {
				title: "Réservation d'une machine"
			}
		}
	});
});
