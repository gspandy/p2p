<#include "/project/menu.ftl" />

<table class="tables">
	<tr>
		<th align="left">项目</th>
		<th align="left">项目组</th>
		<th>项目周期</th>
		<th>进度</th>
	</tr>
	<#if list?exists>
	<#list list as p>
	<tr onclick="toggleContent(this)">
		<td align="left"><a href="javascript:void(0)" style="text-decoration:underline;">${(p.title)!}</a></td>
		<td align="left">${(p.partners)!}</td>
		<td>${(p.starttime?string("yyyy-MM-dd"))!}~${(p.endtime?string("yyyy-MM-dd"))!}</td>
		<td><div style="background:#8DD242;width:${(p.progress)!}%;">　</div></td>
	</tr>
	<tr style="display:none;color:#999999;"><td colspan="4" align="left">${(StringUtil.convertPageToDatabase(p.content))!}</td></tr>
	</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/adminpbs/projectlist.html",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>

<script>
	function toggleContent(obj){
		$(obj).next().toggle();
	}
</script>