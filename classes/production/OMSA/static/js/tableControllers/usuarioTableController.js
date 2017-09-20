app.controller("usuarioTableController", function ($http, $scope) {
    $scope.usuarios = [];
    $scope.pageno = 1;
    $scope.total_count = 0;
    $scope.itemsPerPage= 10;

    $scope.getData = function (pageno) {
        $scope.usuarios=[];
        $http.get("/api/usuario/buscar/"+(pageno-1)+"/item/"+$scope.itemsPerPage).then(
            function (response) {

                $scope.usuarios = response.data;

                $scope.total_count= response.count;
            }, function (response) {
                $scope.usuarios=response.data
            })
    };

});