angular.module('Fablab').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		auth: {
			result: {
				ok: 'Bienvenue',
				internal_error: 'Erreur interne'
			},
			pleaseSignIn: 'Veuillez vous connecter',
			email: 'Adresse email',
			password: 'Mot de passe',
			signIn: 'Se connecter',
			signUp: 'S\'inscrire'
		},
		user: {
			firstname: 'Prénom',
			lastname: 'Nom',
			email: 'Email',
			balance: 'Balance',
			membership: 'Adhésion',
			newpassword: 'Nouveau mot de passe',
			password: 'Mot de passe',
			phone: 'Téléphone',
			address: 'Adresse',
			rfid: 'RFID',
			groups: 'Groupes'
		},
	});
});