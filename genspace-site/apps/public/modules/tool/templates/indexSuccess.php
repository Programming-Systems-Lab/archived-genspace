<div id="content">	
	<div id="post">

<?php 

if($_POST['searchfield']):
?>
<h2>Search Results</h2>
      
	<?php 
		$toolname=ToolRatingsPeer::getSearchResultsTool($_POST['searchfield']);
		$toolid=ToolRatingsPeer::getSearchResultsId($_POST['searchfield']);
		if (sizeof($toolname)==0): echo "No tools matched your query"; ?> <?php else: ?>
		 <p>Click on a tool for more information</p>
		<ul style="list-style: none; margin: 0px;" id="tools">
			      <form>
    Sort: <select><option value="Name - A-Z" onclick="$('ul#tools li').tsort({order:'asc'});">Name - A-Z</option><option value="Name - Z-A" onclick="$('ul#tools li').tsort({attr:'id',order:'desc'});">Name - Z-A</option><option value="Highest Rating" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'value', order:'desc'});">Highest Rating</option><option value="Lowest Rating" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'value', order:'asc'});">Lowest Rating</option><option value="Most Comments" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'id', order:'desc'});">Most Comments</option><option value="Least Comments" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'id', order:'asc'});">Least Comments</option></select>
    </form>
   <?php
		for($a=0;$a<sizeof($toolname);$a++){
	 ?>
        <?php 	 $avg=ToolRatingsPeer::getAvgRating($sf_user->getAttribute('username'), $toolid[$a]);
		$totalrat=ToolRatingsPeer::getCountRating($toolid[$a]);
	 ?>
     
		<li class="rated" style="padding:10px" id=<?php echo $toolname[$a]; ?> >
        <?php 
		 if (round($avg)>0){
		 echo "<span class='rated' value=".round($avg)." id=".$totalrat.">"; 
		  }
		    else{
		   echo "<span class='notrated'>"; 
			}
	 ?>
		<h3 style="border: none" ><?php 
		echo link_to($toolname[$a], "tool/index?id=" . $toolid[$a]);
		  ?></h3>
    
		<form id="avg<?php echo $toolnum;?>" style="width: 200px">
				<?php for($i=1; $i<6; $i++){ if (round($avg)==$i){?>
											<input type="radio" name="rate_avg" value="<?php echo $i;?>"   disabled="disabled" checked="checked" />
                                            <?php } else {?>
	<input type="radio" name="rate_avg" value="<?php echo $i;?>"  disabled="disabled"  />
    <?php } } echo "<span class='notrated' style='color:#cccccc'>&nbsp;&nbsp;&nbsp; ".$totalrat." comments</span>";?> 
					
				</form></span>
	</li>
		<?php $toolnum++; } endif; ?>
		</ul>
<?php else:
if (isset($displayAll)): 
//ToolRatingsPeer::deleteTool(3);
//ToolRatingsPeer::deleteTool(6);
//
//ToolRatingsPeer::deleteTool(8);

	 ?>
		<h2>geWorkbench Tools <a href="/feed"><img src="/images/rss_logo.jpg" width="34" height="36" border="0" /></a></h2>
        <div id='forgotpassdialog' style='display:none'>Enter your email address below, and your password will be reset and sent to you:<br/><br/>
