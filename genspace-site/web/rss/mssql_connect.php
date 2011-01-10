<?php
 try {
    $hostname = "boris.cs.columbia.edu";            //host
    $dbname = "Genspace";            //db name
    $username = "student";            // username like 'sa'
    $pw = "password";                // password for the user

    $dbh = new PDO ("mssql:dbname=$dbname;host=$hostname;","$username","$pw");
$dbh->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_WARNING ); 
  } catch (PDOException $e) {
    echo "Failed to get DB handle: " . $e->getMessage() . "\n";
    exit;
  }
?>