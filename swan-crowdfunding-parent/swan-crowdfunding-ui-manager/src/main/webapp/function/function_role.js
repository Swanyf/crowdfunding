function getPageInfoList() {
	// 发送ajax请求获取要分页的数据
	$.ajax({
		"url" : "get/role/list/page/info.json",
		"data" : {
			"pageNum" : window.pageNum,
			"keyword" : window.keyword
		},
		"type" : "post",
		"dataType" : "json",
		//"contentType" : "application/x-www-form-urlencoded",
		"error" : function (response) {
			layer.msg(response.responseJSON.message);
		},
		"success" : function (response) {
			// 获取响应的role数据
			var pageInfo = response.responseData;
			console.log(pageInfo);
			// 显示分页后的数据
		    showPageInfoList(pageInfo);
		    // 显示导航条
		    showNavigator(pageInfo);
		}
	});
}

//显示分页后的数据
function showPageInfoList(pageInfo){
	
	$("#userPageBody").empty();
	
    // 获取存放在pageInfo的list属性的数据
    var list = pageInfo.list;

    // 遍历list
    for (var i = 0; i < list.length; i++) {
        var role = list[i];
        var id = role.id;
        var name = role.name;
        var check_btn = "<a href='assign/role/" + id +".html' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></a>";
        var edit_btn = "<button id='"
                + id
                + "' type='button' class='btn btn-primary btn-xs editBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var del_btn = "<button id='"
                + id
                + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
        // 设置要显示的数据信息
        var show_msg = "<tr><td>" + (i + 1) + "</td><td><input id='" + id
                + "' class='check_box' type='checkBox'/></td><td>" + name + "</td><td>"
                + check_btn + " " + edit_btn + " " + del_btn + "</td></tr>";
        // 添加要显示信息
        $("#userPageBody").append(show_msg);
	}    		    
}

// 显示导航条
function showNavigator(pageInfo){
	// 获取pageInfo的total(总记录数)
	var page_total = pageInfo.total;
	$("#Pagination").pagination(page_total, {
        prev_text : "上一页", // 显示按钮的文字
        next_text : "下一页",
        items_per_page : pageInfo.pageSize, // 每页显示几条数据
        current_page : (pageInfo.pageNum)-1, // 当前选中的页数
        //num_edge_entries : 0, // 两侧显示的首尾分页的条目数
       // num_display_entries : 1, // 连续分页主体部分显示的分页条目数
        callback : showCheckedPage // 点击页数所显示的数据
    });
}

// 点击页数所显示的数据
function showCheckedPage(page_index, jq) {
    
    /*
     * page_index 表示当前的页码 page_index + 1 表示要前往页面的页码
     */
    window.pageNum = page_index + 1;
    // 调用分页数据的函数
    getPageInfoList();
    return false;
}

function add() {
	$(function() {
		// 将左边选中元素添加到右边
		$("#toRightLi").click(function() {
			$("#leftSelect>:selected").appendTo("#rightSelect");
		});

		// 将右边选中元素添加到左边
		$("#toLeftLi").click(function() {
			$("#rightSelect>:selected").appendTo("#leftSelect");
		});
		
		// 设置点击分配按钮时，将右边元素全部选中
		$("#submit_btn").click(function() {
			$("#rightSelect>option").each(function() {
				this.selected = "selected";
			});
		});
	});
}