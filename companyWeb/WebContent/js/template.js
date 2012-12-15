$(document).ready(function(){
	init();
});

function init(){
	loadTemplate("");
}

function loadTemplate(typeId){
	$.ajax({
		url: "/template",
		type: "get",
		data:{type_id:typeId},
		success:function(dataDict){
			if(typeId == ""){
				//showTemplateType(1);
				//showTempates(2);
				alert(dataDict);
			}
			else{
				alert(dataDict);
				//showTempates(2);
			}
		}
	});
}

function showTemplateType(data){
	$("#type_list").append("<li class=\"type\">type</li>");
}

function showTemplates(data){
	$("#template_list").append("<li class=\"template\">template</li>");
}