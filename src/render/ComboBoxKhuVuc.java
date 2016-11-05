/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import bean.KhuVuc;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.ModelViTri;

/**
 *
 * @author HungPham
 */
public class ComboBoxKhuVuc implements ComboBoxModel<KhuVuc> {
    private KhuVuc kvSelected;
    private ArrayList<KhuVuc> alItem;
    private ModelViTri modelItem;
    

    public ComboBoxKhuVuc(KhuVuc item) {
        modelItem = new ModelViTri();
        alItem = modelItem.getList();
        if(kvSelected == null){
            this.kvSelected = alItem.get(0);
        } 
    }

    

    @Override
    public void setSelectedItem(Object anItem) {
        this.kvSelected = (KhuVuc) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return kvSelected;
    }

    @Override
    public int getSize() {
        return alItem.size();
    }

   
    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }

   
    @Override
    public KhuVuc getElementAt(int index) {
        return alItem.get(index);
    }
    
}
