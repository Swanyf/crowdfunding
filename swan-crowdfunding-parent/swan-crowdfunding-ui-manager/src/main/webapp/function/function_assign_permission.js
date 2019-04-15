/**
 * 初始化树形图结构
 */
function initPermissionTree(roleId) {

	// 发送ajax请求
	$.ajax({
		"url" : "assign/permission.json",
		"type" : "post",
		"data" : {
			"roleId" : roleId
		},
 		"dataType" : "json",
		"success" : function(response) {
			// setting 中用于封装zTree属性结构的属性设置
			var setting = {
				"view" : {
					"addDiyDom" : addDiyDom
				},
				"data" : {
					"key" : {
						"url" : "notExist"
					}
				},
				"check":{
            		"enable":true
            	}
			};

			// zNode存放的是permission属性数据
			var zNodes = response.responseData;

			// 初始化树形图结构
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		},
		"error":function(response) {
			layer.msg(response.responseJSON.message);
		}
	});

}

function addDiyDom(treeId,treeNode){
	//treeNode.icon为null：false
	//treeNode.icon非null：true
	//treeNode.icon数据：从服务器端获取到的节点数据中的icon部分，也就是Permission对象的icon部分
	if ( treeNode.icon ) {
		
		//treeNode.tId：整个节点的li标签的id
		//treeNode.tId + "_ico"：节点的图标的id
		//"#" + treeNode.tId + "_ico"：定位当前节点的图标对象的jQuery选择器
		//根据前面拼装得到的jQuery选择器找到对应的图标对象
		var $icoObj = $("#" + treeNode.tId + "_ico");
		
		//将图标对象上原有的class值移除，也就是移除zTree默认图标
		//添加自定义图标的数据作为新的class值，实现替换
		$icoObj
			.removeClass("button ico_docu ico_open")
			.addClass(treeNode.icon)
			.css("background","");
	}
}
