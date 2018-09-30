var app = angular.module('app',['ngRoute']);
app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'index.html',
    controller  : 'EmployeeController'
  })

  .when('/list', {
    templateUrl : 'list',
    controller  : 'EmployeeController'
  })

  .when('/viewEmployeeDetails', {
    templateUrl : 'viewEmployeeDetails',
    controller  : 'EmployeeController'
  })
  .when('/new', {
      templateUrl : 'new',
      controller  : 'EmployeeController'
    })
  .when('/update', {
      templateUrl : 'new',
      controller  : 'EmployeeController',

   })
   .otherwise({redirectTo: '/'});
});
app.service('EmployeeCRUDService', [ '$http', function($http) {

    this.getEmployee = function getEmployee(employeeId) {
        return $http({
            method : 'GET',
            url : 'employees/' + employeeId
        });
    }
    this.addEmployee = function addEmployee(employee) {

        return $http({
            method : 'POST',
            url : 'employees',
            data : {
            name :employee.name,
            employeeId :employee.employeeId,
            address: employee.address,
            employmentDetails: employee.employmentDetails,
            personalDetails : employee.personalDetails,
            leaveDetails : employee.leaveDetails
            }
        });
    }
    this.updateEmployee = function updateEmployee(employee) {
        return $http({
            method : 'PUT',
            url : 'employees/'+employee.id,
            data : {
                name :employee.name,
                            employeeId :employee.employeeId,
                            address: employee.address,
                            employmentDetails: employee.employmentDetails,
                            personalDetails : employee.personalDetails,
                            leaveDetails : employee.leaveDetails

            }
        });
    }
    this.deleteEmployee = function deleteEmployee(id) {
        return $http({
            method : 'DELETE',
            url : 'employees/' + id
        })
    }
    this.getAllEmployees = function getAllEmployees() {
        return $http({
            method : 'GET',
            url : 'employees'
        });
    }
    this.getAllDesignations = function getAllDesignations() {
            return $http({
                method : 'GET',
                url : 'designations'
            });
        }
        this.getAllSalutations = function getAllSalutations() {
                    return $http({
                        method : 'GET',
                        url : 'salutations'
                    });
                }
         this.getAllStates = function getAllStates() {
                            return $http({
                                method : 'GET',
                                url : 'states'
                            });
                        }
     this.getAllCountries = function getAllStates() {
      return $http({
      method : 'GET',
      url : 'countries'
      });
                                                }
} ]);
app.controller('EmployeeController', ['$scope','EmployeeCRUDService','$routeParams','$location',
 function ($scope,EmployeeCRUDService, $routeParams,$location) {
 $scope.getAllEmployees = function () {
           EmployeeCRUDService.getAllEmployees()
             .then(function success(response) {
                 $scope.employees = response.data;
                 $scope.message='';
                 $scope.errorMessage = '';
                 },
             function error (response) {
                 $scope.message='';
                 $scope.errorMessage = 'Error getting employees!';
             });

       };
       $scope.getEmployee = function () {
                 EmployeeCRUDService.getEmployee($routeParams.id)
                                     .then(function success(response) {
                        $scope.employee = response.data;
                        $scope.monthName=$routeParams.monthName;
                        $scope.message='';
                        $scope.errorMessage = '';
                        },
                    function error (response) {
                        $scope.message='';
                        $scope.errorMessage = 'Error getting employees!';
                    });

       };
       $scope.addEmployee = function () {


               if(!$routeParams.mode){

              EmployeeCRUDService.addEmployee($scope.employee)
                          .then(function success(response) {
                               $scope.employee = response.data;
                              $scope.message='';
                              $scope.errorMessage = '';
                              $location.path('viewEmployeeDetails?id=');
                              },
                           function error (response) {
                               $scope.message='';
                              $scope.errorMessage = 'Error adding employee!';
                           });
}else {

 EmployeeCRUDService.updateEmployee($scope.employee)
                          .then(function success(response) {
                               $scope.employee = response.data;
                              $scope.message='';
                              $scope.errorMessage = '';
                              $location.path('list');
                              },
                           function error (response) {
                               $scope.message='';
                              $scope.errorMessage = 'Error updating employee!';
                           });
}

              };
              $scope.deleteEmployee = function () {


                            EmployeeCRUDService.deleteEmployee($scope.employee.id)
                                        .then(function success(response) {

                                            $scope.message='';
                                            $scope.errorMessage = '';
                                             $location.path('list');
                                            },
                                         function error (response) {
                                             $scope.message='';
                                            $scope.errorMessage = 'Error deleting employee!';
                                         });

                            };

         $scope.generateReport = function (){

         const monthNames = ["January", "February", "March", "April", "May", "June",
           "July", "August", "September", "October", "November", "December"
         ];

         var dateArray = $scope.reportDate.split("/");
         var numberOfDays = new Date(dateArray[1],dateArray[0], 0).getDate();
         $scope.monthName = monthNames[parseInt(dateArray[0])-1];
         $scope.year = dateArray[1];
         var reportUrl = '/reports/report?id='+$scope.employee.id+'&month='+$scope.monthName+'&year='+$scope.year+'&numberOfDays='+numberOfDays;
         window.open(reportUrl);
         console.log($scope.monthName);
         console.log($scope.year);
         };




       $scope.getAllStaticValues = function () {
       EmployeeCRUDService.getAllDesignations()
                   .then(function success(response) {
                       $scope.designations = response.data;
                       $scope.message='';
                       $scope.errorMessage = '';

                   },
                   function error (response) {
                       $scope.message='';
                       $scope.errorMessage = 'Error getting designations!';
                   });
       EmployeeCRUDService.getAllSalutations()
                                      .then(function success(response) {
                                          $scope.salutations = response.data;
                                          $scope.message='';
                                          $scope.errorMessage = '';

                                      },
                                      function error (response) {
                                          $scope.message='';
                                          $scope.errorMessage = 'Error getting salutations!';
                                      });
       EmployeeCRUDService.getAllCountries()
       .then(function success(response) {
       $scope.countries = response.data;
        $scope.message='';
         $scope.errorMessage = '';
         },
          function error (response) {
           $scope.message='';
            $scope.errorMessage = 'Error getting salutations!';
            });
       EmployeeCRUDService.getAllStates()
       .then(function success(response) {
       $scope.states = response.data;
       $scope.message='';
       $scope.errorMessage = '';
       },
      function error (response) {
      $scope.message='';
      $scope.errorMessage = 'Error getting salutations!';
      });

       };




 }



]);

