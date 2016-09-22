/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/9/18.
 */
//线条：开始xy坐标，结束xy坐标，线条透明度
function Line(x, y, _x, _y, o) {
    this.beginX = x;
    this.beginY = y;
    this.closeX = _x;
    this.closeY = _y;
    this.o = o;
}
//点：圆心xy坐标，半径，每帧移动xy的距离
function Circle(x, y, r, moveX, moveY) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.moveX = moveX;
    this.moveY = moveY;
}
//生成max和min之间的随机数
function num(max, _min) {
    var min = arguments[1] || 0;
    return Math.floor(Math.random() * (max - min + 1) + min);
}
// 绘制原点
function drawCricle(cxt, x, y, r, moveX, moveY) {
    var circle = new Circle(x, y, r, moveX, moveY);
    cxt.beginPath();
    cxt.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI);
    cxt.closePath();
    cxt.fill();
    return circle;
}
//绘制线条
function drawLine(cxt, x, y, _x, _y, o) {
    var line = new Line(x, y, _x, _y, o);
    cxt.beginPath();
    cxt.strokeStyle = 'rgba(0,0,0,' + o + ')';
    cxt.moveTo(line.beginX, line.beginY);
    cxt.lineTo(line.closeX, line.closeY);
    cxt.closePath();
    cxt.stroke();

}
//初始化生成原点
function init() {
    circleArr = [];
    for (var i = 0; i < POINT; i++) {
        circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(20, 6), num(20, -20) / 40, num(20, -20) / 40));
    }
    draw();
}

//每帧绘制
function draw() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    for (i = 0; i < POINT; i++) {
        drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
    }
    for (var i = 0; i < POINT; i++) {
        for (var j = 0; j < POINT; j++) {
            if (i + j < POINT) {
                var A = Math.abs(circleArr[i + j].x - circleArr[i].x),
                    B = Math.abs(circleArr[i + j].y - circleArr[i].y);
                var lineLength = Math.sqrt(A * A + B * B);
                var C = 1 / lineLength * 7 - 0.009;
                var lineOpacity = C > 0.03 ? 0.03 : C;
                if (lineOpacity > 0) {
                    drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i + j].x, circleArr[i + j].y, lineOpacity);
                }
            }
        }
    }
}

