angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                // console.log(response);
            });
    };

    $scope.deleteProduct = function (id){
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (){
            $scope.fillTable();
        });
    };

    $scope.createNewProduct = function(){
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function(){
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.getCart = function(){
        $http.get('http://localhost:8189/market/api/v1/cart')
            .then(function(response){
                $scope.cart = response.data;
            });
    }

    $scope.addToCart = function(id){
        $http.post('http://localhost:8189/market/api/v1/cart/' + id)
            .then(function (){
               $scope.getCart();
            });
    }

    $scope.fillTable();
    $scope.getCart();
});