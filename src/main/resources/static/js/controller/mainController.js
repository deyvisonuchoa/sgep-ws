app.controller('mainController',function($scope,$location,$route,$routeParams){
	
	$scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
	
});