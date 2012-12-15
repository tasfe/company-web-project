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
		$("#type_list").append("<li class=\"type\">" + dataObj[d].typeName + "</li>");
	}
	
}

function showTemplates(data){
	var dataObj=eval(data);
	for(var d in dataObj)
	{
		$("#template_list").append("<li class=\"template\">" + dataObj[d].templateName + "</li>");
	}
}