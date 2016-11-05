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
public class ChucVu {
    int machucvu;
    String chucvu;
    int luongcoban;

    @Override
    public String toString() {
        return chucvu;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(int luongcoban) {
        this.luongcoban = luongcoban;
    }

    public ChucVu(int machucvu, String chucvu, int luongcoban) {
        this.machucvu = machucvu;
        this.chucvu = chucvu;
        this.luongcoban = luongcoban;
    }
    
    
}

