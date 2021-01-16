<template>
<div>
    <el-container>
		<el-header>
			<span class="title">学生管理系统</span>
		</el-header>
		<el-main class='table-center'>
      <el-row>
    <el-col :span="12"><div class='content-left'>
        <el-button type="info" @click="sortById(page.pageNum)">按id排序</el-button>
  			<el-button type="warning" @click="sortByAge(page.pageNum)">按年龄排序</el-button>
    	  <el-button type="primary" @click="addPage()">增加</el-button>
  			<el-button type="danger" @click="toDeleteAll()">删除</el-button>
      </div></el-col>
      <el-col :span="12"><div class='content-right'>
        <el-input v-model="search" placeholder="请输入..." style="width:300px"></el-input>
        <el-button type="success" icon="el-icon-search" @click="toSearch()">查询</el-button>
      </div></el-col>
      </el-row>
        <br> <br>
            <el-table :data="students" stripe style="width: 100%" border @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column prop="uid" label="uid"></el-table-column>
					<el-table-column prop="name" label="姓名"></el-table-column>
					<el-table-column prop="nation" label="民族"></el-table-column>
					<el-table-column prop="sex" label="性别"></el-table-column>
					<el-table-column prop="age" label="出生日期"></el-table-column>
					<el-table-column prop="school" label="学院" style="width: 500px"></el-table-column>
					<el-table-column prop="major" label="专业"></el-table-column>
					<el-table-column label="操作" width="230">
						<template slot-scope="scope">
						  <el-button @click="editPage(scope.row.uid)" icon="el-icon-edit"></el-button>
              <el-button @click="detailPage(scope.row.uid)" icon="el-icon-view"></el-button>
              <el-button @click="toDelete(scope.row.uid)" icon="el-icon-delete"></el-button>
						</template>
					</el-table-column>	
            </el-table>
        </el-main>
		<el-footer>
			<el-pagination @current-change="handleCurrentChange" :current-page="currentPage1"
			:page-size="1"
      		layout="total, prev, pager, next, jumper"
      		:total="page.pageTotal" fixed="right">
    		</el-pagination>				
		</el-footer>
    </el-container>
	<el-dialog title="详细信息" :visible.sync="dialogTableVisible">
    <div class="pic_left">
         <el-avatar style="width:2.5cm;height:3.5cm;background-color: white;" shape="square" :src="imageUrl"></el-avatar>
    </div>
    <div class="pic_right">
  		<el-table :data="studentDetail">
    	<el-table-column prop="uid" label="uid" width="115"></el-table-column>
			<el-table-column prop="name" label="姓名"></el-table-column>
			<el-table-column prop="nation" label="民族"></el-table-column>
			<el-table-column prop="sex" label="性别"></el-table-column>
			<el-table-column prop="age" label="出生日期"></el-table-column>
 		</el-table>
     <el-table :data="studentDetail">
			<el-table-column prop="school" label="学院"></el-table-column>
			<el-table-column prop="major" label="专业"  width="115"></el-table-column>
			<el-table-column label="贫困生资格" width="110">{{poor}}</el-table-column>
			<el-table-column prop="tel" label="电话"></el-table-column>
			<el-table-column prop="parentTel" label="监护人电话"></el-table-column>
       </el-table>
    </div>
		<el-table :data="studentDetail">
			<el-table-column prop="detail" label="详细信息" ></el-table-column>
 		</el-table>
     <div class="CVButton">
       <el-row>
		<el-col :span="12">
      <el-tooltip content="upload CV" placement="bottom">
        <el-upload
          class="upload-demo"
          action="http://127.0.0.1:8080/file/upload"
          :on-change="handleChange"
          :before-upload="beforeUpload"
          :on-remove="handleRemove"
          :file-list="fileList">
     <el-button icon="el-icon-upload"></el-button>
  <div slot="tip" class="el-upload__tip">只能上传docx文件</div>
