<?php
try {
    $hostname = "127.0.0.1";            //host
    $dbname = "genspace";            //db name
    $username = "genspace";            // username like 'sa'
    $pw = "g3nsp4c3";                // password for the user

    $dbh = new PDO ("mysql:host=$hostname;dbname=$dbname","$username","$pw");
  } catch (PDOException $e) {
    echo "Failed to get DB handle: " . $e->getMessage() . "\n";
    exit;
  }
  $q=$_GET['q'];
  $stmt = $dbh->prepare("SELECT name as tool FROM tools");
  $stmt->execute();
  $i=0;
					while($resultSet=$stmt->fetch(PDO::FETCH_OBJ)){
						$tools[$i] = $resultSet->tool;
	                   $i++;
						}
			
						$a=$tools;
		//				echo "right here";

//lookup all hints from array if length of q>0
if (strlen($q) > 0)
  {
  for($i=0; $i<count($a); $i++)
    {
    if (strtolower($q)==strtolower(substr($a[$i],0,strlen($q))))
        echo $a[$i]."\n";
    }
  }

?>
