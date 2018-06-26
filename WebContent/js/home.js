$(function(){
	// 获取通知公告
	getAllNoticeBulletin(1);
	
	function getAllNoticeBulletin(pageNo)
	{
		$.ajax({
			url : path + '/noticeBulletin/getNoticeBulletinByDate',
			type : 'POST',
			success : function(data){
				build_noticeBulletin_table(data);
			}
		});
	}
	
	//构建表格
	function build_noticeBulletin_table(result)
	{
		$("#tzgg table tbody").empty();
		var noticeBulletin = result;
		$.each(noticeBulletin, function(index, item){
			var icon = $("<span></span>").addClass("icon-double-angle-right");
			var title = $("<td></td>").append(icon).append(item.nbTitle);
			var dept = $("<td></td>").append(item.dept.dName);
			var emp = $("<td></td>").append(item.employee.eName);
			var date = $("<td></td>").append(item.nbDate);
			$("<tr></tr>").append(title).append(dept).append(emp).append(date).appendTo("#tzgg table tbody");
		});
	}
	
	$(".vertical-nav a[href='/JMOA/javascrip:void(0)']").attr("data-toggle","modal").attr("data-target","#myModal");
	
	$(".vertical-nav a").click(function(){
		if($(this).attr("href") == "/JMOA/javascript:;"){
			return false;
		}
	});
	
	$("#ok").click(function(){
		var name = $("#name").val()
		var key = $("#key").val()
		var desc = $("#desc").val()
		$.ajax({
			url : path + '/activiti/create',
			type : 'POST',
			async: false,
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