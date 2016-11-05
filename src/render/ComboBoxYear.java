/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author HungPham
 */
public class ComboBoxYear implements ComboBoxModel<Object>{
    private int year = 0;
    @SuppressWarnings("rawtypes")
	private ArrayList<Comparable> al;
    
    @SuppressWarnings("rawtypes")
	public ComboBoxYear(){
        al = new ArrayList<Comparable>();
        al.add("Năm");
        for(int i = 2010; i<=2070; i++){
            al.add(i);
        }
    }
    public void setSelectedItem(Object anItem) {
        this.year = (int) anItem;
    }
    

    @Override
    public Object getSelectedItem() {
        if(year !=0)
        return year;
        return "Năm";
    }

    @Override
    public int getSize() {
        return al.size();
    }

    @Override
    public Object getElementAt(int index) {
        return al.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
      
}
