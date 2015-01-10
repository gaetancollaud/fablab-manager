/**
 * Notification service
 */
angular.module('Loader', [], function ($provide) {
	$provide.factory('LoaderService', ['btfModal', '$timeout', function (btfModal, $timeout) {
			var loaderShown = 0;
			var shown = false;
			var modalService = {};

			var template = '' +
					'<div class="modal fade in" tabindex="-1" role="dialog" id="app-loader" aria-hidden="false" style="display: block;">' +
					'<div class="modal-dialog">' +
					'<div class="modal-content">' +
					'<div class="modal-header">' +
					'<h4 class="modal-title" id="commonPopupLoadingPageTitle" translate="loading.title"></h4>' +
					'</div> <div class="modal-body app-loader-body">' +
					'<p translate="loading.text"></p>' +
					//'<p style="text-align:center"> <img src="' + App.BASE_ROUTE_PROVIDER_URL + '/images/ajax-loader.gif"> </p> '+
					'</div> </div> </div></div>' +
					'<div class="modal-backdrop fade in" id="app-loader-backdrop"></div>';

			var close = function () {
				if (shown && loaderShown == 0) {
					shown = false;
					modalService.deactivate();
					var elt = $("#app-loader");
					if (elt) {
						elt.remove();
					}
					elt = $("#app-loader-backdrop");
					if (elt) {
						elt.remove();
					}
				}
			}
			return {
				show: function () {
					modalService = new btfModal({
						template: template
					});

					if (loaderShown == 0) {
						$timeout(function () {
							if (!shown && loaderShown > 0) {
								shown = true;
								modalService.activate();
							}
						}, 100);
					}
					loaderShown++;
				},
				hide: function () {
					loaderShown--;
					if (loaderShown < 0) {
						loaderShown = 0;
					}
					$timeout(function () {
						if (loaderShown == 0) {
							close();
							$timeout(function () {
								close();
							}, 2000);
						}
					}, 100);
				}
			};
		}]);
});