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
import java.text.DecimalFormat;

public class Main {



    public static void main(String[] args) {

        Menu start = new Menu();
        start.openMenu();


    }
}

class Menu extends JFrame implements ActionListener{

    JMenuItem calcScoreItem;
    JMenuItem addItem;
    JMenuItem delItem;
    JMenuItem modItem;
    JTable table;

    DefaultTableModel model;


    public void openMenu(){

        setLocation(500,200);

        setTitle("Videókártya leltár");
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        // MENUBAR ---------------------------------------------------------------->

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu file = new JMenu("File ");

        modItem = new JMenuItem("Edit Row"){ //csak azért hogy ne foglalja el az összes helyet a menubar-ban
            @Override
            public Dimension getMaximumSize(){
                Dimension d1 = super.getPreferredSize();
                Dimension d2 = super.getMaximumSize();
                d2.width=d1.width;
                return d2;
            }
        };
        calcScoreItem = new JMenuItem("Calculate Score"){ //csak azért hogy ne foglalja el az összes helyet a menubar-ban
            @Override
            public Dimension getMaximumSize(){
                Dimension d1 = super.getPreferredSize();
                Dimension d2 = super.getMaximumSize();
                d2.width=d1.width;
                return d2;
            }
        };


        addItem = new JMenuItem("- Add data -");
        delItem = new JMenuItem("- Delete data -");


        menubar.add(file);
        menubar.add(modItem);
        menubar.add(calcScoreItem);

        file.add(addItem);
        file.add(delItem);

        calcScoreItem.addActionListener(this);
        addItem.addActionListener(this);
        delItem.addActionListener(this);
        modItem.addActionListener(this);

        // MENUBAR ---------------------------------------------------------------->


        // TABLE ---------------------------------------------------------------->
        String[] column = {"Manufecturer","Brand","Type","Vram","TDP","Value","Name","Score"}; // Oszlopok

        Amd_graphics gpu1 = new Amd_graphics(Enums.AMD.RX550, Enums.Brand.Gigabyte,300,6,50,"Aurus");//Teszt példányok
        Intel_graphics gpu2 = new Intel_graphics(Enums.Intel.Iris_X, Enums.Brand.Gigabyte,50,2,22,"Szar");//Teszt példányok

        //Teszt adat
        String[][] data = new String[][] {
                {String.valueOf(gpu1.getManufacture()), String.valueOf(gpu1.getBrands()), String.valueOf(gpu1.getType()),
                        String.valueOf(gpu1.getVram())+" GB", String.valueOf(gpu1.getTDP())+" W", String.valueOf(gpu1.getValue())+" $",gpu1.getName()},

                {String.valueOf(gpu2.getManufacture()), String.valueOf(gpu2.getBrands()), String.valueOf(gpu2.getType()),
                        String.valueOf(gpu2.getVram())+" GB", String.valueOf(gpu2.getTDP())+" W", String.valueOf(gpu2.getValue())+" $",gpu2.getName()}
        };

        model = new DefaultTableModel(data,column);


        table = new JTable(model){
            public boolean isCellEditable(int row,int column){
                return false; //Szerkezthetőség letiltása
            }
        };

        // Tábla létrehozása

        JScrollPane sp = new JScrollPane(table);
        add(sp); // Táblázat hozzáadása
        // TABLE ---------------------------------------------------------------->


        setSize(800,800);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(modItem)){
            Modosit();
        }
        //Add row
        if (e.getSource().equals(addItem)){
            AddRow();
        }
        // Add row
        // DELETE item ------------------------------------->
        if (e.getSource().equals(delItem)){
            model.removeRow(table.getSelectedRow());
        }
        // DELETE item ------------------------------------->
        if (e.getSource().equals(calcScoreItem)){
            scoreCalculate();
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

            JComboBox<Enums.Brand> Brandbox = new JComboBox<>();// Brands list
            Brandbox.setModel(new DefaultComboBoxModel<>(Enums.Brand.values()));
            Brandbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),1)));
            Brandbox.setBounds(20,20,90,20);

            JComboBox<Enums.AMD> Amdbox = new JComboBox<>(); //AMD Lista
            Amdbox.setModel(new DefaultComboBoxModel<>(Enums.AMD.values()));

            JComboBox<Enums.Intel> Intelbox = new JComboBox<>(); //Intel Lista
            Intelbox.setModel(new DefaultComboBoxModel<>(Enums.Intel.values()));

            JComboBox<Enums.Nvidia> Nvidiabox = new JComboBox<>(); // Nvidia Lista
            Nvidiabox.setModel(new DefaultComboBoxModel<>(Enums.Nvidia.values()));

            // Videókártya gyártó megállapítása
            if(String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("AMD")){
                Amdbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
                Amdbox.setBounds(150,20,90,20);
                mod.add(Amdbox);
            }else if (String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("Intel")){
                Intelbox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
                Intelbox.setBounds(150,20,90,20);
                mod.add(Intelbox);
            }else if(String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("Nvidia")){
                Nvidiabox.getModel().setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
                Nvidiabox.setBounds(150,20,90,20);
                mod.add(Nvidiabox);
            }
            // Videókárty gyártó megállapításának vége

            // Számérték megadásának kezdete
            String Vramstr = String.valueOf(table.getValueAt(table.getSelectedRow(),3));
            SpinnerNumberModel Vramvalue = new SpinnerNumberModel(Integer.parseInt(Vramstr.substring(0,Vramstr.length()-3)),1,24,1);
            JSpinner Vramspinner = new JSpinner(Vramvalue);
            Vramspinner.setBounds(20,80,50,30);

            String tdpstr = String.valueOf(table.getValueAt(table.getSelectedRow(),4));
            SpinnerNumberModel Tdpvalue = new SpinnerNumberModel(Integer.parseInt(tdpstr.substring(0,tdpstr.length()-2)),1,500,2);
            JSpinner Tdpspinner = new JSpinner(Tdpvalue);
            Tdpspinner.setBounds(100,80,50,30);

            String Vvaluestr = String.valueOf(table.getValueAt(table.getSelectedRow(),5));
            SpinnerNumberModel VValue = new SpinnerNumberModel(Integer.parseInt(Vvaluestr.substring(0,Vvaluestr.length()-2)),50,10000,20);
            JSpinner Value = new JSpinner(VValue);
            Value.setBounds(180,80,100,30);
            // Számérték megadás vége

            // Megnevezés megadásának kezdete
            JTextField Name = new JTextField();
            Name.setText((String) table.getValueAt(table.getSelectedRow(),6));
            Name.setBounds(270,20,100,30);
            // Megnevezés megadásának vége

            // Gomb megadás
            JButton Confirm = new JButton("Véglegesít");
            Confirm.setBackground(Color.LIGHT_GRAY);
            Confirm.setBounds(310,80,100,30);

            // Gomb lenyomáskor kicseréli a táblázatban lévőket a megadott adatokra
            Confirm.addActionListener(e -> {

                table.setValueAt(Brandbox.getSelectedItem(),table.getSelectedRow(),1);

                if(String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("AMD")){
                    table.setValueAt(Amdbox.getSelectedItem(),table.getSelectedRow(),2);
                }else if (String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("Intel")){
                    table.setValueAt(Intelbox.getSelectedItem(),table.getSelectedRow(),2);
                }else if(String.valueOf(table.getValueAt(table.getSelectedRow(),0)).equals("Nvidia")){
                    table.setValueAt(Nvidiabox.getSelectedItem(),table.getSelectedRow(),2);
                }

                table.setValueAt(Vramvalue.getValue()+" GB",table.getSelectedRow(),3);

                table.setValueAt(Tdpvalue.getValue()+" W",table.getSelectedRow(),4);

                table.setValueAt(Value.getValue()+" $",table.getSelectedRow(),5);

                table.setValueAt(Name.getText(),table.getSelectedRow(),6);
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
    public void AddRow(){
        JFrame selectFrame = new JFrame();
        JButton amd,intel,nvidia;

        amd = new JButton("AMD");
        amd.setBounds(20,30,100,50);
        amd.setBackground(Color.lightGray);
        amd.addActionListener(AmdAction -> {
            AddRowAMD();
        });

        intel = new JButton("Intel");
        intel.setBounds(120,30,100,50);
        intel.setBackground(Color.lightGray);
        intel.addActionListener(IntelAction ->{
            AddRowIntel();
        });

        nvidia = new JButton("Nvidia");
        nvidia.setBounds(220,30,100,50);
        nvidia.setBackground(Color.lightGray);
        nvidia.addActionListener(NvidiaAction->{
            AddRowNvidia();
        });

        selectFrame.add(amd);
        selectFrame.add(intel);
        selectFrame.add(nvidia);

        selectFrame.setLocationRelativeTo(null);
        selectFrame.setLayout(null);
        selectFrame.setSize(360,150);
        selectFrame.setVisible(true);
    }
    public void AddRowAMD(){
        JFrame mod = new JFrame(); //Panel létrehozása

        JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
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
        Brandbox.setBounds(20,20,90,20);

        JComboBox<Enums.AMD> Amdbox = new JComboBox<>(); //AMD Lista
        Amdbox.setModel(new DefaultComboBoxModel<>(Enums.AMD.values()));
        Amdbox.setBounds(150,20,90,20);


        // Számérték megadásának kezdete
        SpinnerNumberModel Vramvalue = new SpinnerNumberModel(1,1,24,1);
        JSpinner Vramspinner = new JSpinner(Vramvalue);
        Vramspinner.setBounds(20,80,50,30);

        SpinnerNumberModel Tdpvalue = new SpinnerNumberModel(1,1,120,2);
        JSpinner Tdpspinner = new JSpinner(Tdpvalue);
        Tdpspinner.setBounds(100,80,50,30);

        SpinnerNumberModel VValue = new SpinnerNumberModel(50,50,10000,20);
        JSpinner Value = new JSpinner(VValue);
        Value.setBounds(180,80,100,30);
        // Számérték megadás vége

        // Megnevezés megadásának kezdete
        JTextField Name = new JTextField();
        Name.setBounds(270,20,100,30);
        // Megnevezés megadásának vége

        // Gomb megadás
        JButton Confirm = new JButton("Véglegesít");
        Confirm.setBackground(Color.LIGHT_GRAY);
        Confirm.setBounds(310,80,100,30);

        // Gomb lenyomáskor kicseréli a táblázatban lévőket a megadott adatokra
        Confirm.addActionListener(e -> {
            model.addRow(new Object[]{Enums.Manufacture.AMD,Brandbox.getSelectedItem(),Amdbox.getSelectedItem(),Vramspinner.getValue()+" GB",Tdpspinner.getValue()+" W",VValue.getValue()+" $",Name.getText()});
            JOptionPane.showMessageDialog(null,"Sikeres hozzáadás");
        });

        //Gomb action vége

        //Panelhez való hozzáadás
        mod.add(Confirm);
        mod.add(Name);
        mod.add(Value);
        mod.add(Amdbox);
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
    }
    public void AddRowNvidia(){
        JFrame mod = new JFrame(); //Panel létrehozása

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
        Brandbox.setBounds(20,20,90,20);

        JComboBox<Enums.Nvidia> Nvidiabox = new JComboBox<>(); //Nvidia Lista
        Nvidiabox.setModel(new DefaultComboBoxModel<>(Enums.Nvidia.values()));
        Nvidiabox.setBounds(150,20,90,20);


        // Számérték megadásának kezdete
        SpinnerNumberModel Vramvalue = new SpinnerNumberModel(1,1,24,1);
        JSpinner Vramspinner = new JSpinner(Vramvalue);
        Vramspinner.setBounds(20,80,50,30);

        SpinnerNumberModel Tdpvalue = new SpinnerNumberModel(1,1,120,2);
        JSpinner Tdpspinner = new JSpinner(Tdpvalue);
        Tdpspinner.setBounds(100,80,50,30);

        SpinnerNumberModel VValue = new SpinnerNumberModel(50,50,10000,20);
        JSpinner Value = new JSpinner(VValue);
        Value.setBounds(180,80,100,30);
        // Számérték megadás vége

        // Megnevezés megadásának kezdete
        JTextField Name = new JTextField();
        Name.setBounds(270,20,100,30);
        // Megnevezés megadásának vége

        // Gomb megadás
        JButton Confirm = new JButton("Véglegesít");
        Confirm.setBackground(Color.LIGHT_GRAY);
        Confirm.setBounds(310,80,100,30);

        // Gomb lenyomáskor kicseréli a táblázatban lévőket a megadott adatokra
        Confirm.addActionListener(e -> {
            model.addRow(new Object[]{Enums.Manufacture.Nvidia,Brandbox.getSelectedItem(),Nvidiabox.getSelectedItem(),Vramspinner.getValue()+" GB",Tdpspinner.getValue()+" W",VValue.getValue()+" $",Name.getText()});
            JOptionPane.showMessageDialog(null,"Sikeres hozzáadás");
        });
        //Gomb action vége

        //Panelhez való hozzáadás
        mod.add(Confirm);
        mod.add(Name);
        mod.add(Value);
        mod.add(Nvidiabox);
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
    }
    public void AddRowIntel(){

        JFrame mod = new JFrame(); //Panel létrehozása

        JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
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
        Brandbox.setBounds(20,20,90,20);

        JComboBox<Enums.Intel> Intelbox = new JComboBox<>(); //Nvidia Lista
        Intelbox.setModel(new DefaultComboBoxModel<>(Enums.Intel.values()));
        Intelbox.setBounds(150,20,90,20);


        // Számérték megadásának kezdete
        SpinnerNumberModel Vramvalue = new SpinnerNumberModel(1,1,24,1);
        JSpinner Vramspinner = new JSpinner(Vramvalue);
        Vramspinner.setBounds(20,80,50,30);

        SpinnerNumberModel Tdpvalue = new SpinnerNumberModel(1,1,120,2);
        JSpinner Tdpspinner = new JSpinner(Tdpvalue);
        Tdpspinner.setBounds(100,80,50,30);

        SpinnerNumberModel VValue = new SpinnerNumberModel(50,50,10000,20);
        JSpinner Value = new JSpinner(VValue);
        Value.setBounds(180,80,100,30);
        // Számérték megadás vége

        // Megnevezés megadásának kezdete
        JTextField Name = new JTextField();
        Name.setBounds(270,20,100,30);
        // Megnevezés megadásának vége

        // Gomb megadás
        JButton Confirm = new JButton("Véglegesít");
        Confirm.setBackground(Color.LIGHT_GRAY);
        Confirm.setBounds(310,80,100,30);

        // Gomb lenyomáskor kicseréli a táblázatban lévőket a megadott adatokra
        Confirm.addActionListener(e -> {
            model.addRow(new Object[]{Enums.Manufacture.Intel,Brandbox.getSelectedItem(),Intelbox.getSelectedItem(),Vramspinner.getValue()+" GB",Tdpspinner.getValue()+" W",VValue.getValue()+" $",Name.getText()});
            JOptionPane.showMessageDialog(null,"Sikeres hozzáadás");
        });
        //Gomb action vége

        //Panelhez való hozzáadás
        mod.add(Confirm);
        mod.add(Name);
        mod.add(Value);
        mod.add(Intelbox);
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
    }



    public void scoreCalculate(){

        //  (TDP/VRAM) x VALUE


        double maxScore = 0;
        double szam = 0;
        int maxID = 0;

        //
        String str1 = "";
        String vram = "";

        String str2 = "";
        String tdp = "";

        String str3 = "";
        String value = "";



        for(int i = 0 ;i < model.getRowCount(); i++){
            double calcScore = 0;

            str1 = String.valueOf(model.getValueAt(i,3));
            vram = str1.substring(0,str1.length()-3);
            double vramDouble = Double.parseDouble(vram);

            str2 = String.valueOf(model.getValueAt(i,4));
            tdp = str2.substring(0,str2.length()-2);
            double tdpDouble = Double.parseDouble(tdp);

            str3 = String.valueOf(model.getValueAt(i,5));
            value = str3.substring(0,str3.length()-2);
            double valueDouble = Double.parseDouble(value);

            calcScore = (tdpDouble/vramDouble)*valueDouble;

            String.format("%2f",calcScore); //kerekítés

            model.setValueAt(calcScore,i,7); // Fill cells

            szam = (double) model.getValueAt(i,7);
            if (szam > maxScore){
                maxScore = szam;
                maxID=i;
            }
        }

        JOptionPane.showMessageDialog(null,model.getValueAt(maxID,0)+" "
                +model.getValueAt(maxID,1)+" "
                +model.getValueAt(maxID,2)+" "
                +model.getValueAt(maxID,3)+" "
                +model.getValueAt(maxID,4)+" "
                +model.getValueAt(maxID,5)+" "
                +model.getValueAt(maxID,6)+" "
                +model.getValueAt(maxID,7),"A legjobb Kártya",JOptionPane.PLAIN_MESSAGE);

    }
        // commiting test....
        //talán műkszik------

}