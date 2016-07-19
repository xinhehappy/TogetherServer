<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1,maximum-scale=1" name="viewport"></meta>
<title>news</title>
<link href="style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
	<article>
		<section class="post">
			<div class="post-head">
				<div class="post-head-photo">
					<a href="">
						<img alt="img" src="image/head.png"></img>
					</a>
				</div>
				<p>
					<a href="">随心所聚</a>:
					大家好，我是官方机器人，我们的随心所聚1.0以及发布啦，欢迎大家下载。

					<span>5月17日14:00</span>
				</p>
			</div>
			<div class="pic-box">
				<a href="">
					<img src="图标.png">
				</a>
			</div>
			<div class="post-info">
				<div class="f-l">
					<a href=""><em>评</em>3</a>
					<a href=""><em>转</em>3</a>
				</div>
				<div class="f-r">
				</div>
			</div>
		</section>

		<section class="post">
			<div class="post-head">
				<div class="post-head-photo">
					<a href="">
						<img alt="img" src="图标.png"></img>
					</a>
				</div>
				<p>
					<a href="">随心所聚</a>:
					大家好，我是官方机器人，我们的随心所聚1.0以及发布啦，欢迎大家下载。

					<span>5月17日14:00</span>
				</p>
			</div>
			<div class="pic-box">
				<a href="">
					<img src="avatar.png">
				</a>
			</div>
			<div class="post-info">
				<div class="f-l">
					<a href=""><em>评</em>3</a>
					<a href=""><em>转</em>3</a>
				</div>
			</div>
		</section>

		<section id="publish">
			<h2 class="f-110">发布状态</h2>
			<form enctype="multipart/form-data" action="" method="post">
				<p class="textarea-box">
					<textarea id="post" name="status"></textarea>
				</p>
				<p class="operate">
					<a onclick="showFile(event);" href="#">
						<img src="picture.img"></img>
					</a>
				</p>
				<p id="fileBox" style="display:block;">
					<input type="file" name="pic"></input>
					<br/>小于5M的JPEG、GIF、PNG格式图片
				</p>
				<p class="submit-box">
					<input type="submit" name="post" value="发布"></input>
			</form>
		</section>
	</article>
</body>
</html>