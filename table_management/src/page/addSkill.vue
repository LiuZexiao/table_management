<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">报表信息管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">添加报表</el-breadcrumb-item>
            <el-breadcrumb-item>技能竞赛</el-breadcrumb-item>
        </el-breadcrumb>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="获奖项目" prop="project">
                <el-input v-model="ruleForm.project" placeholder="请输入获奖项目名称"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" placeholder="请输入项目负责人的姓名"></el-input>
            </el-form-item>
            <el-form-item label="身份" prop="identity">
                <el-radio-group v-model="ruleForm.identity">
                <el-radio label="学生"></el-radio>
                <el-radio label="教师"></el-radio>
                </el-radio-group>
            </el-form-item>
            <template v-if="ruleForm.identity === '学生'">
                <el-form-item  label="学号" prop="stuId">
                    <el-input v-model="ruleForm.stuId" placeholder="请输入你的学号"></el-input>
                </el-form-item>
                <el-form-item label="班级" prop="class">
                    <el-input v-model="ruleForm.class" placeholder="请输入你的班级"></el-input>
                </el-form-item>
            </template>
            <el-form-item label="系部" prop="departmentId">
                <el-select disabled v-model="department" placeholder="请选择系部" style="width: 300px">
                    <el-option label="信息工程系" value="信息工程系"></el-option>
                    <el-option label="机械工程系" value="机械工程系"></el-option>
                    <el-option label="电子工程系" value="电子工程系"></el-option>
                    <el-option label="人文社科系" value="人文社科系"></el-option>
                    <el-option label="自动化工程系" value="自动化工程系"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="竞赛种类" prop="type">
                <el-radio-group v-model="ruleForm.type">
                <el-radio label="学科"></el-radio>
                <el-radio label="技能"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="级别" prop="level">
                <el-select v-model="ruleForm.level" placeholder="请选择级别" style="width: 300px">
                    <el-option label="国家级" value="国家级"></el-option>
                    <el-option label="省级" value="省级"></el-option>
                    <el-option label="市级" value="市级"></el-option>
                    <el-option label="区级" value="区级"></el-option>
                    <el-option label="校级" value="校级"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="等级" prop="grade">
                <el-select v-model="ruleForm.grade" placeholder="请选择等级" style="width: 300px">
                    <el-option label="一等奖" value="一等奖"></el-option>
                    <el-option label="二等奖" value="二等奖"></el-option>
                    <el-option label="三等奖" value="三等奖"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="发证时间" required>
                <el-form-item prop="date">
                    <el-date-picker v-model="ruleForm.date" type="month" placeholder="选择日期" style="width: 300px"></el-date-picker>
                </el-form-item>
            </el-form-item> 
            <el-form-item label="发证单位" prop="certificationCompany">
                <el-input type="textarea"  v-model="ruleForm.certificationCompany" placeholder="请输入发证单位"></el-input>
            </el-form-item>
            <el-form-item label="情况说明" prop="explain">
                <el-input type="textarea" v-model="ruleForm.explain"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        department: '',
        ruleForm: {
            project: '',
            name: '',
            identity: '',
            departmentId: 5,
            level: '',  // 级别
            grade: '',  // 等级
            certificationCompany: '',   //发证单位
            type: '',
            semester: '',   // 学期 String
            explain: '',    // 情况说明
            date: '',       // date
            stuId: '',
            class: ''
        },
        rules: {
            project: [
                { required: true, message: '请输入项目名称', trigger: 'blur' }
            ],
            name: [
                { required: true, message: '请输入姓名', trigger: 'blur' },
                { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
            ],
            departmentId: [
                { required: true, message: '请选择系部', trigger: 'change' }
            ],
            identity: [
                { required: true, message: '请选择您的身份', trigger: 'change' }
            ],
            level: [
                { required: true, message: '请选择获奖级别', trigger: 'change' }
            ],
            grade: [
                { required: true, message: '请选择获奖级别', trigger: 'change' }
            ],
            type: [
                { required: true, message: '请选择竞赛种类', trigger: 'change' }
            ],
            certificationCompany: [
                { required: true, message: '请输入发证单位', trigger: 'blur' }
            ],
            stuId: [
                { required: true, message: '请输入学号', trigger: 'blur' }
            ],
            class: [
                { required: true, message: '请输入班级', trigger: 'blur' }
            ],
        }
      };
    },
    watch: {
        department: function(){
            const {ruleForm} = this 
            if(this.department ===  "信息工程系"){
                ruleForm.departmentId = 1
            }else if( this.department === "机械工程系"){
                ruleForm.departmentId = 2
            }else if(this.department === "电子工程系"){
                ruleForm.departmentId = 3
            }else if(this.department === "人文社科系"){
                ruleForm.departmentId = 4
            }else if(this.department = "自动化工程系"){
                ruleForm.departmentId = 5
            }
        }
    },
    mounted(){
        this.request();
    },
    methods: {
        submitForm(formName) {
            const{ruleForm} = this
            
            this.upDate(); 
            // 当身份为教师的时候，清除学号与班级
            if(ruleForm.identity === '教师'){
                ruleForm.styId = ''
                ruleForm.class = ''
            }
            this.$refs[formName].validate((valid) => {
            if (valid) {
                let params = {
                    name: ruleForm.name,
                    departmentId: ruleForm.departmentId,
                    identity: ruleForm.identity,
                    stuId: ruleForm.stuId,
                    stuClass: ruleForm.class,
                    rank: ruleForm.level,  // 级别
                    proName: ruleForm.project,
                    grade: ruleForm.grade,  // 等级
                    type: ruleForm.type,
                    certificationTime: ruleForm.date,       // date
                    certificationCompany: ruleForm.certificationCompany,   //发证单位
                    semester: ruleForm.semester,   // 学期 String
                    plan: ruleForm.explain,    // 情况说明
                }
                this.$axios.post('/api/AddAwardServlet',params)
					.then((res) => {
						if(res.data.code == 0) {
							this.$message({
								showClose: true,
								message: '修改成功\n' + '3 秒后自动跳转到首页',
								type: 'success',
								duration: 2000
							});
							setTimeout(() => {
								this.$router.push({
									path: '/index'
								});
							}, 3000);
						}else{
                            this.$message.error("服务区响应错误");
                        }
					})
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        upDate(){
            var date = new Date(this.ruleForm.date)
            var year =  date.getFullYear()
            var month = date.getMonth() + 1
            var datetime = year + '-' + month + '-1'
            this.ruleForm.date = datetime
            var a
            var b
            var c
            if (month >= 1 && month <= 2){
                a = year - 1;
                c = '上学期'
                this.ruleForm.semester = a + "~" + year + c
            }else if (month >= 3 && month <= 8){
                a = year - 1;
                c = '下学期'
                this.ruleForm.semester = a + "~" + year + c
            }else{
                b = year + 1;
                c = '上学期'
                this.ruleForm.semester = year + "~" + b + c
            }
        },
         request(){
                this.$axios.get('/api/UserinfoServlet')
                    .then((res) => {
                        this.department = res.data.department;
                        this.loadData(this.currentPage, this.pagesize);
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            }
        }
  }
</script>

<style scoped lang="less">
    .demo-ruleForm{
        width: 400px;
        margin: 0 auto;
        margin-top: 60px;
    }
   
</style>
