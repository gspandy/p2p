<#include "/pbs/menu.ftl" />

<table class="tables">
	<tr>
		<th align="left">产品</th>
		<th>活动标签</th>
		<th>募集总额</th>
		<th>年化收益</th>
		<th>计息天数</th>
		<th>起购金额</th>
		<th>开售时间</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<#if list?exists>
	<#list list as p>
	<tr>
		<td align="left">${(p.name)!}</td>
		<td>${(p.activitytitle)!}</td>
		<td>${(p.totalamount)!}</td>
		<td>${(p.annualrateextra)!}% <#if p.annualrate!=0>+ ${(p.annualrate)!}%</#if></td>
		<td>${(p.incomedays)!}</td>
		<td>${(p.unitprice)!}</td>
		<td width="300">${(p.starttime)?string("yyyy-MM-dd HH:mm:ss")!}</td>
		<td><#if p.status=0>未上线<#else>已上线</#if></td>
		<td width="80">
			<#if p.status=0><a href="/adminpbs/selfitemadd.html?id=${(p.id)!}">编辑</a></#if>
		</td>
	</tr>
	</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/adminpbs/selfitemlist.html",${map.get("page").currentPage!},${map.get("page").totalPages!})</script>
</div>