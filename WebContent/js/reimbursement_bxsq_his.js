$(function(){
	
	$("#adopt").click(function(){
		var tableData1 = GetTableData(document.getElementById('tabProduct1'));
		$("#tableData1").val(JSON.stringify(tableData1));
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=报销管理";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	if($("#tableData11").text() != ""){
		var message = $("#message").val();
		var tableData1 = JSON.parse($("#tableData11").text());
		$.each(tableData1, function(index, item){
			AddRows1(document.getElementById('tabProduct1'), 1, item, message);
		})
	}
	
	$(document).on('click', 'input[name=searchImage]', function(){
		 $("#imageModal").modal('show');
		 $("#uploadBtn").text("关闭");
		 $("#img-container").empty();
		 var imageName = $(this).next().text();
		 var is = imageName.split(",");
		 for(var i = 0; i< is.length; i++){
			 var p = path + "/activiti/upload/" + is[i];
			 var img = $("<img src=' " + p + " '>")
			 $("#img-container").append(img);
		 }
	});
	
	var object;
	
	$(document).on('click', 'input[name=uploadAvatar]', function(){
		 $("#imageModal").modal('show');
		 $("#uploadBtn").text("确定");
		 object = $(this).next();
		 var imageName = $(this).next().text();
		 var l = (imageName.split(",").length + 1);
		 if($("#img-container").children().length > l){
			 return;
		 }
		 if(imageName != ""){
			 var is = imageName.split(",");
			 for(var i = 0; i< is.length; i++){
				 var p = path + "/activiti/upload/" + is[i];
				 var img = $("<div class='img-thumb img-item'><img class='thumb-icon' src=' " + p + " '><a href='javscript:;' class='img-remove'>x</a></div>")
				 $("#img-container").prepend(img);
			 }
		 }
	});
	
	$(document).on('click', '#uploadBtn', function(){
		if(fileNames != ""){
			$(object).text(fileNames);
		}
		if($(this).text() == "确定"){
			var formData = new FormData($("#form")[0]);
			$.ajax({
				url : path + '/activiti/multiple',
				type : 'POST',
				data : formData,  
				async: false,  
				cache: false,  
				contentType: false,  
				processData: false,  
				success : function(data){
					var size = $("#img-container div").size();
					$("#img-container div:lt(" + (size - 1) + ")").remove();
				}
			});
		}
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1));
			var text = $(this).val();
			var date1 = new Date($("#reserve11").val());
			var date2 = new Date($("#reserve13").val());
			var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
			if(curVal<0){
				alert("开始时间不能大于结束时间...");
			}else{
				determineAndSubmitBtn(text, "报销管理");
			}
		});
	});
})

var count = 1;

function AddRow1(table, index){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td><input name="uploadAvatar" type="button" value="上传发票扫描件"/><span style="display:none;"></span></td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	count++; 
	return table.tBodies[0].lastChild;
}

function AddRows1(table, index, item, message){
	var newRowEl;
	if(message == "审批中" || message == "完成"){
			newRowEl = [
			'<tr class="ccsq-bdnr">',
			'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
			'	<td>' + item.Num + '</td>',
			'	<td>' + item.Amount + '</td>',
			'	<td>' + item.Amount1 + '</td>',
			'	<td>' + item.Amount2 + '</td>',
			'	<td>' + item.Amount3 + '</td>',
			'	<td>' + item.Amount4 + '</td>',
			'	<td><input name="searchImage" type="button" value="查看图片"/><span style="display:none;">' + item.Amount5 + '</span></td>',
			'</tr>'
			].join("");
	}else{
		newRowEl = [
			'<tr class="ccsq-bdnr">',
			'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
			'	<td>' + item.Num + '</td>',
			'	<td>' + item.Amount + '</td>',
			'	<td>' + item.Amount1 + '</td>',
			'	<td>' + item.Amount2 + '</td>',
			'	<td>' + item.Amount3 + '</td>',
			'	<td>' + item.Amount4 + '</td>',
			'	<td><input name="uploadAvatar" type="button" value="上传发票扫描件"/><span style="display:none;">' + item.Amount5 + '</span></td>',
			'</tr>'
			].join("");
	}
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}