<?php
    $con = new mysqli("localhost", "root", "", "gestionstockandroid");

    $id = $_GET['id'];

    $query = $con->query("DELETE FROM user WHERE id = '$id'");

    if ($query) {
        echo "success";
    }
    else echo "failure";
?>