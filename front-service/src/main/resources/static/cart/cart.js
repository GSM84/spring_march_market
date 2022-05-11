angular.module('market').controller('cartController', function($scope, $http, $localStorage) {

    $scope.loadCart = function(){
        $http.get('http://localhost:5555/cart/api/v1/cart/?guestCartId=' + $localStorage.marchMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.increaseItemCount = function(productId){
        $http.post('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/increase/' + productId)
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.decreaseItemCount = function(productId){
        $http.post('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/decrease/' + productId)
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.clearCart = function(){
        $http.post('http://localhost:5555/cart/api/v1/cart/clear?guestCartId=' + $localStorage.marchMarketGuestCartId)
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.createNewOrder = function() {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (){
                $scope.clearCart();
            });
    };

    $scope.loadCart();
});