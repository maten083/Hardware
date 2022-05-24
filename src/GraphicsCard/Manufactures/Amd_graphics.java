package GraphicsCard.Manufactures;

import GraphicsCard.Enums;
import GraphicsCard.Gpu;
import GraphicsCard.Annotations.getterFuncionName;

public class Amd_graphics extends Gpu {
    @getterFuncionName(value = "getType",type = Enums.AMD.class)
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
