'use strict';

/* Controllers */

var porterApp = angular.module('porterApp', [
    'ui.bootstrap',
    'porterAppCtrls',
    'porterAppServices',
    'ngRoute',
    'angularCharts'
]);

efifaApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/user/home', {
                templateUrl: 'partials/user-home.html',
                controller: 'porterUserCtrl'
            }).
            otherwise({
                redirectTo: '/user/home'
            });
    }]);

