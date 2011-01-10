<?php
try {
    $hostname = "boris.cs.columbia.edu";            //host
    $dbname = "genspace";            //db name
    $username = "student";            // username like 'sa'
    $pw = "password";                // password for the user

    $dbh = new PDO ("mssql:host=$hostname;dbname=$dbname","$username","$pw");
  } catch (PDOException $e) {
    echo "Failed to get DB handle: " . $e->getMessage() . "\n";
    exit;
  }
  $q=$_GET['q'];
  $stmt = $dbh->prepare("SELECT tool FROM tools");
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
