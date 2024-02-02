<?php

    $id = $_POST['idprod'];

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    $q = $con->query("SELECT * FROM article WHERE id = '$id'");

    $result = array();

    while($row = mysqli_fetch_assoc($q)){

        array_push($result, $row);
    }

        $res =  json_encode($result);
        echo $res;

?>