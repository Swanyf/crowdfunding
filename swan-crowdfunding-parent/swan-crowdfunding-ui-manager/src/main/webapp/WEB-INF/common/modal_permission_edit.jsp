<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="permissionEditModal" tabindex="-1" role="dialog"
    aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">尚筹网系统消息</h4>
            </div>
            <div class="modal-body">
               <form role="form">
                    <div class="form-group">
                        <label for="nameEditInput">节点名称</label>
                        <input id="nameEditInput" type="text" class="form-control" placeholder="请输入节点名称">
                    </div>
                    <div id="permissionEditIconDiv">
                        <i class="glyphicon glyphicon-th-list"></i>         <input type="radio" name="icon" value="glyphicon glyphicon-th-list"/>
                        <i class="glyphicon glyphicon-dashboard"></i>       <input type="radio" name="icon" value="glyphicon glyphicon-dashboard"/>
                        <i class="glyphicon glyphicon glyphicon-tasks"></i> <input type="radio" name="icon" value="glyphicon glyphicon glyphicon-tasks"/><br/>
                        <i class="glyphicon glyphicon-user"></i>            <input type="radio" name="icon" value="glyphicon glyphicon-user"/>
                        <i class="glyphicon glyphicon-king"></i>            <input type="radio" name="icon" value="glyphicon glyphicon-king"/>
                        <i class="glyphicon glyphicon-lock"></i>            <input type="radio" name="icon" value="glyphicon glyphicon-lock"/><br/>
                        <i class="glyphicon glyphicon-ok"></i>              <input type="radio" name="icon" value="glyphicon glyphicon-ok"/>
                        <i class="glyphicon glyphicon-check"></i>           <input type="radio" name="icon" value="glyphicon glyphicon-check"/>
                        <i class="glyphicon glyphicon-th-large"></i>        <input type="radio" name="icon" value="glyphicon glyphicon-th-large"/><br/>
                        <i class="glyphicon glyphicon-picture"></i>         <input type="radio" name="icon" value="glyphicon glyphicon-picture"/>
                        <i class="glyphicon glyphicon-equalizer"></i>       <input type="radio" name="icon" value="glyphicon glyphicon-equalizer"/>
                        <i class="glyphicon glyphicon-random"></i>          <input type="radio" name="icon" value="glyphicon glyphicon-random"/><br/>
                        <i class="glyphicon glyphicon-hdd"></i>             <input type="radio" name="icon" value="glyphicon glyphicon-hdd"/>
                        <i class="glyphicon glyphicon-comment"></i>         <input type="radio" name="icon" value="glyphicon glyphicon-comment"/>
                        <i class="glyphicon glyphicon-list"></i>            <input type="radio" name="icon" value="glyphicon glyphicon-list"/><br/>
                        <i class="glyphicon glyphicon-tags"></i>            <input type="radio" name="icon" value="glyphicon glyphicon-tags"/>
                        <i class="glyphicon glyphicon-list-alt"></i>        <input type="radio" name="icon" value="glyphicon glyphicon-list-alt"/>
                    </div>
                    <div>
                        <label for="urlEditInput">节点地址</label>
                        <input id="urlEditInput" type="text" class="form-control" placeholder="请输入节点地址">
                    </div>
                    <button type="button" id="permissionEditBtn" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
                </form>
            </div>
        </div>
    </div>
</div>