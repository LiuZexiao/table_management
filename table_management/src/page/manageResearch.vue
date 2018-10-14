<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">报表信息管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ }">管理报表</el-breadcrumb-item>
            <el-breadcrumb-item>科研获奖</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="float: left; margin: 20px 0 10px 50px; display: flex">
            <el-date-picker v-model="time" style="width: 250px" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2"></el-date-picker>
            <el-select clearable v-model="department" v-show="depart" placeholder="部门" style="width: 120px">
                <el-option label="信息工程系" value="信息工程系"></el-option>
                <el-option label="机械工程系" value="机械工程系"></el-option>
                <el-option label="电子工程系" value="电子工程系"></el-option>
                <el-option label="人文社科系" value="人文社科系"></el-option>
                <el-option label="自动化工程系" value="自动化工程系"></el-option>
            </el-select>
            <el-input v-show="userDis" v-model="selectTable.uName" placeholder="填报人" style="width: 90px"></el-input>
            <el-select clearable v-model="selectTable.rank" placeholder="级别" style="width: 90px">
                <el-option label="国家级" value="国家级"></el-option>
                <el-option label="省级" value="省级"></el-option>
                <el-option label="市级" value="市级"></el-option>
                <el-option label="区级" value="区级"></el-option>
                <el-option label="校级" value="校级"></el-option>
            </el-select>
            <el-select clearable v-model="category" placeholder="成果类别" style="width: 120px">
                <el-option label="论文" value="论文"></el-option>
                <el-option label="专利" value="专利"></el-option>
                <el-option label="软著" value="软著"></el-option>
                <el-option label="教材" value="教材"></el-option>
            </el-select>
            <el-button type="primary" round icon="el-icon-search" style="margin-left: 20px" @click="select"></el-button>
            <el-button type="primary" round icon="el-icon-refresh" style="margin-left: 10px" @click="refresh"></el-button>
            <el-button type="danger" icon="el-icon-delete" @click="del">批量删除</el-button>
            <el-button @click="output" size="medium " type="primary">批量导出</el-button>
        </div>
        <el-table ref="multipleTable" :data="tableData" border tooltip-effect="dark" style="width: 100%;" @selection-change="handleSelectionChange" >
            <el-table-column type="selection" width="35"></el-table-column>
            <el-table-column prop="certificationTime" label="获奖时间" width="150" >
                <!-- <template slot-scope="scope">{{ scope.row.date }}</template> -->
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="100"></el-table-column>
            <el-table-column prop="department" label="系部" width="120" show-overflow-tooltip></el-table-column>
            <el-table-column prop="proName" label="科研成果名称" show-overflow-tooltip></el-table-column>
            <el-table-column prop="categoryName" label="成果类别" width="80"  show-overflow-tooltip></el-table-column>
            <el-table-column prop="proSource" label="成果来源" show-overflow-tooltip></el-table-column>
            <el-table-column prop="rank" label="成果级别"  width="80"  show-overflow-tooltip></el-table-column>
            <el-table-column prop="uName" label="填报人" width="100" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)"></el-button>
                    <el-button size="mini" icon="el-icon-delete" type="danger" @click="handleDelete(scope.$index, scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        
        <div class="block" v-show="display" style=" position:fixed; bottom:30px; left:20%; right:5%;"> <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="pagesize" layout="prev, pager, next, jumper" :total="totalCount"></el-pagination>
        </div>

       <my-dialogResearch :research='research' :info='info'></my-dialogResearch>   

    </div>
</template>

