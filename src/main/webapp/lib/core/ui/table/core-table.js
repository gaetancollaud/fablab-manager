(function(angular) {
  'use strict';

  var htmlFolder = '';

  var table = angular.module('core.ui.table', []);

  table.constant("tableDefaultValues", {
    itemPerPage:20
  });

  table.service('scTableService', ['$filter', function($filter) {
    this.createPagination = function(pageSize) {
      return {
        page: 0,
        pageSize: pageSize
      };
    };

    this.createEmptyPagination = function() {
      return {
        page: 0,
        pageSize: -1
      };
    };

    this.applyLocal = function(data, conf) {
      var newData = data;
      if (conf) {
        if (conf.sort) {
          newData.data = $filter('orderBy')(newData.data, conf.sort.key, conf.sort.order === 'DESC');
        }
        if (conf.page && conf.page.pageSize > 0) {
          var start = conf.page.page * conf.page.pageSize;
          var end = start + conf.page.pageSize;
          if (start >= newData.data.length) {
            newData.data = [];
          } else {
            if (end >= newData.data.length) {
              end = newData.data.length;
            }

            newData.data = newData.data.slice(start, end);
          }
        }
      }
      return newData;
    };
  }]);


  /**
  * table-commands: Used to call the table to execute operations
  * - getConfiguration: Get the paging / sorting info
  * - reload(resetPaging): Reload the table data (resetPaging: true to set page to 0)
  * - resetPaging: Set the page back to 0
  */
  table.directive('scTable', ['$timeout', 'coreService', 'scTableService', function($timeout, coreService, scTableService) {
    return {
      restrict: 'EA',
      replace: true,
      transclude: true,
      template: '<div class="sc-core-table" ng-transclude></div>',
      scope: {
        tableData: '=',
        loadData: '&',
        tableCommands: '=',
        localSorting: '@'
      },
      link: function(scope, element, attribute, controller) {
      },
      controller: ['$scope', '$element', function($scope, $element) {
        var nonSortedData = [];
        var instance = this;
        var headers = [];
        var paginators = [];
        var itemPerPages = [];
        var sort = {
          key: null,
          order: 'ASC'
        };
        var pagination = scTableService.createEmptyPagination();
        var itemPerPage = -1;
        var localSort = $scope.localSorting && $scope.localSorting.toLowerCase() === 'true';
        
        var fromLocal = false;

        var createConfiguration = function() {
          return {
            sort: sort,
            page: pagination
          };
        };
        var reloadData = function() {
          if ($scope.loadData) {
            fromLocal = false;
            $scope.loadData({
              configuration: createConfiguration()
            });
          }
        };
        var applySortPaging = function() {
          if (localSort) {
            fromLocal = true;
            $scope.tableData = scTableService.applyLocal(angular.copy(nonSortedData), createConfiguration());
          } else {
            fromLocal = false;
            reloadData();
          }
        };


        var updatePagination = function (){
          var size;
          if($scope.tableData && $scope.tableData.dataSize){
            size = $scope.tableData.dataSize;
          }
          for (var i=0; i<paginators.length; i++) {
            paginators[i].updatePagination(pagination, size);
          }
        };

        var updateItemPerPages = function(){
          for(var i=0; i<itemPerPages.length; i++){
            itemPerPages[i].updateItemPerPage(pagination.pageSize);
          }
        }

        $scope.$watch('tableData.dataSize', function(newVal, oldVal) {
          if (newVal !== oldVal) {
            updatePagination();
          }
        });


        var applySortToHeaders = function() {
          for (var i=0; i<headers.length; i++) {
            headers[i].sortSet(sort);
          }
        };

        this.applySort = function(key, order) {
          if (!key) {
            return;
          }
          if (!order && sort.key === key) {
            sort.order = sort.order === 'ASC'?'DESC':'ASC';
          } else {
            sort.key = key;
            sort.order = 'ASC';
          }
          applySortToHeaders();
          applySortPaging();
        };

        this.applyPaging = function(page) {
          if (!angular.equals(pagination, page)) {
            pagination = angular.copy(page);
            updatePagination();
            applySortPaging();
          }
        };

        this.applyItemPerPage = function(ps){
          itemPerPage = ps;
          pagination.pageSize = ps;
          updatePagination();
          updateItemPerPages();
          applySortPaging();
        }

        this.registerHeader = function(header) {
          headers.push(header);
          if (header.defaultSort) {
            sort.key = header.key;
            sort.order = header.defaultSort === 'DESC'?'DESC':'ASC';
            header.sortSet(sort);
          }
        };
        this.registerPaginator = function(paginator) {
          paginators.push(paginator);
          if(paginator.pageSize){
            pagination.pageSize = paginator.pageSize;
          }
          updatePagination();
        };
        this.registerItemPerPage = function(itemPerPage){
          itemPerPages.push(itemPerPage);
          itemPerPage = itemPerPage.ngModel;
          pagination.pageSize = itemPerPage;
          updatePagination();
          updateItemPerPages();
        };
        
        if (localSort) {
          $scope.$watch('tableData', function() {
            if (!fromLocal && $scope.tableData) {
              nonSortedData = angular.copy($scope.tableData);
              fromLocal = false;
              applySortPaging();
            }
          });
        }

        // Commands management
        $scope.$watch('tableCommands', function(newVal, oldVal) {
          if (newVal) {
            newVal.getConfiguration = function() {
              return createConfiguration();
            };
            newVal.resetPaging = function() {
              pagination.page = 0;
              updatePagination();
            };
            newVal.reload = function(resetPaging) {
              if (resetPaging) {
                newVal.resetPaging();
              }
              reloadData();
            };

            //TODO MG improve this !
            if (newVal.onTableReady) {
              $timeout(function() {
                newVal.onTableReady();
              }, 100);
            }
          }
        });
      }]
    };
  }]);

  table.directive('scTableHead', ['$log', 'coreService', function($log, coreService) {
    return {
      restrict: 'EA',
      require: '^scTable',
      replace: true,
      transclude: true,
      templateUrl: coreService.getResource('lib/core/ui/table/core-table-head.html'),
      scope: {
        sortable: '@?',
        key: '@?',
        defaultSort: '@'
      },
      link: function(scope, element, attribute, controller) {
        scope.isSortable = true;

        scope.sortInfo = {};

        if (scope.sortable && scope.sortable.toLowerCase() === 'false') {
          scope.isSortable = false;
        } else {
          // Check scope variables
          if (!scope.key || scope.key === '') {
            $log.warn('scTableHead: You must specify "key" when sortable is true !');
          }
        }

        scope.sort = function() {
          if (scope.isSortable) {
            controller.applySort(scope.key);
          }
        };


        scope.sortSet = function(sort) {
          if (sort.key === scope.key) {
            scope.sortInfo = sort;
          } else {
            scope.sortInfo = {};
          }
        };



        // Register header
        controller.registerHeader(scope);
      }
    };
  }]);

  table.directive('scTablePaginator', ['scTableService', 'coreService','tableDefaultValues', function(scTableService, coreService, tableDefaultValues) {
    return {
      restrict: 'EA',
      require: '^scTable',
      replace: true,
      transclude: true,
      templateUrl: coreService.getResource('lib/core/ui/table/core-table-paginator.html'),
      scope: {
        slider: '@',
        showFast: '@'
      },
      link: function(scope, element, attribute, controller) {

        scope.pagination = {};
        scope.dataSize = 0;
        scope.totalPages = 0;
        scope.pages = [];
        scope.enableSlider = true;
        if(attribute.pageSize){
          scope.pageSize = attribute.pageSize;
        }else{
          scope.pageSize = tableDefaultValues.pageSize;
        }

        var buildPages = function(count) {

        }
        var pages = [];

        // Nav in pages
        scope.selectPage = function(page) {
          var goTo = page;
          if (goTo < 0) {
            goTo = 0;
          }
          if (goTo >= scope.totalPages) {
            goTo = scope.totalPages - 1;
          }
          scope.pagination.page = goTo;
          controller.applyPaging(scope.pagination);
        };


        var createPage = function(index) {
          return {
            label: index + 1,
            index: index
          };
        };
        var createNoPage = function() {
          return {
            label: '...',
            index: null
          };
        };

        var buildPages = function() {
          if(!scope.dataSize){
            return;
          }
          var pages = [];
          var nbPages = Math.floor(scope.dataSize / scope.pageSize);
          // Add a page if the last page is not complete
          if (scope.dataSize % scope.pageSize > 0) {
            nbPages++;
          }
          scope.totalPages = nbPages;
          if (nbPages > 0) {
            if (scope.pagination.page >= scope.totalPages) {
              scope.selectPage(nbPages - 1);
            }


            var slider = 10;
            var enableSlider = true;
            if (scope.slider) {
              if (scope.slider.toLowerCase() === 'false'){
                enableSlider = false;
              }
            }
            scope.enableSlider = enableSlider;

            if (enableSlider && nbPages > slider) {
              var start;
              var end;

              start = scope.pagination.page - 3;
              end = scope.pagination.page + 3;

              var addStart = false;
              var addEnd = false;

              if (start < 3) {
                start = 0;
                end = 7;
              } else {
                addStart = true;
              }
              if (scope.totalPages - 1 - end < 3) {
                end = scope.totalPages - 1;
                start = scope.totalPages - 1 - 7;
              } else {
                addEnd = true;
              }

              if (!!addStart) {
                pages.push(createPage(0));
                pages.push(createNoPage());
              }

              for (var i = start; i <= end; i++) {
                pages.push(createPage(i));
              }

              if (!!addEnd) {
                pages.push(createNoPage());
                pages.push(createPage(scope.totalPages - 1));
              }
            } else {
              scope.enableSlider = false;
              for (var i = 0; i < nbPages; i++) {
                // All pages are displayed
                pages.push(createPage(i));
              }
            }
          }
          scope.pages = pages;
        };

        scope.previous = function() {
          scope.selectPage(scope.pagination.page - 1);
        };
        scope.previousFast = function() {
          scope.selectPage(scope.pagination.page - 5);
        };
        scope.next = function() {
          scope.selectPage(scope.pagination.page + 1);
        };
        scope.nextFast = function() {
          scope.selectPage(scope.pagination.page + 5);
        };

        scope.updatePagination = function(pagination, dataSize) {
          scope.pagination = angular.copy(pagination);
          scope.dataSize = dataSize;
          scope.pageSize = pagination.pageSize;
          buildPages();
        };

        controller.registerPaginator(scope);
      }
    };
  }]);

  table.directive('scTableItemPerPage', ['scTableService', 'coreService', 'tableDefaultValues', function (scTableService, coreService, tableDefaultValues) {
    return {
      restrict: 'EA',
      require: '^scTable',
      replace: true,
      transclude: true,
      templateUrl: coreService.getResource('lib/core/ui/table/core-table-item-per-page.html'),
      scope: {
        default: '=',
        values: '=',
        label: '='
      },
      link: function (scope, element, attribute, controller) {

        if (scope.default) {
          scope.ngModel = scope.default;
        } else if (scope.values && scope.values[0]) {
          scope.ngModel = scope.values[0];
        } else {
          scope.ngModel = tableDefaultValues.itemPerPage;
        }

        scope.itemPerPageChange = function(){
          controller.applyItemPerPage(scope.ngModel);
        };

        scope.updateItemPerPage = function(newValue){
          scope.ngModel = newValue;
        };

        controller.registerItemPerPage(scope);
      }
    };
  }]);

})(angular);