<form action="/tool/forgotpass" method="post">Email:<input name="email" type="text" size="40" /><br/><input type='submit' value='Submit' style='float:right' /></form>
</div>	        
		<p>Click on a tool for more information</p>
		<ul style="list-style: none; margin: 0px;" id="tools">
			      <form>
    Sort: <select><option value="Name - A-Z" onclick="$('ul#tools li').tsort({order:'asc'});">Name - A-Z</option><option value="Name - Z-A" onclick="$('ul#tools li').tsort({attr:'id',order:'desc'});">Name - Z-A</option><option value="Highest Rating" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'value', order:'desc'});">Highest Rating</option><option value="Lowest Rating" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'value', order:'asc'});">Lowest Rating</option><option value="Most Comments" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'id', order:'desc'});">Most Comments</option><option value="Least Comments" onclick="$('ul#tools li').tsort('span[class!=notrated]',{attr:'id', order:'asc'});">Least Comments</option></select>
    </form>
   
	
		<?php $i = 0;$toolnum=1;
		foreach ($tools as $tool){ ?>
        <?php 	 $avg=ToolRatingsPeer::getAvgRating($sf_user->getAttribute('username'), $tool->getId());
		$total=ToolRatingsPeer::getCountRating($tool->getId());
	 ?>
     
		<li class="rated" style="padding:10px" id=<?php echo $tool->getTool(); ?> >
        <?php if (round($avg)>0){
		 echo "<span class='rated' value=".round($avg)." id=".$total.">"; 
		  }
		    else{
		   echo "<span class='notrated'>"; 
			}?>
	 
		<h3 style="border: none" ><?php 
		echo link_to($tool->getTool(), "tool/index?id=" . $tool->getId());
		  ?></h3>
    
		<form id="avg<?php echo $toolnum;?>" style="width: 200px">
				<?php for($i=1; $i<6; $i++){ if (round($avg)==$i){?>
											<input type="radio" name="rate_avg" value="<?php echo $i;?>"   disabled="disabled" checked="checked" />
                                            <?php } else {?>
	<input type="radio" name="rate_avg" value="<?php echo $i;?>"  disabled="disabled"  />
    <?php } } echo "<span class='notrated' style='color:#cccccc'>&nbsp;&nbsp;&nbsp; ".$total." comments</span>";?> 
					
				</form></span>
	</li>
		<?php $toolnum++; } ?>
		</ul>
        <?php  elseif (isset($displayUser)):?>
        	<h2><?php 
			echo "<div id='deletedialog' style='display:none'>The comment/rating will be permanently deleted.  Are you sure?</div>";
echo $uOb."'s Profile"; ?></h2>
        <?php  
	$total=ToolRatingsPeer::getUserCountRatings($sf_user->getAttribute('username'));
	$numratings=ToolRatingsPeer::getUserAllRatings($sf_user->getAttribute('username'));
	$one=0;
	$two=0;
	$three=0;
	$four=0;
	$five=0;
	for ($j=0; $j<$total; $j++){
		  if($numratings[$j]==1){
			$one++;
		 }
		 elseif ($numratings[$j]==2){
			$two++; 
		 }
		  elseif ($numratings[$j]==3){
			$three++; 
		 }
		  elseif ($numratings[$j]==4){
		
			$four++; 
		 }
		  elseif ($numratings[$j]==5){
			$five++; 
		 }
		 
	}
	
	?> 
					
				<div id="chartdiv" style="height:150px;width:175px; "></div>
