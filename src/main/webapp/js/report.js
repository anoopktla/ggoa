var app = angular.module('report',['ngRoute']);
app.config(function($routeProvider) {
  $routeProvider
.when('/', {
    templateUrl : 'index.html',
    controller  : 'EmployeeController'
  })
    .otherwise({redirectTo: '/'});
  });
app.service('EmployeeCRUDService', [ '$http', function($http) {

 this.getEmployee = function getEmployee(employeeId) {
        return $http({
            method : 'GET',
            url : '../employees/' + employeeId
        });
    }

   }]);

    app.controller('CRUDCtrl', ['$scope','EmployeeCRUDService','$routeParams',
    function ($scope,EmployeeCRUDService,$routeParams) {
          $scope.getEmployee = function () {

          var url_string = window.location.href;
          var url = new URL(url_string);
          var id = url.searchParams.get("id");
          $scope.month = url.searchParams.get("month");
          $scope.year = url.searchParams.get("year");
           $scope.numberOfDays = url.searchParams.get("numberOfDays");

          EmployeeCRUDService.getEmployee(id)
                .then(function success(response) {
                    $scope.employee = response.data;
                    $scope.employee.id = id;
                    $scope.message='';
                    $scope.errorMessage = '';

                },
                function error (response) {
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'Employee not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting employee!";
                    }
                });
          };
           $scope.init = function () {
                      var url_string = window.location.href
                      var url = new URL(url_string);
                      var id = url.searchParams.get("id");

                    };



         }]);