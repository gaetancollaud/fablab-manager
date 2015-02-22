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
			login:'Login',
			logout:'Logout',
			signup:'Signup'
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
			search: 'Search',
			updateMailingList: 'Update mailing list'
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
			signiIn: 'Sign In',
			signUp: 'Sign Up',
			captcha: 'Are you human ?',
			forgotPasswordQuestion:"Forgot your password ?",
			requestNewPasswod:"Send me a new password"
		},
		user: {
			firstname: 'Firstname',
			lastname: 'Lastname',
			email: 'Email',
			balance: 'Balance',
			membership: 'Membership',
			newpassword: 'New password',
			password: 'Password',
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
