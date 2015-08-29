(function () {
    'use strict';

    angular.module('Fablab').service('StaticDataService', function (GroupService,
            MachineService, MachineTypeService, MachineStatusService, MachineStateService,
            MembershipTypeService, TicketStatusService, ConfigurationService, SupplyTypeService,
            SupplyService, UserService, SupplyUnityService, TrainingLevelService,
            TrainingService) {

        this.loadMemberShipTypes = function (successFn) {
            MembershipTypeService.list(successFn);
        };
        this.loadGroups = function (successFn) {
            GroupService.list(successFn);
        };
        this.loadMachineries = function (successFn) {
            MachineService.list(successFn);
        };
        this.loadMachineriesDispo = function (successFn) {
            MachineService.getStatusLabel("Disponible", successFn);
        };
        this.loadMachineTypes = function (successFn) {
            MachineTypeService.list(successFn);
        };
        this.loadMachineStatus = function (successFn) {
            MachineStatusService.list(successFn);
        };
        this.loadMachineStates = function (successFn) {
            MachineStateService.list(successFn);
        };
        this.findSimpleMachineByCode = function (code, successFn) {
            MachineService.findSimpleByCode(code, successFn);
        };
        this.loadTicketStatus = function (successFn) {
            TicketStatusService.list(successFn);
        };
        this.getConf = function (key, successFn) {
            ConfigurationService.findByKey(key, successFn);
        };
        this.prettyCreationDate = function (date) {
            return moment(date).format('DD.MM.YYYY');
        };
        this.loadSupplyTypes = function (successFn) {
            SupplyTypeService.list(successFn);
        };
        this.loadSupplies = function (successFn) {
            SupplyService.list(successFn);
        };
        this.loadSupplyStock = function (successFn) {
            SupplyService.stock(successFn);
        };
        this.loadUsers = function (successFn) {
            UserService.list(successFn);
        };
        this.loadSupplyUnities = function (successFn) {
            SupplyUnityService.list(successFn);
        };
        this.loadTrainingLevels = function (successFn) {
            TrainingLevelService.list(successFn);
        };
        this.loadTrainings = function (successFn) {
            TrainingService.list(successFn);
        };
        this.loadUsers = function (successFn) {
            UserService.list(successFn);
        };
        this.loadCashiers = function (successFn) {
            UserService.list(successFn);
        };
        this.loadSupervisors = function (successFn) {
            UserService.list(successFn);
        };

    });

}());