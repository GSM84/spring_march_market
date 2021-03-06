angular.module('market').controller('storeController', function($scope, $http, $localStorage) {

    $scope.loadProducts = function (page = 1) {
        $http({
            url : 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params : {
                p: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            console.log($scope.productsPage);
            $scope.generatePagesList($scope.productsPage.totalPages);
        });
    };

    $scope.addToCart = function(productId){
        $http.post('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/add/' + productId)
            .then(function () {});
    };

    $scope.generatePagesList = function (totalPages){
        out = [];
        for (let i = 0;i < totalPages;i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.loadProducts();
});