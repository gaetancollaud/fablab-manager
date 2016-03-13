angular.module('Fablab').directive('markdownEditor', function ($timeout, $translate) {
	var index = 0;
	return {
		restrict: 'EA',
		scope: {
			ngModel: '='
		},
		templateUrl: 'lib/directives/form/markdown-editor.html',
		controller: function ($scope) {
			$scope.actions = [];
			var addAction = function (action, icon) {
				$scope.actions.push({
					command: action,
					icon: icon
				});
			}

			addAction('bold', 'bold');
			addAction('italic', 'italic');
			addAction('code', 'code');
			addAction('ullist', 'list-ul');
			addAction('ollist', 'list-ol');
			addAction('link', 'link');
			addAction('img', 'picture-o');
			addAction('h1', 'header');
		}
	};
});