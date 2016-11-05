/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import bean.ChucVu;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.ModelChucVu;

/**
 *
 * @author HungPham
 */
public class ComboBoxChucVu implements ComboBoxModel<ChucVu> {
    private ChucVu cvSelected;
    private ArrayList<ChucVu> alItem;
    private ModelChucVu modelItem;
    

    public ComboBoxChucVu(ChucVu item) {
        modelItem = new ModelChucVu();
        modelItem.CreateViewChucVu();
        alItem = modelItem.CreatViewChucVu(null);
        if(cvSelected == null){
            this.cvSelected = alItem.get(0);
        }
    }

    

    @Override
    public void setSelectedItem(Object anItem) {
        this.cvSelected = (ChucVu) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return cvSelected;
    }

    @Override
    public int getSize() {
        return alItem.size();
    }

   
    @Override
    public void addListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChucVu getElementAt(int index) {
        return alItem.get(index);
    }
    
}
