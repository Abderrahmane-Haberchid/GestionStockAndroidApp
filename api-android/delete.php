<?php
    $id = $_POST['id'];

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    $query = "DELETE FROM article WHERE id = '$id'";

    if($con->query($query) === TRUE){
        echo 'Success';
    }
    else{
         echo 'Failed';
    } 
    $con->close();
?>