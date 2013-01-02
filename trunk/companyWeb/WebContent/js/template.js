$(document).ready(function(){
	splitPages(5,1);
	init();
});

function init(){
	loadCompanyType();
	loadTemplate("");
	
}

//加载类型
function loadCompanyType(){
	$.ajax({
		url: "../companytype",
		type: "get",
		success:function(data, textStatus){
			showTemplateType(data);
		}
	});
}

//加载模板
function loadTemplate(typeId){
	$.ajax({
		url: "../template",
		type: "get",
		data:{type_id:typeId},
		success:function(data){
			showTemplates(data);
		}
	});
}

//显示模板类型
function showTemplateType(data){
	var dataObj=eval(data);
	for(var d in dataObj)
	{
		if(d == 0){
			$("#type_list").append("<li class=\"active\" ttype=\"\"><a href=\"#\">" + dataObj[d].typeName + "</a></li>");
			continue;
		}
		$("#type_list").append("<li ttype=\"" + dataObj[d].typeId + "\"><a href=\"#\">" + dataObj[d].typeName + "</a></li>");
	}
	$("#type_list > li").click(function(){
		$("#type_list > li").removeAttr("class");
		$(this).attr("class", "active");
		loadTemplate($(this).attr("ttype"));
	});
}

//显示模板
function showTemplates(data){
	var dataObj=eval(data);
	$("#template_list").children().remove();
	if(dataObj==""){
		$("#template_list").append("<div>暂时没有此类模板</div>");
	}
	else{
		for(var d in dataObj)
		{
			$("#template_list").append("<li class=\"template\"><a href=\"" + dataObj[d].templateXmlPath + "template.html\" target=\"_blank\"><img title=\"点击预览\" src=\""+ dataObj[d].templateXmlPath +"template.png\"/></a>" +
					"<label>" + dataObj[d].templateName + "</label></li>");
			//$("#template_list").append("<div class=\"span2\"><img src=\"../img/templates/template1.png\" width=\"100px\" height=\"100px\"/>" + dataObj[d].templateName + "</div>");
		}
	}
}

//分页
function splitPages(pageCount, splitCount){

	function splitPage(pageCount, splitCount){
		pages = Math.round(pageCount/splitCount);
		pageNum = "";
		if (pages>5){
			pages = 5;
		}
		for(var i=2; i<=pages ; i++){
			pageNum += "<li><a href=\"#\">" + i + "</a></li>" 
		}
		return pageNum;
	}
	
	function addPageNum(pageCount, splitCount){
		pageHead = "<li><a href=\"#\">首页</a></li><li class=\"disabled\"><a href=\"#\">上一页</a></li><li class=\"active\"><a href=\"#\">1</a></li>";
		pageNum = splitPage(pageCount, splitCount);
		pageEnd = "<li><a href=\"#\">下一页</a></li><li><a href=\"#\">末页</a></li>";
		return pageHead + pageNum + pageEnd;
	}
	
	$("#pagenum").append(addPageNum(pageCount,splitCount));
	
}

//搜索
function searchTemplate(){
	
}
