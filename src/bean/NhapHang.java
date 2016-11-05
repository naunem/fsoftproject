package bean;

import java.util.Date;


public class NhapHang {
   int idNhapHang;
   Date ngaynhaphang;
   float tongtien;
public int getIdNhapHang() {
	return idNhapHang;
}
public void setIdNhapHang(int idNhapHang) {
	this.idNhapHang = idNhapHang;
}
public Date getNgaynhaphang() {
	return ngaynhaphang;
}
public void setNgaynhaphang(Date ngaynhaphang) {
	this.ngaynhaphang = ngaynhaphang;
}
public float getTongtien() {
	return tongtien;
}
public void setTongtien(float tongtien) {
	this.tongtien = tongtien;
}
public NhapHang(float tongtien) {
	super();
	this.tongtien = tongtien;
}

   public NhapHang(int idNhapHang, Date ngaynhaphang, float tongtien) {
	super();
	this.idNhapHang = idNhapHang;
	this.ngaynhaphang = ngaynhaphang;
	this.tongtien = tongtien;
}
public NhapHang(){
	   
   }
}
