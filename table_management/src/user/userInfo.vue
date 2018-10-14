<template>
    
    <div @keydown="show($event)">
        <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="uName" >
                <el-input v-model="user.uName" placeholder="请输入您的真实姓名"></el-input>
            </el-form-item>
            <el-form-item label="所属系部" prop="departmentId">
                <el-select v-model="department" placeholder="请选择系部" style="width: 300px;">
                    <el-option label="信息工程系" value="信息工程系"></el-option>
                    <el-option label="机械工程系" value="机械工程系"></el-option>
                    <el-option label="电子工程系" value="电子工程系"></el-option>
                    <el-option label="人文社科系" value="人文社科系"></el-option>
                    <el-option label="自动化工程系" value="自动化工程系"></el-option>
                </el-select>
            </el-form-item>
            <el-button class="finishBtn return" v-show="btn" @click="submitForm()">完成</el-button>
            <el-button class="finishBtn" disabled="disabled" v-show="!btn">完成</el-button>
        </el-form>
    </div>
</template>

<script>
export default {
    name: "userInfo",
    data() {
      return {
        btn: true,
        department: '',
        user: {
            uName: '',
            departmentId: 0
        },
        rules: {
            uName:[
                { required: true, message: '请输入姓名', trigger: 'blur' },
                { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
            ],
            departmentId: [
                { required: true, message: '请选择系部', trigger: 'change' }
            ],
        }
      }
    },
     mounted() {
      this.request();
    },
    watch: {
        department: function(){
            const {user} = this 
            if(this.department ===  "信息工程系"){
                user.departmentId = 1
            }else if( this.department === "机械工程系"){
                user.departmentId = 2
            }else if(this.department === "电子工程系"){
                user.departmentId = 3
            }else if(this.department === "人文社科系"){
                user.departmentId = 4
            }else if(this.department = "自动化工程系"){
                user.departmentId = 5
            }
        }
    },
    methods: {
        sign() {
            const {user} = this 
            if(user.departmentId === 1){
                this.department = "信息工程系"
            }else if(user.departmentId === 2){
                this.department = "机械工程系"
            }else if(user.departmentId === 3){
                this.department = "电子工程系"
            }else if(user.departmentId === 4){
                this.department = "人文社科系"
            }else if(user.departmentId === 5){
                this.department = "自动化工程系"
            }
        },
        show(ev) {
            if ( ev.keyCode === 13 ) {
                this.submitForm('loginFor');
            }
        },
        submitForm() {
            const {user} = this 
            if (!(user.uName && user.departmentId)) {
                return this.$message.error('表单填写不完整！');
            };
            let params = {
                uName: user.uName,
                departmentId: user.departmentId
            };
            this.$axios.post('/api/MyUpdataInfoServlet',params)
            	.then((res) => {
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
                        this.$message.error("修改失败！");
                    }
            	})
            	.catch((err) => {
                    console.log(err);
                });
                this.sign();
        },
        request(){
            const{user} = this
            this.$axios.get('/api/UserinfoServlet')
                .then((res) => {
                    user.uName = res.data.uName;
                    user.departmentId = res.data.departmentId;
                    this.sign();
                })
                .catch((err) => {
                    console.log(err);
                });
      }
    }      
}
</script>

<style scoped lang="less">
    .demo-ruleForm{
        width: 400px;
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
