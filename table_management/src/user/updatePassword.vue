<template>
    <div @keydown="show($event)">
        <el-form :model="user" :rules="rules" status-icon ref="user" label-width="100px" class="demo-ruleForm">
            <el-form-item label="旧密码" prop="pass">
                <el-input type="password" v-model="user.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPass">
                <el-input type="password" v-model="user.newPass" auto-complete="off"></el-input>
            </el-form-item>
             <el-form-item label="确认密码" prop="newPass2">
                <el-input type="password" v-model="user.newPass2" auto-complete="off"></el-input>
            </el-form-item>
            <el-button class="finishBtn return" v-show="btn" @click="submitForm()">完成</el-button>
			<el-button class="finishBtn" disabled="disabled" v-show="!btn">完成</el-button>
        </el-form>
    </div>   
</template>

<script>
export default {
    name: "undatePassword",
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                this.btn = false;
                callback(new Error('密码不能为空'));
            } else {
                callback();
            }
        };
        var validateNewPass = (rule, value, callback) => {
            if (value === '') {
                this.btn = false;
                callback(new Error('密码不能为空'));

            } else if(value.length < 6){
                this.btn = false;
                    callback(new Error('密码长度最少为 6 位'));
            }else {
                if (this.user.newPass2 !== '') {
                    this.btn = false;
                    this.$refs.user.validateField('newPass2');
                }
                callback();
            }
        };
        var validateNewPass2 = (rule, value, callback) => {
            if (value === '') {
                this.btn = false;
                callback(new Error('请再次输入密码'));
            } else if(value.length < 6){
                this.btn = false;
                callback(new Error('密码长度最少为 6 位'));
            } else if (value !== this.user.newPass) {
                this.btn = false;
                callback(new Error('两次输入密码不一致!'));
            } else {
                this.btn = true;
                callback();
            }
        };
        return {
            btn: false,
            user: {
                pass: '',
                newPass: '',
                newPass2: ''
            },
            rules:{
                pass:[{required: true,validator: validatePass, trigger: 'blur' }],
                newPass:[{required: true,validator: validateNewPass, trigger: 'blur' }],
                newPass2:[{required: true,validator: validateNewPass2, trigger: 'blur' }]
            }
        }
    },
    methods: {
        show(ev) {
            if ( ev.keyCode === 13 ) {
                this.submitForm('loginFor');
            }
        },
        submitForm() {
            const {user} = this 
            if (!(user.pass && user.newPass && user.newPass2)) {
                return this.$message.error('表单填写不完整！');
            };
            this.$refs.user.validateField('newPass2');
            if(this.btn === false){
                return 
            }
            let params = {
                oldPassword: this.user.pass,
                newPassword: this.user.newPass2
            };
            this.$axios.post('/api/MyUpdataPasswordServlet',params)
            	.then((res) => {
                    console.log(res);
            		if(res.data.code == 0){
            			this.$message({
                            showClose: true,
                            message: '修改成功\n'+'3 秒后自动跳转到管理报表页面',
                            type: 'success',
                            duration: 2000
                        });
            			setTimeout(() => {
                            this.$router.push({path: '/index'});
                        }, 3000);
            		}else{
                        this.$message.error("旧密码输入错误");
                    }
            	})
            	.catch((err) => {
                    console.log(err);
                });
        }
        
        
    }
}
</script>

<style scoped lang="less" >
    .demo-ruleForm{
        width: 500px;
        margin: 0 auto;
        margin-top: 100px;
    }
    .finishBtn{
        background-color: #14CC76;
        color: #fff;
        margin: 25px 0 0 30px;
        width: 94px;
    }
    .finishBtn:hover{
        background-color:  #14CC76;
        color: #c0c4cc;
    }
</style>
