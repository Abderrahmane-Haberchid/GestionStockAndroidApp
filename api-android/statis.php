<?php
    $con = new mysqli("localhost", "root", "", "gestionstockandroid");

    $query1 = $con->query("SELECT * FROM article WHERE etat = 'disponible'");
    $query2 = $con->query("SELECT * FROM article WHERE etat = 'En rupture'");
    $query3 = $con->query("SELECT SUM(qte) AS total_qte FROM article");

    $response = array();

    $res1 = mysqli_num_rows($query1);
    $res2 = mysqli_num_rows($query2);

    while ($a =  mysqli_fetch_assoc($query3)) {
        $res3 = $a['total_qte'];
    }

    echo '[{"dispo":"'.$res1.'","indispo":"'.$res2.'","qte":"'.$res3.'"}]';
     


?>