$(document).ready(function(){
	
	$("#title").append("流程定义列表");
	
	getAllProcess(1, null);
	
	function getAllProcess(pageNo, name)
	{
		$.ajax({
			url : path + '/activiti/processList',
			type : 'POST',
			data : {"pageNo" : pageNo, "name" : name},
			success : function(data){
				if(data.list.length == 0){
					$("#home table tbody").empty();
					var tr = $("<tr></tr>").append($("<td  colspan='8'></td>").append("<h4>暂时没有模型数据</h4>").css("text-align","center"));
					$("#home table tbody").append(tr);
				}else{
					build_activiti_table(data);
					build_activiti_pageInfo(data);
					build_activiti_pagenav(data);
					build_select(data);
					startTip();
				}
			}
		});
	}
	
	var procName = "";
	
	function build_select(data)
	{
		$("#procName").empty();
		$("#procName").append("<option value='0'>请选择流程名称.....</option>")
		$.ajax({
			url : path + '/activiti/getProcdefName',
			type : 'POST',
			success : function(data){
				for(var i = 0; i < data.length; i++){
					if(data[i] == procName){
						$("#procName").append("<option value=" + data[i] + " selected = 'selected'>" + data[i] + "</option>")
					}else{
						$("#procName").append("<option value=" + data[i] + ">" + data[i] + "</option>")
					}
				}
			}
		});
	}
	
	$("#search").click(function(){
		procName = $("#procName").val();
		if(procName == 0){
			getAllProcess(1);
		}else{
			getAllProcess(1, $("#procName").val());
		}
	});
	
	//构建表格
	function build_activiti_table(result)
	{
		$("#home table tbody").empty();
		var processs = result.list;
		$.each(processs, function(index, item){
			var id = $("<td></td>").append(item.id);
			var name = $("<td></td>").append(item.name);
			var key = $("<td></td>").append(item.key);
			var desc = $("<td></td>").append(item.version);			
			var cdate = $("<td></td>").append(item.deploymentId);
			var ldate = $("<td></td>").append(item.resourceName);
			var img = $("<img src='" + path + "/activiti/images/" + item.diagramResourceName + "' width='160px' height='90px' style='background-color:#EAEDF2;'/>")
			var version = $("<td></td>").append(img);
			var btndel = $("<a></a>").attr("href","javascript:void(0)").css("color","#317EEB").append("撤销&nbsp;&nbsp;&nbsp;&nbsp;");
			var btn = $("<td></td>").append(btndel);
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
				getAllProcess(1, procName);
			});
			preLi.click(function(){
				getAllProcess(result.pageNum - 1, procName);
			});
		}
		var nextLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","javascript:void(0)"));
		var lastLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","javascript:void(0)"));
		if(result.pageNum == result.pages){
			nextLi.addClass("disabled");
			lastLi.addClass("disabled");
		}else{
			nextLi.click(function(){
				getAllProcess(result.pageNum + 1, procName);
			});
			lastLi.click(function(){
				getAllProcess(result.pages, procName);
			});
		}
		$.each(result.pageNums,function(index,item){
			var numLi=$("<li></li>").append($("<a></a>").append(item).attr("href","javascript:void(0)"));
			if(result.pageNum==item)
			{
				numLi.addClass("active");
			}
			numLi.click(function(){
				getAllProcess(item, procName);
			});
			ul.append(numLi);
		});
		ul.append(nextLi).append(lastLi);
		$("<nav></nav>").attr("aria-label","Page navigation").append(ul).appendTo("#pageNav");
	}
	
	$(document).on('click', '#home table tbody td a', function(){
		var deploymentId = $(this).parent().parent().find("td:eq(4)").text();
		$.ajax({
			url : path + '/activiti/deleteProcess',
			type : 'POST',
			data : {"deploymentId" : deploymentId},
			success : function(data){
				getAllProcess(currentPageNo);
			}
		});
	});
	
	$(".vertical-nav a[href='/JMOA/javascrip:void(0)']").attr("data-toggle","modal").attr("data-target","#myModal");
	
	$("#ok").click(function(){
		var name = $("#name").val();
		var key = $("#key").val();
		var desc = $("#desc").val();
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
	
	$(document).on('click','#home table tbody td img',function(){
		$(this).zoomify();
	});
	
	function startTip(){
		var text = $("#url").val();
		$.ajax({
			url : path + '/activiti/getProcessModel',
			type : 'POST',
			success : function(data){
				$.each(data,function(index, item){
					if(item.name == text){
						window.location.href = path + "/activiti/pageJump?id=" + item.id + "&name=" + item.name + "&key=" + item.key
					}
				})
			}
			
		});
		
	}
});
