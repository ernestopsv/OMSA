app.controller("rutaTableControllers", function ($http, $scope) {
    $scope.rutas = [];
    $scope.pageno = 1;
    $scope.total_count = 0;
    $scope.itemsPerPage= 10;

    $scope.getData = function (pageno) {
        $scope.rutas=[];
        $http.get("/api/ruta/buscar/pagina/"+(pageno-1)+"/item/"+$scope.itemsPerPage).then(
            function (response) {
                $scope.rutas = response.data;
                console.log($scope.rutas)
            }, function (response) {
                $scope.rutas=[]
            })
    };
});