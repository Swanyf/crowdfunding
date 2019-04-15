<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!-- 导入Pagination插件所需要的样式文件 -->
<link rel="stylesheet" href="css/pagination.css" />

<!-- 导入Pagination插件所需要的库文件 -->
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>

<!-- 避免该页的代码量过大，引入外部js文件 -->
<script type="text/javascript" src="function/function_user.js"></script>
<script type="text/javascript">
	$(function() {
		// 初始化pageNum(当前页数),keyword(查询关键字),便于后面的多操作，为了保持当前页码和查询的关键字不变，都使用这俩个变量
		window.pageNum = 1;
		window.keyword = "";

		// 调用函数实现ajax分页
		generateUserPage();

		// 模糊查询
		// 给单击的查询按钮绑定单击事件		
		$("#searchBtn").click(function() {
			// 将输入框的值赋值给全局变量keyword
			window.keyword = $("#keywordInput").val();
			// 获取分页后的数据
			generateUserPage();
		});

		// 全选/全不选
		$("#summaryBox").click(function() {
			var checkAll = this.checked;
			// 给所有的复选框设置选中属性：checked,动态修改属性
			$(".check_box").prop("checked", checkAll);
		});

		// 给删除按钮绑定事件 
		$("#batchRemoveBtn").click(function() {
			// 判断是否有复选框被选中
			var checkAllBox = $(".check_box:checked");
			if (checkAllBox.length == 0) {
				layer.msg("请选择要删除的信息！");
				return;
			}

			// 获取所有要执行删除操作的userId，存放到数组中
			window.userIdList = new Array();

			// 遍历选中的复选框
			$(".check_box:checked").each(function() {
				// this表示遍历得到的每一个user对象
				// 将得到的id存放到数组中
				userIdList.push(this.id);
			});

			// 调用函数显示提示信息的模态框
			showUserBatchRemoveModal(userIdList);
		});

		/* 单行删除操作
		   .remove_btn是在#userPageBody的范围内定位要绑定事件的元素
		   click  要绑定的事件属性
		 */
		$("#userPageBody").on("click", ".remove_btn", function() {
			window.userIdList = new Array();
			// 获取当前要删除的条目
			var userId = this.id;
			// 存放到userIdList中
			window.userIdList.push(userId);
			// 执行删除操作
			showUserBatchRemoveModal(userIdList);
		});

		// 绑定新增的按钮事件跳转到模态框
		$("#showAddModal").click(function() {
			$("#userAddModal").modal("show");
		});

		/*
		         需要检查用户是否存在才能执行后续的操作
		         给新增按钮绑定事件
		 */
		$("#userAddBtn").click(function() {
			// 获取输入框中账号的名称
			var loginAccount = $
					.trim($("#loginaccInput").val());
	
			// 检测账号是否正确
			if (loginAccount == "" || loginAccount == null) {
				layer.msg("输入的账号名称有误，请重试！");
				return;
			}

			//发送ajax请求查询账号名称是否存在
			$.ajax({
				"url" : "get/loginAccount.json",
				"type" : "post",
				"data" : {
					"loginacct" : loginAccount,
	                "random":Math.random()
				},
				"dataType" : "json",
				"contentType" : "application/x-www-form-urlencoded",
				"success" : function(responseData) {
					// 获取响应的数据
					var result = responseData.result;
					// 用户名可用
					if (result == "SUCCESS") {
						var pwd = $.trim($("#userpswdInput").val());
						if (pwd == null || pwd == "") {
							layer.msg("请填写密码！");
							return;
						}

						//检查确认密码
						var repwd = $.trim($("#pswdConfirmInput").val());
						if (repwd == null || repwd == "") {
							layer.msg("请填写确认密码！");
							return;
						}

						if (pwd != repwd) {
							layer.msg("密码和确认密码不一致！");
							return;
						}

						//检查用户名
						var username = $.trim($("#usernameInput").val());
						if (username == null|| username == "") {
							layer.msg("请填写用户名！");
							return;
						}
			
						//检查Email
						var email = $.trim($(
								"#emailInput").val());
						if (email == null
								|| email == "") {
							layer.msg("请填写Email地址！");
							return;
						}

						// 上述条件符合执行新增用户的操作

						// 发送新增的ajax请求
						$.ajax({
							"url" : "add/user.json",
							"type" : "post",
							"data" : {
								"loginacct" : loginAccount,
								"userpswd" : pwd,
								"username" : username,
								"email" : email
							},
							"contentType" : "application/x-www-form-urlencoded",
							"dataType" : "json",
							"success" : function(responseData) {
								var result = responseData.result;
								if (result == "SUCCESS") {
									layer.msg("注册成功");
									// 刷新分页的数据
									generateUserPage();
									// 点击取消按钮关闭模态框
									$("#userAddModal").modal("hide");
								} else {
									layer.msg("注册失败");
								}
							},
							"error" : function(responseData) {
								var message = responseData.responseJSON.message;
								layer.msg(message);
							}
						});
					}
				},
				"error" : function(responseData) {
				    var message = responseData.responseJSON.message;
					layer.msg(message);
				}
			});
		});

		// 使用动态绑定事件
		$("#userPageBody").on("click", ".edit_btn", function() {

			// 显示编辑的模态框
			$("#userEditModal").modal("show");

			// 回显数据信息,先获取当前的user对象的id
			var userId = this.id;

			// 根据userId获取信息显示到模态框中
			getUserById(userId);
		});

		// 给编辑模态框的更新按钮绑定事件
		$("#userEditBtn").click(function() {

			// 获取表单用户名称的值
			var loginAccount = $("#loginaccEdit").val();
			// 获取回显的用户名称
			var old_loginAccount = window.user.loginacct;

			// 比较二者是否相等
			if (loginAccount == old_loginAccount) {
				// 相等就直接执行更新操作
				editUser();
			}
			if (loginAccount != old_loginAccount) {
				// 如果不相等，发送ajax请求检查输入后的用户名称是否存在
				$.ajax({
					"url" : "get/loginAccount.json",
					"type" : "post",
					"data" : {
						"loginacct" : loginAccount,
						"radom" : Math.random()
					},
					"contentType" : "application/x-www-form-urlencoded",
					"dataType" : "json",
					"success" : function(responseMsg) {
						var result = responseMsg.result;
						if (result == "SUCCESS") {
							// 执行更新操作
							editUser();
						}
						if (result == "FAILED") {
							layer.msg("账号名称已存在la！");
						}
					},
					"error" : function(responseData) {
						var message = responseData.responseJSON.message;
						layer.msg(message);
					}
				});
			}
		});
	});
</script>
</head>

<body>
	<%@include file="/WEB-INF/common/navigator.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/WEB-INF/common/sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="keywordInput" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button id="searchBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button id="showAddModal" type="button" class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryBox" type="checkbox">
										</th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<!-- 动态标签的容器 -->
								<tbody id="userPageBody"></tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination"></div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/common/model_user_remove.jsp"%>
	<%@ include file="/WEB-INF/common/modal_user_add.jsp"%>
	<%@ include file="/WEB-INF/common/modal_user_edit.jsp"%>
</body>
</html>