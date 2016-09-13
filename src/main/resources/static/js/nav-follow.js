/** 导航跟随--子纯 **/
$(function() {
	$(".help-box").each(function() {
		var $navBox = $(this),
			$navBar = $(".help-bar", $navBox),
			$navList = $(".help-list", $navBox),
			$navLi = $("li", $navList),
			width = $navLi.outerWidth(),
			disWid = (width - $navBar.outerWidth()) / 2,
			indexCur = $navList.children("li.cur").index();
		$navBar.css("left", indexCur * width + disWid);
		$navLi.mouseenter(function() {
				var indexMus = $(this).index(),
					barLeft = indexMus * width + disWid;
				$navBar.stop().animate({
					left: barLeft
				}, 300);
			})
			.mouseleave(function() {
				$navBar.stop().animate({
					left: indexCur * width + disWid
				});
			})
			.click(function() {
				indexCur = $(this).index();
				$(this).addClass("cur").siblings("li").removeClass("cur");
			});
	});
	//帮助弹窗tab
	$(".help-list li a").click(function () {
		$(".help-con").eq($(this).parent("li").index()).fadeIn().siblings(".help-con").hide();
	});


});