var app = angular.module('app',['ngRoute']);
app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'index.html',
    controller  : 'VillaController'
  })

  .when('/list', {
    templateUrl : 'villalist',
    controller  : 'VillaController'
  })

  .when('/viewEmployeeDetails', {
    templateUrl : 'viewEmployeeDetails',
    controller  : 'VillaController'
  })
  .when('/new', {
      templateUrl : 'new',
      controller  : 'VillaController'
    })
  .when('/update', {
      templateUrl : 'new',
      controller  : 'VillaController',

   })
   .otherwise({redirectTo: '/'});
});
app.service('VillaCRUDService', [ '$http', function($http) {
 this.getAllVillas = function getAllVillas() {
        return $http({
            method : 'GET',
            url : 'villas/'
        });
    }
    this.getVilla = function getVilla(villaId) {
        return $http({
            method : 'GET',
            url : 'villas/' + villaId
        });
    }
    this.addVilla = function addVilla(villa) {

        return $http({
            method : 'POST',
            url : 'villas',
            data : {
            name :villa.name,
            villaId :villa.villaId,
            address: villa.address,
            employmentDetails: employee.employmentDetails,
            personalDetails : employee.personalDetails,
            leaveDetails : employee.leaveDetails
            }
        });
    }

} ]);
app.controller('VillaController', ['$scope','VillaCRUDService','$routeParams','$location',
 function ($scope,VillaCRUDService, $routeParams,$location) {
 $scope.getAllVillas = function () {
           VillaCRUDService.getAllVillas()
             .then(function success(response) {
                 $scope.villas = response.data;
                 $scope.message='';
                 $scope.errorMessage = '';
                 },
             function error (response) {
                 $scope.message='';
                 $scope.errorMessage = 'Error getting villas!';
             });

       };
       $scope.getVilla = function () {
                 VillaCRUDService.getVilla($routeParams.id)
                                     .then(function success(response) {
                        $scope.villa = response.data;
                        $scope.monthName=$routeParams.monthName;
                        $scope.message='';
                        $scope.errorMessage = '';
                        },
                    function error (response) {
                        $scope.message='';
                        $scope.errorMessage = 'Error getting villas!';
                    });

       };
       $scope.addVilla = function () {


               if(!$routeParams.mode){

              VillaCRUDService.addVilla($scope.villa)
                          .then(function success(response) {
                               $scope.villa = response.data;
                              $scope.message='';
                              $scope.errorMessage = '';
                              $location.path('viewVillaDetails?id=');
                              },
                           function error (response) {
                               $scope.message='';
                              $scope.errorMessage = 'Error adding villa!';
                           });
}else {

 VillaCRUDService.updateVilla($scope.villa)
                          .then(function success(response) {
                               $scope.villa = response.data;
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
              $scope.deleteVilla = function () {


                            VillaCRUDService.deleteVilla($scope.villa.id)
                                        .then(function success(response) {

                                            $scope.message='';
                                            $scope.errorMessage = '';
                                             $location.path('list');
                                            },
                                         function error (response) {
                                             $scope.message='';
                                            $scope.errorMessage = 'Error deleting villa!';
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
       VillaCRUDService.getAllCountries()
       .then(function success(response) {
       $scope.countries = response.data;
        $scope.message='';
         $scope.errorMessage = '';
         },
          function error (response) {
           $scope.message='';
            $scope.errorMessage = 'Error getting salutations!';
            });
       VillaCRUDService.getAllStates()
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

