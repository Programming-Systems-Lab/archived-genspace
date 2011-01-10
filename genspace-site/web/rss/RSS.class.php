<?php
class RSS
{
	public function RSS()
	{
		require_once ('mssql_connect.php');
	}
	
	public function GetFeed()
	{
		return '<?xml version="1.0" encoding="utf-8" ?>
					<rss version="2.0">
						<channel>
							<title>genSpace Tool Comments RSS Feed</title>
							<link>http://bambi.cs.columbia.edu</link>
							<description>A feed of recent comments on geWorkbench tools</description>
							'.$this->getItems();
	}
	
		
		private function getItems()
	{
		$stmt=$dbh->prepare("SELECT tools.tool AS tool, tool_comments.id AS id, tool_comments.comment AS comment, tool_comments.posted_on AS posted_on FROM tool_comments, tools where tool_comments.id=*tools.id");
		$stmt->execute();
   	while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
						$items .= '<item>
						 <title>'. $row['tool'].'</title>
						 <link>http://localhost:8080/tool/index/'. $row['id '].'</link>
						 <description><'. $row['comment'] .'</description>
						 <pubDate>'.$row['posted_on'].'</pubDate>
					 </item>';
		}
	$items .= '</channel>
				 </rss>';
		return $items;
	}
}
?>