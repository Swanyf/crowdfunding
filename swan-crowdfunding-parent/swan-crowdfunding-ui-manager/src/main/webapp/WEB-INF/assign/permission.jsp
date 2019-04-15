<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/common/common.jsp"%>

<!-- 加入zTree树形图 -->
<link rel="stylesheet" href="ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="function/function_assign_permission.js"></script>

<script type="text/javascript">
    $(function() {
    	// 获取controller方法中传递的roleId
		var roleId = ${requestScope.roleId};
		// 调用显示树形图的方法
		initPermissionTree(roleId);
		
		// 给按钮绑定单击事件
		$("button").click(function (){
			

		    //创建数组存储将来要发送给服务器的许可的id
		    var permissionIdArray = new Array();

		    //获取zTree对象
		    var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
		    
		    //根据zTree对象获取当前树形结构中被选中的节点
		    var nodes = zTreeObj.getCheckedNodes(true);
		    console.log(nodes);
		    //遍历nodes
		    for(var i = 0; i < nodes.length; i++) {
		        var node = nodes[i];
		        
		        //当前节点在数据库中的id值
		        var id = node.id;
		        
		        permissionIdArray.push(id);
		    }
		    
		    //将roleId和permissionIdArray封装到一起
		    
		    //为了让handler方法接收数据时可以使用相同的数据类型，所以roleId虽然只有一个值也放在数组中
		    var roleIdList = new Array();
		    roleIdList.push(roleId);
		    
		    var roleIdAndPermissionIdArray = {
		        "roleId":roleIdList,
		        "permissionId":permissionIdArray
		    };
		    
		    // 转换成json字符串
		    var jsonStr = JSON.stringify(roleIdAndPermissionIdArray);
		    
		    //发送Ajax请求
		    $.ajax({
		        "url":"do/assign/permission.json",
		        "type":"post",
		        "contentType":"application/json;charset=UTF-8",
		        "data":jsonStr,
		        "dataType":"json",
		        "error":function(response){
		            layer.msg(response.responseJSON.message);
		        },
		        "success":function(response){
		        	if(response.result == "SUCCESS"){
			            layer.msg("操作成功！");
		        	}
		        }
		    });
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
						<i class="glyphicon glyphicon-th-list"></i> 权限分配列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<button class="btn btn-success">分配许可</button>
						<br /> <br />
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>