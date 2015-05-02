/** Error interceptor */
if (App == null) {
	App = new Object();
}

if (App.interceptors == null) {
	App.interceptors = new Object();
}

if (App.interceptors.event == null) {
	App.interceptors.event = new Object();
}



App.interceptors.XMLHttpRequestCall = function (xhr, progressBar, successFonc, errorFonc) {
	App.interceptors.errorInterceptor.loaderService.show();
	if (progressBar === true) {
		xhr.upload.addEventListener("progress", function (event) {
			var percent = Math.round((event.loaded / event.total) * 100);
			//uploadProgress(event,file.name);
		}, false);
	}
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var response = App.interceptors.success($.parseJSON(xhr.responseText));
				if (successFonc != undefined)
					successFonc(response.data);
			} else {
				var response = null;
				try {
					response = $.parseJSON(xhr.responseText);
				} catch (error) {
					console.error("Cannot parse json : " + error);
					response = {status: xhr.status};
				}
				App.interceptors.error(response);
				if (errorFonc != undefined) {
					errorFonc(response);
				}
			}
		}
	}
}
App.interceptors.success = function (response, scope) {
	App.interceptors.errorInterceptor.loaderService.hide();

	var message = null;

	if (response.data != null) {
		message = response.data.message;
		if (response.data.data != null) {
			response.data = response.data.data;
		}
	}

	if (message != null && message != "") {
		if (App.interceptors.isEventDriven()) {
			App.interceptors.fireEvent(App.interceptors.createEvent(
					App.interceptors.event.SUCCESS, message), scope);
		} else {
			App.interceptors.errorInterceptor.notificationService.notify('SUCCESS', null, message);
		}
	}

	return response;
}

App.interceptors.error = function (response, scope) {
	App.interceptors.errorInterceptor.loaderService.hide();

	var status = response.status;

	// Special case for file upload error handling...
	if (response.data == null) {
		if (response.message != null && response.success != null && response.status != null) {
			response.data = response;
		}
	}

	if (status == null && response.data != null) {
		status = response.data.status;
	}

	if (status == 401) { // unauthorized - redirect to login again
		window.location = App.Constants.rootUrl;
	} else if (response.data == null) {
		if (App.interceptors.isEventDriven()) {
			App.interceptors.fireEvent(App.interceptors.createEvent(
					App.interceptors.event.AJAX, null, response.status), scope);
		} else {
			App.interceptors.errorInterceptor.notificationService.showAjaxErrorMessage(response.status);
		}
	} else {
		var message = response.data.message;

		if (message == null || message == "") {
			if (App.interceptors.isEventDriven()) {
				App.interceptors.fireEvent(App.interceptors.createEvent(
						App.interceptors.event.AJAX, null, response.status), scope);
			} else {
				App.interceptors.errorInterceptor.notificationService.showAjaxErrorMessage(response.status);
			}

		} else {
			if (status == 400) { // validation error display errors

				if (response.data.violations != null) {
					message += "<br /><ul>";
					for (var i = 0; i < response.data.violations.length; i++) {
						message += "<li>" + response.data.violations[i].message + "</li>";
					}
					message += "</ul>";
				}

				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.ERROR, message, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.notify("ERROR", null, message);
				}

			} else if (status == 500) { // Internal server display errors
				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.ERROR, message, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.notify("ERROR", null, message);
				}

			} else if (status == 409) { // Opt lock error display errors
				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.OPT_LOCK, message, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.showOptimisticLock(message);
				}

			} else if (status == 403) { // Security error display errors
				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.ERROR, message, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.notify("ERROR", null, message);
				}

			} else if (status == 404) { // data not found display errors
				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.ERROR, message, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.notify("ERROR", null, message);
				}

			} else {
				if (App.interceptors.isEventDriven()) {
					App.interceptors.fireEvent(App.interceptors.createEvent(
							App.interceptors.event.AJAX, null, response.status), scope);
				} else {
					App.interceptors.errorInterceptor.notificationService.showAjaxErrorMessage(response.status);
				}

			}
		}
	}
}

App.interceptors.createEvent = function (type, message, status, data) {
	var event = new Object();
	event.type = type;
	event.message = message;
	event.status = status;
	event.data = data;
	return event;
}

App.interceptors.fireEvent = function (event, scope) {
	scope.$broadcast(App.Constants.ErrorInterceptor.EVENT_NAME, event);
}

App.interceptors.isEventDriven = function () {
	var ev = false;
	if (App != null && App.Constants != null && App.Constants.ErrorInterceptor != null) {
		ev = App.Constants.ErrorInterceptor.EVENT_DRIVEN;
	}
	return ev;
}

App.interceptors.eventHandler = function (event, evData, messageContainer, optLockFN) {
	var type = evData.type;
	if (type == App.interceptors.event.SUCCESS) {
		CoreUtils.MessageHelper.showMessage(evData.message, CoreUtils.MessageHelper.SUCCESS, true, messageContainer);
	} else if (type == App.interceptors.event.AJAX) {
		CoreUtils.MessageHelper.showAjaxErrorMessage(evData.status, null, false, messageContainer);
	} else if (type == App.interceptors.event.OPT_LOCK) {
		var optfn = function () {
			alert("Opt lock function is not defined !");
		};
		if (optLockFN != null) {
			optfn = optLockFN;
		}
		CoreUtils.MessageHelper.showOpLockMessage(evData.message, optfn, messageContainer)
	} else {
		CoreUtils.MessageHelper.showMessage(evData.message, CoreUtils.MessageHelper.ERROR, false, messageContainer);
	}
}

App.interceptors.errorInterceptor = {};
angular.module('httpInterceptor', [], ['$provide', function ($provide) {
		$provide.factory('httpInterceptor', ['$rootScope', '$q', '$log', function (scope, $q, $log) {
				return {
					request: function (config) {
						App.interceptors.errorInterceptor.loaderService.show();
						return config || $q.when(config);
					},
					response: function (response) {
						App.interceptors.success(response, scope);
						return response || $q.when(response);
					},
					responseError: function (rejection) {
						App.interceptors.error(rejection, scope);
						return $q.reject(rejection);
					}
				}
			}]);
	}]);
	