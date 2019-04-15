/**
 * 显示树形图结构
 */
function showTree() {
	
	// 发送ajax请求
	$.ajax({
		"url" : "get/tree/list.json",
		"type" : "post",
		"dataType" : "json",
		"success" : function(msg) {
			// setting中用于封装最zTree树形结构进行设置的属性
			var setting = {
				"view" : {
					"addDiyDom" : addDiyDomIcon,
					"addHoverDom" : addHoverDom,
					"removeHoverDom" : removeHoverDom
				},
				"data" : {
					"key" : {
						//这里设置一个在Permission对象中不存在的属性就能够在点击节点时不跳转
						"url": "notExists"
					}
				}
			};

			// zNode存放的是属性结构
			var zNodes = msg.responseData;

			// 初始化树形图结构
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		"error" : function(msg) {
			layer(msg.responseJSON.message);
		}
	});
	
}

/**
 * 更改树形图的小图标控件
 */
function addDiyDomIcon(treeId, treeNode) {
	
	/*
	 * treeNode	
	 *     JSON 需要显示自定义控件的节点 JSON 数据对象, 
	 * treeId
	 *	   String 对应 zTree 的treeId，便于用户操控
	 */
	if (treeNode.icon) {

		// treeNode.tId：整个节点的li标签的id
		// treeNode.tId + "_ico"：节点的图标的id
		// "#" + treeNode.tId + "_ico"：定位当前节点的图标对象的jQuery选择器
		// 根据前面拼装得到的jQuery选择器找到对应的图标对象
		var $icoObj = $("#" + treeNode.tId + "_ico");

		// 将图标对象上原有的class值移除，也就是移除zTree默认图标
		// 添加自定义图标的数据作为新的class值，实现替换
		$icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon)
				.css("background", "url(glyphicon glyphicon-list-alt)");
	}
	
}

/**
 * 鼠标移入节点时，添加按钮
 */ 
function addHoverDom(treeId, treeNode) {

	// 获取当前节点的id
	var nodeId = treeNode.tId;
	// layer.msg(nodeId);
	
	// 设计span的标签id
	var spanId = nodeId + "_auto_btn_group";	
	// layer.msg(spanId);
	
	//尝试根据spanId获取对应的jQuery对象
	var $span = $("#"+spanId);
	
	//如果$span对象存在，则不执行追加
	//jQuery对象都是DOM对象的数组，可以根据长度进行判断是否存在
	if($span.length > 0) {
		return ;
	}
	
	//判断当前节点是否已经追加了按钮，如果已经追加了按钮，那么就不追加
	
	// 在节点id的后面追加 _a 得到超链接的id
	var anchorId = nodeId + "_a";
	//通过jQuery选择器获取当前节点的超链接对象
	var $anchorObj = $("#" + anchorId);
	
	//获取当前节点在数据库中的id值
	var permissionId = treeNode.id;
	
	//声明具体按钮的HTML标签字符串
	var addBtnHTML = "<a id='"+permissionId+"' onclick='showAddModal(this)' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'><i class='fa fa-fw fa-plus rbg'></i></a>";
	var deleteBtnHTML = "<a id='"+permissionId+"' onclick='showDelModal(this)' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'><i class='fa fa-fw fa-times rbg'></i></a>";
	var editBtnHTML = "<a id='"+permissionId+"' onclick='showEditModal(this)' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'><i class='fa fa-fw fa-edit rbg '></i></a>";

	//声明span的HTML标签，直接封装为jQuery对象
	var $span = $("<span id='"+spanId+"'></span>");
	
	//获取当前节点的级别数据
	var level = treeNode.level;
	
	//根节点只能增加子节点
	if(level == 0) {
		$span.append(addBtnHTML);
	}
	
	//分支节点等级
	if(level == 1) {
		// 添加和修改
		$span.append(addBtnHTML).append(editBtnHTML);
		
		var children = treeNode.children;
		
		//仅在没有子节点时再追加删除按钮
		if(children.length == 0) {
			$span.append(deleteBtnHTML);
		}
	}
	
	if(level == 2) {
		// 删除和修改
		$span.append(deleteBtnHTML).append(editBtnHTML);
	}
	
	//在$anchorObj的后面追加新的按钮
	$anchorObj.after($span);
	
}

// 鼠标移出节点时，移除(DOM)后面的超链接
function removeHoverDom (treeId, treeNode) {
	
	// 获取当前节点的id
	var nodeId = treeNode.tId;
	
	//拼按钮组span标签的id
	var spanId = nodeId + "_auto_btn_group";
	
	// 移除
	$("#"+spanId).remove();
	
}

/**
 * 显示添加模态框 
 */
function showAddModal (anchor) {
	// 显示添加图标的模态框
	$("#promissionAddModal").modal("show");
	
	// 获取当前节点在数据库 中的id值
	window.permissionId = anchor.id;
}
	
/**
 * 删除 
 */
function showDelModal(anchor){
	
	// 发送ajax请求
	$.ajax({
		"url" : "get/permission/by/" + anchor.id + ".json",
		"type" : "post",
		"dataType" : "json",
		"contentType" : "application/x-www-form-urlencoded",
		"success" : function (response) {
			if (response.result == "SUCCESS") {
				
				var del = confirm("确定要删除节点【" + response.responseData.name + "】吗");
				
				if(del == true){
					$.ajax({
						"url" : "del/permission/by/" + anchor.id + ".json",
						"type" : "post",
						"dataType" : "json",
						"success" : function (response) {
							if (response.result == "SUCCESS") {
								layer.msg("删除成功");
								// 刷新
								showTree();
							}
						},
						"error" : function (response) {
							layer.msg(response.responseJSON.message);
						}
					});
				}
			}
		},
		"error" : function (response) {
			layer.msg(response.responseJSON.message);
		}
	});

}

/**
 * 修改 
 */
function showEditModal(anthor) {
	window.permissionId = anthor.id;
	
	// 发送ajax请求回显数据
	$.ajax({
		// anthor.id获取当前节点在数据库中的值
		"url" : "get/permission/by/" + window.permissionId + ".json",
		"type" : "post",
		"dataType" : "json",
		"success" : function (response) {
			// 接收响应的数据
			var data = response.responseData;
			// 将接收到的数据回显到表单上
			$("#nameEditInput").val(data.name);
			layer.msg(data.name);
			//layer.msg(data.icon);
			$("#permissionEditIconDiv :radio[value='"+ data.icon +"']").attr("checked",true);
			$("#urlEditInput").val(data.url);
			
			// 显示模态框
			$("#permissionEditModal").modal("show");
		},
		"error" : function (response) {
			layer.msg(response.responseJSON.message);
		}
	});
	
}
