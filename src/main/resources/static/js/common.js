$(document).ready(function () {
	//导航cur切换
	$(".top-menu li").click(function () {
		$(this).addClass('cur').siblings('li').removeClass('cur');
	});
	$(".test-l-class span").click(function () {
		$(this).addClass('cur').siblings('span').removeClass('cur');
		$(this).next(".test-l-stu").slideDown().siblings(".test-l-stu").slideUp();
	});
	$(".test-l-stu li").click(function () {
		$(this).addClass('cur').siblings('li').removeClass('cur');
	});
	
	
	//职位筛选tab
	$(".Posfiltrate span").click(function () {
		$(this).addClass("cur").siblings("span").removeClass("cur");
		$(".Posfiltrate-cont").eq($(this).index()).show().siblings(".Posfiltrate-cont").hide();
	});
	//td cyan背景图
	$("td.cyan").click(function () {
		$(this).toggleClass("cyan-click");
	});
	//学生首页tab
	$(".stu-index h2 a").click(function () {
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$(".stu-index.fr ul").eq($(this).index()).show().siblings("ul").hide();
	});
	//部分表格首td缩进
	$(".Posfiltrate-cont tr").hover(function () {
		$(".Posfiltrate-cont td:first-child").css('text-indent', '-1px');
	});
	//职位等级li限字符
	$(function () {
		$(".classify-l .clearfix li").each(function () {
			var liebiao = $(this).html();
			if (liebiao.length > 15) {
				$(this).html(liebiao.substr(0, 14) + '…');
			}
		});
	});
	//职位等级li限字符
	$(function () {
		$(".listBox .box").each(function () {
			var liebiao = $(this).text();
			var input = $(this).find("input");
			if (liebiao.length > 15) {
				$(this).html(liebiao.substr(0, 14) + '…').append(input);
			}
		});
	});

	//登陆界面输入框placeholder兼容
	$(".name .input,.password .input ").focus(function () {
		$(this).removeClass("input");
		$(this).addClass("input-focus");
	});
	$(".name input,.password input").blur(function () {
		$(this).removeClass("input-focus");
		$(this).addClass("input");
	});
	$(".input-text").focus(function () {
		$(this).addClass("input-text-focus");
		$(".input-submit").addClass("input-submit-focus")
	});
	$(".input-text").blur(function () {
		$(this).removeClass("input-text-focus");
		$(".input-submit").removeClass("input-submit-focus")
	});
	(function ($) {
		var placeholderfriend = {
			focus: function (s) {
				s = $(s).hide().prev().show().focus();
				var idValue = s.attr("id");
				if (idValue) {
					s.attr("id", idValue.replace("placeholderfriend", ""));
				}
				var clsValue = s.attr("class");
				if (clsValue) {
					s.attr("class", clsValue.replace("placeholderfriend", ""));
				}
			}
		};
		//判断是否支持placeholder
		function isPlaceholer() {
			var input = document.createElement('input');
			return "placeholder" in input;
		}

		//不支持的代码
		if (!isPlaceholer()) {
			$(function () {
				var form = $(this);
				var elements = form.find("input[type='text'][placeholder]");
				elements.each(function () {
					var s = $(this);
					var pValue = s.attr("placeholder");
					var sValue = s.val();
					if (pValue) {
						if (sValue == '') {
							s.val(pValue);
						}
					}
				});
				elements.focus(function () {
					var s = $(this);
					var pValue = s.attr("placeholder");
					var sValue = s.val();
					if (sValue && pValue) {
						if (sValue == pValue) {
							s.val('');
						}
					}
				});
				elements.blur(function () {
					var s = $(this);
					var pValue = s.attr("placeholder");
					var sValue = s.val();
					if (!sValue) {
						s.val(pValue);
					}
				});
				var elementsPass = form.find("input[type='password'][placeholder]");
				elementsPass.each(function (i) {
					var s = $(this);
					var pValue = s.attr("placeholder");
					var sValue = s.val();
					if (pValue) {
						if (sValue == '') {
							var html = this.outerHTML || "";
							html = html.replace(/\s*type=(['"])?password\1/gi, " type=text placeholderfriend").replace(/\s*(?:value|on[a-z]+|name)(=(['"])?\S*\1)?/gi, " ").replace(/\s*placeholderfriend/, " placeholderfriend value='" + pValue + "' " + "onfocus='placeholderfriendfocus(this);' ");
							var idValue = s.attr("id");
							if (idValue) {
								s.attr("id", idValue + "placeholderfriend");
							}
							var clsValue = s.attr("class");
							if (clsValue) {
								s.attr("class", clsValue + "placeholderfriend");
							}
							s.hide();
							s.after(html);
						}
					}
				});
				elementsPass.blur(function () {
					var s = $(this);
					var sValue = s.val();
					if (sValue == '') {
						var idValue = s.attr("id");
						if (idValue) {
							s.attr("id", idValue + "placeholderfriend");
						}
						var clsValue = s.attr("class");
						if (clsValue) {
							s.attr("class", clsValue + "placeholderfriend");
						}
						s.hide().next().show();
					}
				});
			});
		}
		window.placeholderfriendfocus = placeholderfriend.focus;
	})(jQuery);

	//mould-menu 鼠标点击下拉菜单----------------------------------------------------
	var $menuBox = $(".mould-menu"),
		$dl = $menuBox.children("dl"),
		$dt = $dl.children("dt"),
		$dd = $dl.children("dd"),
		$dtA = $dt.children("a"),
		$ddA = $dd.children("a");
	$dt.each(function () {
		if ($(this).next().is("dd")) {
			$(this).children("a").prop("href", "javascript:void(0);");
		}
		else {
			$(this).addClass('onlyDt');
		}
        $(this).live("click",(function (e) {
			e.stopPropagation();
			$(this).parent("dl").siblings("dl").children("dd").slideUp(300);
			if($(".test-menu").css("display")=="block"){
				$(".test-menu").animate({width: 'toggle'});
			}
			if($(".simulatetraining dd").css("display")=="none"){
				$(".simulatetraining dd a b").removeClass("black");
			}
			$(this).parent("dl").siblings("dl:has(dd)").children("dt").children("a").removeClass('cur');
			if ($(this).hasClass('onlyDt')) {
				$ddA.removeClass('cur');
				$(".onlyDt a").removeClass('cur');
				$(this).children("a").addClass('cur');
			}
			else {
				$(this).children("a").toggleClass('cur');
				$(this).parent("dl").children("dd").stop().slideToggle(300);
			}
		}));
	});
	$dd.each(function () {
		if ($(this).children("a").hasClass('cur')) {
			$(this).parent("dl").children("dd").show();
			$(this).parent("dl").children("dt").children("a").addClass('cur');
		}
        $(this).click(function (e) {
			e.stopPropagation();
			$ddA.add(".onlyDt a").removeClass('cur');
			$(this).children("a").toggleClass('cur');
		});
	});

	var $testBox = $(".test-menu"),
		$dl2 = $testBox.children("dl"),
		$dt2 = $dl2.children("dt"),
		$dd2 = $dl2.children("dd"),
		$dtA2 = $dt2.children("a"),
		$ddA2 = $dd2.children("a");
	$dt2.each(function () {
		if ($(this).next().is("dd")) {
			$(this).children("a").prop("href", "javascript:void(0);");
		}
		else {
			$(this).addClass('onlyDt');
		}
		$(this).click(function (e) {
			e.stopPropagation();
			$(this).parent("dl").siblings("dl").children("dd").slideUp(300);
			$(this).parent("dl").siblings("dl:has(dd)").children("dt").children("a").removeClass('cur');
			if ($(this).hasClass('onlyDt')) {
				$ddA2.removeClass('cur');
				$(".test-menu dt.onlyDt a").removeClass('cur');
				$(this).children("a").addClass('cur');
			}
			else {
				$(".test-menu dt.onlyDt a").removeClass('cur');
				$(this).children("a").toggleClass('cur');
				$(this).parent("dl").children("dd").stop().slideToggle(300);
			}
		});
	});
	$dd2.each(function () {
		if ($(this).children("a").hasClass('cur')) {
			$(this).parent("dl").children("dd").show();
			$(this).parent("dl").children("dt").children("a").addClass('cur');
        }
        $(this).click(function (e) {
			e.stopPropagation();
			$ddA2.add(".onlyDt a").removeClass('cur');
			$(this).children("a").toggleClass('cur');
		});
	});

	$(document).on("click",function(e){
		e.stopPropagation();					//点击其他地方收起子列表
		if($(".test-menu").is(":visible")){
			$(".simulatetraining dd a b").toggleClass("black");
			$(".test-menu").animate({width: 'toggle'});
		}

	});


	//界面高度自适应
	$(".main").height($(window).height() - $(".header").height() - $(".footer").height() - 12 - $(".breadcrumbs").height() - 4);
	$(".main2").height($(window).height() - $(".header").height() - $(".footer").height() - $(".breadcrumbs").height() + 24);
	$(".main5").height($(window).height() - $(".header").height()-2);

	$(window).resize(function (e) {
		$(".main").height($(window).height() - $(".header").height() - $(".footer").height() - 12 - $(".breadcrumbs").height() - 4);
		$(".main2").height($(window).height() - $(".header").height() - $(".footer").height() - $(".breadcrumbs").height() + 25);
		$(".main5").height($(window).height() - $(".header").height()-2);
	});


	//学生端首页
	$(".indexBlock").width($(".main").width() - $(".myInfo").width() - 40);
	$(window).resize(function (e) {
		$(".indexBlock").width($(".main").width() - $(".myInfo").width() - 40);
	});


	$(".myInfo .head .img").hover(function () {
		$(this).children("a").show();
	}, function () {
		$(this).children("a").hide();
	});

	$(".answerTab ul li").click(function () {
		$(this).prevUntil().children("a").addClass('clicked');
		$(this).children("a").addClass("cur");
		$(this).siblings().children("a").removeClass("cur");
	});
	//顶部右侧下拉菜单
	$(".quick-links").hover(function () {
		$(this).children(".logout").addClass('cur');
		$(this).children(".dropDown").show();
	}, function () {
		$(this).children(".logout").removeClass('cur');
		$(this).children(".dropDown").hide();
	});
	$(".news-links").hover(function () {
		$(this).children(".news").addClass('cur');
	}, function () {
		$(this).children(".news").removeClass('cur');
	});
	$(".sub-home").hover(function () {
		$(this).children("a").addClass('cur');
	}, function () {
		$(this).children("a").removeClass('cur');
	});

	//搜索框触发状态
	$(".search .txt").focus(function () {
		$(this).addClass("focus");
	}).blur(function () {
		$(this).removeClass("focus");
	});

	//tab切换
	$(".tab li a").each(function (e) {
		$(this).click(function () {
			$(".tab li a").removeClass('cur');
			$(this).addClass('cur');
			$(".tabCont").hide();
			$(".tabCont").eq(e).show();
		});
	});

	$(function () {
		$(".chapterList li h3").each(function () {
			var bt = $(this).html();
			var z = bt.indexOf("章");
			var j = bt.indexOf("节");
			var p = bt.indexOf("篇");
			var b = bt.indexOf("部");
			var y = bt.indexOf("页");
			var g = bt.indexOf("关");
			var pre = "";
			var tail = "";
			if(z!=-1){
				pre = bt.substr(0,z+1);
				tail = bt.substr(z+1);
			}
			else if(j!=-1){
				pre = bt.substr(0,j+1);
				tail = bt.substr(j+1);
			}
			else if(p!=-1){
				pre = bt.substr(0,p+1);
				tail = bt.substr(p+1);
			}else if(b!=-1){
				pre = bt.substr(0,b+1);
				tail = bt.substr(b+1);
			}else if(y!=-1){
				pre = bt.substr(0,y+1);
				tail = bt.substr(y+1);
			}else if(g!=-1){
				pre = bt.substr(0,g+1);
				tail = bt.substr(g+1);
			}
			else{
				pre = bt.substr(0);
			}
			$(this).html(pre +'<p>'+ tail + '</p>');
		});
	});
	//表格隔行变色
	$(".publicTable").each(function (e) {
		$(this).find("tr:even").addClass("alt");
	});

	//计划方案
	$(".Plan span").hover(function () {
		$(this).children(".Planword").show();
	}, function () {
		$(this).children(".Planword").hide();
	});
	
	//战略地图弹窗－切换
	$(".mapTab li i").each(function(e) {
		$(this).click(function() {
           $(this).removeClass('radio-ed');
           $(this).addClass('radio-ed');
           $(".mapCont").hide();
           $(".mapCont").eq(e).show();
        });
	});
	
	//开始实验-标记参加和不参加的学生
	$(".classItemlist li").hover(function(){
    	$(this).addClass("hover");
	},function(){
    	$(this).removeClass("hover");
	});
	$(".classItemlist li b.remove").click(function(){
    	$(this).parents("li").addClass('eliminate');
	});
	$(".classItemlist li.eliminate").hover(function(){
    	$(this).addClass("hover");
	},function(){
    	$(this).removeClass("hover");
	});
	$(".classItemlist li b.enter").click(function(){
    	$(this).parents("li").removeClass('eliminate');
	});
	
	
	//弹窗业务职能tab切换
	$(".targrttab li a").each(function(e) {
       $(this).click(function() {
		   $(".targrttab li a").removeClass('cur');
           $(this).addClass('cur');
           $(".target .cont").hide();
           $(".target .cont").eq(e).show();
        });
	});
	
	//九宫格员工
	$(".staffList .btnBox .edit").toggle(
		function () {
			$(this).val("保存");
				$(".staffList .editinput").each(function(){
					var input = $(this);
					var inputtext = input.text();
					input.html("<input class='textinput' type='text' value=''>");
					input.find("input").val(inputtext);
					$(".staffList li").addClass("stafftext");
					$(this).append("<i class='ico'></i>");
				});
		},
		function () {
			$(this).val("编辑");
			$(".staffList .editinput").find("input").each(function(){
				var inputtext = $(this).val();
				$(this).parent().html(inputtext);
				$(".staffList li").removeClass("stafftext");
				
			});
			$(".staffList li i").remove();
		}
	);
	
	
	//拖拽
	$(function(){
		
		$(".box").mousedown(function(e){
			var	$me = $(this),
				meWidth = $me.outerWidth(),
				meHeight = $me.outerHeight(),
				meLeft = $me.offset().left,
				meTop = $me.offset().top,	
				mouseX = e.pageX - meLeft,
				mouseY = e.pageY - meTop;
			$me.css("cursor","move");
			$(document).bind("mousemove",boxMoving);
			function boxMoving(event){
				var left = event.pageX - mouseX,
					top = event.pageY - mouseY;
				if(left < 0){
					left = 0;
                }
                if(top < 0){
					top = 0;
                }
                $me.addClass('fixed').css({ "left": left+'px', "top": top+'px' });
            }
            $(document).mouseup(function(){
				var $conBox = $(".conBox"),
					$listBox = $(".listBox"),
					Left = parseFloat($me.css("left")),
					Top = parseFloat($me.css("top")),
					listLeft = $listBox.offset().left,
					listTop = $listBox.offset().top,
					listRight = $listBox.offset().left + $listBox.outerWidth(),
					listBottom = $listBox.offset().top + $listBox.outerHeight();
					$(this).unbind("mousemove");
				$me.removeClass('fixed').css({ "left": "auto", "top": "auto", "cursor":"default" });
				for(i=0; i<$conBox.length; i++){
					var $List = $(".item0"+(i+1)).children(".list");
					x = $conBox.eq(i).offset().left;
					X = $conBox.eq(i).offset().left + $conBox.eq(i).outerWidth();
					y = $conBox.eq(i).offset().top;
					Y = $conBox.eq(i).offset().top + $conBox.eq(i).outerHeight();
					switch (i) {
						case i :
							if (Left>=x & Left<X & Top>=y & Top<Y){
								$me.appendTo($List);
                            }
                        default:
							if (Top>=listTop & Top<=listBottom & Left>=listLeft & Left<=listRight) {
								$me.appendTo($listBox);
							}
                    }
				}
			});
		});
	});

	$(function () {

		$(".sorting-box").mousedown(function (e) {
			var $me = $(this),
				meWidth = $me.outerWidth(),
				meHeight = $me.outerHeight(),
				meLeft = $me.offset().left,
				meTop = $me.offset().top,
				mouseX = e.pageX - meLeft,
				mouseY = e.pageY - meTop;
			$me.css("cursor", "move");
			$(document).bind("mousemove", boxMoving);
			function boxMoving(event) {
				var left = event.pageX - mouseX,
					top = event.pageY - mouseY;
				if (left < 0) {
					left = 0;
				}
                if (top < 0) {
					top = 0;
				}
                $me.addClass('fixed').css({"left": left + 'px', "top": top + 'px'});
            }
            $(document).mouseup(function () {
				var $conBox = $(".conBox2"),
					$listBox = $(".listBox2"),
					Left = parseFloat($me.css("left")),
					Top = parseFloat($me.css("top")),
					listLeft = $listBox.offset().left,
					listTop = $listBox.offset().top,
					listRight = $listBox.offset().left + $listBox.outerWidth(),
					listBottom = $listBox.offset().top + $listBox.outerHeight();
				$(this).unbind("mousemove");
				$me.removeClass('fixed').css({"left": "auto", "top": "auto", "cursor": "default"});
				for (i = 0; i < $conBox.length; i++) {
					var $List = $(".item0" + (i + 1));
					x = $conBox.eq(i).offset().left;
					X = $conBox.eq(i).offset().left + $conBox.eq(i).outerWidth();
					y = $conBox.eq(i).offset().top;
					Y = $conBox.eq(i).offset().top + $conBox.eq(i).outerHeight();
					switch (i) {
						case i :
							if (Left >= x & Left < X & Top >= y & Top < Y) {
								$me.appendTo($List);
							}
                        default:
							if (Top >= listTop & Top <= listBottom & Left >= listLeft & Left <= listRight) {
								$me.appendTo($listBox);
							}
                    }
				}
			});
		});
	});

	$(function () {
		$(".sorting-box2").mousedown(function (e) {
			var $me = $(this),
				meLeft = $me.offset().left,
				meTop = $me.offset().top,
				mouseX = e.pageX - meLeft,
				mouseY = e.pageY - meTop;
			$me.css("cursor", "move");
			$(document).bind("mousemove", boxMoving);
			function boxMoving(event) {
				var left = event.pageX - mouseX,
					top = event.pageY - mouseY;
				if (left < 0) {
					left = 0;
				}
                if (top < 0) {
					top = 0;
				}
                $me.addClass('fixed').css({"left": left + 'px', "top": top + 'px'});
            }
            $(document).mouseup(function () {
				var $conBox = $(".conBox3"),
					$listBox = $(".listBox3"),
					Left = parseFloat($me.css("left")),
					Top = parseFloat($me.css("top")),
					listLeft = $listBox.offset().left,
					listTop = $listBox.offset().top,
					listRight = $listBox.offset().left + $listBox.outerWidth(),
					listBottom = $listBox.offset().top + $listBox.outerHeight();
				$(this).unbind("mousemove");
				$me.removeClass('fixed').css({"left": "auto", "top": "auto", "cursor": "default"});
				for (i = 0; i < $conBox.length; i++) {
					var $List = $(".item" + (i + 1));
					x = $conBox.eq(i).offset().left;
					X = $conBox.eq(i).offset().left + $conBox.eq(i).outerWidth();
					y = $conBox.eq(i).offset().top;
					Y = $conBox.eq(i).offset().top + $conBox.eq(i).outerHeight();
					switch (i) {
						case i :
							if (Left >= x & Left < X & Top >= y & Top < Y) {
								$me.appendTo($List);
							}
                        default:
							if (Top >= listTop & Top <= listBottom & Left >= listLeft & Left <= listRight) {
								$me.appendTo($listBox);
							}
                    }
                }
			});
		});
	});




	//下拉框重写开始
	$.fn.selectOpen = function () {
		var singleSelect = function (parentObj) {
			parentObj.addClass("single-select"); //添加样式
			parentObj.children().hide(); //隐藏内容
			var sltWidth = parentObj.attr("width"); //定义宽度
			var sltHeight = parentObj.attr("height"); //定义高度
			var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); //前插入一个DIV
			divObj.css("width",sltWidth); //写入宽度
			//创建元素
			var titObj = $('<a class="select-tit" href="javascript:;"><span></span><i></i></a>').appendTo(divObj);
			var itemObj = $('<div class="select-items"><ul></ul></div>').appendTo(divObj);
			itemObj.find("ul").css("width",sltWidth); 
			itemObj.find("ul").css("height",sltHeight); 
			var selectObj = parentObj.find("select").eq(0); //取得select对象
			//遍历option选项
			selectObj.find("option").each(function (i) {
				var indexNum = selectObj.find("option").index(this); //当前索引
				var liObj = $('<li title="' + $(this).text() + '">' + $(this).text() + '</li>').appendTo(itemObj.find("ul")); //创建LI
				if ($(this).prop("selected") == true) {
					liObj.addClass("selected");
					titObj.find("span").text($(this).text()).attr("title",$(this).text());
				}
				//检查控件是否启用
				if ($(this).prop("disabled") == true) {
					liObj.css("cursor", "default");
					return;
				}
				//绑定事件
				liObj.click(function () {
					$(this).siblings().removeClass("selected");
					$(this).addClass("selected"); //添加选中样式
					selectObj.find("option").prop("selected", false);
					selectObj.find("option").eq(indexNum).prop("selected", true); //赋值给对应的option
					titObj.find("span").text($(this).text()).attr("title",$(this).text()); //赋值选中值
					itemObj.hide(); //隐藏下拉框
					selectObj.trigger("change"); //触发select的onchange事件
					
				});
			});
			
			//检查控件是否启用
			if (selectObj.prop("disabled") == true) {
				titObj.css("cursor", "default");
				return;
			}
			//绑定单击事件
			titObj.click(function (e) {
				selectObj.trigger("click"); //触发select的click事件
				e.stopPropagation();
				//表单验证提示信息点击之后消失
				$(this).parents(".pst-rela").find(".tips-text").hide();
				if (itemObj.is(":hidden")) {
					//隐藏其它的下位框菜单
					$(".single-select .select-items").hide();
					//位于其它无素的上面
					itemObj.css("z-index", "51");
					//显示下拉框
					itemObj.show();	
					//加深边框颜色	
					divObj.addClass('focus');		
					
				} else {
					//位于其它无素的上面
					itemObj.css("z-index", "");
					//隐藏下拉框
					itemObj.hide();
					//去除边框颜色
					divObj.removeClass('focus');
				}
			});
			//绑定页面点击事件
			$(document).click(function (e) {
				selectObj.trigger("blur"); //触发select的onblure事件
				itemObj.hide(); //隐藏下拉框
				divObj.removeClass('focus');//去除边框颜色
			});
		};
		return $(this).each(function () {
			singleSelect($(this));
		});
	};
	$(function(){
        $(".select-default").selectOpen();
    });
	//下拉框重写结束
});



$(function(){
	var slideHeight = 84; // px
	var defHeight = $('.con-slide').height();
	if(defHeight >= slideHeight){
		$('.con-slide').css('height' , slideHeight + 'px');
		$('.col-slide-state').click(function(){
			$(this).toggleClass("shift");
			var curHeight = $('.con-slide').height();
			if(curHeight == slideHeight){
				$('.con-slide').animate({
					height: defHeight
				}, "normal");

			}else{
				$('.con-slide').animate({
					height: slideHeight
				}, "normal");
			}
			return false;
		});
	}
});

$(function(){
	$(".starBox").each(function(){
		var $box = $(this),
			$li = $("li", $box);
		$li.mouseenter(function(){
			$(this).addClass('hover');
			$(this).prevAll("li").addClass('hover');
			$(this).nextAll("li").removeClass('hover');
		});
		$li.mouseleave(function(){
			$box.mouseleave(function(){
				$li.removeClass('hover');
			});
		});
		$li.click(function(){
			var index = $(this).index(),
				score = index + 1;
			$(this).addClass('cur');
			$(this).prevAll("li").addClass('cur');
			$(this).nextAll("li").removeClass('cur');
		});
	});
});

(function(jQuery){
	$.fn.lrScroll = function (o) {
		o = $.extend({									//设置默认参数
			speed: 1000,
			easing: 'swing',
			liWidth: null,
			showNum: 5,
			scrollNum: 3
		},o || {});
		return this.each(function(){					//回调开始
			var $ts = $(this),
				$btnLR = $ts.children(".btnLR"),
				$btnPre = $ts.children(".prev"),
				$btnNex = $ts.children(".next"),
				$sroCon = $ts.children(".sroCon"),
				$ul = $sroCon.children("ul"),
				$li = $ul.children("li"),
				len = $li.length,
				left = parseFloat($ul.css("left")),
				mrWid = parseFloat($li.css("margin-right"));
			$sroCon.css({								//外层框css设置
				"position": "relative",
				"overflow": "hidden"
			});
			if(len <= o.showNum){						//判断li的个数是否足够进行滚动
				$btnPre.add($btnNex).addClass('disabled');
				$ul.css({								//进行ul样式设置
					"width": o.liWidth * len,
					"left": "auto",
					"margin-left": "auto",
					"margin-right": "auto"
				});
			}
			else{
				$btnPre.addClass('disabled');
				$ul.css({
					"position": "relative",
					"left": 0,
					"width": o.liWidth * len
				});
            }
            $btnLR.hover(								//为了兼容IE6
				function(){
					if(!$(this).hasClass('disabled')){
						$(this).addClass('hover');
					}
				},
				function(){
					$(this).removeClass('hover');
				}
			);
			$btnLR.click(function(){					//左右按钮点击事件
				if($(this).hasClass('disabled') || $ul.is(":animated")){
					return false;						//左、右到底了或者正在滚动中则阻止点击
                }
                if($(this).hasClass('next')){			//向右点击事件
					var dif =  o.liWidth*len - o.liWidth*o.showNum;
					left = left - (o.liWidth * o.scrollNum);
					$btnPre.removeClass('disabled');
					if(left <= -dif){
						left = -dif;
						$btnNex.addClass('disabled');
					}
				}
				else {									//向左点击事件
					left = left + (o.liWidth * o.scrollNum);
					$btnNex.removeClass('disabled');
					if(left >= 0){
						left = 0;
						$btnPre.addClass('disabled');
                    }
                }
                $ul.animate({left: left}, o.speed, o.easing);
			});
		});
	};
})(jQuery);

$(function () {
	if ((screen.width == 1440)) {
		$("#sroBox01").lrScroll({
			speed: 600,                   //滚动速度，默认1000，非必须参数
			easing: 'swing',             //滚动效果，默认'swing',非必须参数
			liWidth: 125,                 //单个li的宽度 + margin-right的总和，不要算错啦，*必须参数
			showNum: 8,                   //显示几个li，父级宽度会自适应，但不要超过最外层的总宽度，*必须参数
			scrollNum: 1                  //一次滚动几个，*必须参数
		});
	}
	if ((screen.width == 1366)) {
		$("#sroBox01").lrScroll({
			speed: 600,                   //滚动速度，默认1000，非必须参数
			easing: 'swing',             //滚动效果，默认'swing',非必须参数
			liWidth: 130,                 //单个li的宽度 + margin-right的总和，不要算错啦，*必须参数
			showNum: 7,                   //显示几个li，父级宽度会自适应，但不要超过最外层的总宽度，*必须参数
			scrollNum: 1                  //一次滚动几个，*必须参数
		});
	}
	if ((screen.width == 1024)) {
		$("#sroBox01").lrScroll({
			speed: 600,                   //滚动速度，默认1000，非必须参数
			easing: 'swing',             //滚动效果，默认'swing',非必须参数
			liWidth: 100,                 //单个li的宽度 + margin-right的总和，不要算错啦，*必须参数
			showNum: 6,                   //显示几个li，父级宽度会自适应，但不要超过最外层的总宽度，*必须参数
			scrollNum: 1                  //一次滚动几个，*必须参数
		});
	}
	else {
		$("#sroBox01").lrScroll({
			speed: 600,                   //滚动速度，默认1000，非必须参数
			easing: 'swing',             //滚动效果，默认'swing',非必须参数
			liWidth: 134,                 //单个li的宽度 + margin-right的总和，不要算错啦，*必须参数
			showNum: 9,                   //显示几个li，父级宽度会自适应，但不要超过最外层的总宽度，*必须参数
			scrollNum: 1                  //一次滚动几个，*必须参数
		});
	}
});

function pageSize(leftWidth) {
    if (!leftWidth) {
        leftWidth = 0;
    }
    var winW, winH;
    if (window.innerHeight) {// all except IE
        winW = window.innerWidth - leftWidth;
        winH = window.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) {// IE 6 Strict Mode
        winW = document.documentElement.clientWidth - leftWidth;
        winH = document.documentElement.clientHeight;
    } else if (document.body) { // other
        winW = document.body.clientWidth - leftWidth;
        winH = document.body.clientHeight;
    }  // for small pages with total size less then the viewport
    return {WinW: (winW - 40)*0.99, WinH: winH*0.4};
}

function dyniframesize(obj) {
	var frame = $(obj)[0];
	frame.style.display="block";
	frame.height = 500;
	//非IE
	if (frame.contentDocument && frame.contentDocument.body.scrollHeight){
	    var contentHeight = frame.contentDocument.body.scrollHeight;
		frame.height = contentHeight + 45;
		frame.contentDocument.body.style.height=contentHeight;
		//frame.contentDocument.body.scrollHeight=contentHeight;
	}
	else {
		//IE
		if (frame.Document && frame.Document.body.scrollHeight){
			var contentHeight = frame.Document.body.scrollHeight;
			frame.height = contentHeight + 45; 
			frame.Document.body.style.height=contentHeight;
			//frame.Document.body.scrollHeight=contentHeight;
		}
	}
	if(frame.height < 500)
	{
		frame.height = 500;
	}
} 

/**
 * 日期格式化
 * 
 * @param format
 *            "yyyy-MM-dd hh:mm:ss" "yyyy年MM月dd日hh小时mm分ss秒"等
 * @returns 日期格式化字符串
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * 监控实验状态
 * @param stuExpId 学生实验编号
 * @param path 项目路径
 */
function checkExpState(stuExpId,path){
	$.post(path+"/student/exp/checkExpState",
		   {stuExpId:stuExpId},
		   function(data){
		     if(data==3){
		    	 parent.artDialog.alert("warning","提示","实验已暂停，请联系实训老师",function(){
		    		 window.parent.location.href = path+"/student/homePage";
		    	 });
		     }else if(data==4){
		    	 parent.artDialog.alert("warning","提示","实验已结束，请联系实训老师",function(){
            	   window.parent.location.href =  path+"/student/homePage";
		    	 });
		     }
	});
}

function errorTips(data,path){
	
	if(data.message==undefined){
		if(data.indexOf("/anon/aboutMe")>-1){//返回的是登录页面，表明session超时
			parent.artDialog.alert("warning","提示","您已退出该账号或者该会话已过期,3秒后将自动跳转到登录页面",function(){
				      parent.location.href= path+"/login";
				});
			setTimeout(function(){
				parent.location.href= path+"/login";
			}, 3000);
	    }else{
	    	if(data){
				parent.artDialog.alert("warning","提示",data,function(){});
			}
    	}
	}else{
		if(data.message){
			parent.artDialog.alert("warning","提示",data.message,function(){});
		}
	}
	
}


$(function(){
	//处理键盘事件，禁止后退键（Backspace）密码或单行、多行文本框除外
	// 禁止input里的enter事件
	function forbidBackKey(e) {

		var ev = e || window.event;// 获取event对象

		var obj = ev.target || ev.srcElement;// 获取事件源

		var tagName = obj.tagName;

		var t = obj.type || obj.getAttribute('type');// 获取事件源类型

		// 获取作为判断条件的事件类型
		var vReadOnly = obj.readOnly;

		var vDisabled = obj.disabled;

		// 处理undefined值情况
		vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;

		vDisabled = (vDisabled == undefined) ? true : vDisabled;

		// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
		// 并且readOnly属性为true或disabled属性为true的，则退格键失效
		var flag1 = ev.keyCode == 8
				&& (t == "password" || t == "text" || t == "textarea")
				&& (vReadOnly == true || vDisabled == true);

		// 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
		var flag2 = ev.keyCode == 8 && t != "password" && t != "text"
				&& t != "textarea";
		// 判断
		if (flag2 || flag1) {
			return false;
		}
		
	}
	// 禁止退格键 作用于Firefox、Opera
	$(document).keypress(forbidBackKey);
	// 禁止退格键 作用于IE、Chrome
	$(document).keydown(forbidBackKey);
});

var strFullPath = window.document.location.href;
var index = strFullPath.indexOf('student');
var path = strFullPath.substr(0,index + 7);

(function($) {
	$.cookie = function(key, value, options) {

		// key and at least value given, set cookie...
		if (arguments.length > 1 && (!/Object/.test(Object.prototype.toString.call(value)) || value === null || value === undefined)) {
			options = $.extend({}, options);

			if (value === null || value === undefined) {
				options.expires = -1;
			}

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setDate(t.getDate() + days);
			}

			value = String(value);

			return (document.cookie = [
				encodeURIComponent(key), '=', options.raw ? value : encodeURIComponent(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// key and possibly options given, get cookie...
		options = value || {};
		var decode = options.raw ? function(s) { return s; } : decodeURIComponent;

		var pairs = document.cookie.split('; ');
		for (var i = 0, pair; pair = pairs[i] && pairs[i].split('='); i++) {
			if (decode(pair[0]) === key) return decode(pair[1] || ''); // IE saves cookies with empty string as "c; ", e.g. without "=" as opposed to EOMB, thus pair[1] may be undefined
		}
		return null;
	};

	if (typeof define === 'function' && define.amd)
	{
		define(['jquery'], function($)
		{
			return $.cookie;
		});
	}

})(jQuery);

function setStuExpId(id) {
	var arr = $.cookie('stuExpIdArr') ? $.cookie('stuExpIdArr').split(',') : [];
	arr.push(id);
	$.cookie('stuExpIdArr', arr.join(","), {expires: 7, path: '/'});
}

function validateStuExpId(id) {
	var arr = $.cookie('stuExpIdArr') ? $.cookie('stuExpIdArr').split(',') : [];
	if ($.inArray(id+"", arr) > -1) {
		artDialog.alert('warning', '操作提示', '您已经提交过该实验了', function () {
            parent.location.href= path + "/homePage";
		});
		return true;
	}
	return false;
}

/**
 * @liufa
 * 全局的异步ajax处理
 * 处理异步清求时session失效
 */
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete:function(XMLHttpRequest,textStatus){
		var sessionStatus=XMLHttpRequest.getResponseHeader("sessionStatus");
		if(sessionStatus=="timeout"){
			//if the session is invalid,and forward to under URI
			window.location = XMLHttpRequest.getResponseHeader("URI");
		}
	}
});
