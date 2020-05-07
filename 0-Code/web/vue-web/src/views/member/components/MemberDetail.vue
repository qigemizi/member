<template>
  <el-card class="form-container" shadow="never">
    <el-form :model="member" ref="brandFrom" label-width="150px">
      <el-form-item label="会员名：" prop="name">
        <el-input v-model="member.name"></el-input>
      </el-form-item>

      <el-form-item label="手机号：" prop="phone">
        <el-input v-model="member.phone"></el-input>
      </el-form-item>

      <el-form-item label="注册日期：" prop="date">
        <el-date-picker v-model="value1" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit('brandFrom')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('brandFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
//   import {createBrand, getBrand, updateBrand} from '@/api/member'
//   import SingleUpload from '@/components/Upload/singleUpload'
import utils from "../../../utils/utils";
import axios from "axios";

const defaultBrand = {
  name: "",
  phone: ""
};
export default {
  name: "MemberDetail",
  components: {
    // SingleUpload
  },
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
  },
  created() {
    console.log("isEdit=");
    console.log(this.isEdit);
    console.log(this.$route.query.id);

    if (this.isEdit) {
      let success = response => {
        // alert(response.data.msg);
        // alert(JSON.stringify(response));
        console.log(JSON.stringify(response));
        // if (response.data.code===0)
        this.member = response.data.data;
      };
      utils.axiosMethod({
        headers: {
          "Content-Type": "application/json;charset=utf-8"
        },
        method: "GET",
        url: "/member/" + this.$route.query.id,
        callback: success
      });
    } else {
      this.member = Object.assign({}, defaultBrand);
    }
  },
  methods: {
    onSubmit() {},

    getMember(id) {
      axios({
        headers: {
          "Content-Type": "application/json;charset=utf-8"
        },
        method: "GET",
        url: "/member/" + id
      });
    }
  }
};
</script>
<style>
</style>


