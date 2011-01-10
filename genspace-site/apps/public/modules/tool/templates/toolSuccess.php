<div id="content">	
	<div id="post">

<?php 


if (isset($displayAll)): ?>
		<h2>geWorkbench Tools</h2>
		<p>Click on a tool for more information</p>
		<ul style="list-style: none; margin: 0px;">
		<?php $i = 0;
		foreach ($tools as $tool): ?>
		<li style="padding: 10px;<?php if (++$i % 2 == 0) echo "background-color: #D2D2D2"; ?> ">
		
		<h3 style="border: none"><?php echo link_to($tool->getTool(), "tool/tool?id=" . $tool->getId()); ?></h3>
		<?php //echo $tool->getDescription(); ?>
		
	</li>
		<?php endforeach; ?>
		</ul>
<?php else: ?>
	<h4 style="letter-spacing:1em; color: #D2D2D2">geWorkbench tools</h3>
	<h2 class="title"><?php echo $tool->getTool(); ?></h2>
	<div class="post">
	<h3 class="title">comments</h2>
	<?php if (sizeof($comments) == 0): ?> <center><i>No yet comments for this tool.</i></center> <?php endif;?>
		
	<?php $i = 0; 
	foreach($comments as $comment):
	?>
		<div class="entry" style="border-bottom: 1px solid black; padding: 5px; <?php if (++$i % 2==0) echo "background-color: #CCCCCC;"; ?>">
			<p><?php echo $comment->getComment(); ?></p>
			<p class="meta" style="padding: 3px; font-style: italic; margin:0px;"> <span class="posted">Posted by <strong><?php echo $comment->getUsername(); ?></strong> on <?php echo date("F j, Y, g:i a", strtotime($comment->getPostedOn())); ?> </span></p>
		</div>
		
	<?php endforeach; ?>
	</div>
	
	<br><br>
	
	<?php if ($sf_user->isAuthenticated()): ?>
	<div class="entry" style="padding: 10px; background-color: #D2D2D2">
		<strong>Submit a comment:</strong><br/>
        <form action="tool/comment" method="post">
		<?php  echo $form; 		
		?>
        <br/>
        <input type="hidden" value="<?php echo $toolId ?>"/>
           <input type="submit" />
     </form>
	</div>
	<?php endif;?>

<?php endif; ?>

	</div>
</div>

<div id="sidebar"><ul>
	
	<?php include_partial('login'); ?>
	<?php if (!isset($displayAll))
		include_partial('rating', array('rating' => $rating, 'toolId' => $toolId)); 
	?>
	
</ul></div>