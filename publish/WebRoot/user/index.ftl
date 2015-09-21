<div id="main" style="height:500px"></div>

<script type="text/javascript" src="/style/js/charts/echarts-all.js"></script>

<script>
    var myChart = echarts.init(document.getElementById('main'));     
        
	option = {
	    tooltip : {
	        show: 'true'
	    },
	    legend: {
	        data:['注册数','绑卡数','交易数','交易额','回款额']
	    },
	    xAxis : [
	        {
	            type : 'category',
	            name : '日期(前一日)',
	            data : [<#list list as p>'${(p.createtime?string('M月d日'))!}'<#if p_has_next>,</#if></#list>]
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name : '数量',
	            axisLabel : {
	                formatter: '{value} 人'
	            }
	        },
	        {
	            type : 'value',
	            name : '金额',
	            axisLabel : {
	                formatter: '{value} 万元'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'注册数',
	            type:'bar',
	            data:[<#list list as p>'${(p.registcount)!}'<#if p_has_next>,</#if></#list>]
	        },
	        {
	            name:'绑卡数',
	            type:'bar',
	            data:[<#list list as p>'${(p.bindcardcount)!}'<#if p_has_next>,</#if></#list>]
	        },
	        {
	            name:'交易数',
	            type:'bar',
	            data:[<#list list as p>'${(p.paysuccesscount)!}'<#if p_has_next>,</#if></#list>]
	        },
	        {
	            name:'交易额',
	            type:'line',
	            yAxisIndex: 1,
	            data:[<#list list as p>'${(p.selfitempaysuccessamount/10000)!}'<#if p_has_next>,</#if></#list>]
	        },
	        {
	            name:'回款额',
	            type:'line',
	            yAxisIndex: 1,
	            data:[<#list list as p>'${(p.cashbackamount/10000)!}'<#if p_has_next>,</#if></#list>]
	        }
	    ]
	};
	
	myChart.setOption(option); 
</script>

