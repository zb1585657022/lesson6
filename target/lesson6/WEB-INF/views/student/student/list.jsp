
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>学生管理</title>
</head>
<body>

<lesson:page title="">
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }
            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
        </script>
    </jsp:attribute>
    <jsp:body>
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>

            <li class="active"><spring:message code="学生管理"/></li>
        </ul>
    </div>

        <div class="container">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                            <%--PAGE CONTENT BEGINS--%>
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="学生管理"/>
                                <span class=" btn-group pull-right">
                                    <sec:authorize ifAnyGranted="OPT_USER_ADD">
                                        <a href="${pageContext.request.contextPath}/student/student/add.do" ><i class="ace-icon glyphicon glyphicon-plus"></i>
                                            <spring:message code="button.add"/>
                                        </a>
                                    </sec:authorize>
                                </span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>学号</th>
                                        <th>姓名</th>
                                        <th >性别</th>
                                        <th class="hidden-480">出生日期</th>
                                        <th class="hidden-480">所在班级</th>
                                        <th>选修科目数</th>
                                        <th>平均分</th>
                                        <th>分数录入 </th>
                                        <th>选课</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${stuList}" var="student">
                                        <tr>
                                            <td>${student.stuId}</td>
                                            <td>${student.stuName}</td>
                                            <td>${student.stuSex}</td>
                                            <td class="hidden-480">${student.stuBirthday}</td>
                                            <td class="hidden-480">${student.stuGrade}</td>
                                            <td>${student.stuSubjectSize}</td>
                                            <td>${student.stuAveMark}</td>
                                            <td><a href="${pageContext.request.contextPath}/student/student/setMark.do?stuId=${student.stuId}">分数录入</a></td>
                                            <td><a href="${pageContext.request.contextPath}/student/student/chooseSub.do?stuId=${student.stuId}">选课</a></td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                    <a href="${pageContext.request.contextPath}/student/student/edit.do?stuId=${student.stuId}"
                                                       class="green">
                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                    </a>
                                                    <a data-id="${mainmenu.id}"
                                                       data-url="${pageContext.request.contextPath}/student/student/stuId/delete.do?stuId=${student.stuId}"
                                                       class="red btn-delete-modal">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </div>
                                                <div class="hidden-md hidden-lg">
                                                    <div class="inline pos-rel">
                                                        <button class="btn btn-minier btn-primary dropdown-toggle"
                                                                data-toggle="dropdown" data-position="auto">
                                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                        </button>
                                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                            <li>
                                                                <a class="green bigger-140 show-details-btn">
                                                                    <span class="green"><i class="ace-icon fa fa-angle-double-down"></i></span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a href="${pageContext.request.contextPath}/student/student/edit.do?stuId=${student.stuId}">
                                                                    <span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                                </a></li>
                                                            <li>
                                                                <a data-id="${mainmenu.id}"
                                                                   data-url="${pageContext.request.contextPath}/student/student/stuId/delete.do?stuId=${student.stuId}" class="red btn-delete-modal">
                                                                    <span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</lesson:page>

</body>
</html>
