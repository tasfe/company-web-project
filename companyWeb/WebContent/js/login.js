$(document).ready(function(){
    $("#login").click(function() {
    	$("#loginForm").attr("action","../login");
    	$("#loginForm").submit();
    });
    $("#loginCancel").click(function() {
    	$("#loginForm").attr("action","../index.jsp");
    	$("#loginForm").submit();
    });
    $("#regist").click(function() {
    	$("#loginForm").attr("action","regist.html");
    	$("#loginForm").submit();
    });
});