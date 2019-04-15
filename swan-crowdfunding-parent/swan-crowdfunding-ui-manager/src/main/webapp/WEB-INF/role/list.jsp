<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/common/common.jsp"%>
<!-- 导入Pagination插件所需要的样式文件 -->
<link rel="stylesheet" href="css/pagination.css" />

<!-- 导入Pagination插件所需要的库文件 -->
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>

<script type="text/javascript" src="function/function_role.js"></script>
<script type="text/javascript" src="function/function_role_crud.js"></script>
<script type="text/javascript">
    $(function () {
    	
    		// 获取输入框的值
    		//window.keyword = $("#inputValue").val();
    		
    		// 显示分页数据和分页条
    		getPageInfoList();
    		
    		// 模糊查询
    		//getRoleListByKeyword();
    		// 给查询按钮绑定单击事件
    	    $("#selectByKeyword").click(function() {
    	       window.keyword = $("#inputValue").val(); 
    	        layer.msg(window.keyword);
    	        // 刷新分页数据
    	        getPageInfoList();
    	    });
    		
    		addRole();
            delSingle();
    		modify();
    		
    		batchDelRole();
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
									<input id="inputValue" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button id="selectByKeyword" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;" id="role_del">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" id="role_add">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
							     <thead>
                                    <tr>
                                        <th width="30">#</th>
                                        <th width="30"><input id="summaryBox" type="checkbox">
                                        </th>
                                        <th>角色</th>
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
								<%-- <!-- 判断数据是否为空 -->
								<c:if test="${ empty requestScope.roleList }">
									<tr>
										<td>没有查询到数据！</td>
									</tr>
								</c:if>
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox"></th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${ requestScope.roleList }" var="role">
									<tr>
                                        <td>${ role.id }</td>
                                        <td><input type="checkbox"></td>
                                        <td>${ role.name }</td>
                                        <td> 
                                            <a href="assign/to/permission/page/${ role.id }.html" class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></a>
                                            <button type='button' class='btn btn-primary btn-xs edit_btn'><i class=' glyphicon glyphicon-pencil'></i></button>
                                            <button type='button' class='btn btn-danger btn-xs remove_btn'><i class=' glyphicon glyphicon-remove'></i></button>
                                        </td>
                                    </tr>
								</c:forEach>
								</tbody> --%>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/modal_role_add.jsp" %>
	<%@ include file="/WEB-INF/common/modal_role_edit.jsp" %>
	<%@ include file="/WEB-INF/common/model_role_remove.jsp" %>
</body>
</html>