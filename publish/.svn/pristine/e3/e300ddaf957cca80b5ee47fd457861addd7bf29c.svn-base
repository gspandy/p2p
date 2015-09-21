<#include "/pbs/menu.ftl" />

<table class="tables">
	<tr>
		<th align="left">项目</th>
		<th>操作</th>
	</tr>
	<#if list?exists>
	<#list list as p>
	<tr>
		<td align="left">${(p.title)!}</td>
		<td width="80">
			<a href="/adminpbs/projectadd.html?id=${(p.id)!}">编辑</a>
		</td>
	</tr>
	</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/adminpbs/projectlist.html?channel=pbs",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>