angular.module('App').config(function ($translateProvider) {
	$translateProvider.translations('en', {
		auth:{
			result:{
				ok:'Login correct',
				internal_error:'Erreur interne'
			},
			pleaseSignIn:'Veuillez vous connecter',
			email:'Adresse email',
			password:'Mot de passe',
			signIn:'Se connecter'
		}
	});
});