function load() {
    init();
    setInterval(function () {
        for (var i = 0; i < POINT; i++) {
            var cir = circleArr[i];
            cir.x += cir.moveX;
            cir.y += cir.moveY;
            if (cir.x > WIDTH) cir.x = 0;
            else if (cir.x < 0) cir.x = WIDTH;
            if (cir.y > HEIGHT) cir.y = 0;
            else if (cir.y < 0) cir.y = HEIGHT;
        }
        draw();
    }, 16);
}
//刷新验证码
function refreshCode() {
    document.getElementById("validateCodeImg").src = "getGifCode?" + Math.random();
}
Ext.onReady(function () {
    //初始化标签中的Ext:Qtip属性。
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    //异步返回先在此拦截
    /*Ext.Ajax.on('requestcomplete',main, this);
    function main(conn, response, options) {
        var json = Ext.util.JSON
            .decode(response.responseText);
        if(json.model.success){
            window.location.href = 'main';
        }
    }*/
    //提交按钮处理方法
    var submitClick = function () {
        if (form.getForm().isValid()) {
            form.getForm().submit({
                method: 'post',
                waitTitle: '登录',
                waitMsg: '正在验证用户信息...',
                async : false,
                success: function (form, action) {
                    window.location = 'main';
                },
                // 如果登录失败，弹出对话框。
                failure: function (form1, action) {
                    if (action.result.model.success) {
                        window.location = 'main';
                    } else {
                        Ext.MessageBox.show({
                            title: '登录失败',
                            msg: action.result.model.message,
                            buttons: Ext.MessageBox.OK,
                            icon: Ext.MessageBox.ERROR
                        });
                        refreshCode();
                        form.getForm().reset();
                    }
                }
            });

            /*Ext.Ajax.request({
                method: 'post',
                waitTitle: '登录',
                waitMsg: '正在验证用户信息...',
                async : false,
                url : 'login',
                params: {
                    username : form.getForm().findField('username').getValue(),
                    password : form.getForm().findField('password').getValue(),
                    captcha : form.getForm().findField('checkcode').getValue()
                },
                success : function(response) {
                    var json = Ext.util.JSON
                        .decode(response.responseText);
                    Ext.MessageBox.show({
                        title: '登录失败',
                        msg: json.model.message,
                        buttons: Ext.MessageBox.OK,
                        icon: Ext.MessageBox.ERROR
                    });
                },
                failure : function(response) {
                    if (validJson(action.response.responseText)) {
                        Ext.MessageBox.show({
                            title : '提示',
                            msg : action.result.msg,
                            buttons : Ext.MessageBox.OK,
                            icon : Ext.MessageBox.ERROR
                        });
                    }
                }
            });*/
        }else{
            win.setHeight(250);
            //如果验证码已经存在，则不刷新
            if (document.getElementById("validateCodeImg").src.toString().indexOf('getGifCode') == -1) {
                refreshCode();
            }
            captcha.setHidden(false)
        }
    };
    //重置按钮"点击时"处理方法
    var resetClick = function () {
        win.setHeight(180);
        captcha.setHidden(true)
        form.getForm().reset();
    };
    //提交按钮
    var submit = new Ext.Button({
        text: '提 交',
        handler: submitClick
    });
    //重置按钮
    var reset = new Ext.Button({
        text: '重 置',
        handler: resetClick
    });
    var captcha = new Ext.Panel({
        width: 155,
        height: 50,
        plain: false,
        border: false,
        html: "<a onclick='refreshCode();'><img id='validateCodeImg' style='border-radius: 10px;cursor: hand' title='看不清楚' src='' /></a>",
        margin: "15px 0px 0px 80px",
        hidden: true
    });
    //表单
    var form = new Ext.form.Panel({
        id: 'form',
        url: 'login',
        labelAlign: 'right',
        labelWidth: 120,
        frame: true,
        cls: 'loginform',
        buttonAlign: 'center',
        defaultType: 'textfield',
        bodyPadding: 10,
        items: [{
            allowBlank: false,
            maxLength: 20,
            name: 'username',
            fieldLabel: '用户名',
            blankText: '请输入用户名',
            maxLengthText: '用户名不能超过20个字符'
        }, {
            allowBlank: false,
            maxLength: 20,
            inputType: 'password',
            name: 'password',
            fieldLabel: '密　码',
            blankText: '请输入密码',
            maxLengthText: '密码不能超过20个字符'
        }, {
            fieldLabel: '验证码',
            id: 'checkcode',
            allowBlank: false,
            anchor: '70%',
            blankText: '请输入验证码！',
            maxLength: 6,
            maxLengthText: '验证码不能超过6个字符!',
            listeners: {
                render: function (p) {
                    p.getEl().on('click', function () {
                        win.setHeight(250);
                        //如果验证码已经存在，则不刷新
                        if (document.getElementById("validateCodeImg").src.toString().indexOf('getGifCode') == -1) {
                            refreshCode();
                        }
                        captcha.setHidden(false)
                    });
                }
            }
        }, captcha],
        buttons: [reset, submit]
    });
    //窗体
    var win = new Ext.Window({
        title: '管理员登录',
        plain: false,
        border: false,
        width: 320,
        height: 180,
        resizable: false,
        shadow: true,
        modal: false,
        closable: false,
        animCollapse: true,
        draggable: false,
        layout: "fit",
        items: form,
        listeners: {
            afterRender: function(thisForm, options){
                this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
                    enter: function(){
                        submitClick();
                    },
                    scope: this
                });
            }
        }
    });
    win.show();
});