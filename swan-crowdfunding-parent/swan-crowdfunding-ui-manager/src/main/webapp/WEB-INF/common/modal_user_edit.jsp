<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="userEditModal" tabindex="-1" role="dialog"
    aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">系统消息</h4>
            </div>
            <div class="modal-body">
               <form role="form">
                    <div class="form-group">
                        <label for="exampleInputPassword1">登录账号</label>
                        <input id="loginaccEdit" type="text" class="form-control" placeholder="请输入登录账号">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">用户名称</label>
                        <input id="usernameEdit" type="text" class="form-control" placeholder="请输入用户名称">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">邮箱地址</label>
                        <input id="emailEdit" type="email" class="form-control" placeholder="请输入邮箱地址">
                        <!-- <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p> -->
                    </div>
                    <button type="button" id="userEditBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 更新</button>
                    <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                </form>
            </div>
        </div>
    </div>
</div>