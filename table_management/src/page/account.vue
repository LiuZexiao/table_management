<template>
    <div>
        <div style="float: left; margin: 20px 0 10px 50px; display: flex">
            <el-select v-show="depart" clearable v-model="department" placeholder="系部" style="width: 180px">
                <el-option label="信息工程系" value="信息工程系"></el-option>
                <el-option label="机械工程系" value="机械工程系"></el-option>
                <el-option label="电子工程系" value="电子工程系"></el-option>
                <el-option label="人文社科系" value="人文社科系"></el-option>
                <el-option label="自动化工程系" value="自动化工程系"></el-option>
            </el-select>
            <el-input v-model="selectTable.workNum" placeholder="请输入账号" style="width: 250px"></el-input>
            <el-button type="primary" round icon="el-icon-search" style="margin-left: 20px" @click="select"></el-button>
            <el-button type="primary" round icon="el-icon-refresh" style="margin-left: 10px" @click="refresh"></el-button>
            <el-button size="medium " type="primary" @click="Add">添加账号</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="del" >批量删除</el-button>
            <el-upload id="excel" name="excel" action="/api/Upload" enctype="multipart/form-data">
                <el-button style="float: right; margin: 2px 0px 0px 10px;" size="medium" type="primary">导入账号</el-button>
            </el-upload>
        </div>
        <el-table ref="multipleTable" :data="tableData" border tooltip-effect="dark" style="width: 100%;" @selection-change="handleSelectionChange" >
            <el-table-column type="selection" width="35"></el-table-column>
            <el-table-column prop="workNum" label="账号" width="250"></el-table-column>
            <el-table-column prop="uName" label="姓名" width="150"></el-table-column>
            <el-table-column prop="uPassword" label="密码" width="250" show-overflow-tooltip></el-table-column>            
            <el-table-column prop="department" label="系部" width="200" show-overflow-tooltip></el-table-column>
            <el-table-column prop="role" label="权限等级" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></el-button>
                    <el-button size="mini" icon="el-icon-delete" type="danger" @click="handleDelete(scope.$index, scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block" v-show="display" style=" position:fixed; bottom:30px; left:20%; right:5%;"> <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"  :current-page.sync="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pagesize" layout="prev, pager, next, jumper" :total="totalCount" ></el-pagination>
        </div>
        <my-dialogAddUser :researchAdd='researchAdd'></my-dialogAddUser>    
        <my-dialogAccount :research='research' :info='info'></my-dialogAccount>   
    </div>
</template>

