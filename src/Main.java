import GraphicsCard.Enums;
import GraphicsCard.Manufactures.Amd_graphics;
import GraphicsCard.Manufactures.Intel_graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        System.out.println("Shit!");
        System.out.println("igen-szbkkk");
        Menu start = new Menu();
        start.openMenu();


    }
}

class Menu extends JFrame implements ActionListener{

    JMenuItem openItem;
    JMenuItem addItem;
    JMenuItem delItem;
    JMenuItem modItem;


    JTable table;


    public void openMenu(){

        setLocation(500,200);

        setTitle("Videókártya leltár");
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        // MENUBAR ---------------------------------------------------------------->

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu file = new JMenu("File");
        modItem = new JMenuItem("MOD");

        openItem = new JMenuItem("- Open -");
        addItem = new JMenuItem("- Add data -");
        delItem = new JMenuItem("- Delete data -");


        menubar.add(file);
        menubar.add(modItem);

        file.add(openItem);
        file.add(addItem);
        file.add(delItem);

        openItem.addActionListener(this);
        addItem.addActionListener(this);
        delItem.addActionListener(this);
        modItem.addActionListener(this);

        // MENUBAR ---------------------------------------------------------------->


        // TABLE ---------------------------------------------------------------->
        String[] column = {"ID","Manufecturer","Type","Vram","TDP","Value","Score"}; // Oszlopok

        Amd_graphics gpu1 = new Amd_graphics(Enums.AMD.RX550, Enums.Brand.Gigabyte,300,6,50,"Aurus");//Teszt példányok
        Intel_graphics gpu2 = new Intel_graphics(Enums.Intel.Iris_X, Enums.Brand.Gigabyte,33,2,22,"Szar");//Teszt példányok

        //Teszt adat
        String[][] data = new String[][] {
                {String.valueOf(gpu1.getID()), String.valueOf(gpu1.getManufacture()), String.valueOf(gpu1.getType()),
                        String.valueOf(gpu1.getVram()), String.valueOf(gpu1.getTDP()), String.valueOf(gpu1.getValue()),gpu1.getName()},
                {String.valueOf(gpu2.getID()), String.valueOf(gpu2.getManufacture()), String.valueOf(gpu2.getType()),
                        String.valueOf(gpu2.getVram()), String.valueOf(gpu2.getTDP()), String.valueOf(gpu2.getValue()),gpu2.getName()}
        };

        DefaultTableModel model = new DefaultTableModel(data,column);
        table = new JTable(model){
            public boolean isCellEditable(int row,int column){
                return false; //Szerkezthetőség letíltása
            }
        }; // Tábla létrehozása

        JScrollPane sp = new JScrollPane(table);
        add(sp); // Táblázat hozzáadása
        // TABLE ---------------------------------------------------------------->


        setSize(800,800);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}