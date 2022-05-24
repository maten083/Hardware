package GraphicsCard.Manufactures;

import GraphicsCard.Enums;
import GraphicsCard.Gpu;
import GraphicsCard.Annotations.getterFuncionName;

public class Nvidia_graphics extends Gpu {
    @getterFuncionName(value = "getType", type = Enums.Nvidia.class)
    protected Enums.Nvidia type;
    public Nvidia_graphics(Enums.Nvidia type, Enums.Brand brands, int value, int vram,int TDP, String name){
        super(brands,value,vram,TDP,name);

        this.manufacture = Enums.Manufacture.Nvidia;
        this.type = type;
    }

    public Enums.Nvidia getType() {
        return type;
    }
}
