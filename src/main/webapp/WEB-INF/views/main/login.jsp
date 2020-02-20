<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>

<body>
	<div class="content">
		<form class="login-form" action="/login" method="POST">
			<h3 class="form-title">用户登录</h3>
			<div class="form-group">
				<label>用户名称</label>
				<input type="text" autocomplete="off" placeholder="用户名称" name="username"/>
			</div>
			<div class="form-group">
				<label>用户密码</label>
				<input type="password" autocomplete="off" placeholder="用户密码" name="password"/>
			</div>
			<div class="form-actions">
				<button id="loginButtonId" type="submit" class="btn btn-success uppercase">登陆</button> 
			</div>
		</form>
	</div>
</body>
</html>