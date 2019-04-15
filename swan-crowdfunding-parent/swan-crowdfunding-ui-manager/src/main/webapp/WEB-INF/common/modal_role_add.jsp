<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="roleAddModal" tabindex="-1" role="dialog"
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
                        <label for="exampleInputPassword1">角色</label>
                        <input id="roleInput" type="text" class="form-control" placeholder="请输入登录账号">
                    </div>
                    <button type="button" id="roleAddBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <button type="reset" id="roleResetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                </form>
            </div>
        </div>
    </div>
</div>