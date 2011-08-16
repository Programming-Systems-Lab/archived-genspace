<?php
$dbh = new PDO("mysql:host=127.0.0.1;dbname=genspace","genspace","g3nsp4c3");

$stmt = $dbh->query("select INET_NTOA(t.ipaddr) from `transaction` t
left join ip2hostname ih on ih.ip=t.ipaddr
where t.ipaddr is not null and ih.hostname is null
group by t.ipaddr");
while($row = $stmt->fetch())
{
	$ip = $row[0];
	$host = gethostbyaddr($ip);
	if($host != $ip)
	{
		print $ip."->$host\n";
		$dbh->exec("INSERT INTO ip2hostname set ip=INET_ATON(\"".$ip."\"), hostname=\"$host\";");
	}
}

//$stmt = $dbh->query("select hostname from transaction group by hostname");
//while($row = $stmt->fetch())
//{
//	$ip = gethostbyname($row[0]);
//	$host = $row[0];
//	if($ip != $host)
//	{
//		$dbh->exec("INSERT INTO ip2hostname set ip=INET_ATON(\"".$ip."\"), hostname=\"$host\";");
//	}
//}
?>