app.controller('empresaController',function($scope,$http){
	
	$scope.empresa = {};
	$http.get('http://localhost:8090/empresa').then(function(response){
		
		console.log(response.data)
		$scope.empresa = response.data;
		
	}, function(response){
		console.log(response);
		
	});
	
});

