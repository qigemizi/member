<template>
  <div>
    <h2>Index</h2>
    <hr>
    <p>{{sContent}}</p>
    <div class="table">
      <el-table :data="tableData.slice((page.currPageNo - 1) * page.pageSize, page.currPageNo * page.pageSize)" border stripe  @selection-change="handleSelectionChange">
      <!-- <el-table :data="tableData" stripe class="table" @selection-change="handleSelectionChange"> -->
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column type="index" label="序号">
        </el-table-column>
        <el-table-column label="图片">
          <template slot-scope="scope">
            <img :src="scope.row.image" width="40" height="40"/>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="商品名称">
        </el-table-column>
        <el-table-column prop="goodsId" label="ID">
        </el-table-column>
        <el-table-column prop="price" label="价格">
        </el-table-column>
      </el-table>
    </div>
    <div>
        <div class="block" style="text-align:right;">
            <el-pagination 
              @size-change="sizeChange" 
              @current-change="currentChange" 
              :current-page="1" 
              :page-sizes="[10, 50]" 
              :page-size="page.pageSize" 
              layout="total, sizes, prev, pager, next, jumper" 
              :total="page.totalRecords">
            </el-pagination>
        </div>
    </div>

</div>
</template>

<script>
export default {
  data () {
    return {
      sContent: '欢迎 页面',
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }
      ],
           
      page: {
          currPageNo: 1, //当前页
          pageSize: 10, //每页条数,  默认10条
          totalRecords: null, //总条数
          totalPages: null, //总页数
      }, //已完成项目翻页

    }
  },
  mounted() {
        this.computePages();


  },


methods: {

        //表格数据列表计算
        computePages() {
          //  let _this=this;
           console.log(this.tableData.length);
           this.page.totalRecords=this.tableData.length
           

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
      
    },


}
</script>




<style>
*{
  /* text-align: center; */
    padding: 0;
    margin: 0;
}

.table{
 
  position:absolute;left:50%;-webkit-transform:translateX(-50%);transform:translateX(-50%);
  width:1000px;
  margin:0 auto;

}

</style>