<script type="text/javascript">
line1 = [[<?php echo (int)$one;?>,1],[<?php echo $two;?>,2],[<?php echo $three;?>,3],[<?php echo $four;?>,4],[<?php echo $five;?>,5]];
plot1 = $.jqplot('chartdiv', [line1], {
    legend:{show:false, location:'ne'},
	axesDefaults:{
		tickOptions:{
            formatString:'%.0f'
          }
	},
    title:'Rating Distribution',
	 seriesDefaults:{
        renderer:$.jqplot.BarRenderer, 
        rendererOptions:{barDirection:'horizontal', barPadding: 1, barMargin:15}, 
        shadowAngle:135},
	grid:{
			drawGridLines:false,
			gridLineColor: '#ffffff' 
		},
    series:[
        {label:'Rating'},
		 {pointLabels:{
            labels:[<?php echo $one;?>,<?php echo $two;?>, <?php echo $three;?>, <?php echo $four;?>, <?php echo $five;?>]
        }}
    ],
    axes:{
        xaxis:{min:0}, 
        yaxis:{renderer:$.jqplot.CategoryAxisRenderer, ticks:['1 star', '2 stars', '3 stars', '4 stars', '5 stars']
        }
    }
});
</script>
	<?php $i = 0; 
	
	$total=ToolRatingsPeer::getUserCountRatings($sf_user->getAttribute('username'));
	$toolid=ToolRatingsPeer::getUserTools($sf_user->getAttribute('username'));
	echo  "<h2>comments</h2>";
	echo "<div id='userrating'>";
  	for($a=0;$a<$total;$a++){
	?>	
	
   <form>	
	<br/><br/>
			<?php  
			$userrating=ToolRatingsPeer::getUserRatings2($sf_user->getAttribute('username'), $toolid[$a]); 
			$usercomments=ToolRatingsPeer::getUserComments($sf_user->getAttribute('username'), $toolid[$a]);
					echo "<h3>".$usercomments['tool']; echo "</h3><br/>";
	
			for($i=1; $i<6; $i++){ if ($userrating==$i){?>
									<input type="radio" name="rate_avg" value="<?php echo $i;?>"   disabled="disabled" checked="checked" />
                                            <?php } else {?>
	<input type="radio" name="rate_avg" value="<?php echo $i;?>"  disabled="disabled"   />
    <?php } }


	   /*
	    * <a class="edit" href="/tool/edit"><img style="float:right" src="/images/edit_butt.png" /></a>
	    */?>
						
		
		</form><p class="entry" style="border-bottom: 1px solid black; padding: 5px; <?php if (++$i % 2==0) echo "background-color: #CCCCCC;"; ?>"> 
       <br/><br/><?php echo  $usercomments['comment']; ?></br><br/><span class="meta" style="padding: 3px; font-style: italic; margin:0px;"> <span class="posted">Posted on <?php echo date("F j, Y, g:i a", strtotime($usercomments['posted_on'])); ?> </span><a class="delete" href="/tool/delete?id=<?php echo $toolid[$a];?>&user=<?php echo $sf_user->getAttribute('username');?> "><img style="float:right" src="/images/delete_butt.png" /></a></span><?php echo "<div id='editdialog' style='display:none; overflow:hidden'><form action='/tool/edit' method='post'>";echo "<h3>".$usercomments['tool']; echo "</h3>	<label for='name' style='position:relative; top:15px'><strong>Rate this</strong>: </label><div id='rat' style='position: relative; left:59px ' >
<select name='comment[rating]'>"; for($i=1; $i<6; $i++){ if  ($userrating==$i){
											echo "<option value=".$i."  selected />";} else { echo"<option value=".$i."  />";
     } } echo "</select></div><br/><br/><br/><strong>Comment</strong>:<textarea  cols='40'  name='comment[comment]'>";echo $usercomments['comment']; echo "</textarea><input type='hidden' name='comment[id]' value=".$toolid[$a]."><input type='hidden'  name='comment[user]' value=".$sf_user->getAttribute('username')."><br/><input type='submit' value='Cancel' style='float:right' onClick='this.close()' /><input type='submit' value='Submit' style='float:right' />";?> </form></div></p>
	<?php  } echo "</div>"; ?>
