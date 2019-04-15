<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/common/common.jsp"%>
<script type="text/javascript" src="function/function_role.js"></script>
<script type="text/javascript">
    add();

</script>
</head>

<body>

    <%@include file="/WEB-INF/common/navigator.jsp"%>
    <div class="container-fluid">
        <div class="row">
            <%@include file="/WEB-INF/common/sidebar.jsp"%>
            <div
                class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">数据列表</a></li>
                    <li class="active">分配角色</li>
                </ol>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form action="assign/do/assign/role.html" method="post" role="form" class="form-inline">
                            <input type="hidden" name="userId" value="${requestScope.userId }" />
                            <div class="form-group">
                                <label for="exampleInputPassword1">未分配角色列表</label><br>
                                <select id="leftSelect" class="form-control" multiple size="10" style="width: 200px; overflow-y: auto;">
                                    <c:forEach items="${requestScope.unAssignList }" var="role">
                                        <option value="${role.id }">${role.name }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <ul>
                                    <li id="toRightLi"
                                        class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                    <br>
                                    <li id="toLeftLi"
                                        class="btn btn-default glyphicon glyphicon-chevron-left"
                                        style="margin-top: 20px;"></li>
                                </ul>
                            </div>
                            <div class="form-group"
                                style="margin-left: 40px;">
                                <label for="exampleInputPassword1">已分配角色列表</label><br>
                                <select name="assignRoleIdList" id="rightSelect" class="form-control" multiple
                                    size="10"
                                    style="width: 200px; overflow-y: auto;">
                                    <c:forEach items="${requestScope.assignList }" var="role">
                                        <option value="${role.id }">${role.name }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button style="width:100px;margin-left: 215px" id="submit_btn" type="submit" class="btn btn-lg btn-success btn-block">分配</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>