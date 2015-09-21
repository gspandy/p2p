	<#if list??>
		<#list list as p>
			<tr class="th">
				<td align="left"><b>${(p.account.mobile)!}</b>：<br>　　${(p.question)!}<br><font color="#999999">${(p.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</font></td>
				<td align="left" id="td_${(p.id)!}"><b>${(p.manager)!}</b>：<br>　　${(p.reply)!}<br><font color="#999999">${(p.replytime?string('yyyy-MM-dd HH:mm:ss'))!}</font></td>
				<td>
					<#if p.status==0>
						<textarea id="fb_${(p.id)!}" style="width:200px;"></textarea><br>
						<input type="button" onclick="reply('${(p.id)!}','reply')" class="submit" value="仅回复">
						<input type="button" onclick="reply('${(p.id)!}','replyandresolve')" class="submit" value="回复并解决">
					<#elseif p.status==1>
						<input type="button" onclick="reply('${(p.id)!}','resolve')" class="submit" value="已解决">
					<#elseif p.status==2>
						已解决
					</#if>
				</td>
			</tr>
		</#list>
	</#if>
</table>

<script>
	function reply(id,category){
		if(category=='reply' || category=='replyandresolve'){
			var reply = $('#fb_'+id).val();
			if(reply!=''){
				var content = encodeURI(reply);
				$.get('/admincrm/feedbackreply.html?id='+id+'&reply='+content,function(data){
					if(data=='success'){
						$('#td_'+id).html(reply);
						if(category=='replyandresolve'){
							$.post('/admincrm/feedbackresolve.html?id='+id,function(data){
								errorTip('更新成功！');
							});
						}else{
							errorTip('更新成功！');
						}
					}else{
						errorTip('已经有其他人回复过了！');
					}
				});
			}
		}else if(category=='resolve'){
			$.post('/admincrm/feedbackresolve.html?id='+id,function(data){
				errorTip('更新成功！');
			});
		}
	}
</script>