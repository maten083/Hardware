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

        String[] column = {"ID","Manufecturer","Type","Vram","TDP","Value","Score"};
        String[][] data = new String[][] { {"0","AMD","RX 460","2GB","40W","200$","123"}};
        DefaultTableModel model = new DefaultTableModel(data,column);
        table = new JTable(model);
        add(new JScrollPane(table),BorderLayout.CENTER);

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

        add(table);
        setSize(800,800);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}