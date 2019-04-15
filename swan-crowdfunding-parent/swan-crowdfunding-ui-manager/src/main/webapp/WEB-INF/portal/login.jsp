<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<base
    href="http://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">

<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- 一定要放在jQuery库文件后面 -->
<script type="text/javascript" src="layer/layer.js"></script>

<script type="text/javascript">
    $(function() {
    	
   		// 设置会员登录的地址
   		var memberLocation = "to/member/login.json";
   		// 设置管理员登录的地址
   		var userLocation = "to/user/page.json";
   		// 设置一个默认登录的地址
   		var defaultLocation = userLocation;
    		
   		// 当用户选中下拉列表时，选中一个跳转到其地址
   		// 获取选中的值
        $("select").change(function () {
        	var checkValue = $(":selected").text();
            if (checkValue = "会员") {
                // 设置会员的地址
                defalutLocation = memberLocation;
            } else {
                // 设置管理员的地址
                defalutLocation = userLocation;
            }
        });    		
    		
    	$("#loginBtn").click(function() {
    		// 获取请求的用户名和密码
    		var loginacct = $("#loginAccount").val();
    		var userpwd = $("#loginPwd").val();
    		
    	 	// 向服务器放松ajax请求
    		$.ajax({
    			// 请求访问的地址
    			"url" : defaultLocation,
    			// 请求的方式
    			"type" : "post",
    			// 请求数据的格式
    			"contentType" : "application/x-www-form-urlencoded",
    			// 请求发送的数据
    			"data" : {
    				"loginacct" : loginacct,
    				"userpswd" : userpwd
    			},
    			// 服务器返回的数据格式类型
    			"dataType" : "json",
    			// 请求成功后处理服务器回调的信息,controller方法的返回值就是服务器回调的信息！！！！
    			"success" : function (msg) {
    				var result = msg.result;
    				// 用户名密码正确
    			    if (result == "SUCCESS") {
    			    	// 跳转到管理员的页面
    			    	window.location.href = "main/to/manager/main.html";
    			    }
    				
    				//用户名密码不正确
    				if (result == "FAILED") {
    					var message = msg.message;
    					layer.msg(message);
    				}
    			    
    			},
    			// 请求失败
    			"error" : function (response) {
    				// 服务器抛出异常
    				var message = response.responseJSON.message;
    				layer.msg(message);
    			}
    		});  
    		
    	}); 
    	
    });
</script>   
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top"
        role="navigation">
        <div class="container">
            <div class="navbar-header">
                <div>
                    <a class="navbar-brand" href="index.html"
                        style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
                </div>
            </div>
        </div>
    </nav>

    <div class="container">

        <form class="form-signin" role="form">
            <h2 class="form-signin-heading">
                <i class="glyphicon glyphicon-log-in"></i> 用户登录
            </h2>
            <p>${ requestScope.MESSAGE }</p>
            <div class="form-group has-success has-feedback">
                <input type="text" class="form-control"
                    id="loginAccount" value="swan" placeholder="请输入登录账号" autofocus>
                <span
                    class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <input type="text" class="form-control"
                    id="loginPwd" value="222222" placeholder="请输入登录密码"
                    style="margin-top: 10px;"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <select class="form-control">
                    <option value="member">会员</option>
                    <option value="user" selected="selected">管理</option>
                </select>
            </div>
            <div class="checkbox">
                <label> <input type="checkbox"
                    value="remember-me"> 记住我
                </label> <br> <label> 忘记密码 </label> <label
                    style="float: right"> <a href="reg.html">我要注册</a>
                </label>
            </div>
            <button id="loginBtn" type="button"
                class="btn btn-lg btn-success btn-block">登录</button>
        </form>
    </div>
</body>
</html>