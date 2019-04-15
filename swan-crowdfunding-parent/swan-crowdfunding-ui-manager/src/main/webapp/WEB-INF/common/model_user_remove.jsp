<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="userRemoveModal" tabindex="-1" role="dialog"
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
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>账号</th>
                            <th>昵称</th> 
                            <th>邮箱</th>
                        </tr>
                    </thead>
                    <tbody id="userRemoveModalBody"></tbody>
                </table>
            </div>
            <div class="modal-footer">
                <script type="text/javascript">
                
                    $(function(){
                        $("#userRemoveModalOKBtn").click(function(){
                            
                        	batchRemoveUserByIdList(window.userIdList);
                            
                        });
                    });
                    
                </script>
                <button id="userRemoveModalOKBtn" type="button" class="btn btn-primary">确认</button>
                
                <script type="text/javascript">
                    $(function(){
                        $("#userRemoveModalCancelBtn").click(function(){
                            $("#userRemoveModal").modal("hide");
                        });
                    });
                </script>
                <button id="userRemoveModalCancelBtn" type="button" class="btn btn-default">取消</button>
            </div>
        </div>
    </div>
</div>