<?php if (!$workflowId)
{
	print "Unable to find requested workflow.";
	exit(0);
} ?>
 <div id='forgotpassdialog' style='display:none'>Enter your email address below, and your password will be reset and sent to you:<br/><br/>
<form action="/tool/forgotpass" method="post">Email:<input name="email" type="text" size="40" /><br/><input type='submit' value='Submit' style='float:right' /></form>
</div>	        
<div id="content">	
	<div id="post">

	<h4 style="letter-spacing:1em; color: #D2D2D2">geWorkbench workflows</h4>
	<h2 class="title">View Workflow:</h2>
	<div class="post">
		Steps: <?php echo $wfName ; ?>
	<h3 class="title">comments</h3>
	<?php if (sizeof($comments) == 0): ?> <center><i>There are no comments for this workflow.</i></center> <?php endif;?>
		
	<?php $i = 0;
	foreach($comments as $comment):
	?>

		<div class="entry" style="border-bottom: 1px solid black; padding: 5px; <?php if (++$i % 2==0) echo "background-color: #CCCCCC;"; ?>">
			<p><?php echo $comment->getComment(); ?></p>
			<p class="meta" style="padding: 3px; font-style: italic; margin:0px;"> <span class="posted">Posted by <strong><?php echo $comment->getCreator(); ?></strong> on <?php echo date("F j, Y, g:i a", strtotime($comment->getCreatedAt())); ?> </span></p>
		</div>
		
	<?php endforeach; ?>
	</div>
	
	<br><br>
	
	<?php if ($sf_user->isAuthenticated()): ?>
	<div class="entry" style="padding: 10px; background-color: #D2D2D2">
		<strong>Submit a comment:</strong></br>
		<form action="/index.php/workflow/index" method="post">
			<input type="hidden" name="id" value="<?php echo $workflowId;?>" />
			<textarea name="comment" style="width: 80%; border: 1px solid black"></textarea><br><br>
			<input type="submit" />
			</form>
	</div>
	<?php endif; ?>

	</div>
</div>

<div id="sidebar"><ul>
	
	<?php include_partial('login'); ?>
	<?php include_partial('rating',array('rating' => $rating, 'workflowId' => $workflowId, 'wf' => $wf)); ?>
	
</ul></div>