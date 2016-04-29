angular.module('Fablab').directive('markdownEditor', function ($timeout, $translate, $compile) {
	var index = 0;
	return {
		restrict: 'EA',
		scope: {
			ngModel: '='
		},
		templateUrl: 'lib/directives/form/markdown-editor.html',
		controller: function ($scope, $element, $compile) {
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

			//$element.find('textarea').attr('msd-elastic', '');
			//$compile($element.contents())($scope);

			$scope.$watch('ngModel', function(){
				$timeout(function(){
					$scope.$broadcast('elastic:adjust');
				});
			});

		},
		link:function(scope, element){
			element.find('textarea').attr('msd-elastic', '\n\n');
			$compile(element.contents())(scope);
		}
	};
});