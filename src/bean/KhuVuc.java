/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.swing.JPanel;

/**
 *
 * @author HungPham
 */
public class KhuVuc {
    int idkhuvuc;
    String khuvuc;
    JPanel pnKhuVuc;

    public KhuVuc(int idkhuvuc, String khuvuc) {
        this.idkhuvuc = idkhuvuc;
        this.khuvuc = khuvuc;
        this.pnKhuVuc = new JPanel();
    }

    public KhuVuc(int idkhuvuc, String khuvuc, JPanel pnKhuVuc) {
        this.idkhuvuc = idkhuvuc;
        this.khuvuc = khuvuc;
        this.pnKhuVuc = pnKhuVuc;
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

    public JPanel getPnKhuVuc() {
        return pnKhuVuc;
    }

    public void setPnKhuVuc(JPanel pnKhuVuc) {
        this.pnKhuVuc = pnKhuVuc;
    }

    @Override
    public String toString() {
        return khuvuc;
    }
    
}
