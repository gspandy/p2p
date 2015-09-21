<#include "/crm/menu.ftl" />

<table class="tables" style="width:1010px;">
<#include "/crm/fb.ftl" />

<script type="text/javascript">page("/admincrm/feedbackall.html","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
