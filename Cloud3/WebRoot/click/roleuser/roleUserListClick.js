var ctx = getRootPath();

$().ready(function() {
	$("#roleUserForm").validate({
		rules: {//验证规则
			linkRoleId:{required:false,maxlength:36,normalText:true},
			userId:{required:false,maxlength:500,normalText:true}
		},
		messages:{//验证消息内容
			linkRoleId:{maxlength:'最大长度不能超过36'},
			userId:{maxlength:'最大长度不能超过500'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'角色人员关系数据导入', url: ctx+'/roleuser/openRoleUserImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
	);
}

/**
 * 关闭导入窗口
 */
function closeImportWin() {
	if(importWin != null) {
		importWin.close();
	}
}

/**
 * 数据导出
 */
var exportWin;
function exportData() {
	var ids = "";
	var objs = document.getElementsByName("ids");
	for(var i=0; i<objs.length; i++) {
		if(objs[i].type.toLowerCase() == "checkbox") {
			if(objs[i].checked == true) {
				ids += objs[i].value+",";
			}
		}
	}
	if(ids.Trim() != "") {
		ids = ids.substring(0,ids.length-1);
	}

	exportWin = $.ligerDialog.open(
		{title:'角色人员关系数据导出', url: ctx+'/roleuser/openRoleUserExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
	);
}

/**
 * 关闭导出窗口
 */
function colseExportWin() {
	if(exportWin != null) {
		exportWin.close();
	}
}
