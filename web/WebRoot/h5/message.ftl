<link rel="stylesheet" href="../style/style.css">
<input type="hidden"  id="login" name="login" value="${(login)!}">
<input type="hidden"  id="code" name="code" value="${(code)!}">
<input type="hidden"  id="perCount" name="perCount" value="${(perCount)!}">
<input type="hidden"  id="pageNo" name="pageNo" value="${(pageNo)!}">
<body class="back">
<section class="message-main">
    <ul id="box"></ul>
</section>
<script>
    //点击详情，显示更多
    function showMessage(obj){
        var p = $(obj).parent();
        p.text(p.attr('info'));
    }
    //加载数据块，并进行上拉加载更多
    var login=$("#login").val();
    var code=$("#code").val();
    var perCount = $("#perCount").val();
    var pageNo = $("#pageNo").val();
    //请求数据块并直接加载
    $(function(){
        requestData(pageNo);
    });

    window.onscroll = function(){
        if(CheckScrollSlide('box','message-li')){
            requestData(++pageNo);
        }
    };

    function requestData(currentPageNo){
        $.ajax({
					type: "post",
					url: "messagelistpage.html",
					data:{"login":login,"perCount":perCount,"pageNo":pageNo,"code":code},
					dataType: 'json',
				    success : function(data) {
                          loadData(data);
				    }
				  
	    		});
    }

    function loadData(data){
        //将数据块渲染到页面中
        for(var i = 0;i<data.length;i++){
            var s = '<li class="message-li">';
            s +='   <div class="message-up">';
            s +='    <h2 class="message-h2">'+data[i].title+'</h2>';
                     if(data[i].subcontent!=null){
           s +='    <p class="message-p1" info="'+data[i].content+'">'+data[i].subcontent+'<a class="message-a" href="javascript:void(0);" onclick="showMessage(this)">详情</a></p>';              
                     }else{
           s +='    <p class="message-p1" >'+data[i].content+'</p>';
                     }
                     
            s +='   </div>';
            s +='   <div class="message-down">';
            s +='    <p class="message-p2"><img class="message-img" src="images/clock.png" alt="" />'+data[i].createtime+'</p>';
            s +='   </div>';
            s +='</li>';
            $('#box').append(s);
        }
    }

</script>
</body>