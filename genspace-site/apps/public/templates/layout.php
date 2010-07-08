<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<?php include_http_metas() ?>
<?php include_metas() ?>

<?php include_title() ?>


<link href="/css/main.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header">
			<div id="logo">
				<h1>genSpace</h1>
			</div>
			<div id="menu">
				<ul>
					<li><?php echo link_to("About", "static/about_us"); ?></li>
					<li><?php echo link_to("Tools", "tool/index"); ?></li>
					<li><?php echo link_to("Support", "static/support"); ?></li>
				</ul>
			</div>
		</div>
		<!-- end #header -->
		<div id="page">
			
			<?php echo $sf_data->getRaw('sf_content'); ?>


		</div>
		<!-- end #page -->
	</div>
	<!-- end #wrapper2 -->
	<div style="clear: both;">&nbsp;</div>
	<div id="footer">
		<p>(c) 2007 Website Name. Design by <a href="http://www.nodethirtythree.com/">NodeThirtyThree</a> + <a href="http://www.freecsstemplates.org/">Free CSS Templates</a></p>
	</div>
</div>
<!-- end #wrapper -->
</body>
</html>


