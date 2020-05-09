<template>

  <div id="login" class="login">

  <form id="upload" enctype="multipart/form-data" method="post" accept-charset="utf-8" >
    <el-upload
      class="upload-demo"
      action=""
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :auto-upload="true"
      :limit="3"
      :on-exceed="handleExceed"
      :http-request="handlePost"
      :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
  </form>

  <el-button type="primary" @click="download2">下载<i class="el-icon-download el-icon--right"></i></el-button>
  <el-button type="primary" @click="listObjects">列出下载桶里面的所有文件<i class="el-icon-download el-icon--right"></i></el-button>
    <!-- <form> -->

     <!-- <input type="button" value="登录" @click="doLogin">
     <input type="button" value="登出" @click="doSignOut"> -->

      <!-- <hr> -->
      
      <!-- <router-link to='/index' >Index</router-link>
      <router-link to='/hello' >Hello</router-link> -->
      
    <!-- </form> -->
     
  </div>

</template>

<script>
import utils from "@/utils/utils"
import axios from 'axios'

export default {
  name: 'login',
  data () {
    return {
     
      loginData:{
        username:"",
        password:"",
        verifyCode:""
      },
      someData:"success",
      fileList: [],
      vcUrl: '/user/verifyCode?time='+new Date(),
    }
  },
  methods:{
    updateVerifyCode(){    
      let success=(response)=>{
        vcUrl:'/user/verifyCode?time='+new Date()
      }
      utils.axiosMethod({
        method:"GET",
        url:'/user/verifyCode',
        callback:success
      })},

    submitLogin(){},
    doLogin(){
      let success=(response)=>{
        // alert(response.data.msg);
        // alert(JSON.stringify(response));
        console.log(JSON.stringify(response));
        // if (response.data.code===0){
          this.$router.push("/welcome")

        // }
      }
      utils.axiosMethod({
        headers: {
        'Content-Type': 'application/json;charset=utf-8'
        },
        method:"POST",
        url:"/user/doLogin",
        data:JSON.stringify(this.loginData),
        callback:success
      })
    },
    doRegister(){
    
        this.$router.push("/register")

    },


     doSignOut(){
      let success=(response)=>{
        alert(response.data.msg);
      }
      utils.axiosMethod({
        method:"POST",
        url:"/user/signOut/",
        data:Qs.stringify(this.loginData),
        callback:success
      })
    },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      handlePost(file) {
      // 参考地址
      // https://www.cnblogs.com/jcfxl/p/10107926.html
      // https://blog.csdn.net/nimoyaoww/article/details/79400885
      var size = file.file.size;//文件大小
      
      // alert('上传文件大小为' + size );
      // alert('文件uid' + file.file.uid);
      console.log("handlePost(file)="+JSON.stringify(file));
   
      const fd =new FormData()
      fd.append('categoryId', 1)
      fd.append('tag', 2)
      fd.append('description', 3)
      fd.append('prefix', 4)
      fd.append('file', file.file)
      
      console.log("fd....="+fd);

      utils.axiosMethod({
        method:"POST",
        url:"/user/upload/",
        data:fd,
      
        })
      },

      // 配置post请求的参数。参数名file,后面跟要传的文件，参数名fileType，值为category（看后端的具体要求）
      // fd.append('file', file)
      // postUpload(fd).then(response => {
      //   console.log(1);
      //   console.log(response);
      //   console.log(file);
      //   console.log(2);
      // })
   

    //download()和download2()都可以使用
    download() {
        axios({
          method:"POST",
          url:"/user/download/"
      
        }).then(response =>{
                let data = response.data;
                if (!data) {
                    return
                }
                console.log("response=");
                console.log(response);
                let contentDisposition = response.headers['content-disposition'];
                let fileName = contentDisposition.substring(contentDisposition.indexOf('=')+1);
                console.log('fileName=' + fileName);
                let url = window.URL.createObjectURL(new Blob([data]))
                let a = document.createElement('a')
                a.style.display = 'none'
                a.href = url
                a.setAttribute('download',fileName)
                document.body.appendChild(a)
                //点击下载
                a.click()
                // 下载完成移除元素
                document.body.removeChild(a);
                // 释放掉blob对象
                window.URL.revokeObjectURL(url);
                console.log("下载完成");
                }).catch(function (error) {
                console.log(error);
            });
        },

        //导出按钮 
       download2() {
        axios({
          method:"POST",
          url:"/user/download/",
          responseType: 'blob', // 服务器返回的数据类型 
        }).then(function(res) { 
            console.log(res) 
            // 此处有个坑。这里用content保存文件流，最初是content=res，但下载的test.xls里的内容如下图1， 
            // 检查了下才发现，后端对文件流做了一层封装，所以将content指向res.data即可 
            // 另外，流的转储属于浅拷贝，所以此处的content转储仅仅是便于理解，并没有实际作用=_= 
            const content = res.data 
            const blob = new Blob([content]) // 构造一个blob对象来处理数据 
            const fileName = '你好吗.txt' // 导出文件名 
            // 对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性 
            // IE10以上支持blob但是依然不支持download 
            if ('download' in document.createElement('a')) { // 支持a标签download的浏览器 
                const link = document.createElement('a') // 创建a标签 
                link.download = fileName // a标签添加属性 
                link.style.display = 'none' 
                link.href = URL.createObjectURL(blob) 
                document.body.appendChild(link) 
                link.click() // 执行下载 
                URL.revokeObjectURL(link.href) // 释放url 
                document.body.removeChild(link) // 释放标签 
            } else { // 其他浏览器 
                navigator.msSaveBlob(blob, fileName) 
                } 
            }).catch((error) => { 
                console.log(error) 
            }); 
        },

       listObjects() {
        axios({
          method:"POST",
          url:"/user/listObjects/",
        }).then(function(res) { 
        
            }).catch((error) => { 
                console.log(error) 
            }); 
        }

  }
}

</script>


<style>
*{
  /* text-align: center; */
    padding: 100;
    margin: 100;
}
.login{
  width: 200px;
  /* margin: auto; */
  margin-top: 100px;

}
.username{
  width: 200px;
}

.password{
  width: 200px;
}

</style>
