<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/common/common.jsp"%>
<link rel="stylesheet" href="ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="function/function_tree.js"></script>
<script type="text/javascript" src="function/function_tree_crud.js"> </script>
<script type="text/javascript">

    $(function(){
    	showTree();
    	add();
    	edit();
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
                        <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 
                        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div>
                    </div>
                    <div class="panel-body">
                        <!-- 附加树形结构的具体的标签 -->
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%@include file="/WEB-INF/common/modal_permission_add.jsp" %>
    <%@include file="/WEB-INF/common/modal_permission_edit.jsp" %>
    
</body>
</html>