<?php
    $name = $_POST['name'];
    $email = $_POST['mail'];
    $mdp = $_POST['mdp'];

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    if(!empty($name) && !empty($email) && !empty($mdp)){

        $q = $con->query("INSERT INTO user VALUES('', '$name', '$email', '$mdp', 'user')");
        
        if($q){
            echo 'success';
        }
        else {
            echo 'failure';
        }

    }
?>