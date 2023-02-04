package model;

public class Water {
	int id;
	String firstName;
	String lastName;
	String email;
	int age;
	String sex;
	String woreda;
	String kebele;
	String houseNumber;
	String waterDept;
	String startDate;
	String endDate;
	public Water(int id, String firstName, String lastName, String email, String waterDept, String startDate,
			String endDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.waterDept = waterDept;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWaterDept() {
		return waterDept;
	}
	public void setWaterDept(String waterDept) {
		this.waterDept = waterDept;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWoreda() {
		return woreda;
	}
	public void setWoreda(String woreda) {
		this.woreda = woreda;
	}
	public String getKebele() {
		return kebele;
	}
	public void setKebele(String kebele) {
		this.kebele = kebele;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
}
