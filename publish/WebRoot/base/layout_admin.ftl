<!doctype html>
<html>
<head>
	<#include "/base/style.ftl" />
</head>
<body>
	<#include "/base/header.ftl" />
	
	<div class="center">
		<div class="content">
			<#if PageUrl??><#include "${PageUrl!}" /></#if>
		</div>
	</div>
	
	<#include "/base/footer.ftl" />
</body>
</html>