angular.module('Fablab').config(function ($translateProvider) {
    $translateProvider.translations('fr', {
        date: {
            format: {
                date: 'DD/MM/YYYY',
                datetime: 'DD/MM/YYYY hh:mm:ss',
                time: 'hh:mm:ss'
            }
        },
        home: {
            user: 'Utilisateur'
        },
        general: {
            days: 'jours',
            date: 'Date',
            user: 'Utilisateur',
            detail: 'Détail',
            comment: 'Commentaire',
            toFr: 'Fr',
            toEn: 'En',
            yes: 'Oui',
            no: 'Non',
            type: 'Type',
            erase: 'Effacer',
            table: {
                action: 'Action'
            },
            form: {
                duration: 'Durée'
            }
        },
        error: {
            internal: 'Erreur interne',
            ajax: {
                unauthorized: 'Non autorisé',
                global: 'Ereur AJAX'
            }
        },
        menu: {
            home: 'Home',
			audit:'Audit',
            userAccount: 'Comptes utilisateurs',
            users: 'Utilisateurs',
            reservation: 'Réservations',
            payment: 'Paiements',
            accounting: 'Comptabilité',
            login: 'Se connecter',
            logout: 'Déconnexion',
            signup: 'S\'inscrire',
            machines: 'Machines',
            machineTypes: 'Types de machines',
            machineState: 'Etats des machines',
            machineStatus: 'Statuts des machines',
            admin: 'Administration',
            membershipTypes: 'Types de membres',
            inOut: 'Entrées / Sorties',
            revision: "Révisions",
            ticketStatus: 'Status des pannes',
            tickets: 'Pannes',
            configurations: 'Configurations',
            key: 'Panel d\'administration',
            supplies: 'Fournitures',
            addFailure: 'Déclarer une panne',
            supplyTypes: 'Types de fournitures',
            purchases: 'Achats',
            addPurchase: 'Faire un achat',
            supplyUnities: 'Untités de mesure',
            motionStocks: 'Mouvements de stock',
            priceMachines: 'Coûts d\'utilisation machine',
            priceMachinesTable: 'Table des coûts<br/><span class="third-ligne">d\'utilisations</span><br/><span class="third-ligne">machines</span>',
            trainingLevels: 'Niveaux de formations',
            trainings: 'Formations',
            certification: 'Certifications',
            roles: 'Droits sur l\'application',
            groups: 'Groupes d\'utilisateurs',
            usages: 'Utilisations machines',
            paymentsUse: 'Paiements et <br/><span class="second-ligne">utilisations machines</span>',
            userPayments: 'Paiements annexes et <br/><span class="second-ligne-bis">remboursements</span>',
            event: "Evènements",
            eventTypes: 'Types d\'évènements',
            eventPersons: 'Liste d\'inscrits',
            eventModules: 'Modules d\'évènements',
            events: 'Evènements',
            eventPayment: 'Paiement d\'un <br/><span class="second-ligne-bis">évènement</span>'
        },
        loading: {
            title: 'Chargement',
            text: 'Chargement...'
        },
        button: {
            cancel: 'Annuler',
            save: 'Sauvegarder',
            return: 'Retour',
            create: 'Créer',
            export: 'Exporter',
            search: 'Rechercher',
            updateMailingList: 'Mise à jour de la liste d\'emails'
        },
        panel: {
            search: 'Filtres'
        },
        filter: {
            from: 'De',
            to: 'A'
        },
        auth: {
            result: {
                ok: 'Bienvenue',
                unknownUserPassword: 'Nom d\'utilisateur ou mot de passe incorrect'
            },
            pleaseSignIn: 'Se connecter',
            email: 'Email',
            password: 'Mot de passe',
            signIn: 'Se connecter',
            signUp: 'S\'inscrire',
            captcha: 'Etes-vous un humain ?',
            forgotPasswordQuestion: "Mot de passe oublié ?",
            requestNewPasswod: "Envoyez-moi un nouveau mot de passe"
        },
        user: {
            firstname: 'Prénom',
            lastname: 'Nom de famille',
            name: 'Nom',
            email: 'Email',
            login:'Login',
            balance: 'Solde',
            membership: 'Membre',
            newpassword: 'Nouveau mot de passe',
            password: 'Mot de passe',
            phone: 'Téléphone',
            address: 'Adresse',
            rfid: 'RFID',
            groups: 'Groupe',
            comment: 'Commentaire',
            birthdate: 'Date de naissance',
            gender: 'Genre',
            genderEnum: {
                unknown: 'Indéterminé',
                male: 'Homme',
                female: 'Femme'
            },
            subscription: {
                subscribe: 'Inscription',
                confirmButton: 'Confirmer',
                confirmTitle: 'Confirmer l\'inscription',
                price: 'Prix',
                never: {
                    myself: 'Vous n\'avez pas confirmé votre inscription !',
                    user: 'L\'utilisateur {{user.firstname}} {{user.lastname}} n\'a pas confirmé son inscription !'
                },
                expired: {
                    myself: 'Votre inscription a expiré depuis {{epirationDate}} ({{dayLeft}} jours)',
                    user: 'L\'inscription de l\'utilisateur  {{user.firstname}} {{user.lastname}} a expiré depuis {{epirationDate}} ({{dayLeft}} jours)'
                },
                ok: {
                    myself: 'Votre inscription expire le {{epirationDate}} ({{dayLeft}} jours restants)',
                    user: 'L\'inscription pour l\'utilisateur {{user.firstname}} {{user.lastname}} expire le {{epirationDate}} ({{dayLeft}} jours restants)'
                }
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cet utilisateur ?'
            },
            notification: {
                saved: 'Utilisateur sauvegardé',
                removed: 'Utilisateur supprimé'
            }
        },
        reservation: {
            day: 'Jour',
            start: 'Début',
            end: 'Fin',
            user: 'Utilisateur',
            machine: 'Machine',
            type: 'Type',
            date: 'Date',
            from: 'De',
            to: 'A',
            hours: 'Heures',
            reservationForDay: 'Reservation pour {{date}}',
            notification: {
                saved: 'Reservation sauvegardée',
                removed: 'Reservation supprimée'
            },
            edit: {
                title: "Réserver une machine"
            }
        },
        payment: {
            userTitle: 'Utilisateur',
            usageTitle: 'Ajouter une utilisation',
            paymentTitle: 'Ajouter un paiement',
            credit: 'Crédit',
            debit: 'Débit',
            type: 'Type',
            userNotAllowed: 'L\'utilisateur connecté n\'est pas autorisé à utiliser cette machine !',
            userNotAllowed2: 'Une certification pour la machine sélectionnée est requise',
            enterName: 'Entrer un nom',
            machine: 'Machine',
            date: 'Date',
            time: 'Temps',
            additionalCost: 'Coûts additionnels',
            comment: 'Commententaires',
            directPaid: 'L\'utilisateur a payé cash',
            amount: 'Quantité',
            total: 'Total',
            addPayment: 'Ajouter un paiement',
            addUsage: 'Ajouter une utilisation',
            details: 'Détails',
            history: 'Compte de l\'utilisateur',
            balanceText: 'Solde pour l\'utilisateur {{firstname}} {{lastname}} : <b>{{balance}}</b>',
            confirmation: {
                historyRemove: 'Etes-vous sûr de vouloir supprimé cette entrée dans l\'historique ?'
            },
            notification: {
                historyRemoved: 'Historique supprimé',
                usageAdded: 'Utilisation ajoutée',
                paymentAdded: 'Paiement ajouté'
            }
        },
        accounting: {
            title: 'Entrée / Sorties',
            today: 'Aujourd\'hui',
            yesterday: 'Hier',
            thisMonth: 'Mois courant',
            lastMonth: 'Mois dernier',
            thisYear: 'Année courante',
            lastYear: 'Année passée',
            summary: 'Résumé',
            sell: 'Montant dépenses',
            moneyIn: 'montant recettes',
            delta: 'Delta',
            debit: 'Débit',
            credit: 'Crédit',
            user: 'Utilisateur',
            accountCredit: 'Compte crédité',
            accountDebit: 'Compte débité'
        },
        machine: {
            title: 'Machine',
            code: 'Code',
            buyPrice: 'Prix d\'achat',
            name: 'Nom',
            acquisitionDate: "Date d\'acquisition",
            machineType: 'Type',
            machineState: 'Etat',
            machineStatus: 'Statut',
            revisions: 'Révisions',
            btnTitleRevision: 'Réviser cette machine',
            btnTitleTicket: 'Annoncer une panne',
            tickets: 'Pannes',
            alreadyExist: 'Le code spécifié existe déjà !',
            toDoRevision: 'Liste de To-Do pour les révisions',
            lists: 'Reservations & Pannes',
            create: 'Création d\'une machine',
            edit: 'Edition de :',
            notification: {
                saved: 'Machine sauvegardée',
                removed: 'Machine supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette machine ?'
            }
        },
        machineType: {
            title: 'Type de machine',
            name: 'Nom',
            technicalname: 'Nom technique',
            restricted: 'Restreint ?',
            alreadyExist: 'Le nom technique existe déjà !',
            membershipTypes: 'Prix par type de membre',
            create: 'Création d\'un type de machine',
            edit: 'Edition de :',
            notification: {
                saved: 'Type de machine sauvegardé',
                removed: 'Type de machine supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce type de machine ?'
            }
        },
        machineState: {
            title: 'Etat de machines',
            label: 'Libellé',
            create: 'Création d\'un état',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            edit: 'Edition de :',
            notification: {
                saved: 'Etat sauvegardé',
                removed: 'Etat supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cet état ?'
            }
        },
        machineStatus: {
            title: 'Statut de machine',
            label: 'Libellé',
            color: 'Couleur',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            create: 'Création d\'un statut',
            edit: 'Edition de :',
            notification: {
                saved: 'Statut sauvegardé',
                removed: 'Statut supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce statut ?'
            }
        },
        membershipType: {
            title: 'Type de membre',
            name: 'Nom',
            duration: 'Durée',
            price: 'Prix',
            durationDay: 'Jours',
            alreadyExist: 'Le nom spécifié existe déjà !',
            machineTypes: 'Types de machine',
            create: 'Création d\'un type de membre',
            edit: 'Edition de :',
            notification: {
                saved: 'Type de membre sauvegardé',
                removed: 'Type de memebre sauvegardé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce type de membre ?'
            }
        },
        revision: {
            title: 'Révisions',
            revisionDate: 'Date de révision',
            revisionTime: 'Heure de révision',
            note: 'Remarques aditionnelles',
            cost: 'Coût',
            machine: 'Machine',
            machineName: 'Machine',
            create: 'Création d\'une révision',
            edit: 'Edititon de la révision du ',
            notification: {
                saved: 'Révision sauvegardée',
                removed: 'Révision supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette révision ?'
            }
        },
        ticketStatus: {
            title: 'Statut de panne',
            label: 'Libellé',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            create: 'Cération d\'un statut de panne',
            edit: 'Edition de :',
            notification: {
                saved: 'Statut de panne sauvegardé',
                removed: 'Statut de panne supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce statut de panne ?'
            }
        },
        ticket: {
            ticketTitle: 'Panne',
            title: 'Titre',
            description: 'Description',
            creationDateList: 'Date de création',
            creationDate: 'Créée le',
            previsionCloseDate: 'Date de clôture estimée',
            closeDateList: 'Date de clôture',
            reopenDescr: 'La panne a déjà été clôturée le ',
            reopenDescr2: 'La panne a été ré-ouverte le ',
            by: ' par ',
            closeDate: 'Close le ',
            machine: 'Machine',
            status: 'Statut : ',
            creationUserList: 'Créée par',
            creationUser: 'par',
            closeUserList: 'Close par',
            closeUser: 'par',
            create: 'Création d\'une panne',
            edit: 'Edition de :',
            closeTicket: 'Clore cette panne',
            alreadyExist: 'Le titre spécifié existe déjà !',
            reOpenTicket: 'Réouvrir cette panne',
            notification: {
                saved: 'Panne sauvegardée',
                removed: 'Panne supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette panne ?'
            }
        },
        configuration: {
            title: 'Propriété d\'applicaiton',
            key: 'Clé',
            value: 'Valeur',
            name: 'Description',
            create: 'Création d\'une propriété',
            warningEdit: '<strong>Attention !</strong> Les changements ne prendront effet uniquement après un rafraichissement de la page (F5 pour la plupart des navigateurs)',
            edit: 'Edition de :',
            notification: {
                saved: 'Propriété d\'applicaiton sauvegardée; N\'oubliez pas de rafraîchir la page (F5) pour voir les changements dans FabLab - Manager',
                removed: 'Propriété d\'applicaiton supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer this configuration ?'
            }
        },
        supply: {
            title: 'Fourniture',
            code: 'Code',
            label: 'Libellé',
            sellingPrice: 'Prix de vente',
            unityBuyingPrice: 'Prix d\'achat par unité',
            orderAddress: 'Adresse de commande',
            supplyType: 'Type de fourniture',
            alreadyExist: 'Le code spécifié existe déjà !',
            note: 'Remarques',
            addQuantity: 'Augmenter le stock de cette fourniture',
            quantityStock: 'Quantité en stock',
            quantityStockModal: 'Quantité',
            supplyUnity: 'Unité',
            back: 'Retour',
            initialQuantity: 'Quantité initiale',
            modalTitle: 'Ajouter au stock ',
            create: 'Création d\'une fourniture',
            edit: 'Edition de :',
            notification: {
                saved: 'Fourniture sauvegardée',
                removed: 'Fourniture supprimée',
                addQuantity: 'Quantity ajoutée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette fourniture ?'
            }
        },
        supplyType: {
            title: 'Type de fourniture',
            label: 'Libellé',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            create: 'Création d\'un type de fourniture',
            edit: 'Edition de :',
            notification: {
                saved: 'Type de fourniture sauvegardé',
                removed: 'Type de fourniture supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer type de fourniture ?'
            }
        },
        purchase: {
            title: 'Achat',
            purchaseDate: 'Date d\'achat',
            cashier: 'Utilisateur ayant encaissé ',
            paidDirectly: 'L\'utilisateur a-t-il payé directement ?',
            quantity: 'Quantité',
            purchasePrice: 'Prix d\'achat',
            supply: 'Fourniture',
            user: 'Utilisateur',
            unityPrice: 'Prix unitaire',
            discount: 'Rabais',
            note: 'Remarques',
            back: 'Retour',
            create: 'Création d\'achat',
            edit: 'Edition de :',
            notification: {
                saved: 'Achat sauvegardé',
                removed: 'Achat supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cet achat ?'
            }
        },
        supplyUnity: {
            title: 'Unité de fourniture',
            label: 'Libellé',
            floating: 'Est non-entier ? ',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            create: 'Création d\'une unité de fourniture',
            edit: 'Edition de :',
            notification: {
                saved: 'Unité de fourniture sauvegardée',
                removed: 'Unité de fourniture supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette unité de fourniture ?'
            }
        },
        motionStock: {
            title: 'Mouvement de stock',
            motionDate: 'Date du mouvement de stock',
            quantity: 'Quantité',
            io: 'Entrée / Sortie',
            supply: 'Fourniture',
            user: 'Utilisateur'
        },
        priceMachine: {
            title: 'Coûts    d\'utilisation',
            titleList: 'Table des coûts d\'utilisations machines',
            price: 'Prix',
            hour: '/ heure',
            machineType: 'Type de machine',
            membershipType: 'Type de membre',
            create: 'Création d\'un coût d\'utilisation',
            editAlter: 'Editer tous les prix',
            edit: 'Edition de :',
            notification: {
                saved: 'Coût d\'utilisation sauvegardé',
                removed: 'Coût d\'utilisation supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce coût d\'utilisation ?'
            }
        },
        trainingLevel: {
            title: 'Niveau de formation',
            label: 'Libellé',
            alreadyExist: 'Le libellé spécifié existe déjà !',
            create: 'Création d\'un niveau de formation',
            edit: 'Edition de :',
            notification: {
                saved: 'Niveau de formation sauvegardé',
                removed: 'Niveau de formation supprimé'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer ce niveau de formation ?'
            }
        },
        training: {
            title: 'Formation',
            name: 'Nom',
            price: 'Prix',
            note: 'Remarques',
            alreadyExist: 'Le nom spécifié existe déjà !',
            trainingLevel: 'Niveau de formation',
            machineType: 'Type de machine',
            nonSelectedListLabel: 'Formations disponibles comme prerequis',
            selectedListLabel: 'Prerequis',
            havePrerequisites: 'A un ou plusieurs prérequis',
            create: 'Création d\'une formation',
            edit: 'Edition de :',
            notification: {
                saved: 'Formation sauvegardée',
                removed: 'Formation supprimée'
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette formation ?'
            }
        },
        certification: {
            title: 'Certification',
            name: 'Nom',
            machineType: 'Type de machine',
            certificationDate: 'Date de certification',
            certificationPrice: 'Prix de la certification',
            alreadyExist: 'Le nom spécifié existe déjà !',
            note: 'Remarques',
            btnTitle: 'Créer une certification',
            training: 'Formation',
            create: 'Création d\'une certification',
            edit: 'Edition de :',
            nonSelectedListLabel: 'Utilisateurs disponibles',
            selectedListLabel: 'Utilisateurs certifiés',
            notification: {
                saved: 'Certification sauvegardée',
                removed: 'Certification supprimée',
                failed: '<strong>Erreur !</strong>Les utilisateurs suivants n\'ont pas toutes les formations requises : '
            },
            confirmation: {
                remove: 'Etes-vous sûr de vouloir supprimer cette certification ?'
            }
        },
        role: {
            title: 'Droits des groupes',
            group: 'Selectionner un groupe',
            roles: 'Droits',
            selectedListLabel: 'Droits assignés',
            nonSelectedListLabel: 'Droits disponible',
            notification: {
                saved: 'Droits sauvegardés'
            }
        },
        group: {
            title: 'Groupe d\'utilisateurs',
            technicalname: 'Nom technique',
            name: 'Nom',
            roles: 'Droits',
            alreadyExist: 'Le nom technique spécifié existe déjà',
            nonSelectedListLabel: 'Rôles assignés',
            selectedListLabel: 'Rôles disponibles',
            create: 'Création d\'un groupe',
            edit: 'Edition de ',
            notification: {
                saved: 'Group sauvegardé',
                removed: 'Group supprimé'
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer ce groupe ?'
            }
        },
        duallist: {
            filterClear: 'Afficher tous les tuples',
            filterPlaceholder: 'Filtre',
            moveSelectedLabel: 'Déplacer uniquement les éléments sélectionnés',
            moveAllLabel: 'Déplacer tous les éléments',
            removeSelectedLabel: 'Supprimer uniquement les éléments sélectionnés',
            removeAllLabel: 'Supprimer tous les éléments',
            preserveSelection: 'déplacé',
            infoAll: '{0} éléments affiché(s)',
            infoFiltered: '<span class="label label-warning">Filtré : </span> {0} de {1}',
            infoEmpty: 'Liste vide !'
        },
        usage: {
            title: 'Utilisation d\'une machine',
            dateStart: 'Date',
            pricePerHour: 'Prix par heure',
            discount: 'Rabais',
            paidDirectly: 'L\'utilisateur a-t-il payé directement ?',
            discountPercent: 'Rabais pourcent',
            minutes: 'Temps d\'utilisation',
            additionalCost: 'Coûts additionnels',
            total: 'Total',
            note: 'Remarques',
            user: 'Utilisateur',
            userNotAllowed: 'L\'utilisateur connecté n\'est pas autorisé à utiliser cette machine !',
            userNotAllowed2: 'Une certification pour la machine sélectionnée est requise',
            directPaid: 'Payé cash ?',
            cashier: 'Utilisateur ayant encaissé',
            machine: 'Machine',
            membershipType: 'Type de membre',
            create: 'Création d\'une utilisation',
            edit: 'Edition de l\'utilisation du ',
            notification: {
                saved: 'Utilisation sauvegardé',
                removed: 'Utilisation supprimé'
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer cette utilisation ?'
            }
        },
        userPayment: {
            title: 'Paiements anexes',
            total: 'Total',
            titleList: 'Paiements annexes et remboursements',
            datePayment: 'Date',
            discount: 'Rabais',
            discountPercent: 'Rabais en pourcent',
            amount: 'Montant',
            label: 'Libellé',
            note: 'Remarques',
            user: 'Utilisateur',
            paidFablab: 'Paye pour le FabLab ? ',
            paidDirectly: 'L\'utilisateur a-t-il payé directement ?',
            cashier: 'Utilisateur ayant encaissé le paiement',
            create: 'Création d\'un paiement annexe',
            edit: 'Edition du paiement du ',
            btnTitle: 'Est-ce que l\'utilisateur paye pour ses propres dépenses ou pour le FabLab ?',
            refund: 'Remboursement',
            accountCredit: 'Compte crédité',
            accountDebit: 'Compte débité',
            alertRefund: 'Votre total à payer est déjà écrit dans le champ total',
            alertOk: 'Vous êtes à jour. Le montant sera crédité à votre compte',
            cred: 'Remise de crédit sur le compte',
            refu: 'Remboursement du dû',
            notification: {
                saved: 'Paiement annexe sauvegardé',
                removed: 'Paiement annexe supprimé'
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer ce paiement annexe ?'
            }
        },
        eventType: {
            title: 'Type d\'évènements',
            label: 'Label',
            alreadyExist: 'Ce type d\'évènements existe déjà',
            create: 'Création d\'un type d\'évènements',
            edit: 'Edition de ',
            notification: {
                saved: 'Type d\'évènements sauvegardé',
                removed: 'Type d\'évènements supprimé'
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer type d\'évènements ?'
            }
        },
        eventPerson: {
            title: 'Inscrit',
            lastname: 'Nom',
            firstname: 'Prénom',
            email: 'Email',
            alreadyExist: 'Cet email existe déjà',
            create: 'Création d\'un inscrit',
            edit: 'Edition de ',
            haveAquired: 'Cette personnes a-t-elle des modules acquis ?',
            nonSelectedListLabel: 'Modules disponibles',
            selectedListLabel: 'Modules acquis',
            notification: {
                saved: 'Inscrit sauvegardé',
                removed: 'Inscrit supprimé',
                failed: 'Les modules suivants nécessites des prérequis non acquis par l\'utilsiateur courant : '
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer cet inscrit ?'
            }
        },
        eventModule: {
            title: 'module d\'évènements',
            name: 'Nom',
            description: 'Description',
            alreadyExist: 'Ce nom existe déjà',
            havePrerequisites: 'Ce module a-t-il des prerequis ?',
            create: 'Création d\'un module d\'évènements',
            edit: 'Edition de ',
            nonSelectedListLabel: 'Modules disponibles comme prérequis',
            selectedListLabel: 'Modules prérequis',
            notification: {
                saved: 'Module d\'évènements sauvegardé',
                removed: 'Module d\'évènements supprimé'
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer ce module d\'évènements ?'
            }
        },
        event: {
            title: 'Evènement',
            dateStart: 'Date de debut',
            dateEnd: 'Date de fin',
            timeStart: 'Heure de debut',
            timeEnd: 'Heure de fin',
            titleEvent: 'Titre',
            btnTitle: 'Cloner cet évènement',
            theme: 'Thème',
            place: 'Lieu',
            failedPerson: 'Les personnes suivantes ne disposent pas de tous les prérequis : ',
            description: 'Description',
            eventType: 'Type d\'évènement',
            supervisor: 'Supervisor',
            cashier: 'Utilisateur ayant encaissé',
            alreadyExist: 'Cet évènement existe déjà',
            create: 'Création d\'un évènement',
            edit: 'Edition de ',
            organizer: 'Organisateurs',
            participants: 'Participants',
            nonSelectedListLabelOrg: 'Organisateurs disponibles',
            selectedListLabelOrg: 'Organisateurs',
            nonSelectedListLabelPart: 'Participants disponibles',
            selectedListLabelPart: 'Participants',
            nonSelectedListLabelMod: 'Modules disponibles',
            selectedListLabelMod: 'Modules',
            addPerson: 'Ajouter la personne',
            price: 'Prix',
            modules: 'Modules',
            addPeople: 'Ajouter des personnes',
            notification: {
                saved: 'Evènement sauvegardé',
                cloned: 'Evènement cloné avec succès',
                clonedFailed: 'Le superviseur ou le type d\'évènement n\'existe plus, impossible de cloner l\'évènement',
                removed: 'Evènement supprimé',
                userSaved: 'Nouveau participant ajouté',
                userFailed: 'Une erreur s\'est produite lors de l\'ajout de la nouvelle personne. Email correct ? '
            },
            confirmation: {
                remove: 'Voulez-vous réellement supprimer cet évènement ?'
            }
        },
        eventPayment: {
            create: 'Création d\'un paiement relatif à un évènement',
            event: 'Evènement',
            user: 'Superviseur',
            saved: 'Paiement d\'évènement sauvegardé'
        },
		audit:{
			title:'Audit',
			object:'Object',
			content:'Contenu'
		}
    });
});
