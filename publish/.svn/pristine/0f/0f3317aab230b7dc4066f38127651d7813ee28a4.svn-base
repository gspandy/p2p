<#include "/project/menu.ftl" />

<table class="tables">
	<tr>
		<th align="left">Bug</th>
		<th>开发</th>
		<th>测试</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<#if list?exists>
	<#list list as p>
	<tr>
		<td align="left"><a href="/adminproject/bugview.html?id=${(p.id)!}" style="text-decoration:underline;">${(p.title)!}</a></td>
		<td>${(p.developer)!}</td>
		<td>${(p.tester)!}</td>
		<td>
			<#if p.status==0><font color="red">未解决</font>
			<#elseif p.status==1><font color="blue">已解决</font>
			<#elseif p.status==2><font color="green">已验证</font>
			<#elseif p.status==3><font color="blue">无法重现</font>
			<#elseif p.status==4><font color="blue">外部原因</font>
			<#elseif p.status==5><font color="red"><b>重新打开</b></font>
			</#if>
		</td>
		<td>
			<#if p.status==0>
				<input type="button" onclick="bugfixed('${(p.id)!}',1)" class="submit" value="我已解决">
				<input type="button" onclick="bugfixed('${(p.id)!}',3)" class="submit" value="无法重现">
				<input type="button" onclick="bugfixed('${(p.id)!}',4)" class="submit" value="外部原因">
				<input type="button" onclick="showAssign('${(p.id)!}',this)" class="submit" value="我要指派">
			<#elseif p.status==1>
				<input type="button" onclick="bugfixed('${(p.id)!}',2)" class="submit" value="通过验证">
				<input type="button" onclick="bugfixed('${(p.id)!}',5)" class="submit" value="重新打开">
			<#elseif p.status==2>
				
			<#elseif p.status==3>
				<input type="button" onclick="bugfixed('${(p.id)!}',5)" class="submit" value="重新打开">
				<input type="button" onclick="bugfixed('${(p.id)!}',6)" class="submit" value="立即关闭">
			<#elseif p.status==4>
				<input type="button" onclick="bugfixed('${(p.id)!}',5)" class="submit" value="重新打开">
				<input type="button" onclick="bugfixed('${(p.id)!}',6)" class="submit" value="立即关闭">
			<#elseif p.status==5>
				<input type="button" onclick="bugfixed('${(p.id)!}',1)" class="submit" value="我已解决">
				<input type="button" onclick="bugfixed('${(p.id)!}',3)" class="submit" value="无法重现">
				<input type="button" onclick="bugfixed('${(p.id)!}',4)" class="submit" value="外部原因">
				<input type="button" onclick="showAssign('${(p.id)!}',this)" class="submit" value="我要指派">
			</#if>
		</td>
	</tr>
	</#list>
	</#if>
</table>

<div id="assignDiv" style="display:none;margin:10px auto;border:1px solid #E8000C;text-align:left;width:65px;padding:5px;">
	<input type="radio" name="developer" value="董晓鹏">董晓鹏<br>
	<input type="radio" name="developer" value="汪磊">汪磊<br>
	<input type="radio" name="developer" value="文金庚">文金庚<br>
	<input type="radio" name="developer" value="郭元壮">郭元壮<br>
	<input type="radio" name="developer" value="杨晓洋">杨晓洋<br>
	<input type="button" onclick="assign()" class="submit" value="确定">
</div>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/adminproject/projectlist.html",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>

<script>
	function bugfixed(id,status){
		$.get('/adminproject/projectbugfixed.html?id='+id+'&status='+status,function(data){
			errorTip('更新成功！');
		});
	}
	
	var assignId;
	
	function showAssign(id,obj){
		assignId = id;
		$('#assignDiv').show();
		$(obj).parent().append($('#assignDiv'));
	}
	
	function assign(){
		var developer = $('[name=developer]:checked').val();
		if(developer!=''){
		developer = encodeURI(developer);
			$.get('/adminproject/projectbugassign.html?id='+assignId+'&developer='+developer,function(data){
				errorTip('指派成功！');
			});
		}
		$('#assignDiv').hide();
	}
</script>