<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/exam/common.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/exam/index.css'/>">
</head>

<body>
	<main>
		<form id="listForm" name="listForm" class="w-75">
			<input type="hidden" id="uuid" name="uuid">
			<button type="button" class="btn btn-secondary" id="showing" style="float: right; margin-bottom: 2%; margin-left: 1%">상영등록</button>
			<button type="button" class="btn btn-primary" id="addition" style="float: right; margin-bottom: 2%;">등록</button>
			<table class="table table-bordered">
            	<thead class="table-primary">
                	<tr class="text-center">
                    	<th scope="col">TITLE</th>
                    	<th scope="col">CONTENTS</th>
                    	<th scope="col">INSERT_TIME</th>
                    	<th scope="col">UPDATE_TIME</th>
                    	<th scope="col">UPDATE_BN</th>
                    	<th scope="col">DELETE_BN</th>
                	</tr>
            	</thead>
            	<tbody class="table-warning">
            		<c:forEach var="data" items="${examList}">	
            			<tr>
            				<td><c:out value="${data.title}"/></td>
            				<td><a href="javascript:void(0);" onclick="view_page('${data.uuid}');"><c:out value="${data.contents}"/></a></td>
            				<td class="text-center"><c:out value="${data.insertTime}"/></td>
            				<td class="text-center"><c:out value="${data.updateTime}"/></td>
            				<td class="text-center"><button type="button" class="btn btn-success" onclick="update_page('${data.uuid}');">수정</button></td>
            				<td class="text-center"><button type="button" class="btn btn-danger" onclick="delete_contents('${data.uuid}');">삭제</button></td>
            			</tr>
            		</c:forEach>
            	</tbody>
        	</table>
			<div id="paging" class="text-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" />
				<input type="hidden" id="pageIndex" name="pageIndex" />
			</div>
		</form>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
	</script>
	<script src="<c:url value='/resources/js/exam/common.js'/>"></script>
    <script src="<c:url value='/resources/js/exam/index.js'/>"></script>
</body>

</html>