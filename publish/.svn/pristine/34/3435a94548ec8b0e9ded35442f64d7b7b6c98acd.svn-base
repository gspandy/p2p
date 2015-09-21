jQuery(function(){
	jQuery(".fancybox").fancybox({
		'width'				: 960,
		'height'			: 420,
		'autoScale'			: true,
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'centerOnScroll'	: true,
		'type'				: 'iframe'
	});
	
	jQuery(".fancybox_local").fancybox({
		'width'				: 960,
		'height'			: 300,
		'autoScale'			: true,
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'centerOnScroll'	: true
	});
	
	jQuery('.fancybox_local').live('mouseover',function(){
		jQuery('#tbl').html('');
		var arr = jQuery(this).attr('ctx').split('~&~');
		for(var i=0;i<arr.length;i++){
			var arr2 = arr[i].split('~#~');
			jQuery('#tbl').append('<tr height="30px"><td id="title2" width="30%">'+arr2[0]+'</td><td id="cont" width="70%">'+arr2[1]+'</td></tr>');
		}
	});
	
	jQuery('#automessage').fancybox();
});

// 嵌套页面
// <a class="fancybox" href="/index.html">测试</a>

// 弹窗
function fancybox(content){
	$('#automessage').html(content);
	$("#autostart").fancybox().trigger('click');
}
