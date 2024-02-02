<?php
    $id = $_POST['id'];
    $title = $_POST['title'];
    $cat = $_POST['cat'];
    $desc = $_POST['desc'];
    $qte = $_POST['qte'];
    $etat = $_POST['etat'];
    $date = date('d/m/y h:i');

    if ($qte == 0) {
        $etat = "En Rupture";
    }

    $con = new mysqli('localhost', 'root', '', 'gestionstockandroid');

    $q = "UPDATE article SET title = '$title', categorie = '$cat', description = '$desc', etat = '$etat', qte = '$qte', heure = '$date' WHERE id = '$id'";

    if($con->query($q) === TRUE){
        echo 'success';
    }
    else {
        echo 'fail';
    }    
    $con->close();
?>