</el-upload>
      </el-tooltip>
		</el-col>
    <el-col :span="12">
      <el-tooltip content="download CV" placement="bottom">
     <a :href="fileList[0].url" download="简历">
     <el-button icon="el-icon-download"></el-button>
     </a>
      </el-tooltip>
		</el-col>
     </el-row>
     </div>
	</el-dialog>


	<el-dialog title="修改学生信息"  :visible.sync="dialogFormVisible">
  <el-form :model="student" :rules="rules" ref="ruleForm">
    <el-row>
		<el-col :span="12">
    <div class="top_left">
    <el-form-item label="u i d" prop="uid">
      <el-input v-model="student.uid" autocomplete="off" style="width:300px" placeholder="如：11701010101"></el-input>
    </el-form-item>
	<el-form-item label="姓名" prop="name">
      <el-input v-model="student.name" autocomplete="off" style="width:300px" placeholder="如：李连杰"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select v-model="student.sex" placeholder="请选择" style="width:300px">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>
	<el-form-item label="民族" prop="nation">
      <el-input v-model="student.nation" autocomplete="off" style="width:300px" placeholder="如：汉"></el-input>
    </el-form-item>
	<el-form-item label="出生日期" prop="age">
		<el-input v-model="student.age" autocomplete="off" style="width:300px" placeholder="如：2014"></el-input>
    </el-form-item>
    </div>
		</el-col>
    <el-col :span="12">
    <div class="top_right">
    <template>
  <el-upload
    class="avatar-uploader"
    action="http://127.0.0.1:8080/file/upload"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload">
    <img v-if="imageUrl" :src="imageUrl" class="avatar">
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>
    </div>
    </el-col>
    </el-row>
    <div class="bottom">
	<el-row>
		<el-col :span="8">
	<el-form-item label="学院" prop="school">
      <el-select v-model="student.school" placeholder="请选择" @change="chooseMajor()">
    <el-option v-for="school in optList1" :key="school" :label="school" :value="school">
    </el-option>
  </el-select>
    </el-form-item>
		</el-col>
	<el-col :span="8">
	<el-form-item label="专业" prop="major">
      <el-select v-model="student.major" placeholder="请选择">
    <el-option v-for="major in optList2" :key="major" :label="major" :value="major">
    </el-option>
  </el-select>
    </el-form-item>
	</el-col>
	</el-row>
	<el-form-item label="贫困生资格">
      <el-select v-model="poor" placeholder="请选择">
    <el-option v-for="poor in optList3" :key="poor" :label="poor" :value="poor">
    </el-option>
  </el-select>
    </el-form-item>
    <el-row>
		<el-col :span="10">
		<el-form-item label="电话" prop="tel">
      <el-input v-model="student.tel" autocomplete="off" style="width:300px" placeholder="如：13374125896"></el-input>
    </el-form-item>
		</el-col>
	<el-col :span="12">
		<el-form-item label="监护人电话" prop="parentTel">
      <el-input v-model="student.parentTel" autocomplete="off" style="width:300px" placeholder="如：13374125896"></el-input>
    </el-form-item>
    </el-col>
	</el-row>
		<el-form-item label="详细信息" >
      <el-input type="textarea" v-model="student.detail" autocomplete="off" style="width:100%"></el-input>
    </el-form-item>
    </div>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="editStudent('ruleForm')">确 定</el-button>
  </div>
