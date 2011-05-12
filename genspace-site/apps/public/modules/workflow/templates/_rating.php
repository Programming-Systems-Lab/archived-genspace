<li style="padding: 5px; border: 1px solid #CCCCCC;">
	<h3>Rating</h3>
	<?php
	?>
<?php if (isset($rating)): ?>
	<?php 	//show rating box with clickablitity
		if ($sf_user->isAuthenticated() && $rating["user"] == 0): ?>
		<form method="post" action="/index.php/workflow/index">
			<div style="font-size: 10px; font-weight: bold; text-transform: uppercase; ">Rate this workflow:</div>
					
				<input type="hidden" name="id" value="<?php echo $workflowId;?>">
				
			<div class="rating-R"><strong>Rate this:</strong> <span id="caption"></span>

			<div id="rat">
<select name="rating" >
													<option  value="1">Not so great</option>
													<option  value="2">Quite good</option>
													<option selected="selected" value="3">Good</option>
													<option  value="4">Great!</option>
													<option  value="5">Excellent!</option>

											</select>
                                            </div>
                                            </div>
<br />
				<input type="submit" value="submit">
				
			
			</div></form>
		<?php
		//display rating, with no clickability
		else: ?>
		<?php for ($i = 0; $i < round($rating["overall"]); $i++){
				echo image_tag('star.png');
			}
			echo "<br>";
		?>
			
		<i><?php if ($rating["total"]) 	echo "Average is " . round($rating["overall"]) . "/5.   Rated " . $rating["total"] . " times.";
			  else echo "This workflow has not yet been rated."; ?>
		</i>
		
		<?php endif; ?>
	<?php else: echo "NO RATING"; ?>
<?php endif;?>
</li>