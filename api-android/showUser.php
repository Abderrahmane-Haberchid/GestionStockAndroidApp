<?php

    $mail = $_GET['mail'];

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');    
    $result = array();

    $query = $con->query("SELECT * FROM user WHERE email = '$mail'");

        while ($row = mysqli_fetch_assoc($query)) {
                array_push($result, $row);
        }

    $response = json_encode($result);

    echo $response;

    

?>