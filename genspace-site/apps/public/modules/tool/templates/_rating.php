<li style="padding: 5px; border: 1px solid #CCCCCC;">
	<h3>Rating</h3>
<?php if (isset($rating)): ?>
	<?php 	//show rating box with clickablitity
		if ($sf_user->isAuthenticated() && $rating["user"] == 0):  ?>
       
		<form class="uniForm" method="post" action="/genspace/tool">	
        	                <fieldset class="inlineLabels">
	<input type="hidden" name="id" value="<?=$toolId;?>">
			
	
			<div class="rating-R"><strong>Rate this:</strong> <span id="caption"></span>

			<div id="rat">
<select name="rate" >
													<option  value="1">Not so great</option>
													<option  value="2">Quite good</option>
													<option selected="selected" value="3">Good</option>
													<option  value="4">Great!</option>
													<option  value="5">Excellent!</option>

											</select>
                                            </div>
                                            </div>
                </fieldset>
</form>
<form id="rat">

</form>

		<?php 
		//display rating, with no clickability
		else: ?>
		<?php //for ($i = 0; $i < round($rating["overall"]); $i++){
				echo "This is the rating: ". $rating["overall"];
			//}
			echo "<br>";
		?>
			
		<i><?php if ($rating["overall"]>0) 	echo "Average is " . round($rating["overall"]) . "/5.   Rated " . $rating["total"] . " times.";
			  else echo "This tool has not yet been rated."; ?>
		</i>
		
		<?php endif; ?>
	<?php else: echo "NO RATING"; ?>
<?php endif;?>
</li>