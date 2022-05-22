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
        modItem = new JMenuItem("Modification"){ //csak azért hogy ne foglalja el az összes helyet a menubar-ban
            @Override
            public Dimension getMaximumSize(){
                Dimension d1 = super.getPreferredSize();
                Dimension d2 = super.getMaximumSize();
                d2.width=d1.width;
                return d2;
            }
        };

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
        String[] column = {"ID","Manufecturer","Brand","Type","Vram","TDP","Value","Name","Score"}; // Oszlopok

        Amd_graphics gpu1 = new Amd_graphics(Enums.AMD.RX550, Enums.Brand.Gigabyte,300,6,50,"Aurus");//Teszt példányok
        Intel_graphics gpu2 = new Intel_graphics(Enums.Intel.Iris_X, Enums.Brand.Gigabyte,33,2,22,"Szar");//Teszt példányok

        //Teszt adat
        String[][] data = new String[][] {
                {String.valueOf(gpu1.getID()), String.valueOf(gpu1.getManufacture()), String.valueOf(gpu1.getBrands()), String.valueOf(gpu1.getType()),
                        String.valueOf(gpu1.getVram()), String.valueOf(gpu1.getTDP()), String.valueOf(gpu1.getValue()),gpu1.getName()},
                {String.valueOf(gpu2.getID()), String.valueOf(gpu2.getManufacture()), String.valueOf(gpu2.getBrands()), String.valueOf(gpu2.getType()),
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
        if (e.getSource() == modItem){
            Modosit();
        }
    }
    public void Modosit(){
        if (table.getSelectedRow() != -1){
            JFrame mod = new JFrame(); //Panel létrehozása

            // Címkék megadása
            JLabel lb1,lb2,lb3,lb4,lb5,lb6;
            lb1 = new JLabel("Márka");
            lb1.setBounds(20,0,50,20);
            lb2 = new JLabel("Típus");
            lb2.setBounds(150,0,50,20);
            lb3 = new JLabel("VRAM");
            lb3.setBounds(20,60,50,20);
            lb4 = new JLabel("TDP");
            lb4.setBounds(100,60,50,20);
            lb5 = new JLabel("Value");
            lb5.setBounds(180,60,50,20);
            lb6 = new JLabel("Megnevezés");
            lb6.setBounds(270,0,100,20);

            JComboBox<Enums.Brand> Brandbox = new JComboBox<>();// Márkák listája
            Brandbox.setModel(new DefaultComboBoxModel<>(Enums.Brand.values()));
            Brandbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
            Brandbox.setBounds(20,20,90,20);

            JComboBox<Enums.AMD> Amdbox = new JComboBox<>(); //AMD Lista
            Amdbox.setModel(new DefaultComboBoxModel<>(Enums.AMD.values()));

            JComboBox<Enums.Intel> Intelbox = new JComboBox<>(); //Intel Lista
            Intelbox.setModel(new DefaultComboBoxModel<>(Enums.Intel.values()));

            JComboBox<Enums.Nvidia> Nvidiabox = new JComboBox<>(); // Nvidia Lista
            Nvidiabox.setModel(new DefaultComboBoxModel<>(Enums.Nvidia.values()));

            // Videókárty gyártó megállapítása
            if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("AMD")){
                Amdbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
                Amdbox.setBounds(150,20,90,20);
                mod.add(Amdbox);
            }else if (String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Intel")){
                Intelbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
                Intelbox.setBounds(150,20,90,20);
                mod.add(Intelbox);
            }else if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Nvidia")){
                Nvidiabox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
                Nvidiabox.setBounds(150,20,90,20);
                mod.add(Nvidiabox);
            }
            // Videókárty gyártó megállapításának vége

            // Számérték megadásának kezdete
            SpinnerNumberModel Vramvalue = new SpinnerNumberModel(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),4)),1,24,1);
            JSpinner Vramspinner = new JSpinner(Vramvalue);
            Vramspinner.setBounds(20,80,50,30);

            SpinnerNumberModel Tdpvalue = new SpinnerNumberModel(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),5)),1,120,2);
            JSpinner Tdpspinner = new JSpinner(Tdpvalue);
            Tdpspinner.setBounds(100,80,50,30);

            SpinnerNumberModel VValue = new SpinnerNumberModel(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),6)),50,10000,20);
            JSpinner Value = new JSpinner(VValue);
            Value.setBounds(180,80,100,30);
            // Számérték megadás vége

            // Megnevezés megadásának kezdete
            JTextField Name = new JTextField();
            Name.setText((String) table.getValueAt(table.getSelectedRow(),7));
            Name.setBounds(270,20,100,30);
            // Megnevezés megadásának vége

            // Gomb megadása
            JButton Confirm = new JButton("Véglegesít");
            Confirm.setBackground(Color.LIGHT_GRAY);
            Confirm.setBounds(310,80,100,30);

            // Gomb lenyomáskor kicseréli a táblázatban lévőket a megadott adatokra
            Confirm.addActionListener(e -> {

                table.setValueAt(Brandbox.getSelectedItem(),table.getSelectedRow(),2);

                if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("AMD")){
                    table.setValueAt(Amdbox.getSelectedItem(),table.getSelectedRow(),3);
                }else if (String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Intel")){
                    table.setValueAt(Intelbox.getSelectedItem(),table.getSelectedRow(),3);
                }else if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Nvidia")){
                    table.setValueAt(Nvidiabox.getSelectedItem(),table.getSelectedRow(),3);
                }

                table.setValueAt(Vramvalue.getValue(),table.getSelectedRow(),4);

                table.setValueAt(Tdpvalue.getValue(),table.getSelectedRow(),5);

                table.setValueAt(Value.getValue()+"$",table.getSelectedRow(),6);

                table.setValueAt(Name.getText(),table.getSelectedRow(),7);
                JOptionPane.showMessageDialog(null,"Sikeres módosítás");
            });
            //Gomb action vége

            //Panelhez való hozzáadás
            mod.add(Confirm);
            mod.add(Name);
            mod.add(Value);
            mod.add(Tdpspinner);
            mod.add(Vramspinner);
            mod.add(Brandbox);
            mod.add(lb1);
            mod.add(lb2);
            mod.add(lb3);
            mod.add(lb4);
            mod.add(lb5);
            mod.add(lb6);

            mod.setLocationRelativeTo(null);
            mod.setLayout(null);
            mod.setSize(500,180);
            mod.setVisible(true);
            //Panelhez való hozzáadás

        }else {
            JOptionPane.showMessageDialog(null,"Nincs kiválasztva sor","Hiba",JOptionPane.ERROR_MESSAGE); // hibakezelés
        }
    }
}