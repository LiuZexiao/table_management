<template>
    <el-dialog title="技能竞赛"  width="1170px" :visible.sync="research.dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="获奖项目" prop="proName">
                    <el-input v-model="ruleForm.proName" placeholder="请输入获奖项目名称"></el-input>
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
                    <el-form-item label="班级" prop="stuClass">
                        <el-input v-model="ruleForm.stuClass" placeholder="请输入你的班级"></el-input>
                    </el-form-item>
                </template>
                <el-form-item label="系部" prop="department">
                    <el-select v-model="ruleForm.department" placeholder="请选择系部" style="width: 300px">
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
                <el-form-item label="级别" prop="rank">
                    <el-select v-model="ruleForm.rank" placeholder="请选择级别" style="width: 300px">
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
                    <el-form-item prop="certificationTime">
                        <el-date-picker v-model="ruleForm.certificationTime" type="month" placeholder="选择日期" style="width: 300px" ></el-date-picker>
                    </el-form-item>
                </el-form-item> 
                <el-form-item label="发证单位" prop="certificationCompany">
                    <el-input type="textarea"  v-model="ruleForm.certificationCompany" placeholder="请输入发证单位"></el-input>
                </el-form-item>
                <el-form-item label="情况说明" prop="plan">
                    <el-input type="textarea" v-model="ruleForm.plan"></el-input>
                </el-form-item>
                <el-form-item style="margin-left: -90px;">
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
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
                ruleForm: {
                    id: 0,
                    proName: '',
                    name: '',
                    identity: '',
                    departmentId: 0,
                    department: '',
                    rank: '',  // 级别
                    grade: '',  // 等级
                    certificationCompany: '',   //发证单位
                    type: '',
                    semester: '',   // 学期 String
                    plan: '',    // 情况说明
                    certificationTime: '',       
                    stuId: '',
                    stuClass: '',
                    userinfoId: 0
                },
                rules: {
                    proName: [
                        { required: true, message: '请输入项目名称', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    department: [
                        { required: true, message: '请选择系部', trigger: 'change' }
                    ],
                    identity: [
                        { required: true, message: '请选择您的身份', trigger: 'change' }
                    ],
                    rank: [
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
                    stuClass: [
                        { required: true, message: '请输入班级', trigger: 'blur' }
                    ],
                }
            };
        },
        watch:{ 
            research: { 
                handler: function(){
                        this.diolog();
                    },
                 deep:true 
            },
            department: function(){
                const {ruleForm} = this;
                if(this.department ===  "信息工程系"){
                    ruleForm.departmentId = 1
                }else if(this.department === "机械工程系"){
                    ruleForm.departmentId = 2
                }else if(this.department === "电子工程系"){
                    ruleForm.departmentId = 3
                }else if(this.department === "人文社科系"){
                    ruleForm.departmentId = 4
                }else if(this.department === "自动化工程系"){
                    ruleForm.departmentId = 5
                }
            }
        },
        methods: {
            submitForm(formName) {
                const{ruleForm} = this;
                this.upDate(); 
                // 当身份为教师的时候，清除学号与班级
                if(ruleForm.identity === '教师'){
                    ruleForm.styId = ''
                    ruleForm.class = ''
                }
                if(ruleForm.department ===  "信息工程系"){
                    ruleForm.departmentId = 1
                }else if(ruleForm.department === "机械工程系"){
                    ruleForm.departmentId = 2
                }else if(ruleForm.department === "电子工程系"){
                    ruleForm.departmentId = 3
                }else if(ruleForm.department === "人文社科系"){
                    ruleForm.departmentId = 4
                }else if(ruleForm.department === "自动化工程系"){
                    ruleForm.departmentId = 5
                }
                this.$refs[formName].validate((valid) => {
                if (valid) {
                    let Params = {
                        id:  ruleForm.id,
                        proName: ruleForm.proName,
                        name: ruleForm.name,
                        identity: ruleForm.identity,
                        departmentId: ruleForm.departmentId,
                        rank: ruleForm.rank,  // 级别
                        grade: ruleForm.grade,  // 等级
                        certificationCompany: ruleForm.certificationCompany,   //发证单位
                        type: ruleForm.type,
                        semester: ruleForm.semester,   // 学期 String
                        plan: ruleForm.plan,    // 情况说明
                        certificationTime: ruleForm.certificationTime,       // date
                        stuId: ruleForm.stuId,
                        stuClass: ruleForm.stuClass,
                        userinfoId: ruleForm.userinfoId
                    }
                    this.$axios.post('/api/UpdataAwardServlet', Params)
                    	.then((res) => {
                    		if(res.data.code == 0) {
                    			this.$message({
                    				showClose: true,
                    				message: '修改成功\n',
                    				type: 'success',
                    				duration: 1000
                                });
                                this.research.dialogTableVisible = false
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
            diolog(){
               this.ruleForm = this.info;
            },
            upDate(){
                var date = new Date(this.ruleForm.certificationTime)
                var year =  date.getFullYear()
                var month = date.getMonth() + 1
                var datetime = year + '-' + month + '-01'
                this.ruleForm.certificationTime = datetime
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
            }
         },
            
    }
</script>
<style>
    .el-form{
        width: 400px;
        margin: 0 auto;

    }
</style>
