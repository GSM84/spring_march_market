angular.module('market').controller('registrationController', function($scope, $http, $window) {
    $scope.register = function() {
        $http.post('http://localhost:5555/auth/register', $scope.newUser)
            .then(function successCallback(){
                    $scope.newUser.username = null;
                    $scope.newUser.password = null;
                    $scope.newUser.confirmPassword = null;
                    $window.location.href = '/market-front/index.html';
                }, function errorCallback() {
                    alert('wrong password');
                    $scope.newUser.password = null;
                    $scope.newUser.confirmPassword = null;
                }
            );
    };
});