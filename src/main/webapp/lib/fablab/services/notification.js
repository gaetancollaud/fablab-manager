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
	$provide.factory('NotificationService', ['ngToast', '$translate', function (toaster, $translate) {
			var notify = function (level, title, html) {
				if (level !== 'success' && level !== 'warning' && level !== 'info') {
					level = 'danger';
				}
				var content = "<b>" + $translate.instant(title) + "</b><br/>";
				if (html) {
					content += html;
				}
				toaster.create({
					class: level.toLowerCase(),
					content: content,
					compileContent: true,
				});
//				switch (level) {
//					case 'SUCCESS':
//						toaster.create('success', title, html, 5000, 'trustedHtml');
//						break;
//					case 'ERROR':
//						toaster.pop('error', title, html, 15000, 'trustedHtml');
//						break;
//					case 'INFO':
//						toaster.pop('note', title, html, 5000, 'trustedHtml');
//						break;
//					case 'WARN':
//						toaster.pop('warning', title, html, 15000, 'trustedHtml');
//						break;
//				}
			};
			return {
				/**
				 * Examples: 
				 * notificationService.notify("SUCCESS", "SUCCESS", "SUCCESS Level");
				 * notificationService.notify("ERROR", "ERROR", "ERROR Level");
				 * notificationService.notify("WARN", "WARNING", "WARNING Level");
				 * notificationService.notify("INFO", "INFO", "INFO Level");
				 */
				notify: notify,
				showAjaxErrorMessage: function (status) {
					switch(status){
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
					toaster.clear();
				}
			};
		}]);
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
	}
});