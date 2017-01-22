angular.module('Fablab').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		date: {
			format: {
				date: 'DD/MM/YYYY',
				datetime: 'DD/MM/YYYY hh:mm:ss',
				time: 'hh:mm:ss'
			}
		},
		general: {
			days: 'days',
			date:'Date',
			user:'User',
			detail:'Detail',
			comment:'Comment',
			form: {
				duration: 'Duration',
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
			reservation: 'Reservations',
			payment: 'Payments',
			accounting: 'Accounting',
			audit: 'Audit',
			admin:'Admin',
			assets:'Assets',
			machines:'Machines',
			login: 'Login',
			logout: 'Logout',
			signup: 'Signup',
			others:'Others'
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
			detail: 'Detail',
			updateMailingList: 'Update mailing list',
			edit:'Edit',
			back:'Back',
			add:'Add'
		},
		markdown:{
			actions:{
				bold:'Bold'
			}
		},
		panel: {
			search: 'Filters'
		},
		filter: {
			from: 'From',
			to: 'To'
		},
		form:{
			preset:{
				day:{
					label:'Day',
					current:'Today',
					ago:'{{x}} day(s) ago',
					next:'in {{x}} day(s)'
				},
				week:{
					label:'Week',
					current:'This week',
					ago:'{{x}} week(s) ago',
					next:'in {{x}} week(s)'
				},
				month:{
					label:'Month',
					current:'This month',
					ago:'{{x}} month(s) ago',
					next:'in {{x}} month(s)'
				},
				year:{
					label:'Year',
					current:'This year',
					ago:'{{x}} year(s) ago',
					next:'in {{x}} year(s)'
				}
			}
		},
		auth: {
			result: {
				ok: 'Welcome',
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
			comment: 'comment',
			birthdate: 'Birth date',
			gender: 'Gender',
			oldPassword:'Old password',
			repeatPassword:'Repeat',
			genderEnum: {
				unknown: 'Unknown',
				male: 'Male',
				female: 'Female'
			},
			subscription: {
				text:'Subscription',
				subscribe: 'Subscribe',
				confirmButton: 'Confirm',
				confirmTitle: 'Confirm subscription',
				price: 'Price',
				never: {
					myself: 'You haven\'t confirm your subription yet !',
					user: 'The user {{user.firstname}} {{user.lastname}} has not confirm its subription yet !'
				},
				expired: {
					myself: 'Your subscription has expired since {{epirationDate}} ({{dayLeft}} days left)',
					user: 'The subscription of the user {{user.firstname}} {{user.lastname}} has expired since {{epirationDate}} ({{dayLeft}} days)'
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
				removed: 'User removed',
				wrongPassword:'Wrong password',
				wrongRepeat:'New password doesn\'t match or is not complicated enough',
				passwordChanged:'Password changed'
			},
			profile:{
				changePassword:'Change password'
			},
		},
		reservation: {
			day: 'Day',
			start: 'Start',
			end: 'End',
			user: 'User',
			machine: 'Machine',
			date: 'Date',
			from: 'From',
			to: 'To',
			hours:'Hours',
			reservationForDay:'Reservation for {{date}}',
			edit: {
				title: "Book a machine"
			}
		},
		payment: {
			userTitle: 'User',
			usageTitle: 'Add an usage',
			paymentTitle: 'Add a payment',
			refundTitle: 'Add a refund',
			subscriptionTitle:'Subscription',
			enterName: 'Enter a name',
			machine: 'Machine',
			date: 'Date',
			time: 'Time',
			additionalCost: 'Additional cost',
			comment: 'Comment',
			directPaid: 'User paid directly',
			amount: 'Amount',
			total: 'Total',
			startDate:'Start date',
			paymentDate:'Payment date',
			addPayment: 'Add payment',
			addRefund: 'Add refund',
			addUsage: 'Add usage',
			addSubscription: 'Add subscription',
			details: 'Details',
			history: 'History',
			balanceText: 'Balance for user <i>{{firstname}} {{lastname}}</i> : <b>{{balance}}</b>',
			confirmation: {
				historyRemove: 'Do you really want to remove this history entry ?'
			},
			notification: {
				historyRemoved: 'Historique supprimé',
				usageAdded: 'Usage added',
				paymentAdded: 'Payment added',
				subscriptionAdded: 'Subscription added'
			}
		},
		accounting:{
			title:'Accounting',
			today:'Today',
			yesterday:'Yesterday',
			thisMonth:'This month',
			lastMonth:'Last month',
			thisYear:'This year',
			lastYear:'Last year',
			summary:'Summary',
			sell:'Sell',
			moneyIn:'Money in',
			delta:'Delta',
			debit:'Debit',
			credit:'Credit'
		},
		audit:{
			content:'Content',
			object:'Object',
			action:'Action',
			title:'Audit',
			alertLimit:'The request reach the limit of {{limit}} entries !'
		},
		machine:{
			title:'Machines',
			add:'Add machine',
			edit:'Edit machine',
			prices:'Prices',
			perhour:'Per hour',
			book:'Book'
		}
	});
});
