<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="reviewList">
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${appendReviewlist }" var="dto">
					<table>
						<tr>
							<td colspan="3" class="left">${dto.mName }</td>
							<td colspan="3" class="left">${dto.rContent }</td>
							<td class="left">${dto.rRdate }</td>
							<c:if test="${dto.mId==member.mId }">
								<a href="${conPath }/review_delete.do?rNo=${dto.rNo}movieNo=${movie_content_view.movieNo }&pageNum=${param.pageNum }">X</a>
							</c:if>
						</tr>
					</table>
				</c:forEach>
			</c:if>
		</div>
</body>
</html>