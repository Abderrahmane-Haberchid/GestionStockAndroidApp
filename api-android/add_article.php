<?php
    $title = $_POST['title'];
    $cat = $_POST['cat'];
    $des = $_POST['des'];
    $qte = $_POST['qte'];
    $etat ="Disponible";
    $date = date('d/m/y h:i');

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    if(!empty($title) && !empty($cat) && !empty($des)){

        $q = $con->query("INSERT INTO article VALUES('', '$title', '$cat', '$des', '$etat', '$qte', '$date')");
        
        if($q){
            echo 'success';
        }
        else {
            echo 'failure';
        }

    }

    ?>