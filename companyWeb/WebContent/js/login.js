
$(document).ready(
    $("#login").click(function() {
    	$("#loginForm").attr("action","./login");
    	$("#loginForm").submit();
    });
    $("#loginCancel").click(function() {
    	$("#loginForm").attr("action","./");
    	$("#loginCancel").submit();
    });
    $("#regist").click(function() {
    	$("#loginForm").attr("action","./");
    	$("#regist").submit();
    });
)