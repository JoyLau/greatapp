<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <script type="text/javascript" src="${path}/static/js/desktop/app/exampool/choice/Choice.js"></script>
    <script type="text/javascript">
        var basePath = '${path}';
    </script>
    <#--<script type="text/javascript">
        var basePath = '${path}';

        //控制tab页面容器大小的函数
        function allComResize(){
            var modelidarr = modelids.split(",");
            var len = modelidarr.length;
            if(len==0){
                return false;
            }
            var w = Ext.getCmp('displayCenterPanel').getActiveTab().getInnerWidth();
            var h = Ext.getCmp('displayCenterPanel').getActiveTab().getInnerHeight();
            for(var i=0; i<len;i++){
                var tmpmodelid = modelidarr;
                var subPage = Ext.getCmp("tab-"+tmpmodelid+"-main");
                if(subPage){
                    subPage.setWidth(w);
                    subPage.setHeight(h);
                }
            }
        }
        //通过window.onresize事件来执行allComResize函数控制tab容器的大小
        var oTime;
        window.onresize = function(){
            if (oTime){
                clearTimeout(oTime);
            }
            oTime = setTimeout("allComResize()", 100); //延迟100毫秒执行
        }
    </script>-->
    <title>ExamPool-Choice</title>
</head>

<body oncontextmenu="return false;">
<div style="background-color: #00ba00">ss</div>
<audio id="tipsMusic" src="${path}/static/music/tip.ogg"></audio>
</body>
</html>