<script>
    import dialogAccount from '../dialog/dialogAccount'
    import dialogAddUser from '../dialog/dialogAddUser'
    export default {
        name: 'account',
        data() {
            return {
                department: '',
                tableData: [],
                multipleSelection: [],// 当前页选中的数据
                selectTable: {
                    departmentId: 0,
                    workNum: ''
                },
                research:{
                    dialogTableVisible: false
                },
                researchAdd:{
                    dialogTableVisible: false
                },
                admin:{
                    departmentId: 1,
                    roleId: 1
                },
                info: {},
                currentPage: 1,     // 当前页
                pagesize: 8,       //默认每页数据量
                totalCount: 1,   //数据总数
                display: false,   // 分页是否显示
                depart: true,        //按部门查询是否显示
                delete: [],
            }
        },
        components: {
            'my-dialogAccount': dialogAccount,
            'my-dialogAddUser': dialogAddUser
        },
        mounted(){
            this.request();
        },
        watch: {
            dialogTableVisible :function(){
                this.request(); 
            },
            tableData: function(){
                if (this.tableData.length >= this.pagesize){
                    this.display = true
                }
            },
            department: function(){
                const {selectTable} = this;
                if(this.department ===  "信息工程系"){
                    selectTable.departmentId = 1
                }else if(this.department === "机械工程系"){
                    selectTable.departmentId = 2
                }else if(this.department === "电子工程系"){
                    selectTable.departmentId = 3
                }else if(this.department === "人文社科系"){
                    selectTable.departmentId = 4
                }else if(this.department === "自动化工程系"){
                    selectTable.departmentId = 5
                }
            },
        },
        methods: {
            Add(){
                this.researchAdd.dialogTableVisible = true;
                this.request();
            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleSizeChange(val) {  //每页显示数据量变更
                this.pagesize  = val;
                this.loadData(this.currentPage, this.pagesize);
            },

            handleCurrentChange(val) {    //页码变更
                this.currentPage = val;
                this.loadData(this.currentPage, this.pagesize);
                
            },
            handleEdit(index, row) {  // 编辑
                this.info = row;
                this.research.dialogTableVisible = true;
            },
            handleDelete(index, row) {    //删除
                this.delete = [row.id];
                var jsonLength = 1;
                this.$confirm('此操作将永久删除该账号, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let Params = {
                        id: this.delete,
                        jsonLength: jsonLength
                    }
                    this.$axios.post('/api/DeleteUserServlet',Params)
                            .then((res) => {
                                if(res.data.code == 0){
                                    this.$message({
                                        type: 'success',
                                        message: '删除成功!'
                                        });
                                     this.loadData(this.currentPage, this.pagesize);
                                }else{
                                    this.$message.error('删除失败');
                                }
                            })
                            .catch((err) => {
                                console.log(err)
                            })
                }).catch(() => {
                    this.$message({
                    type: 'info',
                    message: '已取消删除'
                    });          
                });

            },
            del(){
                var jsonLength = 0; // 获取 json 对象的长度
                for(var item in this.multipleSelection){
                    jsonLength++;
                }
                if(jsonLength === 0){
                    this.$message({
                        message: '请选择需要删除的内容',
                        type: 'warning'
                    });
                    return false;
                }
                for(var i = 0; i < jsonLength; i++){
                    this.delete[i] = this.multipleSelection[i].id
                }
                this.$confirm('此操作将永久删除该账号, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let Params = {
                        id: this.delete,
                        jsonLength: jsonLength
                    }
                    this.$axios.post('/api/DeleteUserServlet',Params)
                            .then((res) => {
                                if(res.data.code == 0){
                                    this.$message({
                                        type: 'success',
                                        message: '删除成功!'
                                        });
                                     this.loadData(this.currentPage, this.pagesize);
                                }else{
                                    this.$message.error('删除失败');
                                }
                            })
                            .catch((err) => {
                                console.log(err)
                            })
                }).catch(() => {
                    this.$message({
                    type: 'info',
                    message: '已取消删除'
                    });          
                });
            },
            select() {    // 查询条件
                if (this.department == '' && this.selectTable.workNum == ''){
                    this.$message({
                            showClose: true,
                            message: '请选择查询条件！\n',
                            type: 'warning',
                            duration: 1000
                        });
                    return false
                }
                this.loadData(this.currentPage, this.pagesize);
                this.$message({
                    showClose: true,
                    message: '查询成功\n',
                    type: 'success',
                    duration: 2000
                });
            },
            refresh() { 
                this.department = '';
                this.selectTable.departmentId = 0;
                this.selectTable.workNum = '';
                this.loadData(this.currentPage, this.pagesize);
                this.$message({
                    showClose: true,
                    message: '刷新成功\n',
                    type: 'success',
                    duration: 1000
                });
            },
            loadData(pageNum, pageSize){    // 当前页码；每页所含有的数据量
            
                let params = {
                        pageNum: pageNum,
                        pageSize:pageSize,
                        departmentId: this.selectTable.departmentId,
                        workNum: this.selectTable.workNum,
                        roleId: this.admin.roleId
                    }
                if(this.admin.roleId === 2){
                    params.departmentId = this.admin.departmentId;
                }
                this.$axios.post('/api/UserPageServlet',params)
                            .then((res) => {
                                this.tableData = res.data.tableData;
                                this.totalCount = res.data.totalCount;
                                
                
                            })
                            .catch((err) => {
                                console.log(err)
                            })

                            
            },
            request(){
                
                const{admin} = this;
                const{tableData} = this;
                this.$axios.get('/api/UserinfoServlet')
                    .then((res) => {
                        admin.departmentId = res.data.departmentId;
                        admin.roleId = res.data.roleId;
                        if(admin.roleId === 2){
                            this.selectTable.departmentId = admin.departmentId;
                            this.depart = false;
                        }
                        this.loadData(this.currentPage, this.pagesize);
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            }
        }
    }
</script>

<style>
    .cell{
        text-align: left;
    }
    .upload-demo{
        float: right;
    }
</style>
