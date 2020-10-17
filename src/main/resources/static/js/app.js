//Crianção modulo principal aplicação
var app = angular.module("app",['ngRoute']);

app.config(function($routeProvider,$locationProvider){
	$routeProvider
	.when('/empresa', {
		templateUrl:'views/empresa.html',
		controller:'empresaController'
	})
	
	$locationProvider.html5Mode({
	    enabled: true,
	    requireBase: false
	});
});
