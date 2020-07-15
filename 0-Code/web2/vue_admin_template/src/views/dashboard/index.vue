<template>
  <div>
    <el-row>
      <el-col :span="24">
        <h2>Index</h2>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <hr />
        <div style="background-color:red;width:100px;height:100px"></div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <p>{{sContent}}</p>
      </el-col>
    </el-row>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button class="btn-add" @click="addMember()" size="mini">添加</el-button>
    </el-card>
    <el-row>
      <el-col :span="24">
        <!-- <div class="table"> -->
        <div>
          <el-table
            :data="tableData.slice((page.currPageNo - 1) * page.pageSize, page.currPageNo * page.pageSize)"
            border
            stripe
            @selection-change="handleSelectionChange"
          >
            <!-- <el-table :data="tableData" stripe class="table" @selection-change="handleSelectionChange"> -->
            <!-- <el-table-column type="selection" width="55"></el-table-column>多选按钮，暂时用不到 -->
            <el-table-column type="index" label="序号"></el-table-column>
            <el-table-column label="图片">
              <template slot-scope="scope">
                <img :src="scope.row.image" width="40" height="40" />
              </template>
            </el-table-column>

            <el-table-column label="会员ID">
              <template slot-scope="scope">{{scope.row.id}}</template>
            </el-table-column>
            <!-- <el-table-column prop="name" label="会员姓名"></el-table-column> -->
            <el-table-column label="会员姓名">
              <template slot-scope="scope">{{scope.row.name}}</template>
            </el-table-column>

            <el-table-column label="手机号">
              <template slot-scope="scope">{{scope.row.phone}}</template>
            </el-table-column>

            <el-table-column label="注册日期">
              <template slot-scope="scope">{{scope.row.registerData}}</template>
            </el-table-column>
            <el-table-column prop="price" label="总消费额">
              <template slot-scope="scope">{{scope.row.totalConsumption}}</template>
            </el-table-column>

            <el-table-column fixed="right" label="操作" width="200">
              <template slot-scope="scope">
                <el-button @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button @click="consumptionQuota(scope.row)">本次消费额度</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div style="text-align:right;background-color:red" class="block">
          <el-pagination
            @size-change="sizeChange"
            @current-change="currentChange"
            :current-page="1"
            :page-sizes="[10, 50]"
            :page-size="page.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="page.totalRecords"
          ></el-pagination>
        </div>
      </el-col>
    </el-row>

    <el-dialog title="会员消费详情" :visible.sync="dialogTableVisible">
      <el-row>
        <el-col :span="24">
          <el-button type="primary" @click="handleClick">刷新</el-button>
        </el-col>
      </el-row>
      <el-table :data="gridData">
        <el-table-column property="memberId" label="姓名" width="200"></el-table-column>
        <el-table-column property="consumptionDate" label="日期" width="150"></el-table-column>
        <el-table-column property="consumptionQuota" label="花费额度"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogTableVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="本次消费额度" :visible.sync="dialogFormVisible">
      <el-form :model="quota">
        <el-form-item label="本次消费额度" :label-width="formLabelWidth">
          <el-input v-model="quota.quota" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import utils from "@/utils/utils";
import axios from "axios";
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      sContent: "欢迎 页面",
      tableData: null,
      page: {
        currPageNo: 1, //当前页
        pageSize: 10, //每页条数,  默认10条
        totalRecords: null, //总条数
        totalPages: null //总页数
      }, //已完成项目翻页

      // 弹出框里面显示的内容
      gridData: null,
      dialogTableVisible: false,
      dialogFormVisible: false,
      quota: {
        quota: ""
      },
      formLabelWidth: "120px"
    };
  },
  // 用到了生命周期函数，在创建之前写入tableData数据，不然页面是空白
  beforeCreate() {
    let success = response => {
      // alert(response.data.msg);
      // alert(JSON.stringify(response));
      console.log(JSON.stringify(response));
      // if (response.data.code===0)
      this.tableData = response.data.data;
    };
    utils.axiosMethod({
      headers: {
        "Content-Type": "application/json;charset=utf-8",
        Authorization: getToken()
      },
      method: "GET",
      url: "/member/list",
      callback: success
    });
  },
  mounted() {
    this.computePages();
  },

  methods: {
    addMember() {
      this.$router.push("/addMember");
    },
    //表格数据列表计算
    computePages() {
      //  let _this=this;
      console.log(this.tableData.length);
      this.page.totalRecords = this.tableData.length;
    },

    //翻页
    sizeChange(val) {
      this.page.pageSize = val;
      this.computePages();
    },
    //当前页
    currentChange(val) {
      this.page.currPageNo = val;
      this.computePages();
    },
    // 点击本次消费额度
    consumptionQuota(row) {
      this.dialogFormVisible = "true";
    },
    handleConfirm(done) {
      this.$confirm("确认？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },

    // 查看按钮
    handleClick(row) {
      console.log(row); // 把id传给后端
      axios({
        method: "GET",
        url: "/consumption/" + row.id
      })
        .then(response => {
          this.gridData = response.data.data;
        })
        .catch(function(error) {
          console.log(error);
        });

      this.dialogTableVisible = "true";
    },

    // 编辑按钮
    handleUpdate(index, row) {
      this.$router.push({ path: "/updateMember", query: { id: row.id } });
    },

    // 不能使用，使用postman可以，在这里就报404，还是跨域的问题，在跨域配置文件里面改好了
    refresh() {
      let success = response => {
        // alert(response.data.msg);
        // alert(JSON.stringify(response));
        console.log(JSON.stringify(response));
        // if (response.data.code===0)
        this.tableData = response.data.data;
      };
      utils.axiosMethod({
        headers: {
          "Content-Type": "application/json;charset=utf-8"
        },
        method: "GET",
        url: "/member/list",
        callback: success
      });
    },

    // 和上面效果一样
    refreshOK() {
      axios({
        headers: {
          "Content-Type": "application/json;charset=utf-8"
        },
        method: "GET",
        url: "/user/list"
      })
        .then(response => {
          this.tableData = response.data.data;
          console.log("完成！");
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
};
</script>




<style>
* {
  /* text-align: center; */
  padding: 50;
  margin: 50;
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