</el-dialog>

	<el-dialog  title="新增学生" :visible.sync="dialogForm1Visible">
  <el-form :model="student" :rules="rules" ref="ruleForm">
    <el-row>
		<el-col :span="12">
    <div class="top_left">
    <el-form-item label="u i d" prop="uid">
      <el-input v-model="student.uid" autocomplete="off" style="width:300px" placeholder="如：11701010101"></el-input>
    </el-form-item>
	<el-form-item label="姓名" prop="name">
      <el-input v-model="student.name" autocomplete="off" style="width:300px" placeholder="如：李连杰"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select v-model="student.sex" placeholder="请选择" style="width:300px">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>
	<el-form-item label="民族" prop="nation">
      <el-input v-model="student.nation" autocomplete="off" style="width:300px" placeholder="如：汉"></el-input>
    </el-form-item>
	<el-form-item label="出生日期" prop="age">
		<el-input v-model="student.age" autocomplete="off" style="width:300px" placeholder="如：2014"></el-input>
    </el-form-item>
    </div>
		</el-col>
    <el-col :span="12">
    <div class="top_right">
 
    </div>
    </el-col>
    </el-row>
    <div class="bottom">
	<el-row>
		<el-col :span="8">
	<el-form-item label="学院" prop="school">
      <el-select v-model="student.school" placeholder="请选择" @change="chooseMajor()">
    <el-option v-for="school in optList1" :key="school" :label="school" :value="school">
    </el-option>
  </el-select>
    </el-form-item>
		</el-col>
	<el-col :span="8">
	<el-form-item label="专业" prop="major">
      <el-select v-model="student.major" placeholder="请选择">
    <el-option v-for="major in optList2" :key="major" :label="major" :value="major">
    </el-option>
  </el-select>
    </el-form-item>
	</el-col>
	</el-row>
	<el-form-item label="贫困生资格">
      <el-select v-model="poor" placeholder="请选择">
    <el-option v-for="poor in optList3" :key="poor" :label="poor" :value="poor">
    </el-option>
  </el-select>
    </el-form-item>
    <el-row>
		<el-col :span="10">
		<el-form-item label="电话" prop="tel">
      <el-input v-model="student.tel" autocomplete="off" style="width:300px" placeholder="如：13374125896"></el-input>
    </el-form-item>
    </el-col>
	<el-col :span="12">
		<el-form-item label="监护人电话" prop="parentTel">
      <el-input v-model="student.parentTel" autocomplete="off" style="width:300px" placeholder="如：13374125896"></el-input>
    </el-form-item>
     </el-col>
	</el-row>
		<el-form-item label="详细信息" >
      <el-input type="textarea" v-model="student.detail" autocomplete="off" style="width:100%"></el-input>
    </el-form-item>
    </div>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogForm1Visible = false">取 消</el-button>
    <el-button type="primary" @click="addStudent('ruleForm')">确 定</el-button>
  </div>
