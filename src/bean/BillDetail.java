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
public class BillDetail {
    int idbill;
    int idfood;
    int soluong;

    public BillDetail() {
    }
    

    public int getIdfood() {
        return idfood;
    }

    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }

    public BillDetail(int idbill, int idfood, int soluong) {
        this.idbill = idbill;
        this.idfood = idfood;
        this.soluong = soluong;
    }

    

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
    }


    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
}
