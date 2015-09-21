<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>用户</th>
		<th>回复</th>
		<th>操作</th>
	</tr>
<#include "/crm/fb.ftl" />

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/feedback.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>