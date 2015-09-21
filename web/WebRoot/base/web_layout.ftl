<!doctype html>
<html>
<head>
	<#include "/base/style.ftl" />
</head>
<body>
	<#include "/base/header.ftl" />
	
	<#if PageUrl??><#include "${PageUrl!}" /></#if>
	
	<#include "/base/footer.ftl" />
</body>
</html>