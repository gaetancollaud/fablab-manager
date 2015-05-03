angular.module('Fablab').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		date: {
			format: {
				date: 'DD/MM/YYYY',
				datetime: 'DD/MM/YYYY hh:mm:ss',
				time: 'hh:mm:ss'
			}
		},
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
			login: 'Login',
			logout: 'Logout',
			signup: 'Signup'
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
			search: 'Filters'
		},
		filter: {
			from: 'From',
			to: 'To',
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
			forgotPasswordQuestion: "Forgot your password ?",
			requestNewPasswod: "Send me a new password"
		},
		user: {
			firstname: 'Firstname',
			lastname: 'Lastname',
			name: 'Name',
			email: 'Email',
			balance: 'Balance',
			membership: 'Membership',
			newpassword: 'New password',
			password: 'Password',
			phone: 'Phone',
			address: 'Address',
			rfid: 'RFID',
			groups: 'Groups',
			lastSubscription: 'Last subscription',
			subscriptionLeft: '{{days}} days left',
			subscriptionExpired: 'expired',
			comment: 'comment',
			birthdate: 'Birth date',
			gender: 'Gender',
			genderEnum: {
				unknown: 'Unknown',
				male: 'Male',
				female: 'Female'
			},
			subscription: {
				never: {
					myself: 'You haven\'t confirm your subription yet !',
					user: 'The user {{user.firstname}} {{user.lastname}} has not confirm its subription yet !'
				},
				expired: {
					myself: 'Your subscription has expired since {{epirationDate}} ({{dayLeft}} days left)',
					user: 'The subscription of the user {{user.firstname}} {{user.lastname}} has expired since {{epirationDate}} ({{dayLeft}} days left)'
				},
				ok: {
					myself: 'Your subscription will expire on {{epirationDate}} ({{dayLeft}} days left)',
					user: 'The subscription of the user {{user.firstname}} {{user.lastname}} will expired on {{epirationDate}} ({{dayLeft}} days left)'
				}
			},
			confirmation: {
				remove: 'Do you really want to remove this user ?'
			},
			notification: {
				saved: 'User saved',
				removed: 'User removed'
			}
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
		},
		payment: {
			userTitle: 'User',
			usageTitle: 'Add an usage',
			paymentTitle: 'Add a payment',
			enterName: 'Enter a name',
			machine: 'Machine',
			date: 'Date',
			time: 'Time',
			additionalCost: 'Additional cost',
			comment: 'Comment',
			directPaid: 'User paid directly',
			amount: 'Amount',
			total: 'Total',
			addPayment: 'Add payment',
			addUsage: 'Add usage',
			details: 'Details',
			history: 'History',
			balanceText: 'Balance for user <i>{{firstname}} {{lastname}}</i> : <b>{{balance}}</b>',
			confirmation: {
				historyRemove: 'Do you really want to remove this history entry ?'
			},
			notification: {
				historyRemoved: 'Historique supprimé',
				usageAdded: 'Usage added',
				paymentAdded: 'Payment added'
			}
		}
	});
});