</el-dialog>
</div>
</template>
<script>
export default {
  data() {
    return {
    fileList: [{
          name: '',
          url: ''
        }],
    imageUrl:'',
    search:'',
    sort:'uid',
	  students: [],
	  checkModel:[],
	  page:'',
	  currentPage1: 1,
	  currentPage2: 2,
	  studentDetail:[],
	  student:{},
	 dialogTableVisible: false,
	 dialogFormVisible: false,
	 dialogForm1Visible: false,
	 poor:'',
	optList1:['计算机科学与工程学院','理学院','会计学院'],
  optList2:[],
	optList3:['无','贫困生'],
	ruleForm:{
		uid: "",
    name: "",
    sex: "",
    age: "",
    nation: "",
    school: "",
		tel: "",
		parentTel: "",
		major: "",
		poor:""
	},
	rules: {
		uid:[
			{ required: true, message: "请输入学号", trigger: "blur" },
          {
            pattern: /^1\d{10}$/,
            message: "学号需为1开头11位数字",
            trigger: "blur"
          }
		],
		name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            pattern: /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/,
            message: "姓名需为正确中文姓名",
            trigger: "blur"
          }
        ],
		age: [
          { required: true, message: "请输入出生日期", trigger: "blur" },
          {
            pattern: /\d{4}$/,
            message: "出生日期应为4位数字",
            trigger: "blur"
          }
		],
		nation:[
			{ required: true, message: "请输入民族", trigger: "blur" },
          {
            pattern: /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{1,20}$/,
            message: "请输入中文",
            trigger: "blur"
          }
		],
		school: [
          { required: true, message: "请选择学院", trigger: "blur" }
        ],
        major: [
          { required: true, message: "请选择专业", trigger: "blur" }
        ],
		sex: [
          { required: true, message: "请选择性别", trigger: "blur" }
		],
		tel:[
			{ required: true, message: "请输入电话", trigger: "blur" },
          {
            pattern: /^1\d{10}$/,
            message: "请输入正确11位电话",
            trigger: "blur"
          }
		],
		parentTel:[
			{ required: true, message: "请输入电话", trigger: "blur" },
          {
            pattern: /^1\d{10}$/,
            message: "请输入正确11位电话",
            trigger: "blur"
          }
		],
        }
    }
  },
  methods: {
    handleRemove() {
        this.$axios.get('http://localhost:8080/test/deleteCv?uid='+ this.studentDetail[0].uid)
      },
    handleChange(file, fileList) {
        this.fileList = fileList.slice(-1);
      },
    handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
    beforeUpload(file) {
        this.$axios.get('http://localhost:8080/file/uid?string='+this.studentDetail[0].uid)
        const isDOC = file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
        if (!isDOC) {
          this.$message.error('请上传docx格式!');
        }
        return isDOC;
      },
      beforeAvatarUpload(file) {
        this.$axios.get('http://localhost:8080/file/uid?string='+this.student.uid)
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
	  chooseMajor(){
		this.student.major=''
      if(this.student.school=='计算机科学与工程学院')
        this.optList2=['软件工程','计算机']
      if(this.student.school=='理学院')
        this.optList2=['数学']
      if(this.student.school=='会计学院')
        this.optList2=['会计']
	},
	handleSelectionChange(val) {
		this.checkModel=[]
		val.forEach(array => {
			this.checkModel.push(array.uid)
		});
		console.log("handleSelectionChange--",this.checkModel)
      },
	handleCurrentChange(val) {
      this.turnPage(val,this.sort,this.serach)
  },
	sortById(pageNum){
    this.sort='uid'
		this.turnPage(pageNum,this.sort,this.serach)
	  },
	sortByAge(pageNum){
    this.sort='age'
    this.turnPage(pageNum,this.sort,this.serach)
    },
  toSearch(){
    this.turnPage(1,this.sort,this.serach)
    },
    /*	sortById(pageNum){
		this.$axios.get('http://localhost:8080/test/sortById')
		this.turnPage(this.page.pageNum)
	  },
	sortByAge(pageNum){
		this.$axios.get('http://localhost:8080/test/sortByAge')
		this.turnPage(this.page.pageNum)
	  },*/
	toDeleteAll(){
		 this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
			this.$axios.get('http://localhost:8080/test/deleteAllStudents?uid='+this.checkModel)
			window.location.reload();
		});
	},
	toDelete(id){
		this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
			this.$axios.get('http://localhost:8080/test/deleteStudent?uid='+id)
			window.location.reload();
		});
	},
	pageTo(){
		this.$axios.get('http://localhost:8080/test/getStudents?pageNum='+this.to)
      .then(res => {
		  this.students = res.data;
      })
      .catch(err => {
          console.log(err);
	  });
	  this.$axios.get("http://localhost:8080/test/getPage")
		.then(res => {
          this.page = res.data;
      })
      .catch(err => {
          console.log(err);
      });
	},
	turnPage(pageNum){
		this.$axios.get('http://localhost:8080/test/getStudents?pageNum='+pageNum+'&sort='+this.sort+'&search='+this.search)
      .then(res => {
		  this.students = res.data;
      })
      .catch(err => {
          console.log(err);
	  });
	  this.$axios.get("http://localhost:8080/test/getPage")
		.then(res => {
		  this.page = res.data;
      })
      .catch(err => {
          console.log(err);
      });
	},
	
    getAllStudent(){
      this.$axios.get("http://localhost:8080/test/getAllStudent")
      .then(res => {
          this.students = res.data;
      })
      .catch(err => {
          console.log(err);
      });
	},
	addPage(){
		this.student={}
		this.poor=''
		this.dialogForm1Visible=true;
	},
    editPage (uid) {
      this.imageUrl=''
	    this.dialogFormVisible=true;
      this.$axios.get('api/test/getStudentById?uid='+ uid)
        .then(res => {
          this.student = res.data
           if(this.student.isPoor==false)
            this.poor="无"
          if(this.student.isPoor==true)
      this.poor="贫困生"
      this.imageUrl=this.student.photo
		  this.chooseMajor()
        })
        .catch(err => {
          this.$message('查询失败');
          console.log(err);
        })
	},
    editStudent(formName) {
		this.$refs[formName].validate((valid) => {
        if (valid) {
            this.dialogFormVisible = false;
      	if(this.poor=='无')
        	this.student.isPoor=false
      	if(this.poor=='贫困生')
			this.student.isPoor=true
		this.$axios.post('api/test/editStudent', this.student)
        .then(res => {
          this.$message('保存成功');
          this.student = {}
          this.turnPage(this.page.pageNum)
        })
        .catch(err => {
          this.$message('保存失败');
          console.log(err);
        })
          }
        });
	},
	addStudent(formName) {
	  this.$refs[formName].validate((valid) => {
        if (valid) {
            this.dialogForm1Visible = false;
      	if(this.poor=='无')
        	this.student.isPoor=false
      	if(this.poor=='贫困生')
			this.student.isPoor=true
		this.$axios.post('api/test/addStudent', this.student)
        .then(res => {
          this.$message('保存成功');
          this.student = {}
          this.turnPage(this.page.pageNum)
        })
        .catch(err => {
          this.$message('保存失败');
          console.log(err);
        })
          }
        });
    },
	detailPage (uid) {
    this.fileList= [{
          name: '',
          url: ''
        }]
		this.$axios.get('http://localhost:8080/test/getStudentById?uid='+ uid)
		.then(res => {
			this.studentDetail=[]
		  this.studentDetail.push(res.data);
		  if(this.studentDetail[0].isPoor==false)
          this.poor="无"
      if(this.studentDetail[0].isPoor==true)
			    this.poor="贫困生"
    this.dialogTableVisible=true;
    this.imageUrl=this.studentDetail[0].photo
    this.fileList[0].url=this.studentDetail[0].cv
    if(this.fileList[0].url== null || this.fileList[0].url== undefined ||this.fileList[0].url=='')
      this.fileList[0].name='无'
    else
      this.fileList[0].name='简历'
      })
      .catch(err => {
          console.log(err);
	  });
    },
  },
  mounted() {

	this.turnPage(1,this.sort,this.serach);
  },
  activated: function() {
	  this.turnPage(1,this.sort,this.serach);
  },
}
</script>
<style>
.el-container{
	width: 100%;
	background-color: #f0ece9;
	margin: auto;
	position: relative;
}
.title{
	font-size: 35px;
	position: relative;
	left: 0px;
	line-height: 70px;
	color: #999;
	text-align: center;
	display:block;
}
.el-pagination{
	float: right;
}
.content-left{
  float: left;
  padding-left:100px ;
}
.content-right{
  float: right;
  padding-right:100px ;
}
.pic_left{
  float:left;
  padding-top: 30px;
}
.pic_right{
  float:right;
  width:86%
}
.CVButton{
  text-align: center;
  padding-top: 20px
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    left: 140px;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 2.5cm;
    height: 3.5cm;
    line-height: 3.5cm;
    text-align: center;
  }
  .avatar {
    width: 2.5cm;
    height: 3.5cm;
    display: block;
  }
  input[type=file] {
    display: none;
}
</style>