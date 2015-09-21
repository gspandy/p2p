<link rel="stylesheet" href="../style/style.css">
<section>
<input type="hidden" id="login" name="login" value="${login!}"  >
<input type="hidden" id="code" name="code" value="${code!}"  >
    <h2 class="point-h2">${scoreasset!}<span>元</span></h2>
      <div class="point-chaining">
        <ul>
            <li><a class="point-a award"><img class="record-img" src="images/box.png" /><p>兑换奖品</p></a></li>
            <li class="active"><a class="point-a1"><img class="record-img" src="images/yman.png" /><p>我的任务</p></a></li>
            <li><a class="point-a record"><img class="record-img" src="images/calendar.png" /><p>积分记录</p></a></li>
        </ul>
    </div>
    <div id="task-box"></div>
</section>


<script type="text/javascript">
    var code = $("#code").val();
    var login = $("#login").val();
    var perCount = 15;
    var pageNo = 1;
      $(".award").on("click", function() {
			window.location.href = "scoreaward.html?code="+code+"&login="+login+"&newopen";
		});
		$(".record").on("click", function() {
			window.location.href = "scorerecord.html?code="+code+"&login="+login+"&newopen";
		});
		
		
		//请求数据块并直接加载
    $(function(){
        requestData(pageNo);
    });

    window.onscroll = function(){
        if(CheckScrollSlide('task-box','task-main')){
            requestData(++ currentPageNo);
        }
    };

    function requestData(pageNo){
         $.ajax({
					type: "post",
					url: "scoretaskpage.html",
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
            var s = '<div class="task-main">';
            s +='       <h3 class="task-h3">'+data[i].title+'</h3>';
            s +='       <h4 class="task-h4">+'+data[i].score+'</h4>';
            s +='     </div>';
         
            $('#task-box').append(s);
        }
    }
		
</script>