<?php elseif (!$displayUser&&!$displayAll):
     
 ?>
        <script language="Javascript" type="text/javascript">
    //<![CDATA[
    google.load('search', '1');

    function OnLoad() {
      // Create a search control
      var searchControl = new google.search.SearchControl();
      var drawOptions = new google.search.DrawOptions();
     // Add in a full set of searchers
      searchControl.addSearcher(new google.search.WebSearch());
      searchControl.addSearcher(new google.search.ImageSearch());
	  // searchControl.addSearcher(new google.search.PatentSearch());
    
           // Set the Local Search center point
    
      // tell the searcher to draw itself and tell it where to attach
drawOptions.setDrawMode(google.search.SearchControl.DRAW_MODE_TABBED);
searchControl.draw(document.getElementById("searchcontrol"), drawOptions);
      // execute an inital search
      searchControl.execute("<?php echo $tool->getTool();?> \"geworkbench\"");
    }
    google.setOnLoadCallback(OnLoad);

    //]]>
    </script>
	<h3 style="letter-spacing:1em; color: #D2D2D2">geWorkbench tools</h3>
	<h2 class="title"><?php echo $tool->getTool(); ?></h2>
    <form id="avg" >
					<?php for($i=1; $i<6; $i++){ if (round($rating['overall'])==$i){?>
											<input type="radio" name="rate_avg" value="<?php echo $i;?>"   disabled="disabled" checked="checked" />
                                            <?php } else {?>
	<input type="radio" name="rate_avg" value="<?php echo $i;?>"  disabled="disabled"  />
    <?php } } echo "<span style='color:#cccccc'>&nbsp;&nbsp;&nbsp; ".sizeof($comments)." comments</span>";
	$total=ToolRatingsPeer::getCountRating($tool->getId());
	//ToolRatingsPeer::deleteRating("test", 46, $tool->getId() );
	//ToolRatingsPeer::deleteAllComments();
	//ToolRatingsPeer::deleteAllRatings();
	
	
	$numratings=ToolRatingsPeer::getRatings( $tool->getId());
	$one=0;
	$two=0;
	$three=0;
	$four=0;
	$five=0;
	for ($j=0; $j<$total; $j++){
		  if($numratings[$j]==1){
			$one++;
		 }
		 elseif ($numratings[$j]==2){
			$two++; 
		 }
		  elseif ($numratings[$j]==3){
			$three++; 
		 }
		  elseif ($numratings[$j]==4){
		
			$four++; 
		 }
		  elseif ($numratings[$j]==5){
			$five++; 
		 }
		 
	}
	
	?> 
					
			</form> <br/><div id="accordion"><h3 style="padding-left:20px">Description</h3><p><?php echo $tool->getDescription()?></p>
<h3 style="padding-left:20px">On the Web</h3> <div id="searchcontrol" >Loading</div></div>
	<div class="post">
    <div id='deletedialog' style='display:none'>The comment/rating will be permanently deleted.  Are you sure?</div>
 	<h2 class="title">comments</h2>
    <?php if (sizeof($numratings)!=0): ?>

      <div id="chartdiv" style="height:150px;width:175px; "></div><br/><br/>
<script type="text/javascript">
line1 = [[<?php echo $one;?>,1],[<?php echo $two;?>,2],[<?php echo $three;?>,3],[<?php echo $four;?>,4],[<?php echo $five;?>,5]];
plot1 = $.jqplot('chartdiv', [line1], {
    legend:{show:false, location:'ne'},
	axesDefaults:{
		tickOptions:{
            formatString:'%.0f'
          }
	},
    title:'Rating Distribution',
	 seriesDefaults:{
        renderer:$.jqplot.BarRenderer, 
        rendererOptions:{barDirection:'horizontal', barPadding: 1, barMargin:15}, 
        shadowAngle:135},
	grid:{
			drawGridLines:false,
			gridLineColor: '#ffffff' 
		},
    series:[
        {label:'Rating'},
		 {pointLabels:{
            labels:[<?php echo $one;?>,<?php echo $two;?>, <?php echo $three;?>, <?php echo $four;?>, <?php echo $five;?>]
        }}
    ],
    axes:{
        xaxis:{min:0}, 
        yaxis:{ renderer:$.jqplot.CategoryAxisRenderer, ticks:['1 star', '2 stars', '3 stars', '4 stars', '5 stars']
        }
    }
});
</script>
	<?php endif; if (sizeof($comments) == 0): ?> <center><i>No comments yet for this tool.</i></center> <?php endif;?>
	  	
        <form>
  	
    Sort: <select><option value="Highest Rating" onclick="$('ul#comments li').tsort({attr:'id', order:'desc'});">Highest Rating</option><option value="Lowest Rating" onclick="$('ul#comments li').tsort({attr:'id', order:'asc'});">Lowest Rating</option><option value="Newest" onclick="$('ul#comments li').tsort('span.posted',{attr:'id', order:'asc'});">Newest</option><option value="Oldest" onclick="$('ul#comments li').tsort('span.posted',{attr:'id', order:'desc'});">Oldest</option></select>
    </form><br/><br/>
        <ul style="list-style: none; margin: 0px;" id="comments">
	
	
		<?php $i = 0; 
	 
	foreach($comments as $comment):

	?>	       	<?php  $total=ToolRatingsPeer::getUserToolRating($comment->getCreatorId(), $tool->getId()); ?>
       
		     <li class="rated" style="padding:10px" id=<?php echo $total ?> >
  <div class="userrating">
    <form >  
     	<?php for($i=1; $i<6; $i++){ if ($total==$i){?>
        					<input type="radio" name="rate_avg" value="<?php echo $i;?>"   disabled="disabled" checked="checked" />
                                            <?php } else {?>
	<input type="radio" name="rate_avg" value="<?php echo $i;?>"  disabled="disabled"   />
    <?php } }?>
						
		
		</form></div><p class="entry" style="border-bottom: 1px solid black; padding: 5px; <?php if (++$i % 2==0) echo "background-color: #CCCCCC;"; ?>"> 
        <br/><br/><?php echo $comment->getComment(); ?></br><br/><span class="meta" style="padding: 3px; font-style: italic; margin:0px;"> <span class="posted" id="<?php echo date("Y/M/D", strtotime($comment->getCreatedAt())); ?>">Posted by <strong><?php echo trim(($comment->getRegistration() == null ? "Anonymous" : $comment->getRegistration()->getFullName()));?></strong> on <?php echo date("F j, Y, g:i a", strtotime($comment->getCreatedAt())); ?></span><?php if (trim($comment->getCreatorId())==$sf_user->getAttribute('username'))  echo "<a class='delete' href='/tool/delete?id=".$tool->getId()."&user=".$sf_user->getAttribute('username')."'>"."<img style='float:right' src='/images/delete_butt.png' /></a><a class='edit' href='/tool/edit'><img style='float:right' src='/images/edit_butt.png' /></a>"; ?><?php echo "<div id='editdialog' style='display:none; overflow:hidden'><form action='/tool/edit' method='post'>";echo "<h3>".$tool->getTool(); echo "</h3>	<label for='name' style='position:relative; top:15px'><strong>Rate this</strong>: </label><div id='rat' style='position: relative; left:59px ' >
