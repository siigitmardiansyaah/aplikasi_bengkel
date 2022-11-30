/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bengkel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author mardi
 */
public class AutoComplete extends JComboBox<Object> {
    String[] keyWord = null;
    Vector vec = new Vector();
    
    public AutoComplete(String[] keyWord){
        this.keyWord = keyWord;
        setModel(new DefaultComboBoxModel<>(vec));
        setSelectedIndex(-1);
        setEditable(true);
        JTextField text = (JTextField)getEditor().getEditorComponent();
        text.setFocusable(true);
        text.setText("");
        text.addKeyListener(new ComboListener(this, vec));
        setMyVector();
        setSelectedIndex(0);
    }
    
    private void setMyVector() {
        int a;
        for (a = 0; a < keyWord.length; a++) {
            vec.add(keyWord[a]);
        }
    }
}

class ComboListener extends KeyAdapter{
    JComboBox cblistener;
    Vector vec;

    public ComboListener(JComboBox cblistener, Vector vec){
        this.cblistener = cblistener;
        this.vec = vec;
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        String text = ((JTextField)e.getSource()).getText();
        cblistener.setModel(new DefaultComboBoxModel(getFilteredList(text)));
        cblistener.setSelectedIndex(-1);
        ((JTextField)cblistener.getEditor().getEditorComponent()).setText(text);
        cblistener.showPopup();
    }

    public Vector getFilteredList(String text){
        Vector v = new Vector();
        for(int i = 0; i < vec.size(); i++){
            if(vec.get(i).toString().startsWith(text))
                v.add(vec.get(i).toString());
            else if(vec.get(i).toString().startsWith(text.toLowerCase()))
                v.add(vec.get(i).toString());
            else if(vec.get(i).toString().startsWith(text.toUpperCase()))
                v.add(vec.get(i).toString());
        }
        return v;
    }    
    
}
