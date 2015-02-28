'use strict';

var porterAppCtrls = angular.module('porterAppCtrls', []);

/* Controllers */
porterAppCtrls.controller('MainCtrl',function($scope, $location, $http) {
    $scope.tabs = [
        {
            'name': 'Home',
            'url': '/user/home'
        },
        {
            'name': 'Rules',
            'url': '/rules'
        }
    ];
    $scope.activeTab=0;
    $location.path('/user/home');

    $scope.setActive = function(index) {
        $scope.activeTab=index;
        $location.path($scope.tabs[index].url);
    }
});

porterAppCtrls.controller('porterUserCtrl',function($scope, $location, $http) {

});

