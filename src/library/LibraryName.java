/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Bi
 */
public class LibraryName {
    public static final String TITLE_NAME = "JB COFFEE SHOP";
    private  Color color_Title_PnCenter;
    private  Color color_small_panel;
    private  Color color_background;
    private  Font font_Title_PnCenter;

    public Color getColor_background() {
        return color_background;
    }

    public void setColor_background(Color color_background) {
        this.color_background = color_background;
    }
   
    public Color getColor_small_panel() {
        return color_small_panel;
    }

    public void setColor_small_panel(Color color_small_panel) {
        this.color_small_panel = color_small_panel;
    }
    public LibraryName(){
        color_Title_PnCenter = new Color(0,0,204);
        font_Title_PnCenter = new Font("Time News Roman", Font.BOLD, 20);
        color_small_panel = new Color(255,255,255);
         color_background = new Color(255,255,204);
    }

    public  Font getFont_Title_PnCenter() {
        return font_Title_PnCenter;
    }

    public  void setFont_Title_PnCenter(Font font_Title_PnCenter) {
        this.font_Title_PnCenter = font_Title_PnCenter;
    }

    public  Color getColor_Title_PnCenter() {
        return color_Title_PnCenter;
    }

    public  void setColor_Title_PnCenter(Color color_Title_PnCenter) {
        this.color_Title_PnCenter = color_Title_PnCenter;
    }
}
    
