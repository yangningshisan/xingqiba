<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script>
	
	$(function(){
		initType()
		initData(1);
	})
	
	function initType(){
		$.ajax({
			type:"post",
			data:{
			},
			dataType:"json",
			url:"/Maven01/book/getBookTypes.do",
			success:function(data){
				var bookTypes = $("select[name=bookType]");
				//循环绑定下拉框
				$.each(data,function(i,v){
					bookTypes.append("<option value="+v["id"]+">"+v["type_name"]+"</option>");
				})
				console.log(data);
			},
			error:function(){
				alert("请求失败");
			}
		});
	}
	
	function initData(i){
		var bookType = $("select[name=bookType]");
		var bookName = $("input[name=bookName]");
		var isBorrow = $("select[name=isBorrow]");
		$.ajax({
			type:"post",
			data:{
				index:i,
				bookName:bookName.val(),
				bookType:bookType.val(),
				isBorrow:isBorrow.val()
			},
			dataType:"json",
			url:"/Maven01/book/getBooks.do",
			success:function(data){
				//循环绑定table
				var tbody = $("table tbody");
				tbody.html("");
				$.each(data["pageUtil"]["list"],function(i,v){
					var tr = "<tr>"
						+"<td>"+v["book_id"]+"</td>"
						+"<td>"+v["type_name"]+"</td>"
						+"<td>"+v["book_name"]+"</td>"
						+"<td>"+v["book_author"]+"</td>"
						+"<td>"+v["publish_press"]+"</td>"
						+"<td>"+(v["is_borrow"]==0?"<a href='#'>申请借阅</a>":"已借阅")+"</td>"
						+"</tr>";
					tbody.append(tr);
				});
				var prePage= data["pageUtil"]["index"]-1;
				var nextPage= data["pageUtil"]["index"]+1;
				if(prePage<=0){
					prePage = 1;
				}
				if(nextPage>data["pageUtil"]["totalPage"]){
					nextPage = data["pageUtil"]["totalPage"];
				}
				var page = "<tr><td colspan=6>"
						+"<a href='javascript:void(0)' onclick='initData(1)'>首页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+prePage+")'>上一页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+nextPage+")'>下一页</a>"
						+"<a href='javascript:void(0)' onclick='initData("+data["pageUtil"]["totalPage"]+")'>末页</a>"
						+"</td></tr>";
				tbody.append(page);
				console.log(data);
			},
			error:function(){
				alert("请求失败");
			}
		})
	}
	
	</script>
  </head>
  
  <body>
    <div>
    	<h1>图书借阅系统</h1>
    	<form action="/maven01/book/getBooks.do" method="post">
    	<p>
    		图书分类：<select name="bookType">
    					<option value="-1">-请选择-</option>
    					<c:forEach var="type" items="${bookTypes }">
    						<option value="${type.id }">${type.type_name }</option>
    					</c:forEach>
    				</select>
    		图书名称：<input name="bookName"/>
    		
    		是否借阅：<select name="isBorrow">
    					<option value="-1">-请选择-</option>
    					<option value="1">是</option>
    					<option value="0">否</option>
    				</select>
    				<input type="submit" value="查询"/>
    	</p>
    	</form>
    	<p>
    		<span>当前用户:${user.user_code }</span>
    		<a href="#">退出</a>
    	</p>
    	<table>
    		<thead>
    			<tr>
    				<th>图书编号</th>
    				<th>图书分类</th>
    				<th>图书名称</th>
    				<th>作者</th>
    				<th>出版社</th>
    				<th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="book" items="${pageList.list }">
    				<tr>
    					<td>${book.book_code }</td>
    					<td>${book.type_name }</td>
    					<td>${book.book_name }</td>
    					<td>${book.book_author }</td>
    					<td>${book.publish_press }</td>
    					<c:if test="${book.is_borrow==1}">
    						<td>已借阅</td>
    					</c:if>
    					<c:if test="${book.is_borrow==0}">
    						<td><a href="#">申请借阅</a></td>
    					</c:if>
    					<!--
    					<td>${book.is_borrow==1?'是':'否' }</td>
    					
    					
    					 0.待支付，1.已支付，2.已收货，3.已评价，其他.已失效
    					<c:choose>
    						<c:when test="${book.is_borrow==0}">
    							<td>待支付</td>
    						</c:when>
    						<c:when test="${book.is_borrow==1}">
    							<td>已支付</td>
    						</c:when>
    						<c:when test="${book.is_borrow==2}">
    							<td>已收货</td>
    						</c:when>
    						<c:when test="${book.is_borrow==3}">
    							<td>已评价</td>
    						</c:when>
    						<c:otherwise>
    							<td>已失效</td>
    						</c:otherwise>
    					</c:choose>
    					 -->
    				</tr>
    			</c:forEach>
    			<tr>
    				<td colspan="6">
    					<a href="/BooksSys03/book/getBooks.do?index=1">首页</a>|
    					<c:if test="${pageList.index==1 }">
    						上一页
    					</c:if>
    					<c:if test="${pageList.index>1 }">
    						<a href="/BooksSys03/book/getBooks.do?index=${pageList.index-1 }">上一页</a>|
    					</c:if>
    					<c:if test="${pageList.index==pageList.totalPage }">
    						下一页
    					</c:if>
    					<c:if test="${pageList.index<pageList.totalPage }">
	    					<a href="/BooksSys03/book/getBooks.do?&index=${pageList.index+1 }">下一页</a>|
    					</c:if>
    					<a href="/BooksSys03/book/getBooks.do?&index=${pageList.totalPage }">末页</a>
    					&nbsp;
    					当前第${pageList.index}页
    					共${pageList.totalPage }页
    				</td>
    			</tr>
    		</tbody>
    	</table>
    </div>
  </body>
</html>
