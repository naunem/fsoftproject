package bean;

public class ChiTietNhapHang {
   int idNhapHang;
   int idThucPham;
   int soluong;
   float gianhap;
public int getIdNhapHang() {
	return idNhapHang;
}
public void setIdNhapHang(int idNhapHang) {
	this.idNhapHang = idNhapHang;
}
public int getIdThucPham() {
	return idThucPham;
}
public void setIdThucPham(int idThucPham) {
	this.idThucPham = idThucPham;
}
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}
public float getGianhap() {
	return gianhap;
}
public void setGianhap(float gianhap) {
	this.gianhap = gianhap;
}
public ChiTietNhapHang(int idThucPham, int soluong,
		float gianhap) {
	super();
	this.idThucPham = idThucPham;
	this.soluong = soluong;
	this.gianhap = gianhap;
}

public ChiTietNhapHang(int idNhapHang, int idThucPham, int soluong,
		float gianhap) {
	super();
	this.idNhapHang = idNhapHang;
	this.idThucPham = idThucPham;
	this.soluong = soluong;
	this.gianhap = gianhap;
}
public ChiTietNhapHang(){
	
}
}
