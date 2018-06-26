$(function(){
	
	$("#title").append("模型列表");
	
	getAllModel(1);
	
	function getAllModel(pageNo)
	{
		$.ajax({
			url : path + '/activiti/modelList',
			type : 'POST',
			data : {"pageNo" : pageNo},
			success : function(data){
				if(data.list.length == 0){
					$("#home table tbody").empty();
					var tr = $("<tr></tr>").append($("<td  colspan='8'></td>").append("<h4>暂时没有模型数据</h4>").css("text-align","center"));
					$("#home table tbody").append(tr);
				}else{
					build_activiti_table(data);
					build_activiti_pageInfo(data);
					build_activiti_pagenav(data);
				}
			}
		});
	}
		
	//构建表格
	function build_activiti_table(result)
	{
		$("#home table tbody").empty();
		var activitis = result.list;
		$.each(activitis, function(index, item){
			var id = $("<td></td>").append(item.id);
			var name = $("<td></td>").append(item.name);
			var key = $("<td></td>").append(item.key);
			var desc = $("<td></td>").append(item.description);			
			var cdate = $("<td></td>").append(item.createTime);
			var ldate = $("<td></td>").append(item.lastUpdateTime);
			var version = $("<td></td>").append(item.version);
			var btnEdit = $("<a></a>").attr("href","javascript:void(0)").css("color","#317EEB").append("编辑&nbsp;&nbsp;&nbsp;&nbsp;");
			var span1 = $("<span></span>").css("color","silver").append("|");
			var btndel = $("<a></a>").attr("href","javascript:void(0)").css("color","#317EEB").append("&nbsp;&nbsp;撤销&nbsp;&nbsp;&nbsp;&nbsp;");
			var span2 = $("<span></span>").css("color","silver").append("|");
			var btnsel = $("<a></a>").attr("href","javascript:void(0)").css("color","#317EEB").append("&nbsp;&nbsp;部署");
			var btn = $("<td></td>").append(btnEdit).append(span1).append(btndel).append(span2).append(btnsel);
			$("<tr></tr>").append(id).append(name).append(key).append(desc).append(cdate).append(ldate).append(version).append(btn).appendTo("#home table tbody");
		});
	}
	
	var currentPageNo;
	
	//构建分页信息
	function build_activiti_pageInfo(result)
	{
		$("#pageInfo").empty();
		$("#pageInfo").append("当前第" + result.pageNum + "页,总共" + result.pages + "页,总共" + result.total + "记录");
		currentPageNo = result.pageNum;
	}
	
	//构建分页条
	function build_activiti_pagenav(result)
	{
		$("#pageNav").empty();
		var ul = $("<ul></ul>").addClass("pagination fy");
		var firstLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","javascript:void(0)"));
		var preLi=$("<li></li>").append($("<a></a>").append("&laquo;").attr("href","javascript:void(0)"));
		ul.append(firstLi).append(preLi);
		if(result.pageNum == 1){
			firstLi.addClass("disabled");
			preLi.addClass("disabled");
		}else{
			firstLi.click(function(){
				getAllModel(1);
			});
			preLi.click(function(){
				getAllModel(result.pageNum - 1);
			});
		}
		var nextLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","javascript:void(0)"));
		var lastLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","javascript:void(0)"));
		if(result.pageNum == result.pages){
			nextLi.addClass("disabled");
			lastLi.addClass("disabled");
		}else{
			nextLi.click(function(){
				getAllModel(result.pageNum + 1);
			});
			lastLi.click(function(){
				getAllModel(result.pages);
			});
		}
		$.each(result.pageNums,function(index,item){
			var numLi=$("<li></li>").append($("<a></a>").append(item).attr("href","javascript:void(0)"));
			if(result.pageNum==item)
			{
				numLi.addClass("active");
			}
			numLi.click(function(){
				getAllModel(item);
			});
			ul.append(numLi);
		});
		ul.append(nextLi).append(lastLi);
		$("<nav></nav>").attr("aria-label","Page navigation").append(ul).appendTo("#pageNav");
	}
	
	$(document).on('click', '#home table tbody td a', function(){
		var id = $(this).parent().parent().find("td:eq(0)").text();
		if($(this).text().trim() == "编辑"){
			window.location.href = path + "/modeler.html?modelId=" + id;
		}else if($(this).text().trim() == "撤销"){
			$.ajax({
				url : path + '/activiti/deleteModel',
				type : 'POST',
				data : {"id" : id},
				success : function(data){
					getAllModel(currentPageNo);
				}
			});
		}else if($(this).text().trim() == "部署"){
			$.ajax({
				url : path + '/activiti/deployModel',
				type : 'POST',
				data : {"id" : id},
				success : function(data){
					if(data == true){
						window.location.href = path + "/views/process.jsp";
					}
				}
			});
		}
	});
	
	$(".vertical-nav a[href='/JMOA/javascrip:void(0)']").attr("data-toggle","modal").attr("data-target","#myModal");
	
	$("#ok").click(function(){
		var name = $("#name").val()
		var key = $("#key").val()
		var desc = $("#desc").val()
		$.ajax({
			url : path + '/activiti/create',
			type : 'POST',
			data : {"name":name, "key":key, "description":desc},
			success : function(data){
				if(data != "false"){
					window.location.href = path + "/modeler.html?modelId=" + data;
				}
			}
		});
		$(this).attr("data-dismiss", "modal");
	});
});