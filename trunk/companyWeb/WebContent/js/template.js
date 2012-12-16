$(document).ready(function(){
	init();
});

function init(){
	loadCompanyType();
	loadTemplate("");
}

function loadCompanyType(){
	$.ajax({
		url: "../companytype",
		type: "get",
		success:function(data, textStatus){
			showTemplateType(data);
		}
	});
}
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