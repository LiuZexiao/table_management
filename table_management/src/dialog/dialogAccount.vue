<template>
    <el-dialog title="账号信息"  width="1170px" :visible.sync="research.dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false" >
            <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-ruleForm">
                <el-form-item label="账号" prop="workNum">
                    <el-input v-model="user.workNum" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="uName">
                    <el-input v-model="user.uName" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="uPassword">
                    <el-input v-model="user.uPassword" placeholder="请输入账号密码"></el-input>
                </el-form-item>
                <el-form-item label="系部" prop="departmentId">
                    <el-select v-model="user.department" placeholder="请选择系部" style="width: 300px">
                        <el-option label="信息工程系" value="信息工程系"></el-option>
                        <el-option label="机械工程系" value="机械工程系"></el-option>
                        <el-option label="电子工程系" value="电子工程系"></el-option>
                        <el-option label="人文社科系" value="人文社科系"></el-option>
                        <el-option label="自动化工程系" value="自动化工程系"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="权限等级"  prop="roleId">
                    <el-select v-model="user.role"  placeholder="请选择等级" style="width: 300px">
                        <el-option label="普通用户" value="普通用户"></el-option>
                        <el-option label="系部管理员" value="系部管理员"></el-option>
                        <el-option   v-show="show" label="超级管理员" value="超级管理员"></el-option>
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
        props: ['research','info'],
        data() {
            return {
                department: '',
                role: '',
                user: {
                    id: 0,
                    workNum: 'G171301148',
                    uName: '王小虎',
                    uPassword: '12345678',
                    departmentId: 0,
                    department: '',
                    roleId: 0,
                    role: ''
                },
                admin: {
                    departmentId: 0,
                    roleId: 0,
                },
                show: false,    
                rules: {
                    workNum: [
                        { required: true, message: '请输入账号', trigger: 'blur' }
                    ],
                    uName: [
                        { required: false, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    departmentId: [
                        { required: true, message: '请选择系部', trigger: 'change' }
                    ],
                    roleId: [
                        { required: true, message: '请选择权限等级', trigger: 'change' }
                    ],
                    uPassword: [
                        { required: true, message: '请输入账号密码', trigger: 'blur' }
                    ]
                }
            };
        }, 
        watch:{ 
            research: { 
                handler: function(){
                            this.diolog();
                        },
                 deep:true 
            } 
        },
        methods: {
            submitForm(user) {
                this.department = this.user.department;
                this.role = this.user.role;
                if(this.department ===  "信息工程系"){
                    this.user.departmentId = 1
                }else if(this.department === "机械工程系"){
                    this.user.departmentId = 2
                }else if(this.department === "电子工程系"){
                    this.user.departmentId = 3
                }else if(this.department === "人文社科系"){
                    this.user.departmentId = 4
                }else if(this.department === "自动化工程系"){
                    this.user.departmentId = 5
                };
                if(this.role === "超级管理员"){
                    this.user.roleId = 1
                }else if(this.role === "系部管理员"){
                    this.user.roleId = 2
                }else if(this.role === "普通用户"){
                    this.user.roleId = 3
                };
                this.$refs[user].validate((valid) => {
                if (valid) {
                    let Params = {
                        id : this.user.id,
                        uName: this.user.uName,
                        uPassword: this.user.uPassword,
                        departmentId: this.user.departmentId,
                        roleId: this.user.roleId,  // 级别
                        workNum: this.user.workNum
                    }
                    this.$axios.post('/api/UpdataUserServlet', Params)
                    	.then((res) => {
                    		if(res.data.code == 0) {
                    			this.$message({
                    				showClose: true,
                    				message: '修改成功\n',
                    				type: 'success',
                    				duration: 2000
                                });
                                this.research.dialogTableVisible = false
                    		}
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
            resetForm(user) {
                this.$refs[user].resetFields();
            },
            diolog(){
               this.user = this.info;
                // if(this.admin.roleId === 2 && this.user.roleId === 1){
                //         this.show = false;
                // }else{
                //     this.show = true;
                // }
            },
            request(){
                this.$axios.get('/api/UserinfoServlet')
                        .then((res) => {
                            this.admin.roleId = res.data.roleId;
                            this.admin.departmentId = res.data.departmentId
                            if(res.data.roleId === 1){
                                this.show = true;
                            }
                        })
                        .catch((err) => {
                            console.log(err)
                        })
            }

        },
        mounted(){
            this.request();
        }
    }
</script>
<style>
    .el-form{
        width: 400px;
        margin: 0 auto;

    }
</style>
