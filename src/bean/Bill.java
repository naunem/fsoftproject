/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
/**
 *
 * @author HungPham
 */
public class Bill {
    int idbill;
    String username;
    Date ngaythanhtoan;
    int idkhuvuc;
    String khuvuc;
    int ban;
    int thanhtien;
    int thanhtoan;

    public Bill() {
        
    }

    public Bill(int idkhuvuc, int ban) {
        this.idkhuvuc = idkhuvuc;
        this.ban = ban;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    public Bill(int idbill) {
        this.idbill = idbill;
    }

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getNgaythanhtoan() {
		return ngaythanhtoan;
	}

	public void setNgaythanhtoan(Date ngaythanhtoan) {
		this.ngaythanhtoan = ngaythanhtoan;
	}

	public int getIdkhuvuc() {
        return idkhuvuc;
    }

    public void setIdkhuvuc(int idkhuvuc) {
        this.idkhuvuc = idkhuvuc;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public Bill(int idbill, String username, Date ngaythanhtoan, int idkhuvuc, String khuvuc, int ban, int thanhtoan) {
        this.idbill = idbill;
        this.username = username;
        this.ngaythanhtoan=ngaythanhtoan;
        this.idkhuvuc = idkhuvuc;
        this.khuvuc = khuvuc;
        this.ban = ban;
        this.thanhtoan = thanhtoan;
    }
    
    
}
