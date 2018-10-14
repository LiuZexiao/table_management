<template>
    <el-container>
      <el-header class="title">报表信息管理平台</el-header>
      <router-link to="/index/userInfo" class="sign-out-btn1">{{ user.uName }}</router-link>
      <a href="javascript:void(0)" class="sign-out-btn" @click="isLogOut">退出</a>
      <el-container>
        <el-aside width="250px" >
          <el-col :span="12">
            <router-link to="/index" class="menu">
              <el-header class="identity">{{ identity+department }}</el-header>
            </router-link>
            <el-menu>
              <el-submenu index="1">
                <template slot="title"><i class="el-icon-message"></i>账号信息管理</template>
                <el-menu-item-group>
                  <template v-if="user.userRole === 3">
                    <router-link to="/index/userInfo" class="menu"><el-menu-item index="1-1">个人信息</el-menu-item></router-link>
                    <router-link to="/index/updatePassword" class="menu"><el-menu-item index="1-2">修改密码</el-menu-item></router-link>
                  </template>
                  <template v-else>
                    <router-link to="/index/account" class="menu"><el-menu-item index="1-3">管理账号</el-menu-item></router-link>
                  </template>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>报表信息管理</template>
                <el-submenu index="2-1" v-if="user.userRole === 3">
                  <template slot="title">添加报表</template>
                  <router-link to="/index/addSkill"  class="menu"><el-menu-item index="2-1-1" style="padding-left: 90px">技能竞赛</el-menu-item></router-link>
                  <router-link to="/index/addResearch"  class="menu"><el-menu-item index="2-1-2" style="padding-left: 90px">科研获奖</el-menu-item></router-link>
                </el-submenu>
                <el-submenu index="2-2">
                  <template slot="title">管理报表</template>
                  <router-link to="/index/manageSkill"  class="menu"><el-menu-item index="2-2-1" style="padding-left: 90px">技能竞赛</el-menu-item></router-link>
                  <router-link to="/index/manageResearch"  class="menu"><el-menu-item index="2-2-2" style="padding-left: 90px">科研获奖</el-menu-item></router-link>
                </el-submenu>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
</template>

<script>
export default {
    name: 'index',
    data() {
      return {
        user: {
            uName: '',
            workNum: '',        // 工号
            departmentId: 2,   // 部门id
            userRole:   2    // 权限等级
        },
        identity: '普通用户',
        department: '【信息】'
      }
    }, 
     mounted() {
      this.request();
    },
    methods: {
      sign() {
        const {user} = this 
        if(user.departmentId === 1){
          this.department = '【信息】'
        }else if(user.departmentId === 2){
          this.department = '【机械】'
        }else if(user.departmentId === 3){
          this.department = '【电子】'
        }else if(user.departmentId === 4){
          this.department = '【人文】'
        }else if(user.departmentId === 5){
          this.department = '【自动化】'
        }
        if(user.userRole === 1){
          this.identity = "超级管理员"
          this.department = ''
        }else if(user.userRole === 2){
          this.identity = "管理员"
        }else if(user.userRole === 3){
          this.identity = "普通用户"
        }
      },
      isLogOut(){
        this.$confirm('确认退出?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => { 
                  this.$router.push('/');
                    });
      },
      request(){
        const{user} = this
        this.$axios.get('/api/UserinfoServlet')
                  .then((res) => {
                    user.uName = res.data.uName;
                    user.workNum = res.data.workNum;
                    user.userRole = res.data.roleId;
                    user.departmentId = res.data.departmentId;
                    this.sign();
                  })
      }
    }
   
}
</script>

<style scoped lang="less" >
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #fff;
    height: 630px;
    color: #333;
    text-align: center;
    line-height: 200px;
    margin: 20px;
  }
  .el-main {
    overflow-y: auto;
    height: 630px;
    background-color: #fff;
    color: #333;
    text-align: center;
    margin: 20px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .el-col-12{
    width: 100%;
  }

  //
  .title{
    color: #fff;
    font-weight: 600;
    letter-spacing: 5px;
    font-size: 25px;
    background:  rgba(58, 70, 100, 1);
  }
  .sign-out-btn{
    position: absolute;
    left: 95%;
    width: 60px;
    height: 60px;
    line-height: 60px;
    text-align:center;
    color: #fff;
    text-decoration: none;
    align-items: center;
    justify-content: center;
    font-size: 18px;
  }
   .sign-out-btn1{
    position: absolute;
    left: 90%;
    // width: 60px;
    height: 60px;
    line-height: 60px;
    text-align:center;
    color: #fff;
    text-decoration: none;
    align-items: center;
    justify-content: center;
    font-size: 16px;
  }
  .el-menu-item.is-active{
    color: #000;
    background: rgba(58, 70, 100, 0.2);
  }
  .identity{
    color: #fff;
    background:  rgba(58, 70, 100, 0.9);
  }
  .menu{
    text-decoration:none; 
  }
</style>
