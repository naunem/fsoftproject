package bean;

public class Kho {
  private int idthucuong;
  private int soluongcon;
public int getIdthucuong() {
	return idthucuong;
}
public void setIdthucuong(int idthucuong) {
	this.idthucuong = idthucuong;
}
public int getSoluongcon() {
	return soluongcon;
}
public void setSoluongcon(int soluongcon) {
	this.soluongcon = soluongcon;
}
public Kho(int idthucuong, int soluongcon) {
	super();
	this.idthucuong = idthucuong;
	this.soluongcon = soluongcon;
}
  public Kho(){
	  
  }
}
