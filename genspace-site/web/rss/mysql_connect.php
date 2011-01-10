<?php

DEFINE ('DB_USER', 'student');
DEFINE ('DB_PASSWORD', 'password');
DEFINE ('DB_HOST', 'boris.cs.columbia.edu');
DEFINE ('DB_NAME', 'Genspace');

// Make the connnection and then select the database.
$dbc = @mysql_connect (DB_HOST, DB_USER, DB_PASSWORD) OR die ('Could not connect to MySQL: ' . mysql_error() );
mysql_select_db (DB_NAME) OR die ('Could not select the database: ' . mysql_error() );

?>