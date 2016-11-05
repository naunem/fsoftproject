package bean;

public class ThongKe {
   private int idthucuong;
   private int soluong;
   private float tongtien;
public ThongKe(int idthucuong, int soluong) {
	super();
	this.idthucuong = idthucuong;
	this.soluong = soluong;
}
public ThongKe(){
	
}
public int getIdthucuong() {
	return idthucuong;
}
public void setIdthucuong(int idthucuong) {
	this.idthucuong = idthucuong;
}
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}
public float getTongtien() {
	return tongtien;
}
public void setTongtien(float tongtien) {
	this.tongtien = tongtien;
}

}
