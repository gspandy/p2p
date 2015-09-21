<#include "/fcs/menu.ftl" />

<table class="tables">
	<tr>
		<th>创建时间</th>
		<th>内容</th>		
	</tr>
	<#if list??>
		<#list list as p>
			<tr class="th">		
				<td>${(p.createtime?string('yyyy-MM-dd'))!}</td>
				 <td align = "left">${(p.content)!}</td>
			</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/adminfcs/checkreport.html",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>