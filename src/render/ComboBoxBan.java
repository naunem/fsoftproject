/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import bean.Ban;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.ModelViTri;

/**
 *
 * @author HungPham
 */
public class ComboBoxBan implements ComboBoxModel<Ban> {
    private Ban bSelected;
    private ArrayList<Ban> alItem;
    private ModelViTri modelItem;
    

    public ComboBoxBan(Ban bSelected, int kv) {
        modelItem = new ModelViTri();
        alItem = modelItem.getBan(kv);
        if(bSelected == null){
            this.bSelected = alItem.get(0);
        } else{
            this.bSelected = bSelected;
        }
    }

    

    @Override
    public void setSelectedItem(Object anItem) {
        this.bSelected = (Ban) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return bSelected;
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
    public Ban getElementAt(int index) {
        return alItem.get(index);
    }
    
}
