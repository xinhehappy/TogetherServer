<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>随心所聚</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="together,party,download">
<meta http-equiv="description" content="Together for download">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="shoutcut icon" href="favicon.ico" />
</head>

<body>
	<div style="margin: 0 auto; width: 960px;">

		<h1 style="text-align: center">这是我的毕业设计：随心所聚.</h1>
		<table border="1" style="width: 600px; margin: auto">
			<tr>
				<th>Type</th>
				<th>Filename</th>
				<th>Size</th>
				<th>Date Added</th>
			<tr>
				<td>nightly</td>
				<td><a href="Download/together-20130512-NIGHTLY.apk">together-20130512-NIGHTLY.apk</a></td>
				<td>4.39M</td>
				<td>2013-05-11 23:06:00</td>
			</tr>
			<tr>
				<td>RC</td>
				<td><a href="Download/together-20130519-RC.apk">together-20130519-RC.apk.apk</a></td>
				<td>6.5M</td>
				<td>2013-05-19 22:46:00</td>
			</tr>
		</table>


	</div>
</body>
</html>
