<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<lesson:page title="学科管理">
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

                <li class="active"><spring:message code="学科管理"/></li>
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
                                <spring:message code="学科管理"/>
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_USER_ADD">
                                    <a href="/student/subject/add.do" ><i class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                                </span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>学科号</th>
                                    <th class="hidden-480">学科名</th>
                                    <th>选修人数</th>
                                    <th>平均分</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${subjectList}" var="subject">
                                    <tr>
                                        <td>${subject.subId}</td>
                                        <td class="hidden-480">${subject.subName}</td>
                                        <td>${subject.subNum}</td>
                                        <td>${subject.subAveMark}</td>
                                        <td class="hidden-480">
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                <a href="/student/subject/edit.do?subId=${subject.subId}"
                                                   class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>
                                                <a data-id="${mainmenu.id}"
                                                   data-url="/student/subject/subId/delete.do?subId=${subject.subId}"
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
                                                            <a href="/student/subject/edit.do?subId=${subject.subId}">
                                                                <span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                            </a></li>
                                                        <li>
                                                            <a data-id="${mainmenu.id}"
                                                               data-url="/student/subject/subId/delete.do?subId=${subject.subId}" class="red btn-delete-modal">
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
