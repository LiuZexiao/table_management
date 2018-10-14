<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">报表信息管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">添加报表</el-breadcrumb-item>
            <el-breadcrumb-item>科研获奖</el-breadcrumb-item>
        </el-breadcrumb>
    
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="科研成果" prop="project">
                <el-input v-model="ruleForm.project" placeholder="请输入科研成果名称"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" placeholder="请输入负责人的姓名"></el-input>
            </el-form-item>
            <el-form-item label="成果类别" prop="categoryId">
                <el-select v-model="category" placeholder="请选择成果类别" style="width: 300px">
                    <el-option label="论文" value="论文"></el-option>
                    <el-option label="专利" value="专利"></el-option>
                    <el-option label="软著" value="软著"></el-option>
                    <el-option label="教材" value="教材"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="系部" prop="departmentId">
                <el-select disabled v-model="department" placeholder="请选择系部" style="width: 300px">
                    <el-option label="信息工程系" value="信息工程系"></el-option>
                    <el-option label="机械工程系" value="机械工程系"></el-option>
                    <el-option label="电子工程系" value="电子工程系"></el-option>
                    <el-option label="人文社科系" value="人文社科系"></el-option>
                    <el-option label="自动化工程系" value="自动化工程系"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="成果来源" prop="proSource">
                <el-input v-model="ruleForm.proSource" placeholder="请输入成果来源"></el-input>
            </el-form-item>
            <el-form-item label="成果级别" prop="level">
                <el-select v-model="ruleForm.level" placeholder="请选择级别" style="width: 300px">
                    <el-option label="国家级" value="国家级"></el-option>
                    <el-option label="省级" value="省级"></el-option>
                    <el-option label="市级" value="市级"></el-option>
                    <el-option label="区级" value="区级"></el-option>
                    <el-option label="校级" value="校级"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="取得时间" required>
                <el-form-item prop="date">
                    <el-date-picker v-model="ruleForm.date" type="month" placeholder="选择日期" style="width: 300px"></el-date-picker>
                </el-form-item>
            </el-form-item> 
            <el-form-item label="参与情况" prop="situation"  placeholder="请输入参与情况">
                <el-input  v-model="ruleForm.situation"></el-input>
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
        category: '',
        ruleForm: {
            project: '',
            name: '',
            level: '',      // 级别
            categoryId: 1,
            proSource: '', // 成果来源
            departmentId: 1,    // 系部
            date: '',       // date
            situation: ''   // 参与情况
        },
        rules: {
            project: [
                { required: true, message: '请输入科研成果名称', trigger: 'blur' }
            ],
            name: [
                { required: true, message: '请输入姓名', trigger: 'blur' },
                { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
            ],
            departmentId: [
                { required: true, message: '请选择系部', trigger: 'change' }
            ],
            level: [
                { required: true, message: '请选择成果级别', trigger: 'change' }
            ],
            categoryId: [
                { required: true, message: '请选择成果类别', trigger: 'change' }
            ],
            situation:  [
                { required: true, message: '请输入参与情况', trigger: 'blur' }
            ],
            proSource:  [
                { required: true, message: '请输入成果来源', trigger: 'blur' }
            ]
        }
      };
    },
    watch: {
        category: function(){
            const {ruleForm} = this;
            if(this.category ===  "论文"){
                ruleForm.categoryId = 1
            }else if(this.category === "专利"){
                ruleForm.categoryId = 2
            }else if(this.category === "软著"){
                ruleForm.categoryId = 3
            }else if(this.category === "教材"){
                ruleForm.categoryId = 4
            }
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
    mounted(){
        this.request();
    },
    methods: {
        submitForm(formName) {
            const{ruleForm} = this

            // 将日期修改为字符串
            var date = new Date(this.ruleForm.date)
            var year =  date.getFullYear()
            var month = date.getMonth() + 1
            var datetime = year + '-' + month + '-1'
            this.ruleForm.date = datetime
            this.$refs[formName].validate((valid) => {
            if (valid) {
                let Params = {
                    proName: ruleForm.project,
                    name: ruleForm.name,
                    departmentId: ruleForm.departmentId,
                    rank: ruleForm.level,  // 级别
                    categoryId: ruleForm.categoryId,
                    situation: ruleForm.situation,    // 参与情况
                    certificationTime: ruleForm.date,       // date
                    proSource: ruleForm.proSource      // 成果来源
                }
                this.$axios.post('/api/AddScientificServlet', Params)
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
