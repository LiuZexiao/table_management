<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  body{
  margin: 0;
  background: #f2f2f2;
  overflow-x: hidden;
  }
  /* 主界面左侧列表样式 */
  .el-submenu__title{
  padding: 0 30px;
  }
  /* 去除导航栏中的空白条 */
  .el-menu-item-group__title{
  padding: 0;
  }
  /* 首页图片大小 */
  .el-carousel__container{
    height: 430px;
  }
  /* input 框的的宽度 */
  /* .el-input__inner{
    width: 500px;
  } */
</style>
