package GraphicsCard.Manufactures;

import GraphicsCard.Enums;
import GraphicsCard.Gpu;
import GraphicsCard.Annotations.getterFuncionName;

public class Intel_graphics extends Gpu {

    public Intel_graphics(Object type, Object brands, int value, int vram, int TDP, String name){
        super(brands,value,vram,TDP,name);

        this.manufacture = Enums.Manufacture.Intel;
        this.type = type;
    }

    public Object getType() {
        return type;
    }
}
