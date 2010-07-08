<li style="padding: 5px; border: 1px solid #CCCCCC;">
	<h3>Rating</h3>
<?php if (isset($rating)): ?>
	<?php 	//show rating box with clickablitity
		if ($sf_user->isAuthenticated() && $rating["user"] == 0): ?>
		<form method="post" action="/genspace/tool">		
			<div style="font-size: 10px; font-weight: bold; text-transform: uppercase; ">Rate this tool:</div>
					
				<input type="hidden" name="id" value="<?=$toolId;?>">
				
				<script>
					new Starry('stars', {width: 18, height: 18, name:'rating',  maxLength:5, startAt: <? echo round($rating["overall"]); ?> });
				</script>
				<input type="submit" value="submit">
				
			
			</div></form>
		<?php 
		//display rating, with no clickability
		else: ?>
		<? for ($i = 0; $i < round($rating["overall"]); $i++){
				echo image_tag('star.png');
			}
			echo "<br>";
		?>
			
		<i><? if ($rating["total"]) 	echo "Average is " . round($rating["overall"]) . "/5.   Rated " . $rating["total"] . " times.";
			  else echo "This tool has not yet been rated."; ?>
		</i>
		
		<? endif; ?>
	<? else: echo "NO RATING"; ?>
<? endif;?>
</li>