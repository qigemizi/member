<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="再次输入密码">
        <el-input v-model="form.password2"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>

      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="请选择">
          <el-option label="男" value="male"></el-option>
          <el-option label="女" value="female"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="生日">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button  @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import utils from "../utils/utils"
import axios from 'axios'
const defaultUser = {
    id: null,
    username: "",
    password: "",
    password2: "",
    phone: "",
    sex: "",
    birthday: "",
    status: 1
  };
export default {
  data() {
    return {
      form: Object.assign({}, defaultUser),
      // form: {
      //   username: "",
      //   password: "",
      //   password2: "",
      //   phone: "",
      //   sex: "",
      //   birthday: ""
      // }
    };
  },
  methods: {
    onSubmit(){
      let success=(response)=>{
      
        // if (response.data.code===0){
          // this.$router.push("/welcome")

        // }
      }
      utils.axiosMethod({
        method:"POST",
        url:"/user/register/",
        data:this.form,
        callback:success
      })
    },
    onCancel(){
       this.$router.push("/")
    },
  }
};
</script>



<style>
* {
  text-align: center;
  padding: 100;
  margin: 100;
}

.table {
  position: absolute;
  left: 50%;
  -webkit-transform: translateX(-50%);
  transform: translateX(-50%);
  width: 1000px;
  margin: 0 auto;
}
</style>

