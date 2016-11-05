/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import bean.ChucVu;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.ModelChucVu;

/**
 *
 * @author HungPham
 */
public class ComboboxModelChucVu implements ComboBoxModel<ChucVu>{
    private ChucVu catSelected;
    private ArrayList<ChucVu> alItem;
    private ModelChucVu modelCat;
    public ComboboxModelChucVu(ChucVu catSelected) throws ClassNotFoundException, SQLException, IOException{
        modelCat = new ModelChucVu();
        alItem = new ArrayList<ChucVu>();
        modelCat.CreateViewChucVu();
        alItem = modelCat.CreatViewChucVu(null);
        if(catSelected == null){
            this.catSelected = alItem.get(0);
        } else{
            this.catSelected = catSelected;
        }
        
        
    }

    

    

    @Override
    public void setSelectedItem(Object anItem) {
        this.catSelected = (ChucVu) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return catSelected;
    }

    @Override
    public int getSize() {
        return alItem.size();
    }

    @Override
    public ChucVu getElementAt(int index) {
        return alItem.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
       
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
}
