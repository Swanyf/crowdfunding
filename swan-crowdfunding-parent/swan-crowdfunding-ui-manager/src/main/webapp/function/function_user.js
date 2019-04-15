/**
 * 生成分页的函数
 */
function generateUserPage() {
	
	// 发送ajax请求
	$.ajax({
		"url" : "user/get/page.json",
		"type" : "post",
		"data" : {
			"pageNum" : window.pageNum,
			"keyword" : window.keyword
		},
		"contentType" : "application/x-www-form-urlencoded",
		"dataType" : "json",
		// 请求成功后响应的分页数据
		"success" : function(responseMsg) {
			// pageInfo 分页数据
			var pageInfo = responseMsg.responseData;
			console.log(pageInfo);

			// 生成分页后表格的数据
			generateUserTable(pageInfo);

			// 初始化页码导航条
			generateNavigator(pageInfo);
		},
		// 请求失败提示信息
		"error" : function(responseMsg) {
			var message = responseMsg.responseJSON.message;
			layer.msg(message);
		}
	});
	
}

// 生成表格数据
function generateUserTable(pageInfo) {
	
	// 清除表格追加的旧数据
	$("#userPageBody").empty();

	// 获取分页pageInfo的list数据
	var list = pageInfo.list;

	// 判断list是否有数据
	if (list == null || list.length == 0) {
		$("#userPageBody")
				.append(
						"<tr><td style='text-align:center;' colspan='6'><font color='gray'>sorry...没有查询到相关数据...<font/></td></tr>");
		// 为空直接结束
		return;
	}

	// 遍历list中的数据
	for (var i = 0; i < list.length; i++) {
		// 获取每个对象的数据
		var user = list[i];
		var id = user.id;
		var loginacct = user.loginacct;
		var userpswd = user.userpswd;
		var username = user.username;
		var email = user.email;
		// 设置三个按钮
		var check_btn = "<a href='assign/role/" + id +".html' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></a>";
		var edit_btn = "<button id='"
				+ id
				+ "' type='button' class='btn btn-primary btn-xs edit_btn'><i class=' glyphicon glyphicon-pencil'></i></button>";
		var del_btn = "<button id='"
				+ id
				+ "' type='button' class='btn btn-danger btn-xs remove_btn'><i class=' glyphicon glyphicon-remove'></i></button>";
		// 设置要显示的数据信息
		var show_msg = "<tr><td>" + (i + 1) + "</td><td><input id='" + id
				+ "' class='check_box' type='checkBox'/></td><td>" + loginacct
				+ "</td><td>" + username + "</td><td>" + email + "</td><td>"
				+ check_btn + " " + edit_btn + " " + del_btn + "</td></tr>";
		// 添加要显示信息
		
		$("#userPageBody").append(show_msg);
	}
	
}

// 初始化页码导航条
function generateNavigator(pageInfo) {
	
	// 获取分页的总记录数()
	var page_total = pageInfo.total;
	$("#Pagination").pagination(page_total, {
		prev_text : "上一页", // 显示按钮的文字
		next_text : "下一页",
		items_per_page : pageInfo.pageSize, // 每页显示几条数据
		current_page : (pageInfo.pageNum) - 1, // 当前选中的页数
		num_edge_entries : 3, // 两侧显示的首尾分页的条目数
		num_display_entries : 4, // 连续分页主体部分显示的分页条目数
		callback : showCheckedPage
	// 点击页数所显示的数据
	});
	
}

// 注释掉jquery.pagination.js最后一行的回调函数,否则将导致死循环重新执行分页操作

// 点击页数所显示的数据
function showCheckedPage(page_index, jq) {
	
	/*
	 * page_index 表示当前的页码 page_index + 1 表示要前往页面的页码
	 */
	window.pageNum = page_index + 1;
	// 调用分页数据的函数
	generateUserPage();
	return false;
	
}

// 根据用户id的list数据显示模态框的删除消息提示的信息
function showUserBatchRemoveModal(userIdList) {

	// 显示确认删除消息的模态框
	$("#userRemoveModal").modal("show");
	// 1、查询id对应的用户对象信息
	// 2、在模态框中显示要查询到的信息
	getUserListByIdList(userIdList);
	
}

