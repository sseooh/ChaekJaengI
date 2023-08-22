package ChaekJaengI.ChaekJaengI.controller;

public class MemberForm {
    private String id;

    private String pwd;

    private String pwd_check;


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPwd(){
        return pwd;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public String getPwd_check() {
        return pwd_check;
    }

    public void setPwd_check(String pwd_check) {
        this.pwd_check = pwd_check;
    }
}
