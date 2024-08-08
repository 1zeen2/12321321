<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 언어와 인코딩 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 코어 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dress List</title> <!-- 페이지 제목 -->
</head>
<body>
    <h1>Dress List</h1> <!-- 페이지 제목 -->
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
                <td>${dress.d_no}</td> <!-- 드레스 번호 -->
                <td>${dress.d_subject}</td> <!-- 드레스 제목 -->
                <td>${dress.d_price}</td> <!-- 드레스 가격 -->
                <td><img src="${dress.d_image}" width="100" height="100"></td> <!-- 드레스 이미지 -->
                <td>
                    <form action="DressServlet" method="post"> <!-- 상세보기 폼 -->
                        <input type="hidden" name="d_no" value="${dress.d_no}"> <!-- 드레스 번호를 숨김 필드로 전달 -->
                        <input type="submit" value="Detail"> <!-- 상세보기 버튼 -->
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
