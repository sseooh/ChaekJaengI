package ChaekJaengI.ChaekJaengI.domain;

import javax.persistence.*;

@Entity
public class Member {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //이거 무조건 지워야 함!!!!! 이 놈 때문에 시간 엄청 버림....
    @Id
    private String id;
    private String pwd;


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
}
