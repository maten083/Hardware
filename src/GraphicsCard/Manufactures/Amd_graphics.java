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
