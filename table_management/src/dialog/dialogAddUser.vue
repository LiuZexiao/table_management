<template>
    <el-dialog title="添加账号"  width="800px" :visible.sync="researchAdd.dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false" >
            <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-ruleForm">
                <el-form-item label="账号" prop="workNum">
                    <el-input v-model="user.workNum" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="user.name" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="user.password" placeholder="请输入账号密码"></el-input>
                </el-form-item>
                <el-form-item label="系部" prop="departmentId" v-show="show">
                    <el-select v-model="department" placeholder="请选择系部" style="width: 300px">
                        <el-option label="信息工程系" value="信息工程系"></el-option>
                        <el-option label="机械工程系" value="机械工程系"></el-option>
                        <el-option label="电子工程系" value="电子工程系"></el-option>
                        <el-option label="人文社科系" value="人文社科系"></el-option>
                        <el-option label="自动化工程系" value="自动化工程系"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="权限等级" prop="roleId">
                <el-select v-model="role" placeholder="请选择等级" style="width: 300px">
                    <el-option label="用户" value="用户"></el-option>
                    <el-option label="系部管理员" value="系部管理员"></el-option>
                    <el-option label="超级管理员" value="超级管理员" v-show="show"></el-option>
                </el-select>
            </el-form-item>
                <el-form-item style="margin-left: -90px;">
                    <el-button type="primary" @click="submitForm('user')">提交</el-button>
                    <el-button @click="resetForm('user')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>   
</template>
<script>
    export default {
        props: ['researchAdd'],
        data() {
            return {
                department: '',
                role: '',
                show: true,
                user: {
                    workNum: '',
                    name: '',
                    password: '',
                    departmentId: 1,
                    roleId: 1
                    },
                show: false,
                rules: {
                    workNum: [
                        { required: true, message: '请输入账号', trigger: 'blur' }
                    ],
                    name: [
                        { required: false, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    departmentId: [
                        { required: true, message: '请选择系部', trigger: 'change' }
                    ],
                    roleId: [
                        { required: true, message: '请选择权限等级', trigger: 'change' }
                    ],
                    password: [
                        { required: true, message: '请输入账号密码', trigger: 'blur' }
                    ]
                }
            };
        },
        watch: {
            department: function(){
                const {user} = this; 
                if(this.department ===  "信息工程系"){
                    user.departmentId = 1
                }else if(this.department === "机械工程系"){
                    user.departmentId = 2
                }else if(this.department === "电子工程系"){
                    user.departmentId = 3
                }else if(this.department === "人文社科系"){
                    user.departmentId = 4
                }else if(this.department = "自动化工程系"){
                    user.departmentId = 5
                }
            },
            role: function(){
                const {user} = this;
                if(this.role ==="超级管理员"){
                    user.roleId = 1
                }else if(this.role === "系部管理员"){
                    user.roleId = 2
                }else if(this.role === "普通用户"){
                    user.roleId = 3
                }
            }
        },
        mounted(){
            this.request();
        },
        methods: {
            resetForm(user) {
                this.$refs[user].resetFields();
            },
            submitForm(user) {
                this.$refs[user].validate((valid) => {
                if (valid) {
                    let Params = {
                        uName: this.user.name,
                        uPassword: this.user.password,
                        departmentId: this.user.departmentId,
                        roleId: this.user.roleId,  // 级别
                        workNum: this.user.workNum
                    }
                    this.$axios.post('/api/AddUserServlet', Params)
                    	.then((res) => {
                    		if(res.data.code == 0) {
                    			this.$message({
                    				showClose: true,
                    				message: '添加成功\n',
                    				type: 'success',
                                    duration: 2000
                                });
                                this.researchAdd.dialogTableVisible = false
                    		}else{
                                this.$message.error("账号已被注册");

                            }
                            this.user.name = '';
                            this.user.password = '';
                            this.user.workNum = '';
                        })
                        .catch((err) => {
                            console.log(err)
                        })
                } else {
                    console.log('error submit!!');
                    return false;
                }
                });
            },
            request(){
                this.$axios.get('/api/UserinfoServlet')
                    .then((res) => {
                        this.department = res.data.department;
                        if(res.data.roleId === 1){
                            this.show = true;
                        }
                        
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            }
            
        }
            
    }
</script>
<style>
    .el-form{
        width: 400px;
        margin: 0 auto;

    }
</style>
