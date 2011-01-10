<?php

require 'lib/model/om/BaseRegistrationPeer.php';


/**
 * Skeleton subclass for performing query and update operations on the 'registration' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    lib.model
 */
class RegistrationPeer extends BaseRegistrationPeer {
public static function resetPassword($email){
	$connection = Propel::getConnection();
			
		$chars = "abcdefghijkmnopqrstuvwxyz023456789";

    srand((double)microtime()*1000000);

    $i = 0;

    $pass = '' ;



    while ($i <= 7) {

        $num = rand() % 33;

        $tmp = substr($chars, $num, 1);

        $pass = $pass . $tmp;

        $i++;

    }



    return $pass;

		$statement = $connection->prepare("Update registration set password=".sha1($pass)." where email=:email");
		$statement->bindValue(':email', $email, PDO::PARAM_STR);
             
			$statement->execute();
		
							

			return $pass;
	}
} // RegistrationPeer
