<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>省级公务员试题单选题在线预览</title>
</head>
<body>
<p style="text-align:center">
    <strong><span style="font-family: 宋体;font-size: 14px;position: relative;top: -4px"></span></strong>
</p>
<p style="text-align:center">
    <span style="font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong><span style="font-size: 19px; position: relative; top: -4px;">一战成公</span></strong><strong><span style="font-size: 19px; position: relative; top: -4px;"></span></strong></span>
</p>
<p style="text-align:center">
    <span style="font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong><span style="font-size: 19px; position: relative; top: -4px;">省级公务员试题单选题在线预览v1.0</span></strong><strong><span style="font-size: 19px; position: relative; top: -4px;"></span></strong><strong><span style="font-size: 19px; position: relative; top: -4px;"></span></strong></span>
</p>
<p>
    <span style="font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong><span style="font-family: 宋体; font-size: 19px; position: relative; top: -4px;"><br/></span></strong><strong><span style="font-family: 宋体; font-size: 19px; position: relative; top: -4px;"></span></strong></span>
</p>
<p style="text-align:center;line-height:24px">
    <span style="font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong><span style="font-size: 14px; font-family: 宋体;">by ${user} on ${.now}</span></strong></span>
</p>
<p style="line-height: 24px;">
    <span style="font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong><span style="font-size: 14px; font-family: 宋体;"><br/></span></strong><strong><span style="font-size: 14px; font-family: 宋体;"></span></strong></span>
</p>

<#list list as PcseSingleChoice>
<#--题目开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;">${PcseSingleChoice_index + 1}.${PcseSingleChoice.title}</span>
</p>
<#--题目结束-->
<#--选项A开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;">A．${PcseSingleChoice.answerA}</span>
</p>
<#--选项A结束-->
<#--选项B开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;">B．${PcseSingleChoice.answerB}</span>
</p>
<#--选项B结束-->
<#--选项C开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;">C．${PcseSingleChoice.answerC}</span>
</p>
<#--选项C结束-->
<#--选项D开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;">D．${PcseSingleChoice.answerD}</span>
</p>
<#--选项D结束-->
<#--题目类型开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong>【题目类型】</strong>${ (PcseSingleChoice.type == 0) ?string('行测','申论')}</span>
</p>
<#--题目类型结束-->
<#--正确答案开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong>【正确答案】</strong><#if PcseSingleChoice.answerRight == 0>A<#elseif PcseSingleChoice.answerRight == 1>B<#elseif PcseSingleChoice.answerRight == 2>C<#else>D</#if></span>
</p>
<#--正确答案结束-->
<#--解析开始-->
<p style="margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px">
    <span style="font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;"><strong>【解析】</strong>${PcseSingleChoice.meno}</span>
</p>
<#--解析结束-->
    <br/><br/>
</#list>
</body>
</html>