<link rel="stylesheet" href="/style/js/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="/style/js/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="/style/js/kindeditor/lang/zh_CN.js"></script>

<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content"]', {
			allowImageUpload : false
		});
	});
</script>
