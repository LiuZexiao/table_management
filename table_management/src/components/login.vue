<template>
    <div id="wrapper" class="center" @keydown="show($event)">
        <div class="header"><img src="../../static/img/icon.png" alt=""><span class="title">报表信息管理平台</span></div>
        <el-carousel indicator-position="none" style="height: 430px;">
            <el-carousel-item  style="height:380px;">
                <img src="../../static/img/banner3.jpg" class="icon" alt="" >
            </el-carousel-item>
        </el-carousel>
        <div class="login">
            <h1 class="login-title">用户登录</h1>
            <p class="loginSub">请使用账号密码登录</p>
            <el-form class="form" :model="user" status-icon :rules="rules2" ref="user" label-width="0px" >
                <el-form-item prop="workNum">
                    <el-input type="text" v-model="user.workNum" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="upassword">
                    <el-input type="password" v-model="user.upassword"  placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item class="loginBtn_box">
                    <el-button type="primary" @click="submitForm" class="login_success">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="foot">南京机电职业技术学院&nbsp;|&nbsp;信息工程系&nbsp;|&nbsp;软件工作室</div>
    </div>
</template>

<script>
export default {
    name: 'login',
    data() {
        var validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        var  validateWorkNum = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                callback();
            }
        };
        return {
            user: {
                workNum: '',         // 工号
                upassword: '',        // 密码
                departmentId: '1',   // 部门id
                userRole: '0'        // 权限等级
            },
            // url:[
            //     '../../static/img/banner3.png'
            // ],
            rules2: {
                workNum: [
                    {validator: validateWorkNum, trigger: 'blur'}
                ],
                upassword: [
                    { validator: validatePassword, trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        show(ev) {
                if ( ev.keyCode === 13 ) {
                    this.submitForm();
                }
         },
        submitForm() {
            let params = {
                    workNum: this.user.workNum,
                    upassword: this.user.upassword,
            };
            this.$axios.post('/api/LoginServlet',params)
            .then((res) => {
                if(res.data.code === 0){
                    sessionStorage.setItem('accessToken' , this.user.workNum);
                     this.$message({
                            message: '登录成功！',
                            type: 'success'
                            });
                            setTimeout(() => {
                                this.$router.push({path: '/index'});
                            }, 500);
                }else{
                    this.$message.error("账号或密码错误");
                }
                           
            }).catch((res) => {
                this.message.error("服务器请求错误")
            })
        }
    }
}
</script>

<style scoped lang="less">
 
    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }
    
    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
    /*  */
    @keyframes fade-in {
        0% {opacity: 0;}/*初始状态 透明度为0*/
        40% {opacity: 0;}/*过渡状态 透明度为0*/
        100% {opacity: 1;}/*结束状态 透明度为1*/
    }
    @-webkit-keyframes fade-in {/*针对webkit内核*/
        0% {opacity: 0;}
        40% {opacity: 0;}
        100% {opacity: 1;}
    }
    #wrapper {
        animation: fade-in;/*动画名称*/
        animation-duration: 1.5s;/*动画持续时间*/
        -webkit-animation:fade-in 1.5s;/*针对webkit内核*/
    }
    .header{
        margin: 4% 0 1% 18% ;
        img{
            width: 50px;
            height: 60px;
            // position: absolute;
            // top: 65px;
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
        }
        .title{
            display: inline-block;
            font-size: 30px;
            letter-spacing: -1px;
            color: #414141;
            vertical-align: middle;
        }
    }
    .icon{
        height: 100%;
        width: 100%;
        opacity:0.8;
    }
    .login{
        position: absolute;
        top: 200px;
        left: 60%;
        z-index: 2;
        width: 370px;
        height: 300px;
        background: #fff;
        opacity: 0.9;
        .login-title {
            font-size: 18px;
            color: #333333;
            margin: 28px 0 10px 28px;
        }
        .loginSub {
            margin: 0 0 15px 28px;
            color: #6a7075;
            font-size: 14px;
            letter-spacing: 1px;
        }
        .form{
            margin: 0 0 15px 28px;  
            width: 300px;
            .login_success {
                width: 61%;
                font-size: 16px;
                width: 100%;
                letter-spacing: 1px;
            }
        }
        
    }
    .foot{
            margin: 20px;
            text-align:center;
            font-size: 18px;
        }
  
</style>