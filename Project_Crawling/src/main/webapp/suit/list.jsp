<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 언어와 인코딩 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL 코어 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Suit List</title> <!-- 페이지 제목 -->
</head>
<body>
    <h1>Suit List</h1> <!-- 페이지 제목 -->
    <table border="1"> <!-- 테이블 시작 -->
        <tr>
            <th>No</th> <!-- 테이블 헤더: 번호 -->
            <th>Subject</th> <!-- 테이블 헤더: 제목 -->
            <th>Price</th> <!-- 테이블 헤더: 가격 -->
            <th>Image</th> <!-- 테이블 헤더: 이미지 -->
            <th>Detail</th> <!-- 테이블 헤더: 상세보기 -->
        </tr>
        <c:forEach var="dress" items="${dressList}"> <!-- dressList를 순회하면서 각 드레스 정보를 출력 -->
            <tr>
                <td>${suit.su_no}</td> <!-- 드레스 번호 -->
                <td>${suit.su_subject}</td> <!-- 드레스 제목 -->
                <td>${suit.su_price}</td> <!-- 드레스 가격 -->
                <td><img src="${suit.su_image}" width="100" height="100"></td> <!-- 드레스 이미지 -->
                <td>
                    <form action="SuitServlet" method="post"> <!-- 상세보기 폼 -->
                        <input type="hidden" name="d_no" value="${suit.su_no}"> <!-- 드레스 번호를 숨김 필드로 전달 -->
                        <input type="submit" value="Detail"> <!-- 상세보기 버튼 -->
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
