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
  $searchfield=$_POST["searchfield"];

  $stmt = $dbh->prepare("SELECT tool FROM tools WHERE tool LIKE '%".$searchfield."%'");
  $stmt->execute();
  $i=0;
					while($resultSet=$stmt->fetch(PDO::FETCH_OBJ)){
						$tools[$i] = $resultSet->tool;
	                   $i++;
						}
			
						$a=$tools;
		//				echo "right here";

//lookup all hints from array if length of q>0
$this->redirect('tool','index?search=true');
?>
