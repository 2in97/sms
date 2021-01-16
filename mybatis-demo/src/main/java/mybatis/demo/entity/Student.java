package mybatis.demo.entity;

public class Student {
	private String uid;
	private String name;
	private String nation;
	private String sex;
	private Integer age;
	private String school;
	private String major;
	private String detail;
	private boolean isPoor;
	private String tel;
	private String parentTel;
	private String photo;
	private String cv;
	

	public Student() {
		super();
	}
	
	public Student(String uid, String name, String nation, String sex, Integer age, String school, String major,
			String detail, boolean isPoor, String tel, String parentTel, String photo, String cv) {
		super();
		this.uid = uid;
		this.name = name;
		this.nation = nation;
		this.sex = sex;
		this.age = age;
		this.school = school;
		this.major = major;
		this.detail = detail;
		this.isPoor = isPoor;
		this.tel = tel;
		this.parentTel = parentTel;
		this.photo = photo;
		this.cv = cv;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}

	@Override
	public String toString() {
		return "Student [uid=" + uid + ", name=" + name + ", nation=" + nation + ", sex=" + sex + ", age=" + age
				+ ", school=" + school + ", major=" + major + ", detail=" + detail + ", isPoor=" + isPoor + ", tel="
				+ tel + ", parentTel=" + parentTel + "]";
	}
	public String getParentTel() {
		return parentTel;
	}
	public void setParentTel(String parentTel) {
		this.parentTel = parentTel;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean getIsPoor() {
		return isPoor;
	}
	public void setPoor(boolean isPoor) {
		this.isPoor = isPoor;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
