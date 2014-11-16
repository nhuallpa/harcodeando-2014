/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui.utils;

import ar.com.hardcodeando.ui.MD5PanelEvaluar;
import javax.swing.AbstractCellEditor;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author estefi
 */  
public class MyCellEditor extends AbstractCellEditor implements TableCellEditor {

    
  public static String pattern = "<html><div([^>]+)>([^<]+)<";
  public static Pattern hexaCode = Pattern.compile(pattern);
  JComponent component = new JTextField();
  private MD5PanelEvaluar reference = null;

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
      int rowIndex, int vColIndex) {
      String aux = (String) value;
      ((JTextField) component).setText(cleanText(aux));
    return component;
  }

  public Object getCellEditorValue() {
    return ((JTextField) component).getText();
  }
  
  public void setMainPanelReference(MD5PanelEvaluar ref){
      this.reference=ref;
  }
  
  public static String cleanText(String text){
    if(text==null) return null;
    if(text.startsWith("<html>")){
        Matcher m = hexaCode.matcher(text);
        if (m.find( )) {
            return m.group(2);
        } 
    }
    return text;
  }
}