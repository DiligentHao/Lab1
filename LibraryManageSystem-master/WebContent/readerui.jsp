<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>

	<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String id = (String)session.getAttribute("rdrid");//�ᴩ����Reader������ҳ
 %>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
		<title>����ҳ��</title>
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.js"></script>
		<script src="js/application.js"></script>
		<link rel="stylesheet" style="text/css" href="CSS/bootstrap.css" />
		<link rel="stylesheet" href="CSS/alertifycore.css">
    <link rel="stylesheet" href="CSS/bootstrapalertify.css">
		<script src="js/alertify.min.js"></script>
		<style>
		input.span3{height:30px;}
		.modal{width:400px;height:300px;}
				.rightlong{
 float: left;
 width: 150px;
}
.rightlong:focus{
 width: 230px;
}input.search-query{
height:30px;
}
ul.nav{
position:relative;
width:100%;
}
ul#yahh > li:first-child{
padding-left:60px;
}
ul.nav li.dropdown{
position:absolute;
left:70%;
}
		</style>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				 
					<ul class="nav" id="yahh">
					<li><a class="brand" href="#"><span class="add-on"><i
									class="icon-home icon-white"></i> </span>
            ����������ͼ���
          </a></li>
							<li>
								<a href="topten.action" target="readerframe" ><span class="add-on"><i
										class="icon-align-justify icon-white"></i> </span>����ͼ��Top10</a>
							</li>
							<li>
								<a href="showlikelist.action?like.readerid=${sessionScope.rdrid}" target="readerframe"><span class="add-on"><i
										class="icon-shopping-cart icon-white"></i> </span>�ҵ�ѡ�鳵</a>
							</li>
							<li>
								<a href="showborrowlist.action?borrow.readerid=<%=id %>" target="readerframe"><span
									class="add-on"><i class="icon-file icon-white"></i> </span>�ҵĽ��鵥</a>
							</li>
							<li>
								<a href="queryUI.jsp" target="readerframe"><span class="add-on"><i
										class="icon-search icon-white"></i> </span>ͼ������</a>
							</li>
							<li><a href="showNews.action" target="readerframe"><span class="add-on"><i
									class="icon-text-height icon-white"></i> </span>�������</a>
							</li>
							<li><a href="daohang.jsp" target="readerframe"><span class="add-on"><i
									class="icon-map-marker icon-white"></i> </span>���ݵ���</a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="add-on"><i
										class="icon-user icon-white"></i> </span><%=id %>��ѡ��</a>
								<ul class="dropdown-menu">
  <li><a tabindex="-1" href="myinfor_.action?reader.readerid=<%=id %>" target=readerframe><span class="add-on"><i class="icon-file"></i></span>&nbsp;&nbsp;&nbsp;��������</a>
  </li>
  <li class="divider"></li>
  <li><a href="#" onclick="return rexit()"><span class="add-on"><i class="icon-off"></i></span>&nbsp;&nbsp;&nbsp;�˳���¼</a>
  </li>
</ul>
							</li>
						</ul>
				
			</div>
			<script>
			function rexit(){
	   alertify.confirm("<h4 align='center'>ȷ���˳�ϵͳ?</h4>",function (e){
	   if(e){
	      window.location.href="reader_exit.action";	      
	   }else{
	   alertify.error("<h4 class='wryh'>ȡ���˳�!</h4>");
	   }
	   });
	   return false;
	}
			</script>
			</div>
		<!-- Title Over -->
		<div class="container">
		<div class="container">
		<br/><br/>
		</div>
		
		</div>
<iframe src="queryUI.jsp" name="readerframe" width="100%" height="600px" frameborder="0" allowtransparency=tru>
		</iframe>
	</body>
</html>