// 根据多个用户的id查询多个用户信息
function getUserListByIdList(userIdList) {
	
	// 发送ajax请求
	$.ajax({
		"url" : "get/user/list/by/id/list.json",
		"data" : {
			"userIdList" : userIdList,
			"random" : Math.random()
		},
		"type" : "post",
		"contentType" : "application/x-www-form-urlencoded",
		"dataType" : "json",
		"error" : function(responseMsg) {
			var message = response.responseJSON.message;
			layer.msg(message);
		},
		"success" : function(responseMsg) {
			// 获取服务器响应的多个用户信息
			var userList = responseMsg.responseData;
			delConfimTable(userList);
		}
	});
	
}

// 在模态框中显示要删除的数据,根据user的list显示表格
function delConfimTable(userList) {
	
	// 清空重复的旧数据
	$("#userRemoveModalBody").empty();

	// 遍历获取的用户数据
	for (var i = 0; i < userList.length; i++) {
		var user = userList[i];
		var loginacct = user.loginacct;
		var username = user.username;
		var email = user.email;
		// 将数据拼成一行
		var tr_items = "<tr><td>" + loginacct + "</td><td>" + username
				+ "</td><td>" + email + "</td></tr>";
		// 将拼成的数据存放
		$("#userRemoveModalBody").append(tr_items);
	}
	
}

// 批量删除信息
function batchRemoveUserByIdList(userIdList) {

	// 将json格式的userIdList数据转换成java数据格式
	var changeUserIdList = JSON.stringify(userIdList);
	// 发送ajax请求
	$.ajax({
		"url" : "batch/remove/user/by/id/list.json",
		"type" : "post",
		"data" : changeUserIdList,
		"contentType" : "application/json;charset=UTF-8", // 服务器端handler方法中使用@RequestBody接收
		"dataType" : "json",
		"success" : function(responseMsg) {
			// 关闭模态框
			$("#userRemoveModal").modal("hide");
			// 显示提示消息
			layer.msg("操作成功！");
			// 重新执行分页函数，刷新数据
			generateUserPage();
			// 初始化全局变量的userIdList供下次操作 使用
			window.userIdList = new Array();
		},
		"error" : function(response) {
			var message = response.responseJSON.message;
			layer.msg(message);
		}
	});
	
}

// 根据id获取user对象信息
function getUserById(userId) {
	
	// 发送ajax请求获取对象
	$.ajax({
		"url" : "get/user/by/id.json",
		"type" : "post",
		"data" : {
			"id" : userId,
			"random" : Math.random()
		},
		"contentType" : "application/x-www-form-urlencoded", 
		"dataType" : "json",
		"success" : function(responseMsg) {
			// 将获取到的user对象设置为全局变量
			window.user = responseMsg.responseData;
			console.log(user);
			// 回显表单信息
			showEditItems(user);
		},
		"error" : function(response) {
			var message = response.responseJSON.message;
			layer.msg(message);
		}
	});
	
}

// 回显表单信息
function showEditItems(user) {
	
	$("#loginaccEdit").val(user.loginacct);
	$("#usernameEdit").val(user.username);
	$("#emailEdit").val(user.email);
	
}

// 修改用户信息
function editUser(){
	
	// 获取全局变量user的id
	var id = window.user.id;
	
	// 发送ajax请求
	$.ajax({
		"url" : "edit/user.json",
		"type" : "post",
		"data" : {
			"id" : id,
			"loginacct" : $("#loginaccEdit").val(),
			"username" : $("#usernameEdit").val(),
			"email" : $("#emailEdit").val(),
			"random":Math.random()
		},
		"contentType" : "application/x-www-form-urlencoded", // 服务器端handler方法中使用@RequestBody接收
		"dataType" : "json",
		"success" : function (responseMsg) {
			//关闭模态框
			$("#userEditModal").modal("hide");
			
			//重新调用分页方法
			generateUserPage();
		},
		"error" : function(responseMsg) {
			var message = responseMsg.responseJSON.message;
			layer.msg(message);
		}
	});
	
}