// JavaScript Document
function Delete(id)
{
	if(confirm("确定要删除吗？")) {
		location.href = "/mnews.servlet?id=" + id + "&action=delete";
	}
}