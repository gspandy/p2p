<#include "/${channel!}/menu.ftl" />

<table class="tables">
	<tr class="th">
		<td align="left">图片</td>
		<td width="200">链接</td>
	</tr>
	<#if list?exists>
	<#list list as p>
	<tr>
		<td align="left"><a href="${ImageServer!}${p.url!}" target="_blank"><img src="${ImageServer!}${p.url!}" style="height:200px;width:200px;"></a></td>
		<td><textarea style="width:200px;height:50px;">${p.url!}</textarea></td>
	</tr>
	</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincslc/picturelist.html?channel=${channel!}",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>