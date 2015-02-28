'use strict';

var porterAppServices = angular.module('porterAppServices', []);

porterAppServices.factory("UserService", ['$http',
    function($http, $q) {
        var promise;
        var myService = {
            getUser: function() {
                if ( !promise ) {
                    promise = $http.get("user/home").then(function(response) {
                        return response.data;
                    });
                }
                return promise;
            }
        };
        return myService;
    }
]);
