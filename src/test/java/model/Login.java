package model;

public class Login {
	
	private String email; 
    private String password;	
    private String mobile;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	 @Override
		public String toString() {
			return "Login [email=" + email + ", password=" + password + ", mobile=" + mobile + "]";
		}

}
