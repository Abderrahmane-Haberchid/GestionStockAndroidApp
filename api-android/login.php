<?php
    $email = $_POST['mail'];
    $mdp = $_POST['mdp'];

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    if(!empty($email) && !empty($mdp)){

        $q = $con->query("SELECT * FROM user WHERE email = '$email' AND mdp = '$mdp'");
        
        if(mysqli_num_rows($q) > 0){
            echo 'success';
        }
        else {
            echo 'failure';
        }

    }
?>