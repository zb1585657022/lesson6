<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<lesson:page title="学生选课">
    <jsp:attribute name="script">
        <link rel="stylesheet" href="static-resource/ace/assets/css/jquery.gritter.min.css" />
        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-editable.min.css" />
        <script src="static-resource/ace/assets/js/jquery.gritter.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery-1.11.3.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
		<script src="static-resource/ace/assets/js/ace-editable.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-tag.min.js"></script>
        <script type="application/javascript">

        </script>
        <script type="application/javascript">
            $(function () {
                $("#tijiao").click(function () {
                    check_val = [];
                    $('input[id="check"]:checked').each(function () {
                        check_val.push($(this).val());
                    });
                    /*$('input[id="checked"]:not(checked)').each(function () {
                        check_val.push($(this).val());
                    });*/
                    $("#checkValue").val(check_val.toString());
                    $("#stu_class_sub").submit();
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/student/student/list.do">
                        <spring:message code="学生管理"/>
                    </a>
                </li>
                <li>
                    <spring:message code="学生选课"/>
                </li>

            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="学生选课"/>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                            class="ace-icon fa fa-angle-left"></i>
                                        <spring:message code="common.back"/>
                                    </a>
                                </span>
                            </h3>
                            <div class="clearfix"></div>
                            <table id="simple-table" align="center"  method="post"class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                    </th>
                                    <th class="center">课程名</th>
                                    <th class="center">选课人数</th>
                                    <th class="center">平均分</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="index" value="0" />
                                <form action="${pageContext.request.contextPath}/student/student/save_chooseSub.do" method="post" id="stu_class_sub">
                                    <c:forEach items="${subjects}" var="subject">
                                        <tr>
                                            <td class="center">
                                                <c:forEach items="${student.subjects}" var="studentSub">
                                                    <c:if test="${subject.subId==studentSub.subId}">
                                                        <c:set var="xx" value="${subject.subId}" />
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${xx.equals(subject.subId)}">
                                                        <label class="pos-rel">
                                                            <input type="checkbox" name="checked" id="checked" class="ace" checked="checked"  value="${subject.subName}" disabled="disabled">
                                                            <span class="lbl"></span>
                                                        </label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <label class="pos-rel">
                                                            <input type="checkbox" id="check" name="check"  class="ace"  value="${subject.subId}">
                                                            <span class="lbl"></span>
                                                        </label>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="center">
                                                <label class="pos-rel">${subject.subName}</label>
                                            </td>

                                            <td class="center">
                                                <label class="pos-rel">${subject.subNum}</label>
                                            </td>

                                            <td class="center">
                                                <label class="pos-rel">${subject.subAveMark}</label>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <input type="hidden" id="stuId" name="stuId" value="${student.stuId}"/>
                                    <input type="hidden" id="checkValue" name="checkValue" />
                                    <tr><td colspan="4" align="center"><font size="4" ><input type="button" value="提交" id="tijiao"></font></td></tr>
                                </form>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</lesson:page>