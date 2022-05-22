package GraphicsCard.Manufactures;

import GraphicsCard.Enums;
import GraphicsCard.Gpu;

public class Intel_graphics extends Gpu {
    protected Enums.Intel type;
    public Intel_graphics(Enums.Intel type,Enums.Brand brands,int value,int vram,int TDP,String name){
        super(brands,value,vram,TDP,name);

        this.manufacture = Enums.Manufacture.Intel;
        this.type = type;
    }

    public Enums.Intel getType() {
        return type;
    }
}
