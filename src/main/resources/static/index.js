angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.deleteProduct = function (id){
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (){
            $scope.fillTable();
        });
    };

    $scope.createNewProduct = function(){
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function(){
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.getCart = function(){
        if ($scope.jwtToken != null) {
            $http.defaults.headers.common.Authorization = "Bearer " + $scope.jwtToken;
            $http.get('http://localhost:8189/market/api/v1/cart')
                .then(function (response) {
                    $scope.cart = response.data;
                });
        }
    }

    $scope.addToCart = function(productId){
        $http.post('http://localhost:8189/market/api/v1/cart/add/' + productId)
            .then(function (){
               $scope.getCart();
            });
    }

    $scope.increaseItemCount = function(productId){
        $http.post('http://localhost:8189/market/api/v1/cart/increase/' + productId)
            .then(function(){
                $scope.getCart();
            });
    }

    $scope.decreaseItemCount = function(productId){
        $http.post('http://localhost:8189/market/api/v1/cart/decrease/' + productId)
            .then(function(){
                $scope.getCart();
            });
    }

    $scope.clearCart = function(){
        $http.post('http://localhost:8189/market/api/v1/cart/clear')
            .then(function(){
                $scope.getCart();
            });
    }

    $scope.authUser = function(){
        $http.post('http://localhost:8189/market/auth', $scope.jwtRequest)
            .then(function(response){
                $scope.jwtToken = response.data["token"];
                $scope.user = null;
                $scope.getCart();
            });
    };

    $scope.fillTable();
    $scope.getCart();
});