<script>
    import dialogResearch from '../dialog/dialogResearch'

    export default {
        name: 'manageResearch',
        data() {
            return {
                department: '',
                category: '',
                time:[],
                tableData: [],
                multipleSelection: [],
                 info:{},
                selectTable: {
                    departmentId: 0,
                    rank: '',
                    categoryId: 0, // 成果类别
                    clock1: '',
                    clock2: '',
                    uName: ''
                },
                pickerOptions2: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近六个月',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 180);
                        picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一年',
                        onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 360);
                        picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                research:{
                    dialogTableVisible: false
                },
                admin:{
                    userinfoId: 0,
                    departmentId: 1,
                    roleId: 1
                },
                currentPage: 1,     // 当前页
                pagesize: 8,       //默认每页数据量
                totalCount: 10,   //默认数据总数
                display: false,   // 分页是否显示
                depart: true,
                delete: [],
                userDis: true,
                local: '',
                }
        },
        components: {
            'my-dialogResearch': dialogResearch
        },
        watch:{
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
                }else{
                    selectTable.departmentId = 0
                }
            },
            category: function(){
                const {selectTable} = this;
                if(this.category ===  "论文"){
                    selectTable.categoryId = 1
                }else if(this.category === "专利"){
                    selectTable.categoryId = 2
                }else if(this.category === "软著"){
                    selectTable.categoryId = 3
                }else if(this.category === "教材"){
                    selectTable.categoryId = 4
                }else{
                     selectTable.categoryId = 0
                }
            },
            tableData: function(){
                if (this.tableData.length >= this.pagesize){
                    this.display = true
                }
            },
            time: function(){
                if(this.time.length === 0){
                    this.selectTable.clock1='';
                    this.selectTable.clock2='';
                }else{
                    var date = new Date(this.time[0])
                    var year =  date.getFullYear()
                    var month = date.getMonth() + 1
                    var datetime = year + '-' + month + '-1'
                    this.selectTable.clock1 = datetime
                    var date1 = new Date(this.time[1])
                    var year1 =  date1.getFullYear()
                    var month1 = date1.getMonth() + 1
                    var datetime1 = year1 + '-' + month1 + '-1'
                    this.selectTable.clock2 = datetime1
                }
            }
        },
        methods: {
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleSizeChange(val) {   // 当前页
                this.loadData(this.currentPage, this.pagesize);
            },
            handleCurrentChange(val) {    // 当前页
                this.loadData(this.currentPage, this.pagesize);
            },
            handleEdit(index, row) {  // 编辑
                this.info = row;
                this.research.dialogTableVisible = true;
                // this.requestInfo();
            },
            handleDelete(index, row) {    //删除
            console.log("正在删除");
                this.delete = [row.id];
                var jsonLength = 1;
                this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let Params = {
                        id: this.delete,
                        jsonLength: jsonLength
                    }
                    this.$axios.post('/api/DeleteScientificServlet',Params)
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
                 console.log("正在删除");
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
                this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let Params = {
                        id: this.delete,
                        jsonLength: jsonLength
                    }
                    console.log("正在删除");
                    this.$axios.post('/api/DeleteScientificServlet',Params)
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
                const{selectTable} = this
                let Params = {
                        department: this.department,
                        rank: selectTable.rank,  // 级别
                        categoryId: selectTable.categoryId,
                        clock1: selectTable.clock1,   
                        clock2: selectTable.clock2,   
                        uName: selectTable.uName
                    }
                if (!(Params.identity || this.department || Params.rank || this.category || selectTable.uName ||  Params.clock1 ||Params.clock2 )){
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
                    duration: 1000
                });
            },
            refresh(){    // 刷新
                var newSelectTable = {// 清空筛选条件
                            departmentId: 0,
                            categoryId: 0,
                            rank: '',
                            clock1: '',
                            clock2: '',
                            uName: ''
                        }
                        this.department = '',
                        this.category='',
                        this.time = [];
                    this.selectTable = newSelectTable;
                    this.request()
                    this.$message({
                        showClose: true,
                        message: '刷新成功\n',
                        type: 'success',
                        duration: 1000
                    });
            },
            output(){
                 const{selectTable} = this
                let params = {
                            departmentId: selectTable.departmentId,
                            rank: selectTable.rank,  // 级别
                            clock1: selectTable.clock1,       // date
                            clock2: selectTable.clock2,
                            uName: selectTable.uName,
                            categoryId:  selectTable.categoryId,
                            userinfoId: this.admin.userinfoId
                            }
                if(this.admin.roleId === 2){
                        params.departmentId = this.admin.departmentId;
                }
                this.$axios.post('/api/OutPutScientificServlet',params,{responseType: 'arraybuffer'}).then((res) => {
                    if(!res) {
                      return
                    }
                    let url = window.URL.createObjectURL(new Blob([res.data],{type: 'application/vnd.ms-excel'}))
                    console.log(url);
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download', '技能竞赛.xls')

                    document.body.appendChild(link)
                    link.click()
                  }
                ).catch((err) => {
                                console.log(err)
                            })
            },
            loadData(pageNum, pageSize){    // 当前页码；每页所含有的数据量
                        const{selectTable} = this
                        let params = {
                                pageNum: pageNum,
                                pageSize: pageSize,
                                departmentId: selectTable.departmentId,
                                rank: selectTable.rank,  // 级别
                                clock1: selectTable.clock1,       // date
                                clock2: selectTable.clock2, 
                                uName: selectTable.uName,
                                categoryId:  selectTable.categoryId,
                                userinfoId: this.admin.userinfoId
                            }
                        if(this.admin.roleId === 2){
                            params.departmentId = this.admin.departmentId;
                        }
                        this.$axios.post('/api/ScientificPageServlet',params)
                                    .then((res) => {
                                        this.tableData = res.data.tableData;
                                        this.totalCount = res.data.totalCount
                                       
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
                                if(admin.roleId > 1){
                                    this.selectTable.departmentId = admin.departmentId;
                                    this.depart = false;
                                }
                                if(admin.roleId === 3){
                                    this.admin.userinfoId = res.data.id;
                                     this.userDis = false;
                                }else{
                                    this.admin.userinfoId = 0;
                                }
                                this.loadData(this.currentPage, this.pagesize);
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
    .cell{
        text-align: left;
    }
</style>
