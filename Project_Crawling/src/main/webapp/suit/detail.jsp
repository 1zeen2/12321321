<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 언어와 인코딩 설정 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dress Detail</title> <!-- 페이지 제목 -->
</head>
<body>
    <h1>Suit Detail</h1> <!-- 페이지 제목 -->
    <table border="1"> <!-- 테이블 시작 -->
        <tr>
            <th>No</th> <!-- 테이블 헤더: 번호 -->
<%--             <td>${dress.d_no}</td> <!-- 드레스 번호 --> --%>
            <td>${suit.su_no}</td> <!-- 드레스 번호 -->
        </tr>
        <tr>
            <th>Subject</th> <!-- 테이블 헤더: 제목 -->
<%--             <td>${dress.d_subject}</td> <!-- 드레스 제목 --> --%>
            <td>${suit.su_subject}</td> <!-- 드레스 제목 -->
        </tr>
        <tr>
            <th>Price</th> <!-- 테이블 헤더: 가격 -->
<%--             <td>${dress.d_price}</td> <!-- 드레스 가격 --> --%>
            <td>${suit.su_price}</td> <!-- 드레스 가격 -->
        </tr>
        <tr>
            <th>Image</th> <!-- 테이블 헤더: 이미지 -->
<%--             <td><img src="${dress.d_image}" width="200" height="200"></td> <!-- 드레스 이미지 --> --%>
            <td><img src="${suit.su_image}" width="200" height="200"></td> <!-- 드레스 이미지 -->
        </tr>
    </table>
    <a href="SuitServlet">Back to List</a> <!-- 리스트로 돌아가는 링크 -->
</body>
</html>
