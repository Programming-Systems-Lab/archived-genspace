<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<?php Header("Cache-control: private, no-cache");
Header("Expires: Mon, 26 Jun 1997 05:00:00 GMT");
Header("Pragma: no-cache"); ?>
<?php include_http_metas() ?>
<?php include_metas() ?>

<?php include_title() ?>

<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui-1.8.6.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.ui.stars.js"></script>
    <script type="text/javascript" src="/js/jquery.jqplot.js"></script>
  <script type="text/javascript" src="/js/jquery.tinysort.js"></script>
   <script src="https://www.google.com/jsapi" type="text/javascript"></script>


 <!--[if IE]><script language="javascript" type="text/javascript" src="/js/excanvas.js"></script><![endif]-->


<script type="text/javascript">
	$.jqplot.config.enablePlugins = true;

</script>

    <script type="text/javascript" src="/js/plugins/jqplot.categoryAxisRenderer.min.js"></script>
    	<script type='text/javascript' src='/js/lib/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='/js/lib/jquery.ajaxQueue.js'></script>
<script type='text/javascript' src='/js/lib/thickbox-compressed.js'></script>
<script type='text/javascript' src='/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="/js/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="/js/plugins/jqplot.pointLabels.min.js"></script>
	<script type="text/javascript">
	/*
Clear default form value script- By Ada Shimar (ada@chalktv.com)
*/
function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = ""
}
	
	
	$(function(){
		
		$( "#accordion" ).accordion({
			collapsible: true,
			autoHeight:false,
			clearStyle:true
		});
	

		$('a.delete').click(function() { 
  var a = this; 
  $('#deletedialog').dialog({
	  autoopen:false,
	  show: 'blind',
			hide: 'blind',
			title: 'Confirm Delete',
			modal: true,
        buttons: {
          "Delete": function() {
                 window.location = a.href; 
          },
		  "Cancel":function(){$( this ).dialog( "close" );
}

        }
		
  }); 
  
  return false;
});
		$('a.edit').click(function() { 
  var a = this; 
  $('#editdialog').dialog({
	  autoopen:false,
	  show: 'blind',
			hide: 'blind',
			title: 'Edit Comment',
			width:380,
			modal: true
       
		
  }); 
  
  return false;
});
$('a.forgotpass').click(function() { 
  var a = this; 
  $('#forgotpassdialog').dialog({
	  autoopen:false,
	  show: 'blind',
			hide: 'blind',
			title: 'Forgot Password',
			width:380,
			modal: true
       
		
  }); 
  
  return false;
});
	$("#searchfield").autocomplete('http://localhost:8080/requestssuggest.php');	
	
			$("#starify").children().not(":input").hide();
			for (i=1;i<=30;i++)
{

           $("#avg"+i).stars();
		   
}
           $("#avg").stars();
		   
		    $("#userrating").children().stars({
			 inputType:"radio",
			 cancelShow: false	
			});
			 $("div.userrating").children().stars({
			 inputType:"radio",
			 cancelShow: false	
			});
			//$("#userrating2").children().not(":input").hide();
			$("#userrating2").children().stars({
			 inputType:"radio",
			 cancelShow: false	
			});
			
              $("#userratings").children().stars();
           
		   	$("#rat").stars({
				inputType: "select",
				cancelShow: false,
				captionEl: $("#caption"),
							});

			
						// Create stars from :radio boxes
			$("#starify").stars({
				cancelShow: false
			});
			$("#userratings").children().stars({
				cancelShow: false
			});
		});
	</script>
	
<link href="/css/main.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/css/jquery.ui.stars.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="/js/jquery.jqplot.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="/js/lib/thickbox.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.8.6.custom.css"/>


</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header">
			<div id="logo">
				<h1>genSpace</h1>
                
			</div>
            <br/>
            <div id="menu">
				<ul>
					<li><?php echo link_to("About", "/static/about_us"); ?></li>
					<li><?php echo link_to("Tools", "/tool"); ?></li>
					<li><?php echo link_to("Support", "/static/support"); ?></li>
				</ul>
                	<form action="/tool/index" method="post" id="toolsearch"><span style="color:white"><strong>Quick Tool Search:</strong></span>  <input name="searchfield" type="text" id="searchfield" style="height:25" size="25" onfocus="clearText(this)" />
                    <input name="image" type="image" style="border:0px;position:relative; top:5px; left:-35px; " src="http://localhost:8080/images/btn_search.gif" onclick="/tool/index"></form>
			
			</div>
            
		</div>
		<!-- end #header -->
		<div id="page">
			
			<?php echo $sf_data->getRaw('sf_content'); ?>
   
		<!-- end #page -->
	</div>
	<!-- end #wrapper2 -->
	<div style="clear: both;">&nbsp;</div>
	<div id="footer">
		<p>(c) 2010 Columbia University Programming Systems Lab. Design by <a href="http://www.nodethirtythree.com/">NodeThirtyThree</a> + <a href="http://www.freecsstemplates.org/">Free CSS Templates</a></p>
	</div>
</div>
<!-- end #wrapper -->
</body>
</html>


