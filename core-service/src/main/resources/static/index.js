angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.marchMarketUser) {
        try {
            let jwt = $localStorage.marchMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.marchMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.marchMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marchMarketUser.token;
        }
    }

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market-core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.isUserLoggedIn = function() {
        return !!($localStorage.marchMarketUser);
    };

    $scope.tryToAuth = function() {
        $http.post('http://localhost:8189/market-core/auth', $scope.user)
                .then(function successCallBack(response){
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};
                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }, function errorCallBack() {
                    alert('wrong passord');
                        $scope.user.username = null;
                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                );
    };

    $scope.clearUser = function() {
        delete $localStorage.marchMarketUser;
        $http.defaults.headers.common.Authorization = '';
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    // $scope.deleteProduct = function (id){
    //     $http.delete('http://localhost:8189/market/api/v1/products/' + id)
    //         .then(function (){
    //         $scope.loadProducts();
    //     });
    // };

    $scope.createNewProduct = function(){
        $http.post('http://localhost:8189/market-core/api/v1/products', $scope.newProduct)
            .then(function(){
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    };

    $scope.loadCart = function(){
        $http.get('http://localhost:8190/market-cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });

    }

    $scope.addToCart = function(productId){
        $http.post('http://localhost:8190/market-cart/api/v1/cart/add/' + productId)
            .then(function (){
               $scope.loadCart();
            });
    }

    $scope.increaseItemCount = function(productId){
        $http.post('http://localhost:8190/market-cart/api/v1/cart/increase/' + productId)
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.decreaseItemCount = function(productId){
        $http.post('http://localhost:8190/market-cart/api/v1/cart/decrease/' + productId)
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.clearCart = function(){
        $http.post('http://localhost:8190/market-cart/api/v1/cart/clear')
            .then(function(){
                $scope.loadCart();
            });
    }

    $scope.makeOrder = function() {
        alert('make order');
    };

    $scope.loadProducts();
    $scope.loadCart();
});