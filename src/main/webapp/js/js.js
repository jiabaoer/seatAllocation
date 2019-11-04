//熏染特殊属性的颜色;
function upDateColor(b,color1="orange",color2="white",color3="rgb(60, 118, 61)"){
    b[0].forEach((v)=>{
        let li = document.getElementsByClassName(v)[0];
        li.style.color = color1;
    })
    b[1].forEach((v,i)=>{
        let li = document.getElementsByClassName(v)[0];
        li.style.color = color2;
    })
    b[2].forEach((v,i)=>{
        let li = document.getElementsByClassName(v)[0];
        li.style.color = color3;
    })
}
//判断不满足的所有用户
function noMeetnoMeetUser(no,number){
    if (no.length>0) {
        $(".panel-heading #showNoMeetUser").text("总人数为"+number+"人，已满足"+(number-no.length)+"人，未满足用户需求共计" + no.length + "人");
        no.forEach((user) => {
            //添加到user元素节点中;
            let cause = "";
            //判断vip用户
            if (user.vip==1) {
                if (user.preference==1) {
                    cause="您是vip预留靠窗用户，但靠窗座位已被比你先订的vip预留用户所占";
                }
                if (user.preference==2) {
                    cause="您是vip预留过道用户，但过道座位已被比你先订的vip预留用户所占";
                }
                if (user.preference==3) {
                    cause="您是vip预留登机口用户，但登机口座位已被比你先订的vip预留用户所占";
                }
            }
            if (user.userGroup!="null") {
                cause="您是坐一起的一组用户，由于没有相邻的座位，只能分开坐";
            }
            if (user.vip==0&&user.preference==1) {
                cause="您是靠窗用户，但靠窗座位已被比你先订的用户或vip预留用户所占";
            }
            if (user.vip==0&&user.preference==2) {
                cause="您是过道用户，但过道座位已被比你先订的用户或vip预留用户所占";
            }
            if (user.vip==0&&user.preference==3) {
                cause="您是登机口用户，但登机口座位已被比你先订的用户或vip预留用户所占";
            }
            //添加到user元素节点中;
            var users = $("<div class='alert alert-success' role='alert'></div>")
                .append("<p></p>")
                .text("身份证号：" + user.idCard + "   姓名：" + user.name +"     座位号："+user.seatallocation.seatSerialNumber+ "----->需求不满足的原因:"+cause);
            $("#user").append(users);
        });
    }else{
        $(".panel-heading #showNoMeetUser").text("总人数为"+number+"人，已满足所有用户需求");
    }

}
//熏染座位
function show(x,parsonageColor="yellow",groupColor="red",time=700){
    x.forEach((v,i)=>{
        setTimeout(()=> {
            let li = document.getElementsByClassName(v.seatallocation.seatSerialNumber)[0];
            //名字
            li.setAttribute("userName", v.name);
            //id身份证
            li.setAttribute("idCard", v.idCard);
            //是否是vip
            li.setAttribute("vip", v.vip);
            //是否是婴儿
            li.setAttribute("baby", v.baby);
            //性别
            li.setAttribute("sex", v.sex);
            //是否残疾
            li.setAttribute("disability", v.disability);
            //偏好
            li.setAttribute("preference", v.preference);
            //组号
            li.setAttribute("userGroup", v.userGroup);
            //时间
            li.setAttribute("createTime", v.seatallocation.createTime);
            //你的座位号
            li.setAttribute("seatSerialNumber", v.seatallocation.seatSerialNumber);
            //给分配座位的熏染颜色
            li.style.color = parsonageColor;
        },i*time)
    });
}
//绑定事件
function showInfo(collections){
    collections.onmouseover = function(){
        if(this.getAttribute("userName")){
            //获取所有的属性；
            let userName = this.attributes.userName.nodeValue,
                idCard = this.attributes.idCard.nodeValue,
                vip = this.attributes.vip.nodeValue,
                baby = this.attributes.baby.nodeValue,
                disability = this.attributes.disability.nodeValue,
                seatSerialNumber = this.attributes.seatSerialNumber.nodeValue,
                sex = this.attributes.sex.nodeValue,
                preference = this.attributes.preference.nodeValue,
                usergroup = this.attributes.userGroup.nodeValue,
                createTime = this.attributes.createTime.nodeValue

            //把数据过滤
            sex=sex==1?"男":"女";
            vip=vip==1?"是":"否";
            //whetherToMeet = whetherToMeet?"是":"否";
            baby=baby==1?"是":"否";
            disability=disability==1?"是":"否";
            if (usergroup=="null"||usergroup=="") {
                usergroup="单人";
            }else{
                usergroup="多人、组号为："+usergroup;
            }
            if(preference==0){
                preference="随机坐";
            }else if(preference==1){
                preference="靠窗";
            }else if(preference==2){
                preference="过道";
            }else{
                preference="登机口";
            }
            if(createTime=="null"){
                createTime="无";
            }
            //把数据放到dom节点中;
            //清除节点内容；
            $(".shows").html("");
            $(".shows")
                .append($("<span></span><br/>")
                    .append("身份证号：" + idCard))
                .append($("<span></span><br/>")
                    .append("姓名：" + userName))
                .append($("<span></span><br/>")
                    .append("性别：" + sex))
                .append($("<span></span><br/>")
                    .append("vip预留：" + vip))
                .append($("<span></span><br/>")
                    .append("携带婴儿：" + baby))
                .append($("<span></span><br/>")
                    .append("是否残疾：" + disability))
                .append($("<span></span><br/>")
                    .append("座位偏好：" + preference))
                .append($("<span></span><br/>")
                    .append("用户组类型：" + usergroup))
                .append($("<span></span><br/>")
                    .append("座位号：" + seatSerialNumber))
                .append($("<span></span><br/>")
                    .append("购票时间：" + createTime))
        }else{
            console.log(1)
            return;
        }
    }
}
//渲染机型1
function renders(){
    let li1 = document.getElementsByClassName("li1")[0],
        li2 = document.getElementsByClassName("li2")[0],
        li3 = document.getElementsByClassName("li3")[0],
        li4 = document.getElementsByClassName("li4")[0],
        li5 = document.getElementsByClassName("li5")[0],
        li6 = document.getElementsByClassName("li6")[0],
        li7 = document.getElementsByClassName("li7")[0];
    let arr =["A","C","D","E","F","G","H","K"]//座位列表
    let seat1 = "";
    let seat2 = "";
    let seat3 = "";
    let seat4 = "";
    let seat5 = "";
    let seat6 = "";
    let seat7 = "";
    let seat8 = "";
    for (let i=37;i<=46;i++){
        seat1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seat2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
        seat3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seat4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seat5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seat6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        if(arr[0]+i !== "A46"||arr[1]+i !== "C46"){
            seat7 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
            seat8 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";
        }

    }
    li1.innerHTML = seat1 + seat2;
    li2.innerHTML = seat3 + seat4 + seat5 + seat6;
    li3.innerHTML = seat7 + seat8;

    let seats1 = "";
    let seats2 = "";
    let seats3 = "";
    let seats4 = "";
    let seats5 = "";
    let seats6 = "";
    let seats7 = "";
    let seats8 = "";

    for (let i=47;i<=61;i++){
        seats1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seats2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
        seats3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seats4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";

    }
    li4.innerHTML = seats1 + seats2;
    li6.innerHTML = seats3 + seats4;

    for (let i=47;i<=57;i++){
        seats5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seats6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seats7 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seats8 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
    }
    li5.innerHTML = seats5 + seats6 + seats7 +seats8;

    let sea1 = "";
    let sea2 = "";
    let sea3 = "";
    for (let i=58;i<=62;i++){
        sea1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        sea2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        sea3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
    }
    li7.innerHTML = sea1 + sea2 + sea3;
}
//渲染机型2
function renders1(){
    let li1 = document.getElementById("li1"),
        li2 = document.getElementById("li2"),
        li3 = document.getElementById("li3"),
        li4 = document.getElementById("li4"),
        li5 = document.getElementById("li5"),
        li6 = document.getElementById("li6"),
        li7 = document.getElementById("li7")
    let arr =["A","C","D","E","F","G","H","K"]//座位列表
    let seat1 = "";
    let seat2 = "";
    let seat3 = "";
    let seat4 = "";
    let seat5 = "";
    let seat6 = "";
    let seat7 = "";
    let seat8 = "";
    for (let i=35;i<=51;i++){
        seat1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seat2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
        seat3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seat4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seat5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seat6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
    }
    for (let i=34;i<=51;i++){
        seat7 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seat8 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";
    }
    li1.innerHTML = seat1 + seat2;
    li2.innerHTML = seat3 + seat4 + seat5 + seat6;
    li3.innerHTML = seat7 + seat8;

    let seats1 = "";
    let seats2 = "";
    let seats3 = "";
    let seats4 = "";
    let seats5 = "";
    let seats6 = "";
    let seats7 = "";
    let seats8 = "";

    for (let i=52;i<=64;i++){
        seats1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seats2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
        seats3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seats4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";

    }
    li4.innerHTML = seats1 + seats2;
    li6.innerHTML = seats3 + seats4;

    for (let i=52;i<=61;i++){
        seats5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seats6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seats7 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seats8 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
    }
    li5.innerHTML = seats5 + seats6 + seats7 +seats8;

    let sea1 = "";
    let sea2 = "";
    let sea3 = "";
    for (let i=62;i<=65;i++){
        sea1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        sea2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        sea3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
    }
    li7.innerHTML = sea1 + sea2 + sea3;
}
//渲染机型3
function renders2(){
    let li1 = document.getElementById("li11"),
        li2 = document.getElementById("li12"),
        li3 = document.getElementById("li13"),
        li4 = document.getElementById("li14"),
        li5 = document.getElementById("li15"),
        li6 = document.getElementById("li16")
    let arr =["A","B","C","H","J","K"]//座位列表
    let seat1 = "";
    let seat2 = "";
    let seat3 = "";
    let seat4 = "";
    let seat5 = "";
    let seat6 = "";
    for (let i=31;i<=36;i++){
        seat1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seat2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seat3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seat4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        seat5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seat6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";
    }
    li1.innerHTML = seat1 + seat2 + seat3;
    li2.innerHTML = seat4 + seat5 + seat6;

    let seats1 = "";
    let seats2 = "";
    let seats3 = "";
    let seats4 = "";
    let seats5 = "";
    let seats6 = "";

    for (let i=37;i<=48;i++){
        seats1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seats2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seats3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seats4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        seats5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seats6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";

    }
    li3.innerHTML = seats1 + seats2 + seats3;
    li4.innerHTML = seats4 + seats5 + seats6;
    let sea1 = "";
    let sea2 = "";
    let sea3 = "";
    let sea4 = "";
    let sea5 = "";
    let sea6 = "";
    for (let i=49;i<=62;i++){
        sea1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        sea2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        sea3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        sea4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        sea5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        sea6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";
    }
    li5.innerHTML = sea1 + sea2 + sea3;
    li6.innerHTML = sea4 + sea5 + sea6;
    $(".H49").css({
        color:"transparent",
    })
}
//渲染机型4
function renders3(){
    let li1 = document.getElementById("li111"),
        li2 = document.getElementById("li222"),
        li3 = document.getElementById("li333"),
        li4 = document.getElementById("li444"),
        li5 = document.getElementById("li555"),
        li6 = document.getElementById("li666")
    let arr =["A","B","C","D","E","F","G","H","J","K"]//座位列表
    let seat1 = "";
    let seat2 = "";
    let seat3 = "";
    let seat4 = "";
    let seat5 = "";
    let seat6 = "";
    for (let i=39;i<=47;i++){
        seat1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[9]+i +"'></li>";
        seat2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[8]+i +"'></li>";
        seat3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seat4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        seat5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seat6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";
    }
    li1.innerHTML = seat1 + seat2 + seat3;
    li3.innerHTML = seat4 + seat5 + seat6;

    let seats1 = "";
    let seats2 = "";
    let seats3 = "";
    let seats4 = "";
    let seats5 = "";
    let seats6 = "";

    for (let i=48;i<=60;i++){
        seats1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[9]+i +"'></li>";
        seats2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[8]+i +"'></li>";
        seats3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[7]+i +"'></li>";
        seats4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[2]+i +"'></li>";
        seats5 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[1]+i +"'></li>";
        seats6 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[0]+i +"'></li>";

    }
    li4.innerHTML = seats1 + seats2 + seats3;
    li6.innerHTML = seats4 + seats5 + seats6;
    let sea2 = "";
    let sea3 = "";
    let sea1 = "";
    let sea4 = "";
    for (let i=39;i<=46;i++){
        sea1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        sea2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        sea3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        sea4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
    }
    li2.innerHTML = sea4 + sea1 + sea2 + sea3;
    let seatp1 = "",
        seatp2 = "",
        seatp3 = "",
        seatp4 = ""
    for (let i=49;i<=61;i++){
        seatp1 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[5]+i +"'></li>";
        seatp2 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[4]+i +"'></li>";
        seatp3 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[3]+i +"'></li>";
        seatp4 += "<li class='iconfont icon-zuoweixuanzhong "+ arr[6]+i +"'></li>";
    }
    li5.innerHTML = seatp4 + seatp1 + seatp2 + seatp3;
    let arrs = ["C47","C57","C58","C59","C60","H57","H58","H59","H60"];
    arrs.forEach((v)=>{
        $("."+v).css({
            color:"transparent",
        })
    })
}
//没选座位的内容
function auto(Arr){
    let box = document.getElementsByClassName("a")[0];
    Arr.forEach((v,i)=>{
        let seat = v.seatallocation.seatSerialNumber
        let  p = document.getElementsByClassName(seat)[0]
        p.setAttribute("indexs",1);
    })
    let Li = box.getElementsByTagName("li");
    for(let i=0;i<Li.length;i++){
        let index = Li[i].getAttribute("indexs");
        if(!index){
            Li[i].onmousemove = function(){
                $(".shows").html("");
                $(".shows")
                    .append($("<span></span><br/>")
                        .append("当前位置没有人选"))
            }
        }else{
            Li[i].onmousemove = null;
            this.showInfo(Li[i])
        }
    }
}
//隐藏层的跟随
function moverOver(){
    let box = document.getElementsByClassName("a")[0];
    let shows = document.getElementsByClassName("shows")[0];
    box.onmouseover= function(){
        shows.style.opacity = "0.8";
        this.onmousemove=function(e){
            if(e.pageX < 650){//在左边显示
                shows.style.left = e.pageX+ 75+ "px";
            }else if(e.pageX >=650) {//在右边显示
                shows.style.left = e.pageX - (shows.clientWidth + 60) + "px";
            }
            //在上面显示
            shows.style.top = e.pageY<450?e.pageY+30+ "px":e.pageY-(shows.clientHeight+30)+"px";
        }
        this.onmouseleave = function(){

            let time = 0;
            shows.style.opacity = "0";
            time = setTimeout(()=>{
                clearTimeout(time)
                this.onmousemover = null;
                this.onmouseup = null;
            },300)
        }
    }
}
//整机请求
function ajaxs(url,datas,_this){
    $.ajax({
        url:url,
        type:"post",
        data:datas,
        success(data){
            let Arr= data.userListShow;
            _this.show(Arr,"blue","yellow",40);
            _this.auto(Arr);
            $("#delallpartdialog").text("分配时间："+data.time+"秒");
            $("#dialog").fadeIn(1000).fadeOut(10000);
        }
    });
}
//报文格式
function messageFormat(Arr,num){
    if(num){
        let arr= [];
        let JSON = {};
        JSON.passenger_num = Arr.length;
        JSON.passenger_info = []
        Arr.forEach((v)=>{
            JSON.passenger_info.push(v);
        })
        arr.push(JSON)
        return arr;
    }else{
        let arr= [];
        let JSON = {};
        JSON.passenger_num = "1";
        JSON.passenger_info = [];
        JSON.passenger_info.push(Arr);
        arr.push(JSON)
        return arr
    }
}
//提示框的内容
function prompt(a,b){
    $("#infos").html("")
    $('#prompt').modal("toggle")
    $("#titleContent").text(a)
    $("#infos").append("<pre>"+b+"</pre>");
}

function fn(data,aValue,_this){
    $.ajax({
        url:"${pageContext.request.getContextPath()}/insertSeatAllocation",
        type:"post",
    });
    let Arr= data.userListShow;
    let info =data.noMeetUserList;
    _this.show(Arr,"blue","yellow",40);
    _this.auto(Arr);
    let number=Arr.length;
    let noMeetNumber=info.length;
    let probability=((number-noMeetNumber)/number*100).toFixed(2);
    _this.probability = probability
    _this.$emit("xxxx",probability,_this.fillRate)
    //分配不满足用户的函数
    _this.noMeetnoMeetUser(info,number);
    aValue.innerText="分配完成";
    $("#delallpartdialog").text("整机分配耗时："+data.time+"秒");
    $("#dialog").fadeIn(1000).fadeOut(10000);
}

function ajaxss(data){
    console.log(data);
    $.ajax({
        url:"${pageContext.request.getContextPath()}/insertSeatAllocation",
        type:"post",
    });

    let info =data.extend.noMeetUserList;
    console.log(info);
    info.forEach((v,i)=>{
        let li = document.getElementsByClassName(v.seatallocation.seatSerialNumber)[0];
        console.log(li);
        setTimeout(() => {
            li.style.color="red";

        }, i * 45)
    })
}