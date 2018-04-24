/**
 * 
 */
$(function() {
	$("a").each(function() {
		this.onclick = function() {
			var serializeVal = $(":hidden").serialize();
			var href = this.href + "&" + serializeVal;
			window.location.href = href;
			return false;
		};
	});

	$(".delete").click(function() {

		var $tr = $(this).parent().parent();
		var title = $.trim($tr.find("td:first").text());
		var flag = confirm("确定要删除" + title + "的信息吗?");

		if (flag) {
			return true;
		}

		return false;
	});
});
