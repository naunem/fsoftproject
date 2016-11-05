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
public class ThongTin {
    String tenquan;
    String diachi;
    String phone;
    String chuquan;
    String giomocua;

    public ThongTin(String tenquan, String diachi, String phone, String chuquan, String giomocua) {
        this.tenquan = tenquan;
        this.diachi = diachi;
        this.phone = phone;
        this.chuquan = chuquan;
        this.giomocua = giomocua;
    }

    public String getTenquan() {
        return tenquan;
    }

    public void setTenquan(String tenquan) {
        this.tenquan = tenquan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChuquan() {
        return chuquan;
    }

    public void setChuquan(String chuquan) {
        this.chuquan = chuquan;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }
    
}
