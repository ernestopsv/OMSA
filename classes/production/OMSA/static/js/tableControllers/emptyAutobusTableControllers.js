app.controller("emptyAutobusTableController", function ($http, $scope) {
    $scope.autobuses = [];
    $scope.pageno = 1;
    $scope.total_count = 0;
    $scope.itemsPerPage= 10;

    $scope.isTrue = function (val){
        return val===true
    }

    $scope.isNull = function (val){
        return val===null
    }
    $scope.getData = function (pageno) {
        $scope.autobuses=[];

        $http.get("/api/autobus/buscar/size/").then(function (response) {
            $scope.total_count=response.data
        });

        $http.get("/api/autobus/sinRuta/buscar/"+(pageno-1)+"/"+$scope.itemsPerPage).then(
            function (response) {
                $scope.autobuses = response.data;


            }, function (response) {
                $scope.autobuses=response.data
            })
    };

});