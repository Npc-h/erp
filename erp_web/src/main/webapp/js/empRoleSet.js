$(function(){
	$('#tree').tree({
		animate:true,
		checkbox:true
	});

	$('#grid').datagrid({
		url: 'emp_list',
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'name',title:'名称',width:100}
		]],
		singleSelect:true,
		onClickRow:function(rowIndex, rowData){
			$('#tree').tree({
				url: 'emp_readEmpRoles?id=' + rowData.uuid,
				animate:true,
				checkbox:true
			});
		}
	});
	
	$('#btnSave').bind('click',function(){
		var nodes = $('#tree').tree('getChecked');
		var ids = new Array();
		$.each(nodes,function(i, node){
			ids.push(node.id);
		});
		//[1,2,3,4] => "1,2,3,4"
		var checkedStr = ids.join(',');//把数组里的每个元素都拼接上逗号
		//构建提交数据
		var formdata = {};
		//id赋值
		formdata.id= $('#grid').datagrid('getSelected').uuid;
		//选中的角色ID
		formdata.checkedStr=checkedStr;
		$.ajax({
			url: 'emp_updateEmpRoles',
			data:formdata,
			type:'post',
			dataType: 'json',
			success:function(rtn){
				$.messager.alert('提示',rtn.message,'info');
			}
		});
	});
});