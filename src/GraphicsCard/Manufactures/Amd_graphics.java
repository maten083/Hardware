package GraphicsCard.Manufactures;

import GraphicsCard.Enums;
import GraphicsCard.Gpu;

public class Amd_graphics extends Gpu {
    protected Enums.AMD type;
    public Amd_graphics(Enums.AMD type, Enums.Brand brands, int value, int vram,int TDP, String name){
        super(brands,value,vram,TDP,name);

        this.manufacture = Enums.Manufacture.AMD;
        this.type = type;
    }

    public Enums.AMD getType() {
        return type;
    }
}
// Videókárty gyártó megállapítása
        /*if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("AMD")){
            Amdbox.setBounds(260,20,90,20);
            mod.add(Amdbox);
        }else if (String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Intel")){
            Intelbox.setBounds(260,20,90,20);
            mod.add(Intelbox);
        }else if(String.valueOf(table.getValueAt(table.getSelectedRow(),1)).equals("Nvidia")){
            Nvidiabox.setBounds(260,20,90,20);
            mod.add(Nvidiabox);
        }*/