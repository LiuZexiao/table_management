<template>
    <el-dialog title="科研获奖"  width="1170px" :visible.sync="research.dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="科研成果" prop="proName">
                    <el-input v-model="ruleForm.proName" placeholder="请输入科研成果名称"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入负责人的姓名"></el-input>
                </el-form-item>
                <el-form-item label="成果类别" prop="categoryName">
                    <el-select v-model="ruleForm.categoryName" placeholder="请选择成果类别" style="width: 300px">
                        <el-option label="论文" value="论文"></el-option>
                        <el-option label="专利" value="专利"></el-option>
                        <el-option label="软著" value="软著"></el-option>
                        <el-option label="教材" value="教材"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="系部" prop="department">
                    <el-select v-model="ruleForm.department" placeholder="请选择系部" style="width: 300px">
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
                <el-form-item label="成果级别" prop="rank">
                    <el-select v-model="ruleForm.rank" placeholder="请选择级别" style="width: 300px">
                        <el-option label="国家级" value="国家级"></el-option>
                        <el-option label="省级" value="省级"></el-option>
                        <el-option label="市级" value="市级"></el-option>
                        <el-option label="区级" value="区级"></el-option>
                        <el-option label="校级" value="校级"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="取得时间" required>
                    <el-form-item prop="date">
                        <el-date-picker v-model="ruleForm.certificationTime" type="month" placeholder="选择日期" style="width: 300px"></el-date-picker>
                    </el-form-item>
                </el-form-item> 
                <el-form-item label="参与情况" prop="situation"  placeholder="请输入参与情况">
                    <el-input  v-model="ruleForm.situation"></el-input>
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
            category: '',
            ruleForm: {
                id: 0,
                proName: '',
                name: '',
                rank: '',      // 级别
                categoryId: 0,
                categoryName: '',
                proSource: '', // 成果来源
                departmentId: 0,    // 系部
                department: '',    // 系部
                certificationTime: '',       // date
                situation: '',  // 参与情况
                userinfoId: 0
            },
            rules: {
                proName: [
                    { required: true, message: '请输入科研成果名称', trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                ],
                department: [
                    { required: true, message: '请选择系部', trigger: 'change' }
                ],
                rank: [
                    { required: true, message: '请选择成果级别', trigger: 'change' }
                ],
                categoryName: [
                    { required: true, message: '请选择成果类别', trigger: 'change' }
                ],
                situation:  [
                    { required: true, message: '请输入参与情况', trigger: 'blur' }
                ],
                proSource:  [
                    { required: true, message: '请输入成果来源', trigger: 'blur' }
                ]
            }
        }
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
                }else{
                    ruleForm.departmentId = 0
                }
            },
            category: function(){
                const {selectTable} = this;
                if(this.categoryName ===  "论文"){
                    selectTable.categoryId = 1
                }else if(this.categoryName === "专利"){
                    selectTable.categoryId = 2
                }else if(this.categoryName === "软著"){
                    selectTable.categoryId = 3
                }else if(this.categoryName === "教材"){
                    selectTable.categoryId = 4
                }else{
                    selectTable.categoryId = 0
                }
            },
        },
    methods: {
        submitForm(formName) {
            const{ruleForm} = this
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
            }else{
                ruleForm.departmentId = 0
            }
            if(ruleForm.categoryName ===  "论文"){
                ruleForm.categoryId = 1
            }else if(ruleForm.categoryName === "专利"){
                ruleForm.categoryId = 2
            }else if(ruleForm.categoryName === "软著"){
                ruleForm.categoryId = 3
            }else if(ruleForm.categoryName === "教材"){
                ruleForm.categoryId = 4
            }else{
                ruleForm.categoryId = 0
            }
             // 将日期修改为字符串
            var date = new Date(this.ruleForm.certificationTime)
            var year =  date.getFullYear()
            var month = date.getMonth() + 1
            var datetime = year + '-' + month + '-1'
            this.ruleForm.certificationTime = datetime

            this.$refs[formName].validate((valid) => {
            if (valid) {
                let Params = {
                    id:  ruleForm.id,
                    proName: ruleForm.proName,
                    name: ruleForm.name,
                    departmentId: ruleForm.departmentId,
                    rank: ruleForm.rank,  // 级别
                    categoryId: ruleForm.categoryId,
                    situation: ruleForm.situation,    // 参与情况
                    certificationTime: ruleForm.certificationTime,       // date
                    proSource: ruleForm.proSource,     // 成果来源
                    userinfoId: ruleForm.userinfoId
                }
                this.$axios.post('/api/UpdataScientificServlet', Params)
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
        resetForm(ruleForm) {
            this.$refs[ruleForm].resetFields();
        },
        diolog(){
               this.ruleForm = this.info;
                
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
