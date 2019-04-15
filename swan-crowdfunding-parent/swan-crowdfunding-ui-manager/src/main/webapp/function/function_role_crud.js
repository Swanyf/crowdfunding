// 模糊查询
function getRoleListByKeyword() {

	// 给查询按钮绑定单击事件
	$("#selectByKeyword").click(function() {
		window.keyword = $("#inputValue").val();
		layer.msg(window.keyword);
		// 刷新分页数据
		getPageInfoList();
	});
}

// 添加
function addRole() {
	// 点击新增弹出模态框
	$("#role_add").click(function() {
		// 弹出添加模态框
		$("#roleAddModal").modal("show");
	});

	// 给添加按钮绑定单击事件
	$("#roleAddBtn").click(function() {
		// 获取输入的角色值
		var roleName = $.trim($("#roleInput").val());
		if (roleName == null || roleName == "") {
			layer.msg("输入角色名有误，请重试！");
			return;
		}

		// 发送ajax请求检查角色名称是否存在
		$.ajax({
			"url" : "get/role/by/name/" + roleName + ".json",
			"type" : "post",
			"dataType" : "json",
			"error" : function(response) {
				layer.msg(response.responseJSON.message);
			},
			"success" : function(response) {
				if (response.result == "SUCCESS") {
					// 角色名可用
					$.ajax({
						"url" : "add/role.json",
						"data" : {
							"name" : roleName
						},
						"type" : "post",
						"dataType" : "json",
						"error" : function(response) {
							layer.msg(response.responseJSON.message);
						},
						"success" : function(response) {
							layer.msg("添加成功！");
							// 关闭模态框
							$("#roleAddModal").modal("hide");
							// 刷新分页数据
							getPageInfoList();
						}
					});
				}
				if (response.result == "FAILED") {
					// 角色名不可用
					layer.msg("角色名已存在！");
				}
			}
		});
	});

}

// 删除单条记录
function delSingle() {
	 $("#userPageBody").on("click",".removeBtn",function () {
         // 获取当前请求的id
         var id = this.id;
		 // 发送ajax请求获取要执行操作的role对象
		 $.ajax({
			 "url" : "get/role/by/id/" + id + ".json",
			 "type" : "post",
			 "dataType" : "json",
			 "error" : function (response) {
			 	layer.msg(response.responseJSON.message);
			 },
			 "success" : function (response) {
			 	 var del = confirm("确定要删除【" + response.responseData.name + "】吗？");
			 	 if(del){
					 // 删除role对象
					 $.ajax({
						 "url" : "del/role/by/id/" + id + ".json",
						 "type" : "post",
						 "dataType" : "json",
						 "error" : function (response) {
							 layer.msg(response.responseJSON.message);
						 },
						 "success" : function (response) {
							 layer.msg("删除成功！");
							 // 刷新分页
							 getPageInfoList();
						 }
					 });
				 }
			 }
		 });
	 });
}

function modify(){
	// 给修改图标绑定单击事件
	$("#userPageBody").on("click",".editBtn",function () {
		// 显示模态框
		$("#roleEditModal").modal("show");
		// 发送ajax请求获取要执行操作的role对象
		var id = this.id;
		
		$.ajax({
			"url" : "get/role/by/id/" + id + ".json",
			"type" : "post",
			"dataType" : "json",
			"error" : function (response) {
				layer.msg(response.responseJSON.message);
			},
			"success" : function (response) {
				var role = response.responseData;
				// 回显数据
				$("#rolenameEdit").val(role.name);
				$("#roleEditBtn").click(function () {
					var name = $.trim($("#rolenameEdit").val());
					if (name == role.name) {
						layer.msg("角色名已存在！");
					} else if (name == null || name == ""){
						layer.msg("请输入要修改的信息！");
					}  if (name != role.name) {
						// 检查角色是否存在
						$.ajax({
							"url" : "get/role/by/name/" + name + ".json",
							"type" : "post",
							"dataType" : "json",
							"error" : function (response) {
								layer.msg(response.responseJSON.message);
							},
							"success" : function (response) {
								if (response.result == "SUCCESS") {
									layer.msg("用户名可用！");
									$.ajax({
										"url" : "modify/role.json",
										"type" : "post",
										"data" : {
											"id" : id,
											"name" : name
										},
										"dataType" : "json",
										"error" : function (response) {
											layer.msg(response.responseJSON.message);
										},
										"success" : function (response) {
											layer.msg("更新成功！");
											// 关闭模态框
											$("#roleEditModal").modal("hide");
											// 刷新列表
											getPageInfoList();
										}
									});
								} else {
									layer.msg("用户名已存在！");
								}
							}
						});
					}
				});
	
			}
		});
	});
}

// 批量删除
function batchDelRole(){
	// 绑定按钮单击事件
	$("#role_del").click(function () {
		// 获取执行删除操作的复选框
		var chkCheck = $(".check_box:checked");
		if (chkCheck.length == 0) {
			layer.msg("请选择要删除的信息！");
			return;
		}
		// 创建一个数组用于存放删除信息的集合
		window.roleList = new Array();
		// 遍历被选中的复选框
		$(".check_box:checked").each(function () {
			var id = this.id;
			roleList.push(id);
		});
	});
}



