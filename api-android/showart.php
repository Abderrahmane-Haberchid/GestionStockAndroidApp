<?php
        $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');


        $q = $con->query("SELECT * FROM article ORDER BY id DESC");

        $response = array();

        while($row = mysqli_fetch_assoc($q)){
            array_push($response, $row);
        }
        $res = json_encode($response);

        echo $res;
?>