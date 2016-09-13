// JavaScript Document(function($){
$(document).ready(function () {
//组件样式重写
	$(".radioGroup").radio();
	$(".select").select();
	$(".checkbox").checkbox();
	
});
(function($){
	$.fn.extend({
		checkbox:function(){
			$(".checkbox").on("click",".chkAll",function(){
				if(!$(this).hasClass("disable")){
					$(this).addClass("chkAll-ed").removeClass("chkAll");
					$(this).closest(".checkbox").find(".chkItem").addClass("chkItem-ed").removeClass("chkItem");
				}
			});
			$(".checkbox").on("click",".chkAll-ed",function(){
				if(!$(this).hasClass("disable")){
					$(this).addClass("chkAll").removeClass("chkAll-ed");
					$(this).closest(".checkbox").find(".chkItem-ed").addClass("chkItem").removeClass("chkItem-ed");
				}
			});
			$(".checkbox").on("click",".chkItem",function(event) {
				if(!$(this).hasClass("disable")){
					$(this).addClass("chkItem-ed").removeClass("chkItem");
					var $parentObj=$(this).closest(".checkbox");
					var len=$parentObj.find(".chkItem").length;
					if(len==0){
						$parentObj.find(".chkAll").addClass("chkAll-ed").removeClass("chkAll");
					}
				}
			});
			$(".checkbox").on("click",".chkItem-ed",function(){
				if(!$(this).hasClass("disable")){
					$(this).addClass("chkItem").removeClass("chkItem-ed");
					$(this).closest(".checkbox").find(".chkAll-ed").addClass("chkAll").removeClass("chkAll-ed");
				}
			});
		},
		getCheckValue:function(){
			var chkVals = "";
			$(this).find(".chkItem-ed").each(function(){
				chkVals+=$(this).attr("value")+",";
			});
			return chkVals.substring(0,chkVals.length-1);
		},
		radio:function(){
			$(".radioGroup").on("click",".radio",function(){
				if(!$(this).hasClass("disable")){
					$(this).closest(".radioGroup").find(".radio-ed").addClass("radio").removeClass("radio-ed");
					$(this).addClass("radio-ed").removeClass("radio");
				}
			});
		},
		getRadioValue:function(){
			var chkVals = "";
			$(this).find(".radio-ed").each(function(){
				chkVals+=$(this).attr("value")+",";
			});
			return chkVals.substring(0,chkVals.length-1);
		},
		select: function(options) { 
			if(options!=null&options!=""){
				return this.each(function() {
					new $.SelectBox($(this).find("select"),options);
				});
			}else{
				return this.each(function() {
					new $.SelectBox($(this).find("select"));
				});
			}
		}
	});
})(jQuery);
