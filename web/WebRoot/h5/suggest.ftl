<link rel="stylesheet" href="../style/style.css">
<div class="feedback-container">

    <div class="feedback-main">
      <#if records??>
         <#list records as l>
    
        <div class="feedback-block">
            <div class="feedback-time"><span>${(l.createtime?string("yyyy-MM-dd HH:mm:ss"))!}</span></div>
            <div class="feedback-matter">
                <img class="pic-left" src="images/black.png" />
                <p class="feedback-p1">${l.question!}<img class="pic-white" src="images/white.png" /></p>
            </div>
        </div>
        <#if l.status?? &l.status=1>
        <div class="feedback-block">
            <div class="feedback-time"><span>${(l.replytime?string("yyyy-MM-dd HH:mm:ss"))!}</span></div>
            <div class="feedback-matter">
                <img class="pic-right" src="images/black.png" />
                <p class="feedback-p2">${l.reply!}<img class="pic-green" src="images/green.png" /></p>
            </div>
        </div>
        </#if>
        
        </#list>
      </#if> 
    </div>
        
    <div class="feedback-footer">
        <form action="submitsuggest.html" method="post" name="chat">
        <input type="hidden"  id="login" name="login" value="${(login)!}">
        <input type="hidden"  id="code" name="code" value="${(code)!}">
            <textarea class="feedback-text"  name="question"></textarea> <button type="submit" class="feedback-btn">提交</button>
        </form>
    </div>
</div>
<script>
   //获取可视区域的高度
    var bigHeight = document.documentElement.clientHeight;
    var smallHeight = bigHeight - 45;
    $('#max-height').css('height',smallHeight+'px');
    
    
    
    

</script>