<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <style type="text/css">
        #loading-mask {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            z-index: 20000;
            background-color: white;
        }
        #loading {
            position: absolute;
            left: 50%;
            top: 50%;
            padding: 2px;
            z-index: 20001;
            height: auto;
        }
        #loading img {
            margin-bottom: 5px;
        }
        #loading .loading-indicator {
            background: white;
            color: #444;
            font: bold 16px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            text-align: left;
            height: auto;
        }
        #loading-msg {
            color: #2f4976;
            font-size: 11px;
        }
    </style>
    <script type="text/javascript">
        var basePath = '${path}';
    </script>
    <title>ExamPool-Choice</title>
</head>

<body oncontextmenu="return false;">
<div id="loading-mask"></div>
<div id="loading">
    <div class="loading-indicator">
        <img src="${path}/static/images/extanim32.gif" width="32" height="32"
             style="margin-right: 8px; float: left; vertical-align: top;">
        <br>
        <span id="loading-msg">loading...</span>
    </div>
</div>
<audio id="tipsMusic" src="${path}/static/music/tip.ogg"></audio>
</body>
</html>
<#include "../common/common.ftl" />
<script type="text/javascript" src="${path}/static/js/desktop/app/exampool/choice/Choice.js"></script>
