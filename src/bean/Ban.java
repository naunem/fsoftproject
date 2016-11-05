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
public class Ban {
    int idkhuvuc;
    String tenkhuvuc;
    int soban;
    int tinhtrang = -1;

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public Ban(int soban, int idkhuvuc) {
        this.idkhuvuc = idkhuvuc;
        this.soban = soban;
    }
    
    public Ban(int idkhuvuc, String tenkhuvuc, int soban) {
        this.idkhuvuc = idkhuvuc;
        this.tenkhuvuc = tenkhuvuc;
        this.soban = soban;
    }

    public int getIdkhuvuc() {
        return idkhuvuc;
    }

    public void setIdkhuvuc(int idkhuvuc) {
        this.idkhuvuc = idkhuvuc;
    }

    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public void setTenkhuvuc(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public int getSoban() {
        return soban;
    }

    public void setSoban(int soban) {
        this.soban = soban;
    }

    @Override
    public String toString() {
        if(tenkhuvuc!=null)
        return  tenkhuvuc +"-"+soban;
        return soban + "";
    }
    
}
