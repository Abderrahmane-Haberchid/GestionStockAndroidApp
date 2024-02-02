<?php

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    $query = $con->query("SELECT * FROM user");

    $res = array();

    while ($row = mysqli_fetch_assoc($query)) {
            array_push($res, $row);
    }
    
    $json = json_encode($res);

    echo $json;

?>