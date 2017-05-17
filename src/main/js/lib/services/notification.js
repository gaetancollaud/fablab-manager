import angular from 'angular'
import toastr from 'toastr'

/**
 * Notification service
 */
if (App == null) {
	App = {};
}
if (App.optLockFn == null) {
	App.optLockFn = function () {
		location.reload();
	};
}
angular.module('Notification', [], function ($provide) {
	$provide.factory('NotificationService', function ($translate) {
		var notify = function (level, title, html) {
			level = level.toLowerCase();
			if (level !== 'success' && level !== 'warn' && level !== 'info') {
				level = 'error';
			}

			var toastrContent = $translate.instant(title);
			var toastrTitle;
			if (html) {
				toastrTitle = toastrContent;
				toastrContent = html;
			}

			switch (level) {
				case 'success':
					toastr.success(toastrContent, toastrTitle);
					break;
				case 'warn':
					toastr.warning(toastrContent, toastrTitle);
					break;
				case 'error':
					toastr.error(toastrContent, toastrTitle);
					break;
				case 'info':
				default:
					toastr.info(toastrContent, toastrTitle);
			}
		};
		return {
			notify: notify,
			showAjaxErrorMessage: function (status) {
				switch (status) {
					case 403:
						notify('ERROR', 'error.ajax.unauthorized');
						break;
					default:
						notify('ERROR', 'error.ajax.global', status);

				}
			},
			showOptimisticLock: function (message, fn) {
				var optLockFn = function () {
					location.reload();
				};
				if (fn != null) {
					optLockFn = fn;
				}

				App.optLockFn = optLockFn;

				var msg = message +
						"<br /><br />" +
						"<a type=\"button\" class=\"btn btn-default btn-glyph\" onclick=\"App.optLockFn()\" style=\"color: black;\">" +
						"Reload <span class=\"glyphicon glyphicon-refresh\"></span></a>";
				notify('WARN', null, msg);

			},
			clear: function () {
//					toaster.clear();
				ngNotify.dismiss();
			}
		};
	});
}).filter("formatObject", function () {
	return function (object) {
		var ret = "";
		var lev = 0;
		function recursive(obj) {
			ret += "<div style='margin-left : " + lev + "px'>";
			lev += 5;
			for (var key in obj) {
				ret += key + ":";
				if (Object.prototype.toString.call(obj[key]) == "[object Object]")
					recursive(obj[key]);
				else if (Object.prototype.toString.call(obj[key]) == "[object String]")
					ret += obj[key] + "<br />";

			}
			ret += "</div>";
		}
		recursive(object);

		return ret;
	};
});