<select name='comment[rating]'>"; for($i=1; $i<6; $i++){ if  ($total==$i){
											echo "<option value=".$i."  selected />";} else { echo"<option value=".$i."  />";
     } } echo "</select></div><br/><br/><br/><strong>Comment</strong>:<textarea  cols='40'  name='comment[comment]'>";echo $comment->getComment(); echo "</textarea><input type='hidden' name='comment[id]' value=".$tool->getId()."><input type='hidden'  name='comment[user]' value=".$sf_user->getAttribute('username')."><br/><input type='submit' value='Cancel' style='float:right' onClick='this.close()' /><input type='submit' value='Submit' style='float:right' />";?></form></span></p></li>
	<?php endforeach; ?>	</ul>
	</div>
	
	<br/><br/>
	
	<?php 
	$userRating=ToolRatingsPeer::checkUser($sf_user->getAttribute('username'), $tool->getId());
	 if ($sf_user->isAuthenticated()&&($userRating==0)): ?>
  
  	<div class="entry" style="padding: 10px; background-color: #f5f5f5; background-image:url(/images/bottom_fade.png); background-repeat:repeat-x; background-position:bottom; border:1px solid #cccccc">
		<strong><span style="font-size:14px">Submit a Comment:</span></strong><br/><br/>
        <form action="/index.php/tool/comment" method="post">
       	<div class="ctrlHolder"><label for="name" style="position:relative; top:15px"><strong>Rate this</strong>:</label> <span id="caption"></span>

			<div id="rat" style="position: relative; left:57px " >
<select name="comment[rating]" >
													<option  value="1">Poor</option>
													<option  value="2">Fair</option>
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
           <input name="image" type="image" style="border:0px;" src="/images/submit_butt.png" onclick="/tool/comment">
     </form>
	</div>
	<?php endif;?>

<?php endif; ?>
<?php endif; ?>



		</div>

</div>
<div id="sidebar">
<ul>
<?php include_partial('login'); ?>

</ul>

</div>
