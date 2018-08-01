<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cs" scope="page" value="${cmd}"/>
<c:choose>
    <c:when test="${cs.equals('add')}">
        <li class="active">
            <spring:message code="新增学科"/>
        </li>
    </c:when>
    <c:otherwise>
        <li class="active">
            <spring:message code="修改学科信息"/>
        </li>
    </c:otherwise>
</c:choose>
<lesson:page title="">
    <jsp:attribute name="script">
        <link rel="stylesheet" href="static-resource/ace/assets/css/jquery.gritter.min.css" />
        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-editable.min.css" />

        <script src="static-resource/ace/assets/js/jquery.gritter.min.js"></script>

        <script src="static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
		<script src="static-resource/ace/assets/js/ace-editable.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>


		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-tag.min.js"></script>

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
                    <a href="/student/subject/list.do">
                        <spring:message code="学科管理"/>
                    </a>
                </li>
                <c:choose>
                    <c:when test="${cs.equals('add')}">
                        <li class="active">
                            <spring:message code="新增学科"/>
                        </li>
                    </c:when>
                    <c:when test="${cs.equals('edit')}">
                        <li class="active">
                            <spring:message code="修改学科信息"/>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="active">
                            <spring:message code="删除学科信息"/>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <c:choose>
                                    <c:when test="${cs.equals('add')}">
                                            <spring:message code="新增学科"/>
                                    </c:when>
                                    <c:when test="${cs.equals('edit')}">
                                            <spring:message code="修改学科信息"/>
                                    </c:when>
                                    <c:otherwise>
                                            <spring:message code="删除学科信息"/>
                                    </c:otherwise>
                                </c:choose>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>

                            <div class="clearfix"></div>
                            <div  class="user-profile row col-sm-offset-3">
                                <div class="col-xs-12 col-sm-3 center">
                                    <div>
												<span class="profile-picture">
													<img id="avatar" height="180px" width="180px" class="editable img-responsive" alt="Logo"  src="${empty admin.logo?'static-resource/ace/assets/images/avatars/profile-pic.jpg':admin.logo}" />
												</span>
                                        <div class="space-4"></div>

                                    </div>
                                </div>
                            </div>

                            <form action="student/subject/save_${cmd}.do" method="post" class="form-horizontal" role="form"
                                  id="admin-add-form">
                                <input type="hidden" value="${cmd}" id="cmd"/>
                                <input   type="hidden" id="logo" name="logo" value="${admin.logo}">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        <spring:message code="学科号"/>
                                    </label>
                                    <c:choose>
                                        <c:when test="${cs.equals('add')}">
                                            <div class="col-sm-3">
                                                <input type="hidden"  id="subId" name="subId" maxlength="50" value="10000000">
                                                <input readonly autocomplete="off" type="text" value="班级号自动赋值" class="required form-control"/>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="col-sm-3">
                                                <input type="text" autocomplete="off" id="subId" name="subId" maxlength="50" value="${subject.subId}"
                                                       class="required form-control" readonly/>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        <spring:message code="学科名"/>
                                    </label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="subName" name="subName" maxlength="50" value="${subject.subName}"
                                               class="required form-control"/>
                                    </div>
                                </div>

                                <br/>
                                <br/>
                                <div class="col-sm-8" align="center">
                                    <input type="submit" value="提交">
                                    <input type="reset" value="重置">
                                </div>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>
