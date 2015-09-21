<link rel="stylesheet" href="../style/style.css">
<script type="text/javascript" src="/js/slideToggle.js"></script>
<section>
    <div class="qa-item">
    <#if records??>
           <#list records as r>
        <div class="qa-1">
            <div class="qa-1-up"><h2 class="qa-h2">${(r.question)!}</h2><a  class="qa-a1" href="javascript:void(0);" onclick="slideToggle('${r_index}')"><img class="qa-big-img" src="images/right-arrows.png" /></a></div>
            <div class="qa-1-down" id="${r_index}">
              <#if r.list??>
                 <#list r.list as l>
                <div class="qa-2-up">
                    <h3 class="qa-h3">${(l.question)!}<a class="qa-a2" href="javascript:void(0);" onclick="slideToggle('${r_index}-${l_index}')"><img class="qa-small-img" src="images/right.png" /></a></h3></div>
                    <p class="qa-p" id="${r_index}-${l_index}">${(l.answer)!}</p>
                 </#list>
              </#if>     
                    
            </div>
        </div>
         </#list>
      </#if>  
       
    </div>
</section>