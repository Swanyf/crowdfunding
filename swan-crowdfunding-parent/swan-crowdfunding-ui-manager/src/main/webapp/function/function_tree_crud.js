/**
 * 给新增按钮绑定单击事件
 */
function add() {

	$("#permissionAddBtn").click(function() {
		var name = $("#nameInput").val();
		var icon = $("#permissionAddIconDiv [name='icon']:checked").val();
		var url = $("#urlInput").val();

		// 发送ajax请求
		$.ajax({
			"url" : "add/permission.json",
			"type" : "post",
			"contentType" : "application/x-www-form-urlencoded",
			"dataType" : "json",
			"data" : {
				"pid" : window.permissionId,	// 从全局变量中获取当前操作的节点id(也就是新创建的pid)
				"name" : name,
				"icon" : icon,
				"url" : url,
				"random" : Math.random()
			},
			"success" : function(responseData) {
				var result = responseData.result;
				if (result == "SUCCESS") {                                 
					layer.msg("添加成功");                                 
					// 重置                                                
					$("#permissionResetBtn").click();                      
					// 关闭模态框                                          
					$("#promissionAddModal").modal("hide");                
					// 刷新数据                                            
					showTree();                                            
				}                                                          
			},                                                             
			"error" : function(responseData) {                             
				layer.msg(responseData.responseJSON.message);              
			}                                                              
		});                                                                
	});                                                                    
                                                                           
}                                                                          
                                                                           
/**                                                                        
 * 给修改按钮绑定单击事件                                                  
 */
function edit() {

	 //模态框中修改按钮的单击响应函数
    $("#permissionEditBtn").click(function(){
    	
    	//收集表单项数据
    	var name = $("#nameEditInput").val();
    	
    	//在更新的表单范围内查找被选中的radio
    	var icon = $("#permissionEditIconDiv :radio:checked").val();
    	var url = $("#urlEditInput").val();
    	
    	//从全局变量获取permissionId
    	var permissionId = window.permissionId;
    	alert(permissionId);
    	//发送Ajax请求
    	$.ajax({
    		"url":"edit/permission.json",
    		"type":"post",
    		"data":{
    			"id":permissionId,
    			"name":name,
    			"icon":icon,
    			"url":url,
    			"random":Math.random()
    		},
    		"dataType":"json",
    		"error":function(response){
    			layer.msg(response.responseJSON.message);
    		},
    		"success":function(response){
    			//关闭模态框
                $("#permissionEditModal").modal("hide");
                //重新加载树形结构
                showTree();
    		}
    	});
    	
    });

}
