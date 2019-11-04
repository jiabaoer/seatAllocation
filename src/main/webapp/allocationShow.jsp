<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>航班座位自动分配系统</title>
<script src="js/jq.js"></script>
<script src="js/boot.js"></script>
<link rel="stylesheet" href="css/boot.css">
<link rel="stylesheet" href="css/css4.css">
<link rel="stylesheet" href="icon1/iconfont.css">
<link rel="stylesheet" href="icon/iconfont.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/js.js"></script>
</head>
<body>
	<div id="app">
		<div class="container" v-show="show">
			<div class="modal fade" id="prompt" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="titleContent"></h3>
						</div>
						<div class="modal-body" id="infos"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" click="l">关闭</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="oneAllocation" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog" role="document">
					<div class="modal-content panel panel-primary">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" @click="remove1">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel1">航班座位自动分配系统(单人分配)</h4>
							<br />
							<p>
								本航班是:<span>{{models}}</span> 经济舱座位总数: <span>{{number}}</span>
							</p>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="addForm1" method="post">
								<div class="form-group">
									<label for="idCard" class="col-sm-4 control-label">身份证号</label>
									<div class="col-sm-8">
										<input type="text" name="idCard" class="form-control"
											id="idCard_input" placeholder="身份证号" v-model="idCard"
											@input.prevent="changeCount(0)" /> <span class="help-block"
											v-text="idCard?'':'身份证不能为空'"></span>
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-4 control-label">姓名</label>
									<div class="col-sm-8">
										<input type="text" name="name" class="form-control"
											id="name_input" placeholder="姓名" v-model="userName"
											@input.prevent="changeCount(0)" /> <span class="help-block"
											v-text="userName?'':'姓名不能为空'"></span>
									</div>
								</div>
								<div class="form-group">
									<label for="sex" class="col-sm-4 control-label">性别</label>
									<div class="col-sm-8">
										<label class="radio-inline"> <input type="radio"
											name="sex" id="gender1_add_input1" value="1" v-model="sex">
											男
										</label> <label class="radio-inline"> <input type="radio"
											name="sex" id="gender2_add_input1" value="2" v-model="sex">
											女
										</label>
									</div>
								</div>
								<div class="form-group">
									<label for="special" class="col-sm-4 control-label">个人属性</label>
									<div class="col-sm-8">
										<input type="checkbox" name="vip" id="vip1" value="0"
											v-model="vip" />VIP <input type="checkbox" name="baby"
											id="baby1" value="1" v-model="baby" />带婴儿 <input
											type="checkbox" name="disability" id="disability1" value="2"
											v-model="disability" />残疾
									</div>
								</div>
								<div class="form-group">
									<label for="preference" class="col-sm-4 control-label">个人偏好</label>
									<div class="col-sm-4">
										<select class="btn btn-default" name="preference"
											id="dept_add_select1" v-model="preference">
											<option value="0">随机</option>
											<option value="1">靠窗</option>
											<option value="2">过道</option>
											<option value="3">登机口</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" @click="remove1">关闭</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" @click="ManyPeopleSubmits" v-show="isShow">提交</button>
							<button type="button" class="btn btn-primary" disabled="disabled"
								v-show="!isShow">提交</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="moreAllocation" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
				data-keyboard="false">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content panel panel-primary">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" @click="close">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">航班座位自动分配系统(组分配)</h4>
							<br />
							<p>
								本航班是:<span>{{models}}</span> 经济舱座位总数: <span>{{number}}</span>
							</p>
						</div>
						<div class="modal-body">
							<div class="row">
								<form class="form-horizontal col-md-5" id="addForm"
									method="post">
									<div class="form-group">
										<label for="idCard" class="col-sm-3 control-label">身份证号</label>
										<div class="col-sm-9">
											<input type="text" name="idCard" class="form-control"
												id="idCard_input" placeholder="身份证号" v-model="idCard1"
												@input.prevent="changeCount(1)" /> <span class="help-block"
												v-text="idCard1?'':'身份证号不能为空'"></span>
										</div>
									</div>
									<div class="form-group">
										<label for="name" class="col-sm-3 control-label">姓名</label>
										<div class="col-sm-9">
											<input type="text" name="name" class="form-control"
												id="name_input" placeholder="姓名" v-model="userName1"
												@input.prevent="changeCount(1)" /> <span class="help-block"
												v-text="userName1?'':'姓名不能为空'"></span>
										</div>
									</div>
									<div class="form-group">
										<label for="sex" class="col-sm-3 control-label">性别</label>
										<div class="col-sm-9">
											<label class="radio-inline"> <input type="radio"
												name="sex1" id="gender1_add_input" value="1" v-model="sex1">
												男
											</label> <label class="radio-inline"> <input type="radio"
												name="sex1" id="gender2_add_input" value="2" v-model="sex1">
												女
											</label>
										</div>
									</div>
									<div class="form-group">
										<label for="special" class="col-sm-3 control-label">特殊</label>
										<div class="col-sm-9">
											<input type="checkbox" name="special" id="vip" value="VIP"
												v-model="vip1" />VIP <input type="checkbox" name="special"
												id="baby" value="带婴儿" v-model="baby1" />带婴儿 <input
												type="checkbox" name="special" id="disability" value="残疾"
												v-model="disability1" />残疾
										</div>
									</div>
									<div class="form-group">
										<label for="preference" class="col-sm-3 control-label">座位偏好</label>
										<div class="col-sm-4">
											<select class="btn btn-default" name="preference"
												id="dept_add_select" v-model="preference1">
												<option value="0">随机</option>
												<option value="1">靠窗</option>
												<option value="2">过道</option>
												<option value="3">登机口</option>
											</select>
										</div>
									</div>
									<Button class="btn btn-primary col-md-3" id="addThePassengers"
										@click.prevent="adds" v-show="isShow">添加</Button>
									<Button class="btn btn-primary col-md-3" disabled="disabled"
										v-show="!isShow">添加</Button>
									<Button class="btn btn-danger col-md-3" id="reset"
										@click.prevent="reset">重置</Button>
								</form>

								<ul class="list-group col-md-6" id="usergroups">
									<li class="list-group-item" id="list-group-item"
										v-for="(itme,index) in userGroups" :key="index">
										<dl class="dl-horizontal">
											<dt>你的身份证是:</dt>
											<dd>{{itme.idCard}}</dd>
											<dt>你的名字:</dt>
											<dd>{{itme.name}}</dd>
											<dt>你的的性别:</dt>
											<dd>{{itme.sex | fil}}</dd>
											<dt>你的特殊属性:</dt>
											<dd>vip:{{itme.vip | fil1}} ---baby:{{itme.baby |
												fil1}}---残疾:{{itme.disability | fil1}}</dd>
											<dt>你的偏好属性:</dt>
											<dd>{{itme.preference | fil2}}</dd>
											<dt>
												<button class="btn btn-danger" @click="remove(index)">删除</button>
											</dt>
											<dd></dd>
										</dl>

									</li>
								</ul>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" @click="close">关闭</button>
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#myModal" data-dismiss="modal" @click="colse()"
								v-show="isShows">提交</button>
							<button type="button" class="btn btn-primary" disabled="disabled"
								v-show="!isShows">提交</button>
						</div>
					</div>
				</div>
			</div>
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">航班座位自动分配系统</a>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active" id="bnt" @click="alloction(1)"><a
								href="#" id="alloctions"> 整机分配 </a></li>
							<li @click="k"><a href="#" data-toggle="modal"
								data-target="#oneAllocation"> 单人分配 <span class="sr-only">(current)</span>
							</a></li>
							<li @click="k"><a href="#" data-toggle="modal"
								data-target="#moreAllocation"> 组分配 </a></li>
							<li id="bnt" @click="clearData"><a href="#" id="alloctions">
									清除数据 </a></li>
							<li id="bnt1" @click="alloction(0)"><a href="#"
								id="alloctions1"> 显示不满足 </a></li>
							<li id="bnt2">
								<form class="navbar-form navbar-left">
									<div class="form-group">
										<input type="text" class="form-control"
											placeholder="通过身份证查找座位" v-model="cha">
									</div>
									<button type="submit" class="btn btn-default"
										@click.prevent="chaz">查找</button>
								</form>
							</li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">机型{{models}}<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#"
										@click="flight('flight1','A330-300',8,197,'image/A330-300-2.png')">A330-300</a></li>
									<li><a href="#"
										@click="flight('flight2','A33G',8,242,'image/A33G-2.png')">A33G</a></li>
									<li><a href="#"
										@click="flight('flight3','A321(32L)',6,185,'image/A321(32L)-2.png')">A321(32L)</a></li>
									<li><a href="#"
										@click="flight('flight4','B777B',10,207,'image/B777B-2.png')">B777B</a></li>
								</ul></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
		<div id="app-body" v-show="show">
			<ul class="information clearfix">
				<li><i class="iconfont icon-yinger"></i> <span>婴儿座椅</span></li>
				<li><i class="iconfont icon-cesuo"></i> <span>厕所</span></li>
				<li><i class="iconfont icon-dengjikou"></i> <span>登机口</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: rgb(3, 169, 250);"></i> <span>未占座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: blue;"></i> <span>已占座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: #3c763d;"></i> <span>登机口座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: rgb(255, 255, 255);"></i> <span>vip座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: orange"></i> <span>婴儿座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong" style="color: red"></i>
					<span>不满足座位</span></li>
				<li><i class="iconfont icon-zuoweixuanzhong"
					style="color: yellow"></i> <span>查询座位</span></li>
			</ul>
			<div class="img clearfix">
				<img :src="image">
			</div>
		</div>

		<div class="panel panel-primary" id="dialog"
			style="position: fixed; top: 80px; right: 20px; display: none; font-size: 20px; z-index: 999">
			<div class="panel-heading">
				<h3 class="panel-title">分配时间</h3>
			</div>
			<div class="panel-body" id="delallpartdialog"></div>
		</div>
		<keep-alive> <component :is="radio" ref="ssss"
			@xxx="homes" @xxxx="probability1"></component> </keep-alive>
		<div class="shows" v-show="show">
			<span>请先分配座位</span>
		</div>
		<div class="container" v-show="show">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 class="text-center" id="showNoMeetUser">航班座位自动分配系统</h4>
					<div id="bshi" v-show="fillRate">
						<span class="bshi"></span>满足率 <span class="bshi"></span>不满足率
					</div>
					<div class="progress" v-show="fillRate">
						<div class="progress-bar progress-bar-success"
							:style='probability|star'>{{probability}}%</div>
						<div class="progress-bar progress-bar-warning"
							:style="probability|star1">
							{{(100-probability).toFixed(2)}}%</div>
					</div>
				</div>
				<div class="panel-body" id="user"></div>
			</div>
		</div>
	</div>
	<script src="vue/vue.js"></script>
	<script src="vue/routes.js"></script>
	<!--首页航班图-->
	<template id="home1">
	<section id="home">
		<div class="container">
			<div class="home-intro">
				<div class="animated bounceInRight">
					<h2>
						你好! <br>欢迎来到航班座位自动分配系统
					</h2>

					<button class="submit" @click="sub">点击进入</button>
				</div>
			</div>
		</div>
	</section>

	</template>
	<!--航班组件1-->
	<template id="flight1">
	<div>
		<div class="a">
			<div class="left clearfix">
				<p class="the-letter">
					<span>k</span> <span>H</span> <span>G</span> <span>F</span> <span>E</span>
					<span>D</span> <span>C</span> <span>A</span>
				</p>
			</div>
			<div class="midden clearfix">
				<ul class="li1" ref="li1">
				</ul>
				<ul class="the-Corridor clearfix">
					<span>37</span>
					<span>38</span>
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
				</ul>
				<ul class="li2" ref="li2">
				</ul>
				<ul class="the-Corridor clearfix">
					<span>37</span>
					<span>38</span>
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
				</ul>
				<ul class="li3" ref="li3">
				</ul>
			</div>
			<div class="export">
				<i class="iconfont icon-yinger" id="yinger1"></i> <i
					class="iconfont icon-yinger" id="yinger2"></i>
				<div class="c"></div>
				<i class="iconfont icon-cesuo" id="cesuo1"></i> <i
					class="iconfont icon-cesuo" id="cesuo2"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou2"></i>
			</div>
			<div class="right clearfix">
				<ul class="li4" ref="li4">
				</ul>
				<ul class="the-Corridor2 clearfix">
					<span>47</span>
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
					<span>62</span>
				</ul>
				<div class="right-r">
					<ul class="li5" ref="li5">
					</ul>
					<ul class="li7" ref="li7">
					</ul>
				</div>
				<ul class="the-Corridor2 clearfix">
					<span>47</span>
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
					<span>62</span>
				</ul>
				<ul class="li6" ref="li6">
				</ul>
			</div>
			<div class="end">
				<i class="iconfont icon-cesuo" id="cesuo3"></i> <i
					class="iconfont icon-cesuo" id="cesuo4"></i>
				<div class="c"></div>
				<i class="iconfont icon-dengjikou" id="dengjikou3"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou4"></i>
			</div>
		</div>
	</div>
	</template>
	<!--航班组件2-->
	<template id="flight2">
	<div>
		<div class="a">
			<div class="left clearfix">
				<i class="iconfont icon-yinger" id="yinger11"></i> <i
					class="iconfont icon-yinger" id="yinger12"></i> <i
					class="iconfont icon-yinger" id="yinger13"></i> <i
					class="iconfont icon-yinger" id="yinger14"></i>
				<p class="the-letter">
					<span>k</span> <span>H</span> <span>G</span> <span>F</span> <span>E</span>
					<span>D</span> <span>C</span> <span>A</span>
				</p>
			</div>
			<div class="midden1 clearfix">
				<ul class="li1" ref="li1" id="li1">
				</ul>
				<ul class="the-Corridor3 clearfix">
					<span>34</span>
					<span>35</span>
					<span>36</span>
					<span>37</span>
					<span>38</span>
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
					<span>47</span>
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
				</ul>
				<ul class="li2" ref="li2" id="li2">
				</ul>
				<ul class="the-Corridor3 clearfix">
					<span>34</span>
					<span>35</span>
					<span>36</span>
					<span>37</span>
					<span>38</span>
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
					<span>47</span>
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
				</ul>
				<ul class="li3" ref="li3" id="li3">
				</ul>
			</div>
			<div class="export1">
				<i class="iconfont icon-yinger" id="yinger3"></i> <i
					class="iconfont icon-yinger" id="yinger4"></i>
				<div class="c"></div>
				<i class="iconfont icon-cesuo" id="cesuos1"></i> <i
					class="iconfont icon-cesuo" id="cesuos2"></i> <i
					class="iconfont icon-cesuo" id="cesuo5"></i> <i
					class="iconfont icon-cesuo" id="cesuo6"></i> <i
					class="iconfont icon-dengjikou dengjikou5"></i> <i
					class="iconfont icon-dengjikou dengjikou6"></i>
			</div>
			<div class="right1 clearfix">
				<ul class="li4" ref="li4" id="li4">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
					<span>62</span>
					<span>63</span>
					<span>64</span>
					<span>65</span>
				</ul>
				<div class="right-r">
					<ul class="li5" ref="li5" id="li5">
					</ul>
					<ul class="li7" ref="li7" id="li7">
					</ul>
				</div>
				<ul class="the-Corridor4 clearfix">
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
					<span>62</span>
					<span>63</span>
					<span>64</span>
					<span>65</span>
				</ul>
				<ul class="li6" ref="li6" id="li6">
				</ul>
			</div>
			<div class="end">
				<i class="iconfont icon-cesuo" id="cesuo7"></i> <i
					class="iconfont icon-cesuo" id="cesuo8"></i>
				<div class="c"></div>
				<i class="iconfont icon-dengjikou dengjikou9"></i> <i
					class="iconfont icon-dengjikou dengjikou8"></i>
			</div>
		</div>
	</div>
	</template>
	<!--航班组件3-->
	<template id="flight3">
	<div>
		<div class="a">
			<div class="left clearfix">
				<p class="the-letter1">
					<span>k</span> <span>J</span> <span>H</span> <span>C</span> <span>B</span>
					<span>A</span>
				</p>
			</div>
			<div class="midden2 clearfix">
				<ul id="li11">
				</ul>
				<ul class="the-Corridor3 clearfix">
					<span>31</span>
					<span>32</span>
					<span>33</span>
					<span>34</span>
					<span>35</span>
					<span>36</span>
				</ul>
				<ul id="li12">
				</ul>
			</div>
			<div class="export1">
				<i class="iconfont icon-yinger" id="yinger5"></i> <i
					class="iconfont icon-yinger" id="yinger6"></i>
				<div class="c"></div>
				<i class="iconfont icon-cesuo" id="cesuo10"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou10"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou11"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou12"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou13"></i>
			</div>
			<div class="right2 clearfix">
				<ul id="li13">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>37</span>
					<span>38</span>
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
					<span>47</span>
					<span>48</span>
				</ul>
				<ul id="li14">
				</ul>
			</div>
			<div class="right3 clearfix">
				<ul id="li15">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>49</span>
					<span>50</span>
					<span>51</span>
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
					<span>62</span>
				</ul>
				<ul id="li16">
				</ul>
			</div>
			<div class="end">
				<i class="iconfont icon-cesuo" id="cesuo12"></i> <i
					class="iconfont icon-cesuo" id="cesuo13"></i>
				<div class="c"></div>
				<i class="iconfont icon-dengjikou" id="dengjikou13"></i> <i
					class="iconfont icon-dengjikou" id="dengjikou14"></i>
			</div>
		</div>
	</div>
	</template>
	<!--航班组件4-->
	<template id="flight4">
	<div>
		<div class="a">
			<div class="left clearfix">
				<p class="the-letter4">
					<span>k</span> <span>J</span> <span>H</span> <span>G</span> <span>F</span>
					<span>E</span> <span>D</span> <span>C</span> <span>B</span> <span>A</span>
				</p>
			</div>
			<div class="midden3 clearfix">
				<ul ref="li1" id="li111">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
					<span>47</span>
				</ul>
				<ul ref="li2" id="li222">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>39</span>
					<span>40</span>
					<span>41</span>
					<span>42</span>
					<span>43</span>
					<span>44</span>
					<span>45</span>
					<span>46</span>
					<span>47</span>
				</ul>
				<ul ref="li3" id="li333">
				</ul>
			</div>
			<div class="export1">
				<div class="c"></div>
				<i class="iconfont icon-cesuo" id="cesuo9"></i> <i
					class="iconfont icon-cesuo" id="cesuo14"></i> <i
					class="iconfont icon-cesuo" id="cesuo11"></i> <i
					class="iconfont icon-dengjikou dengjikou5"></i> <i
					class="iconfont icon-dengjikou dengjikou6"></i>
			</div>
			<div class="right4 clearfix">
				<ul ref="li4" id="li444">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
				</ul>
				<ul ref="li5" id="li555">
				</ul>
				<ul class="the-Corridor4 clearfix">
					<span>48</span>
					<span>49</span>
					<span>50</span>
					<span>51</span>
					<span>52</span>
					<span>53</span>
					<span>54</span>
					<span>55</span>
					<span>56</span>
					<span>57</span>
					<span>58</span>
					<span>59</span>
					<span>60</span>
					<span>61</span>
				</ul>
				<ul ref="li6" id="li666">
				</ul>
			</div>
			<div class="end">
				<i class="iconfont icon-cesuo cesuo7"></i> <i
					class="iconfont icon-cesuo cesuo8"></i>
				<div class="c"></div>
				<i class="iconfont icon-dengjikou dengjikou9"></i> <i
					class="iconfont icon-dengjikou dengjikou8"></i>
			</div>
		</div>

	</div>
	</template>
	<script>
    // 方法
    Vue.prototype.upDateColor = upDateColor;
    Vue.prototype.noMeetnoMeetUser = noMeetnoMeetUser;
    Vue.prototype.show = show;
    Vue.prototype.showInfo = showInfo;
    Vue.prototype.renders = renders
    Vue.prototype.renders1 = renders1
    Vue.prototype.renders2 = renders2
    Vue.prototype.renders3 = renders3
    Vue.prototype.auto = auto;
    Vue.prototype.moverOver = moverOver;
    Vue.prototype.messageFormat = messageFormat;
    Vue.prototype.prompt = prompt;
    Vue.prototype.ajaxs = ajaxs;
    Vue.prototype.fn = fn;
    Vue.prototype.ajaxss = ajaxss;
    Vue.$ = window.$;
    //子组件
    let home = {
        template:"#home1",
        methods:{
            sub(){
                let models = "flight1"
                this.$emit("xxx",models)
            }
        },
        created(){
            this.$nextTick(()=>{
                let app = document.getElementById("app");
                app.classList.add("app");
            })
        }
    }
    let flight1 = {
        template:"#flight1",
        data(){
             return{
                fillRate:false,
				flon:false
            }
        },
        filters:{
            star(score){
                return{
                    width:score
                }
            }
        },
        props:["models"],
        created(){
            //座位特殊属性
            let INFO = [
                ["E47","F47"],
                ["A37","C37","D37","E37","F37","G37","H37","K37"],
                ["H47","K47","A47","C47"]
            ];
            //不忙组用户

            this.$nextTick(()=>{
                //熏染页面的位置;
                this.renders();

                // 熏染特殊属性函数
                this.upDateColor(INFO);

                // 鼠标滚随
                this.moverOver()
            })
        },
        methods: {
            //航班1个人分配
            IndividualAssignment(data,models,seating){
                let _this=this
                console.log(data,models,seating);
                console.log(JSON.stringify(data));
                this.ajaxs("${pageContext.request.getContextPath()}/oneAllocation?planeType="+models,
                    {"oneUser":JSON.stringify(data)},_this);
                $("body").css("padding",0);
            },
            //航班1多人分配
            ManyPeopleAssigned(data,models,seating){
                let _this=this
                console.log(data,models,seating);
                console.log(JSON.stringify(data));
                this.ajaxs("${pageContext.request.getContextPath()}/moreAllocation?planeType="+models+"&a="+seating,{"groupUserList":JSON.stringify(data)},_this);
                $("body").css("padding",0);
            },
            //航班1全部分配
            DistributionOfAll(models,seating,n){
                console.log(models,seating);
                let _this=this;
                let aValue=document.getElementById("alloctions");
                let flon = false;
                if(n){
                    if (aValue.innerText=="整机分配") {
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                            	/* console.log(data) */
                                if(data.code==200){
                                    _this.fillRate = true;
                                    _this.fn(data.extend,aValue,_this);
                                    _this.flon = true;

                                }else{
                                    _this.prompt("警告","所有用户已超过所有座位数，不能分配")
                                }
                            }
                        });
                    }else{
                        _this.prompt("提示","请先清空数据")
                    }
                }else{//分配不满足用户的座位
                    if(_this.flon){
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                _this.ajaxss(data)
                                _this.flon = false;
                            }
                        });
                    }else{
                        _this.prompt("警告","请先分配座位/先清空数据")
                    }
                }
            }
        },


    }
    let flight2 = {
        template:"#flight2",
        data(){
            return{
                fillRate:false,
				flon:false
            }
        },
        filters:{
            star(score){
                return{
                    width:score
                }
            }
        },
        created(){
            let INFO = [
                ["E52","F52","A34","E35","F35","H35"],
                ["C34","A35","C35","D35","G35","K35" ],
                ["H52","K52","A52","C52"]
            ]
            this.$nextTick(()=>{
                this.renders1()
                this.upDateColor(INFO);
                this.moverOver()
            })
        },
        methods: {
            //航班2个人分配
            IndividualAssignment(data,models,seating){
                let _this=this
                console.log(data,models,seating);
                //data是一个具体的对象数据;
                //models是机型;
                this.ajaxs("${pageContext.request.getContextPath()}/oneAllocation?planeType="+models,
                    {"oneUser":JSON.stringify(data)},_this);
            },
            //航班2多人分配
            ManyPeopleAssigned(data,models,seating){
                let _this=this
                console.log(data,models,seating);
                //data是一个数组
                //models是机型
                this.ajaxs("${pageContext.request.getContextPath()}/moreAllocation?planeType="+models+"&a="+seating,{"groupUserList":JSON.stringify(data)},_this);
            },
            //航班2全部分配
            DistributionOfAll(models,seating,n){
                console.log(models,seating);
                let _this=this;
                let aValue=document.getElementById("alloctions");
                if(n){
                    if (aValue.innerText=="整机分配") {
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                if(data.code==200){
                                    _this.fillRate = true;
                                    _this.fn(data.extend,aValue,_this);
                                    _this.flon = true;

                                }else{
                                    _this.prompt("警告","所有用户已超过所有座位数，不能分配")
                                }
                            }
                        });
                    }else{
                        _this.prompt("提示","请先清空数据")
                    }
                }else{//分配不满足用户的座位
                    if(_this.flon){
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                _this.ajaxss(data)
                                flon = false;
                            }
                        });
                    }else{
                        _this.prompt("警告","请先分配座位/先清空数据")
                    }
                }
            }
        },

    }
    let flight3 = {
        template:"#flight3",
        data(){
             return{
                fillRate:false,
				flon:false
            }
        },
        filters:{
            star(score){
                return{
                    width:score
                }
            }
        },
        created() {
            let INFO = [
                ["J37","B37"],
                ["A31","B31","C31","H31","J31","K31"],
                ["J49","K49","A49","B49","C49"]
            ]
            this.$nextTick(() => {
                this.renders2()
                this.upDateColor(INFO);
                this.moverOver()
            })
        },
        methods: {
            //航班3个人分配
            IndividualAssignment(data,models,seating){
                console.log(data,models,seating);
                //data是一个具体的对象数据;
                //models是机型;
                let _this=this;
                this.ajaxs("${pageContext.request.getContextPath()}/oneAllocation?planeType="+models,
                    {"oneUser":JSON.stringify(data)},_this);
            },
            //航班3多人分配
            ManyPeopleAssigned(data,models,seating){
                console.log(data,models,seating);
                //data是一个数组
                //models是机型
                let _this=this;
                this.ajaxs("${pageContext.request.getContextPath()}/moreAllocation?planeType="+models+"&a="+seating,{"groupUserList":JSON.stringify(data)},_this);
            },
            //航班3全部分配
            DistributionOfAll(models,seating,n){
                console.log(models,seating);
                let _this=this;
                let aValue=document.getElementById("alloctions");
                if(n){
                    if (aValue.innerText=="整机分配") {
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                _this.fillRate = true;
                                if(data.code==200){
                                    _this.fn(data.extend,aValue,_this);
                                    _this.flon = true;

                                }else{
                                    _this.prompt("警告","所有用户已超过所有座位数，不能分配")
                                }
                            }
                        });
                    }else{
                        _this.prompt("提示","请先清空数据")
                    }
                }else{//分配不满足用户的座位
                    if(_this.flon){
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                _this.ajaxss(data)
                                flon = false;
                            }
                        });
                    }else{
                        _this.prompt("警告","请先分配座位/先清空数据")
                    }
                }
            }
        },
    }
    let flight4 = {
        template:"#flight4",
        data(){
             return{
                fillRate:false,
				flon:false
            }
        },
        filters:{
            star(score){
                return{
                    width:score
                }
            }
        },
        created() {
            let INFO = [
                [],
                ["A39","B39","C39","H39","J39","K39","D39","E39","F39","G39"],
                ["J48","K48","A48","B48","C48","H48"]
            ]
            this.$nextTick(() => {
                this.renders3()
                this.upDateColor(INFO);
                this.moverOver()
            })
        },
        methods: {
            //航班4个人分配
            IndividualAssignment(data,models,seating){
                console.log(data,models,seating);
                //data是一个具体的对象数据;
                //models是机型;
                let _this=this;
                this.ajaxs("${pageContext.request.getContextPath()}/oneAllocation?planeType="+models,
                    {"oneUser":JSON.stringify(data)},_this);
            },
            //航班4多人分配
            ManyPeopleAssigned(data,models,seating){
                console.log(data,models,seating);
                //data是一个数组
                //models是机型
                let _this=this;
                this.ajaxs("${pageContext.request.getContextPath()}/moreAllocation?planeType="+models+"&a="+seating,{"groupUserList":JSON.stringify(data)},_this);
            },
            //航班4全部分配
            DistributionOfAll(models,seating,n){
                console.log(models,seating);
                let _this=this;
                let aValue=document.getElementById("alloctions");
                if(n){
                    if (aValue.innerText=="整机分配") {
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                if(data.code==200){
                                    _this.fillRate = true;
                                    _this.fn(data.extend,aValue,_this);
                                    _this.flon = true;

                                }else{
                                    _this.prompt("警告","所有用户已超过所有座位数，不能分配")
                                }
                            }
                        });
                    }else{
                        _this.prompt("提示","请先清空数据")
                    }
                }else{//分配不满足用户的座位
                    if(_this.flon){
                        $.ajax({
                            url : "${pageContext.request.getContextPath()}/allAllocation?planeType="+models+"&a="+seating,
                            type : "POST",
                            dataType : "JSON",
                            success(data) {
                                _this.ajaxss(data)
                                flon = false;
                            }
                        });
                    }else{
                        _this.prompt("警告","请先分配座位/先清空数据")
                    }
                }


            }
        },
    }

    //根组件
    let vm = new  Vue({
        el:"#app",
        data:{
            radio:"home",
            models:"A330-300",
            seating:8,
            show:false,
            number:197,
            isShow:false,
            isShows:false,
            image:"image/A330-300-2.png",
            idCard:"",
            userName:"",
            sex:'1',
            vip:false,
            baby:false,
            disability:false,
            preference1:"0",
            vip1:false,
            baby1:false,
            disability1:false,
            preference:"0",
            idCard1:"",
            userName1:"",
            sex1:'1',
            userGroups:[],
            dataOfModel1:{},
            cha:"",
            probability:"",
            fillRate:false
        },
        components:{
            home,
            flight1,
            flight2,
            flight3,
            flight4
        },
        methods: {
            flight(model, models, seating, num, image) {
                this.radio = model;
                this.models = models;
                this.seating = seating;
                this.number = num;
                this.image = image;
                localStorage.setItem("image", JSON.stringify(image));
				 this.fillRate = false;
            },
            alloction(n) {
                this.moverOver()
                this.$refs.ssss.DistributionOfAll(this.models, this.seating, n)
                //机型
            },
            //个人数据提交
            ManyPeopleSubmits() {
                this.dataOfModel1.vip = this.vip ? 1 : 0;
                this.dataOfModel1.baby = this.baby ? 1 : 0;
                this.dataOfModel1.disability = this.disability ? 1 : 0;
                this.dataOfModel1.idCard = this.idCard;
                this.dataOfModel1.name = this.userName;
                this.dataOfModel1.sex = this.sex;
                this.dataOfModel1.preference = this.preference;
                let messageFormat = this.messageFormat(this.dataOfModel1, 0);
                this.$refs.ssss.IndividualAssignment(messageFormat, this.models, this.seating)
                $("body").css("padding", 0);
                this.idCard = "",
                    this.userName = "",
                    this.sex = '1',
                    this.vip = false,
                    this.baby = false,
                    this.disability = false,
                    this.preference1 = "0"
            },
            // 个人数据的清除
            remove1() {
                this.idCard = "",
                    this.userName = "",
                    this.sex = '1',
                    this.vip = false,
                    this.baby = false,
                    this.disability = false,
                    this.preference1 = "0"
            },
            //添加数据
            adds() {
                if (this.userGroups.length <= 7) {
                    this.userGroups.push({
                        idCard: this.idCard1,
                        name: this.userName1,
                        sex: this.sex1,
                        vip: this.vip1 ? 1 : 0,
                        baby: this.baby1 ? 1 : 0,
                        disability: this.disability1 ? 1 : 0,
                        preference: this.preference1,
                    })
                } else {
                    alert("乘客人数不能超过八个")
                    return
                }
                this.idCard1 = "";
                this.userName1 = "";
                this.sex1 = '1';
                this.vip1 = false;
                this.baby1 = false;
                this.disability1 = false;
                this.preference1 = "0";
                this.isShow = false;
            },
            //删除数据;
            remove(index) {
                this.userGroups.splice(index, 1)
            },
            //点击取消
            close() {
                this.userGroups = [];
                this.idCard1 = "";
                this.userName1 = "";
                this.sex1 = '1';
                this.vip1 = false;
                this.baby1 = false;
                this.disability1 = false;
                this.preference1 = "0";
            },
            //多人数据提交
            colse() {
                let messageFormat = this.messageFormat(this.userGroups, 1);
                this.$refs.ssss.ManyPeopleAssigned(messageFormat, this.models, this.seating);
                this.userGroups = [];
                this.idCard1 = "";
                this.userName1 = "";
                this.sex1 = '1';
                this.vip1 = false;
                this.baby1 = false;
                this.disability1 = false;
                this.preference1 = "0";
                $("body").css("padding", 0);
            },
            //首页
            homes(models) {
                this.radio = models
                this.show = true;

                this.$nextTick(() => {
                    let app = document.getElementById("app");
                    app.classList.remove("app");

                })
            },
            //重置
            reset() {
                this.preference1 = "0",
                    this.vip1 = false,
                    this.baby1 = false,
                    this.disability1 = false,
                    this.preference = "0",
                    this.idCard1 = "",
                    this.userName1 = "",
                    this.sex1 = '1'
            },
            //判断文本框
            changeCount(n) {
                if (n) {
                    this.isShow = (this.userName1 == "" || this.idCard1 == "") ? false : true;
                } else {
                    this.isShow = (this.userName == "" || this.idCard == "") ? false : true;
                }

            },
            k() {
                this.isShow = false;
            },
            l() {
                $('body').on('hidden.bs.modal', '.modal', function () {
                    $(this).removeData('bs.modal');
                });
            },
            clearData() {
                localStorage.setItem("serialNumber", JSON.stringify(this.radio))
                localStorage.setItem("models", JSON.stringify(this.models))
                localStorage.setItem("seating", this.seating)
                window.location.reload();
            },
            chaz() {
                let _this = this
                if(this.fillRate){
                    if (_this.cha !== "") {
                        $.ajax({
                            url: "${pageContext.request.getContextPath()}/checkUsers",
                            type: "POST",
                            data: {idCard: _this.cha},
                            dataType: "JSON",
                            success(data) {
                            	console.log(data)
                                //返回的数据
                                if (data.code == 200) {
                                    data.extend.userListShow.forEach((v) => {
                                        let li = document.getElementsByClassName(v.seatallocation.seatSerialNumber)[0]
                                        li.style.color = "yellow";
                                    })
                                } else {
                                    _this.prompt("提示", "没有找到相关的用户")
                                    return
                                }
                            }
                        });
                        _this.cha = "";
                    } else {
                        _this.prompt("提示", "输入框不能为空")
                        return
                    }
                }else{
                    _this.prompt("提示", "请先分配座位")
                }


            },
            probability1(probability,fillRate){
                this.probability = probability
                this.fillRate = fillRate
            }
        },
        watch:{
                radio:{
                    handler(){
                        localStorage.setItem("航班号",JSON.stringify(this.radio))
                    }
                },
                userGroups(){
                    if(this.userGroups.length<2){
                        this.isShows = false;
                    }else{
                        this.isShows = true;
                    }
                }
            },
        filters:{
                fil(value){
                    return value == 1? "男":"女";
                },
                fil1(value){
                    return value ? "是":"否";
                },
                fil2(value){
                    if(value==0) return "随机";
                    else if(value==1) return "靠窗";
                    else if(value==2) return "过道";
                    else if (value==3) return "登机口";
                },
                star(score){
                    return{
                        width:score+"%"
                    }
                },
                star1(score1){
                    return{
                        width:(100-score1)+"%"
                    }
                },
            },
        created(){
                let num = JSON.parse(localStorage.getItem("serialNumber")) || 0;
                if(num){
                    this.show = true;
                    this.radio = JSON.parse(localStorage.getItem("航班号")) || this.radio;
                    this.models = JSON.parse(localStorage.getItem("models")) || this.models;
                    this.image = JSON.parse(localStorage.getItem("image")) || this.image;
                    this.seating = localStorage.getItem("seating")||this.image;
                }else{
                    this.radio = "home"
                    localStorage.setItem("image",JSON.stringify(this.image));
                }

                localStorage.setItem("serialNumber",JSON.stringify(""))
            },
        });

</script>
</body>
</html>