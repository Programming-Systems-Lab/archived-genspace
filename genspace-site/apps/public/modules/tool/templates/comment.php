
	
	<?php if ($sf_user->isAuthenticated()): ?>
	<div class="entry" style="padding: 10px; background-color: #ffffff">
		<strong><span style="font-size:14px">Submit a Comment:</span></strong><br/><br/>
        <form action="http://localhost:8080/tool/comment" method="post">
       	<div class="ctrlHolder"><label for="name" style="position:relative; top:15px"><strong>Rate this</strong>:</label> <span id="caption"></span>

			<div id="rat" style="position: relative; left:57px " >
<select name="rate" >
													<option  value="1">Not so great</option>
													<option  value="2">Quite good</option>
													<option  value="3">Good</option>
													<option  value="4">Great!</option>
													<option  value="5">Excellent!</option>

											</select>
                                            </div>
                                            </div><br/>
		        <br/>
         <label for="comment"><strong>Comment</strong>:</label>
         <textarea  cols="40"  name="comment[comment]" ></textarea>
		
        <input type="hidden" name="comment[id]" value="<?php echo $toolId ?>"/><br/>
           <input type="submit" value="Submit" />
     </form>
	</div>


<?php endif; ?>

	</div>
</div>

