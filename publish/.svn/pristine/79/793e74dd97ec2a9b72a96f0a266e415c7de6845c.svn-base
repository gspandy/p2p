function page(url, currentPage, pageCount){
	if(pageCount <= 1){
		return;
	}
	var URL;
	if(url.indexOf("?")== -1){
		URL = url + "?currentPage=";
	}else{
		URL = url + "&currentPage=";
	}
	document.write("<div class=\"pages\">")
	if(currentPage > 1){
		document.write(link = "<a href=\""+ URL + (currentPage - 1) +"\"><<</a> ");
	}else{
		document.write(link = "<a href=\"#\"><<</a> ");
	}
	if(pageCount<=10){
		for(var i = 1; i <= pageCount; i++){
			if(i == currentPage){
				document.write(link = "<span class=\"current\">" + i + "</span> ");
			}else{
				document.write(link = "<a href=\""+ URL + i +"\">"+ i +"</a> ");
			}		
		}
	}else{
		if(currentPage>3){
			document.write(link = "<a href=\""+ URL + "1\">1</a> ");
			document.write("... ");
			var endPage = 0;
			if(pageCount <= currentPage + 2){
				endPage = pageCount;
			}else{
				endPage = currentPage + 2;
			}
			for(var i = currentPage-2; i <= endPage; i++){
				if(i == currentPage){
					document.write(link = "<span class=\"current\">" + i + "</span> ");
				}else{
					document.write(link = "<a href=\""+ URL + i +"\">"+ i +"</a> ");
				}
			}
		}else{
			for(var i = 1; i <= currentPage + 2; i ++){
				if(i == currentPage){
					document.write(link = "<span class=\"current\">" + i + "</span> ");
				}else{
					document.write(link = "<a href=\""+ URL + i +"\">"+ i +"</a> ");
				}
			}
		}
		if(currentPage < pageCount - 2){
			document.write("... ");
			document.write(link = "<a href=\""+ URL + pageCount +"\">"+ pageCount +"</a> ");
		}
	}
	if(currentPage < pageCount){
		document.write(link = "<a href=\""+ URL + (currentPage + 1) +"\">>></a> ");
	}else{
		document.write(link = "<a href=\"#\">>></a> ");
	}
	document.write("</div>")
}