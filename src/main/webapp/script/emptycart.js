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
});
