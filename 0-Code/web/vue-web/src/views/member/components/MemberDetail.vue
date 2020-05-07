<template>
  <el-card class="form-container" shadow="never">
    <el-form :model="member" ref="brandFrom" label-width="150px">
      <el-form-item label="会员名：" prop="name">
        <el-input v-model="member.name"></el-input>
      </el-form-item>

      <el-form-item label="手机号：" prop="phone">
        <el-input v-model="member.phone"></el-input>
      </el-form-item>

      <el-form-item label="注册日期：" prop="registerDate">
        <el-date-picker v-model="member.registerDate" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit('brandFrom')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('brandFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { getMember, updateMember, createMember } from "@/api/member";
import utils from "../../../utils/utils";

const defaultBrand = {
  name: "",
  phone: "",
  registerDate: ""
};
export default {
  name: "MemberDetail",
  components: {},
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      // 日期默认为当天
      value1: new Date(),
      member: Object.assign({}, defaultBrand)
    };
  },
  beforeCreate() {
    // console.log("isEdit=");
    // console.log(this.isEdit);
    // 在beforeCreate里this.isEdit都还没有值
  },
  created() {
    console.log("isEdit=");
    console.log(this.isEdit);
    console.log(this.$route.query.id);

    if (this.isEdit) {
      // 这里是因为在created方法里面只能调用外面的，写在自己methods里面的会报未定义错误
      getMember(this.$route.query.id).then(response => {
        this.member = response.data.data;
      });
      // // 上面的也OK了，可以请求到数据
      // // 下面的是OK的，可以请求到数据
      // let success = response => {
      //   // alert(response.data.msg);
      //   // alert(JSON.stringify(response));
      //   console.log(JSON.stringify(response));
      //   // if (response.data.code===0)
      //   this.member = response.data.data;
      // };
      // utils.axiosMethod({
      //   headers: {
      //     "Content-Type": "application/json;charset=utf-8"
      //   },
      //   method: "GET",
      //   url: "/member/" + this.$route.query.id,
      //   callback: success
      // });
    } else {
      this.member = Object.assign({}, defaultBrand);
    }
  },
  methods: {
    onSubmit() {
      this.$confirm("是否提交数据", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
        // type: "warning"
      }).then(() => {
        if (this.isEdit) {
          updateMember(this.$route.query.id, this.member).then(response => {
            this.$refs[formName].resetFields();
            this.$message({
              message: "修改成功",
              type: "success",
              duration: 1000
            });
            this.$router.back();
          });
        } else {
          console.log("this.member=");
          console.log(JSON.stringify(this.member));
          // this.member.name = "111";
          // this.member.phone = "123";
          // this.member.registerDate = "2020-1-1";
          createMember(this.member).then(response => {
            this.$refs[formName].resetFields();
            this.member = Object.assign({}, defaultBrand);
            this.$message({
              message: "添加成功",
              type: "success",
              duration: 1000
            });
          });
        }
      });
    }
  }
};
</script>
<style>
</style>


