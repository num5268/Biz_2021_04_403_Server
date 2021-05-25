<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${rootPath}/static/css/home.css?ver2021-05-20-005" 
rel="stylesheet" />
<style>
	button{
		padding:5px;
		outline: 0;
		border:0;
		border-radius: 5px;
		background-color: #c6f6d8;
		color:black;
		width: 120px;
	}
	button:hover{
		cursor:pointer;
		box-shadow: 2px 2px 2px rgba(0,0,0,0.3);
	}
	
</style>
<script>

document.addEventListener("DOMContentLoaded",function(){
	
	document.querySelector("button.btn_save")
	.addEventListener("click",function(ev){
		document.location.href 
		= "${rootPath}/todolist/insert"
	})	
	
	document.querySelector("button.btn_save")
	.addEventListener("click",function(ev){
		
		let dom = document;
		
		let td_work
		= dom.querySelector("input[name='td_work']");
		let td_date 
		= dom.querySelector("input[name='td_date']");
		let td_time 
		= dom.querySelector("input[name='td_time']");
		let td_place
		= dom.querySelector("input[name='td_place']");
		
		dom.querySelector("form.v1").submit();
	}) 
}) 
</script>
</head>
<body>
<form class="v1" method="POST" >
	<h1>TO DO List</h1>
	
	<input name="td_date" placeholder="작성일자" >
	<input name="td_time" placeholder="작성시각" >
	<input name="td_work" placeholder="할일">
	<input name="td_place" placeholder="장소" >
	<table id="add">
	<button class="btn_save" type="button">추가</button>
	<tr>
		<th>No.</th>
		<th>할일</th>
		<th>작성일자</th>
		<th>작성시간</th>
		<th>장소</th>
	</tr>
	<c:forEach items="${TDLIST}" var="TD" varStatus="index">
		<tr>
			<td>${index.count}</td>
			<td>${TD.td_work}</td>
			<td>${TD.td_date}</td>
			<td>${TD.td_time}</td>
			<td>${TD.td_place}</td>
		</tr>
	</c:forEach>
	</table>
	</form>
</body>
</html>