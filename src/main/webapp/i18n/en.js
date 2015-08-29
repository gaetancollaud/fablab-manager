angular.module('Fablab').config(function ($translateProvider) {
    $translateProvider.translations('en', {
        date: {
            format: {
                date: 'DD/MM/YYYY',
                datetime: 'DD/MM/YYYY hh:mm:ss',
                time: 'hh:mm:ss'
            }
        },
        home: {
            user: 'User'
        },
        general: {
            days: 'days',
            date: 'Date',
            user: 'User',
            detail: 'Detail',
            comment: 'Comment',
            toFr: 'Fr',
            toEn: 'En',
            yes: 'Yes',
            no: 'No',
            type: 'Type',
            erase: 'Erase',
            table: {
                action: 'Action'
            },
            form: {
                duration: 'Duration'
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
            userAccount: 'Comptes utilsiateurs',
            users: 'Users',
            reservation: 'Reservation',
            payment: 'Payment',
            accounting: 'Accounting',
            login: 'Login',
            logout: 'Logout',
            signup: 'Signup',
            machines: 'Machineries',
            machineTypes: 'Machinery types',
            machineState: 'Machinery states',
            machineStatus: 'Machinery status',
            admin: 'Administration',
            membershipTypes: 'Membership types',
            inOut: 'In / Out',
            revision: "Revisions",
            ticketStatus: 'Ticket status',
            tickets: 'Tickets',
            configurations: 'Configurations',
            key: 'Admin panel',
            supplies: 'Supplies',
            addFailure: 'Announce a failure',
            supplyTypes: 'Supply types',
            purchases: 'Purchases',
            addPurchase: 'Make a purchase',
            supplyUnities: 'Supply unities',
            motionStocks: 'Stock motion',
            priceMachines: 'Use cost',
            priceMachinesTable: 'Use cost table',
            trainingLevels: 'Training levels',
            trainings: 'Trainings',
            certification: 'Certifications',
            roles: 'Roles',
            groups: 'Groups',
            usage: 'Usages',
            usages: 'Usages',
            paymentsUse: 'Payment and usages',
            userPayments: 'Annex payments and <br/><span class="second-ligne-bis">remboursements</span>',
            event: "Evènements",
            eventTypes: 'Event types',
            eventPersons: 'Event persons list',
            eventModules: 'Event modules',
            events: 'Events',
            eventPayment: 'Event payments'
        },
        loading: {
            title: 'Loading',
            text: 'Loading...'
        },
        button: {
            cancel: 'Cancel',
            save: 'Save',
            return: 'Return',
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
            to: 'To'
        },
        auth: {
            result: {
                ok: 'Welcome',
                unknownUserPassword: 'Login and/or password incorrect'
            },
            pleaseSignIn: 'Please sign in',
            email: 'Email address',
            password: 'Password',
            signIn: 'Sign In',
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
            login: 'Login',
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
            genderEnum: {
                unknown: 'Unknown',
                male: 'Male',
                female: 'Female'
            },
            subscription: {
                subscribe: 'Subscribe',
                confirmButton: 'Confirm',
                confirmTitle: 'Confirm subscription',
                price: 'Price',
                never: {
                    myself: 'You haven\'t confirm your subription yet !',
                    user: 'The user {{user.firstname}} {{user.lastname}} has not confirm its subription yet !'
                },
                expired: {
                    myself: 'Your subscription has expired since {{epirationDate}} ({{dayLeft}} days)',
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
                removed: 'User removed'
            }
        },
        reservation: {
            day: 'Day',
            start: 'Start',
            end: 'End',
            user: 'User',
            machine: 'Machinery',
            type: 'Type',
            date: 'Date',
            from: 'From',
            to: 'To',
            hours: 'Hours',
            reservationForDay: 'Reservation for {{date}}',
            notification: {
                saved: 'Reservation saved',
                removed: 'Reservation removed'
            },
            edit: {
                title: "Book a machinery"
            }
        },
        payment: {
            userTitle: 'User',
            usageTitle: 'Add an usage',
            paymentTitle: 'Add a payment',
            credit: 'Credit',
            debit: 'Debit',
            type: 'Type',
            userNotAllowed: 'The connected user is not allowed to use this machine !',
            userNotAllowed2: 'A certification for the chosen machinery is required',
            enterName: 'Enter a name',
            machine: 'Machinery',
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
            history: 'User account',
            balanceText: 'Balance for user {{firstname}} {{lastname}} : <b>{{balance}}</b>',
            confirmation: {
                historyRemove: 'Do you really want to remove this history entry ?'
            },
            notification: {
                historyRemoved: 'Historique supprimé',
                usageAdded: 'Usage added',
                paymentAdded: 'Payment added'
            }
        },
        accounting: {
            title: 'In / Out',
            today: 'Today',
            yesterday: 'Yesterday',
            thisMonth: 'This month',
            lastMonth: 'Last month',
            thisYear: 'This year',
            lastYear: 'Last year',
            summary: 'Summary',
            sell: 'Sell',
            moneyIn: 'Money in',
            user: 'User',
            delta: 'Delta',
            debit: 'Debit',
            credit: 'Credit',
            accountCredit: 'Credit account',
            accountDebit: 'Debit account'
        },
        machine: {
            title: 'Machinery',
            code: 'Code',
            buyPrice: 'Buy price',
            name: 'Name',
            acquisitionDate: "Acquisition date",
            machineType: 'Type',
            machineState: 'State',
            machineStatus: 'Status',
            btnTitleRevision: 'Make a revision',
            btnTitleTicket: 'Announce a failure',
            revisions: 'Revisions',
            tickets: 'Tickets',
            alreadyExist: 'The specified code already exist !',
            toDoRevision: 'To-Do for revision',
            lists: 'Reservations & Tickets',
            create: 'Machinery creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery saved',
                removed: 'Machinery removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery ?'
            }
        },
        machineType: {
            title: 'Machinery type',
            name: 'Name',
            technicalname: 'Technical name',
            restricted: 'Restricted ?',
            alreadyExist: 'The technical name specified already exists !',
            membershipTypes: 'Price per membership type',
            create: 'Machinery type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery type saved',
                removed: 'Machinery type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery type ?'
            }
        },
        machineState: {
            title: 'Machinery state',
            label: 'Label',
            alreadyExist: 'The label specified already exists !',
            create: 'Machinery state creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery state saved',
                removed: 'Machinery state removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery state ?'
            }
        },
        machineStatus: {
            title: 'Machinery status',
            label: 'Label',
            color: 'Color',
            alreadyExist: 'The specified label already exist !',
            create: 'Machinery status creation',
            edit: 'Edit :',
            notification: {
                saved: 'Machinery status saved',
                removed: 'Machinery status removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this machinery status ?'
            }
        },
        membershipType: {
            title: 'Membership types',
            name: 'Name',
            duration: 'Duration',
            price: 'Price',
            durationDay: 'Days',
            alreadyExist: 'The specified name already exist !',
            machineTypes: 'Machinery types',
            create: 'Membership type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Membership type saved',
                removed: 'Membership type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this membership type ?'
            }
        },
        revision: {
            title: 'Revisions',
            revisionDate: 'Revision date',
            revisionTime: 'Revision time',
            note: 'Note',
            cost: 'Cost',
            machine: 'Machinery',
            machineName: 'Machinery',
            create: 'Revision creation',
            edit: 'Edit revision @',
            notification: {
                saved: 'Revision saved',
                removed: 'Revision removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this revision ?'
            }
        },
        ticketStatus: {
            title: 'Ticket status',
            label: 'Label',
            alreadyExist: 'The specified label already exist !',
            create: 'Ticket status creation',
            edit: 'Edit :',
            notification: {
                saved: 'Ticket status saved',
                removed: 'Ticket status removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this ticket status ?'
            }
        },
        ticket: {
            ticketTitle: 'Ticket',
            title: 'Title',
            description: 'Description',
            creationDateList: 'Creation date',
            creationDate: 'Create on',
            previsionCloseDate: 'Estimated closing date',
            closeDateList: 'Closing date',
            reopenDescr: 'This ticket was already closed at ',
            reopenDescr2: 'This ticket was re-open at ',
            by: ' by ',
            closeDate: 'Closed on',
            machine: 'Machinery',
            status: 'Status : ',
            creationUserList: 'Create by',
            creationUser: 'by',
            closeUserList: 'Closed by',
            closeUser: 'by',
            create: 'Ticket creation',
            edit: 'Edit :',
            alreadyExist: 'The specified title already exist !',
            closeTicket: 'Close this ticket',
            reOpenTicket: 'Re-Open this ticket',
            notification: {
                saved: 'Ticket saved',
                removed: 'Ticket removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this ticket ?'
            }
        },
        configuration: {
            title: 'Configuration',
            key: 'Key',
            value: 'Value',
            name: 'Common Name',
            create: 'Configuration creation',
            warningEdit: '<strong>Warning !</strong> Changes will be visible after a window refresh (F5 for most common browsers)',
            edit: 'Edit :',
            notification: {
                saved: 'Configuration saved; Don\'t forget to refresh the window (F5) to see changes in FabLab - Manager applicaiton.',
                removed: 'Configuration removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this configuration ?'
            }
        },
        supply: {
            title: 'Supply',
            code: 'Code',
            label: 'Label',
            sellingPrice: 'Selling price',
            unityBuyingPrice: 'Unity buying price',
            alreadyExist: 'The specified code already exist !',
            orderAddress: 'Order address',
            supplyType: 'Supply type',
            note: 'Note',
            addQuantity: 'Add some quantity to this supply',
            quantityStockModal: 'Quantity',
            supplyUnity: 'Unity',
            back: 'Return',
            initialQuantity: 'Initial quantity',
            modalTitle: 'Add some quantity to ',
            create: 'Supply creation',
            edit: 'Edit :',
            notification: {
                saved: 'Supply saved',
                removed: 'Supply removed',
                addQuantity: 'Quantity added'
            },
            confirmation: {
                remove: 'Do you really want to remove this supply ?'
            }
        },
        supplyType: {
            title: 'Supply type',
            label: 'Label',
            alreadyExist: 'The specified label already exist !',
            create: 'Supply type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Supply type saved',
                removed: 'Supply type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this supply type ?'
            }
        },
        purchase: {
            title: 'Purchase',
            purchaseDate: 'Purchase date',
            cashier: 'cashier',
            paidDirectly: 'Did the user pay directly ?',
            quantity: 'Quantity',
            purchasePrice: 'Purchase price',
            supply: 'Supply',
            user: 'User',
            unityPrice: 'Unity price',
            discount: 'Discount',
            note: 'Note',
            back: 'Return',
            create: 'Purchase creation',
            edit: 'Edit :',
            notification: {
                saved: 'Purchase saved',
                removed: 'Purchase removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this purchase ?'
            }
        },
        supplyUnity: {
            title: 'Supply unity',
            label: 'Label',
            floating: 'isFloating ? ',
            alreadyExist: 'The specified label already exist !',
            create: 'Supply unity creation',
            edit: 'Edit :',
            notification: {
                saved: 'Supply unity saved',
                removed: 'Supply unity removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this supply unity ?'
            }
        },
        motionStock: {
            title: 'Stock motion',
            motionDate: 'Motion date',
            quantity: 'Quantity',
            io: 'Io',
            supply: 'Supply',
            user: 'User'
        },
        priceMachine: {
            title: 'Use cost',
            titleList: 'Use cost table',
            price: 'Price',
            hour: '/ hour',
            machineType: 'Machinery type',
            membershipType: 'Membership type',
            create: 'Use cost creation',
            editAlter: 'Edit all prices',
            edit: 'Edit :',
            notification: {
                saved: 'Use cost saved',
                removed: 'Use cost removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this use cost ?'
            }
        },
        trainingLevel: {
            title: 'Training level',
            label: 'Label',
            alreadyExist: 'The specified label already exist !',
            create: 'Training level creation',
            edit: 'Edit :',
            notification: {
                saved: 'Training level saved',
                removed: 'Training level removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this training level ?'
            }
        },
        training: {
            title: 'Training',
            name: 'Name',
            price: 'Price',
            note: 'Note',
            trainingLevel: 'Training level',
            machineType: 'Machine type',
            alreadyExist: 'The specified name already exist !',
            nonSelectedListLabel: 'Available trainings as prerequisites',
            selectedListLabel: 'Prerequisites',
            havePrerequisites: 'Have one or more prerequisites',
            create: 'Training creation',
            edit: 'Edit :',
            notification: {
                saved: 'Training saved',
                removed: 'Training removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this training ?'
            }
        },
        certification: {
            title: 'Certification',
            name: 'Name',
            machineType: 'Machinery type',
            certificationDate: 'Certification date',
            certificationPrice: 'Certification price',
            alreadyExist: 'The specified name already exist !',
            btnTitle: 'Make a new certification',
            note: 'Note',
            training: 'Training',
            create: 'Certification creation',
            edit: 'Edit :',
            nonSelectedListLabel: 'Available users',
            selectedListLabel: 'Certified users',
            notification: {
                saved: 'Certification saved',
                removed: 'Certification removed',
                failed: '<strong>Error !</strong> the following users do not have all the prerequisites certifications : '
            },
            confirmation: {
                remove: 'Do you really want to remove this certification ?'
            }
        },
        role: {
            title: 'Groups roles',
            group: 'Select a group',
            roles: 'Roles',
            selectedListLabel: 'Assigned roles',
            nonSelectedListLabel: 'Available roles',
            notification: {
                saved: 'Roles saved'
            }
        },
        group: {
            title: 'Group',
            technicalname: 'Technical name',
            name: 'Name',
            roles: 'Roles',
            alreadyExist: 'The technical name specified already exists',
            nonSelectedListLabel: 'Assigned roles',
            selectedListLabel: 'Available roles',
            create: 'Group creation',
            edit: 'Edit :',
            notification: {
                saved: 'Group saved',
                removed: 'Group removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this group ?'
            }
        },
        duallist: {
            filterClear: 'Show all',
            filterPlaceholder: 'Filter',
            moveSelectedLabel: 'Move selected only',
            moveAllLabel: 'Move all',
            removeSelectedLabel: 'Remove selected only',
            removeAllLabel: 'Remove all',
            preserveSelection: 'moved',
            infoAll: 'Showing all {0}',
            infoFiltered: '<span class="label label-warning">Filtered</span> {0} from {1}',
            infoEmpty: 'Empty list!'
        },
        usage: {
            title: 'Usage',
            dateStart: 'Date',
            pricePerHour: 'Price per hour',
            discount: 'Discount',
            discountPercent: 'Discount percent',
            minutes: 'Use time',
            additionalCost: 'Additional cost',
            total: 'Total',
            note: 'Note',
            user: 'User',
            cashier: 'Cashier',
            machine: 'Machine',
            paidDirectly: 'Did the user pay directly ?',
            membershipType: 'Membership type',
            create: 'Usage creation',
            directPaid: 'Direct payed ?',
            userNotAllowed: 'The connected user is not allowed to use this machine !',
            userNotAllowed2: 'A certification for the chosen machinery is required',
            edit: 'Edit usage at ',
            notification: {
                saved: 'Usage saved',
                removed: 'Usage removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this usage ?'
            }
        },
        userPayment: {
            title: 'Annex payment',
            titleList: 'Annex payment and refund',
            total: 'Total',
            datePayment: 'Date',
            discount: 'Discount',
            discountPercent: 'Discount percent',
            amount: 'Amount',
            paidDirectly: 'Did the user pay directly ?',
            paidFablab: 'Pay for the FabLab ? ',
            label: 'Label',
            note: 'Note',
            user: 'User',
            cashier: 'Cashier',
            create: 'Annex payment creation',
            edit: 'Edit payment at :',
            btnTitle: 'Does the current user pay for him or for the FabLab ?',
            refund: 'Refund',
            accountCredit: 'Credit account',
            accountDebit: 'Debit account',
            alertRefund: 'Your total to refund is already set in the total input',
            alertOk: 'You\'re up to date, no need to refund. You can add some money to your account',
            cred: 'Credit the account',
            refu: 'Refund the account',
            notification: {
                saved: 'Annex payment saved',
                removed: 'Annex payment removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this annex payment ?'
            }
        },
        eventType: {
            title: 'Event type',
            label: 'Label',
            alreadyExist: 'This event type already exists',
            create: 'Event type creation',
            edit: 'Edit :',
            notification: {
                saved: 'Event type saved',
                removed: 'Event type removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this event type ?'
            }
        },
        eventPerson: {
            title: 'Event person',
            lastname: 'Lastname',
            firstname: 'Firstname',
            email: 'Email',
            alreadyExist: 'This event person already exists',
            create: 'Event person creation',
            edit: 'Edit :',
            haveAquired: 'Does this person aquired modules ?',
            nonSelectedListLabel: 'Available modules',
            selectedListLabel: 'Acquired modules',
            notification: {
                saved: 'Event person saved',
                removed: 'Event person removed',
                failed: 'The following modules contains prerequisits modules : '
            },
            confirmation: {
                remove: 'Do you really want to remove this event person ?'
            }
        },
        eventModule: {
            title: 'Event module',
            name: 'Name',
            description: 'Description',
            havePrerequisites: 'Does this module have prerequisites ?',
            alreadyExist: 'This event module already exists',
            create: 'Event module creation',
            edit: 'Edit :',
            nonSelectedListLabel: 'Available modules as prerequisites',
            selectedListLabel: 'Prerequisites modules',
            notification: {
                saved: 'Event module saved',
                removed: 'Event module removed'
            },
            confirmation: {
                remove: 'Do you really want to remove this event module ?'
            }
        }, event: {
            title: 'Event',
            dateStart: 'Date start',
            dateEnd: 'Date end',
            timeStart: 'Time start',
            timeEnd: 'Time end',
            titleEvent: 'Title',
            theme: 'Theme',
            place: 'Place',
            btnTitle: 'Cloner this event',
            description: 'Description',
            eventType: 'Event type',
            failedPerson: 'The following people does not have all the prerequisites : ',
            supervisor: 'Supervisor',
            cashier: 'Cashier',
            alreadyExist: 'This event already exists',
            create: 'Event creation',
            edit: 'Edit :',
            organizer: 'Organizers',
            participants: 'Participants',
            nonSelectedListLabelOrg: 'Available organizors',
            selectedListLabelOrg: 'Organizers',
            nonSelectedListLabelPart: 'Available participants',
            selectedListLabelPart: 'Participants',
            nonSelectedListLabelMod: 'Available modules',
            selectedListLabelMod: 'Modules',
            price: 'Price',
            modules: 'Modules',
            addPerson: 'Add person',
            eventPayment: 'Event payment',
            addPeople: 'Add people',
            notification: {
                saved: 'Event saved',
                cloned: 'Event cloned',
                clonedFailed: 'Supervisor or event typ does not exist anymore',
                removed: 'Event removed',
                userSaved: 'New participant added',
                userFailed: 'An error occurs during the new person save. Email correct ? '
            },
            confirmation: {
                remove: 'Do you really want to remove this event ?'
            }
        },
        eventPayment: {
            create: 'Event payment creation',
            event: 'Event',
            user: 'Supervisor',
            saved: 'Event payment saved'
        }
    });
});
