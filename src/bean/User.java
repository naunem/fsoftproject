/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author HungPham
 */
public class User {
	private int id;
    private int  id_chucvu;
    private String username, chucvu, password, fullname, noio, email;

    @Override
    public String toString() {
        return username;
    }

    public User(int id_chucvu, String username, String chucvu, String password, String fullname, String noio, String email) {
        this.id_chucvu = id_chucvu;
        this.username = username;
        this.chucvu = chucvu;
        this.password = password;
        this.fullname = fullname;
        this.noio = noio;
        this.email = email;
    }
    public User(int id ,int id_chucvu, String username, String chucvu, String password, String fullname, String noio, String email) {
        this.id = id;
        this.id_chucvu = id_chucvu;
        this.username = username;
        this.chucvu = chucvu;
        this.password = password;
        this.fullname = fullname;
        this.noio = noio;
        this.email = email;
    }

    public void setId(int id){
    	this.id = id;
    }
    public int getId(){
    	return id;
    }
    public int getId_chucvu() {
        return id_chucvu;
    }

    public void setId_chucvu(int id_chucvu) {
        this.id_chucvu = id_chucvu;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getNoio() {
        return noio;
    }

    public void setNoio(String noio) {
        this.noio = noio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    private boolean active;
    private boolean search_active;

    public boolean isSearch_active() {
        return search_active;
    }

    public void setSearch_active(boolean search_active) {
        this.search_active = search_active;
    }

    public User(int id_user,String username, String password, String fullname, boolean active) {
        
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        
        this.search_active = active;
    }
    
    public User() {
    }

     

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
   
    
}
