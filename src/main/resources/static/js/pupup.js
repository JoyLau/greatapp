
//弹窗页面语句

$(document).ready(function () {
    $(".ui-dialog-username,.ui-dialog-password ").focus(function(){
        $(this).addClass("user_hover");
    });
    $(".ui-dialog-username,.ui-dialog-password").blur(function(){
        $(this).removeClass("user_hover");
    });
});
