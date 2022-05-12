angular.module('market').controller('storeController', function($scope, $http, $localStorage) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.addToCart = function(productId){
        $http.post('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/add/' + productId)
            .then(function () {});
    };

    $scope.loadProducts();
});