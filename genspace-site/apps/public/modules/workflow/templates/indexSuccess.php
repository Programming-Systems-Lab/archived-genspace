<? if (!$workflowId) exit(0); ?>

<div id="content">	
	<div id="post">

	<h4 style="letter-spacing:1em; color: #D2D2D2">geWorkbench workflows</h3>
	<h2 class="title"><? echo $wfName; ?></h2>
	<div class="post">
	<h3 class="title">comments</h2>
	<? if (sizeof($comments) == 0): ?> <center><i>No yet comments for this workflow.</i></center> <? endif;?>
		
	<? $i = 0; 
	foreach($comments as $comment):
	?>
		<div class="entry" style="border-bottom: 1px solid black; padding: 5px; <? if (++$i % 2==0) echo "background-color: #CCCCCC;"; ?>">
			<p><? echo $comment->getComment(); ?></p>
			<p class="meta" style="padding: 3px; font-style: italic; margin:0px;"> <span class="posted">Posted by <strong><? echo $comment->getUsername(); ?></strong> on <? echo date("F j, Y, g:i a", strtotime($comment->getPostedOn())); ?> </span></p>
		</div>
		
	<? endforeach; ?>
	</div>
	
	<br><br>
	
	<? if ($sf_user->isAuthenticated()): ?>
	<div class="entry" style="padding: 10px; background-color: #D2D2D2">
		<strong>Submit a comment:</strong></br>
		<? echo use_helper('Form');
		echo form_tag('workflow/index');
		echo textarea_tag('comment', '','style="width: 80%; border: 1px solid black"'); echo "<br><br>";
		echo input_hidden_tag('id', $workflowId);
		echo submit_tag('submit');
		
		?>
	</div>
	<? endif; ?>

	</div>
</div>

<div id="sidebar"><ul>
	
	<? include_partial('login'); ?>
	<? if (isset($workflowId))
		include_partial('rating', array('rating' => $rating, 'workflowId' => $workflowId)); 
	?>
	
</ul></div>