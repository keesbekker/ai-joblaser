appJob.controller('RegisterController',['$scope', function ($scope){

    $scope.customer ={};
    $scope.customer.firstName="";
    $scope.customer.lastName="";
    $scope.customer.email="";
    $scope.customer.phoneNumber="";
    $scope.customer.companyName="";
    $scope.customer.userName="";
    $scope.customer.password="";
    $scope.customer.repeatPassword="";

    if(window.localStorage.getItem("customer.firstName")){$scope.customer.firstName=window.localStorage.getItem("customer.firstName")}
    if(window.localStorage.getItem("customer.lastName")){$scope.customer.lastName=window.localStorage.getItem("customer.lastName")}
    if(window.localStorage.getItem("customer.email")){$scope.customer.email=window.localStorage.getItem("customer.email")}
    if(window.localStorage.getItem("customer.phoneNumber")){$scope.customer.phoneNumber=window.localStorage.getItem("customer.phoneNumber")}
    if(window.localStorage.getItem("customer.companyName")){$scope.customer.companyName=window.localStorage.getItem("customer.companyName")}
    if(window.localStorage.getItem("customer.userName")){$scope.customer.userName=window.localStorage.getItem("customer.userName")}





    $scope.save=function () {
        if($scope.customer.firstName !== "" && $scope.customer.lastName!=="" && $scope.customer.email!=="" && $scope.customer.phoneNumber!=="" && $scope.customer.companyName!=="" && $scope.customer.userName!=="" && $scope.customer.password!=="" && $scope.customer.repeatPassword!=="") {
            window.localStorage.setItem("customer.firstName", $scope.customer.firstName);
            window.localStorage.setItem("customer.lastName", $scope.customer.lastName);
            window.localStorage.setItem("customer.email", $scope.customer.email);
            window.localStorage.setItem("customer.phoneNumber", $scope.customer.phoneNumber);
            window.localStorage.setItem("customer.companyName", $scope.customer.companyName);
            window.localStorage.setItem("customer.userName", $scope.customer.userName);
            window.localStorage.setItem("customer.repeatPassword", $scope.customer.repeatPassword);
        }
        else{
            var errorElement = document.querySelector('.error');
            errorElement.textContent = "Please fill in all form fields";
            errorElement.classList.add('visible');
        }
    };

}]);



