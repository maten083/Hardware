package GraphicsCard;

import GraphicsCard.Annotations.getterFuncionName;

//abstract generic class------------->
public abstract class Gpu{

    @getterFuncionName(value = "getManufacture",type = Enums.Manufacture.class)
    protected Enums.Manufacture manufacture;
    @getterFuncionName(value = "getBrands", type = Enums.Brand.class)
    protected Object brands;
    @getterFuncionName(value = "getValue", type = Integer.class)
    protected int value;
    @getterFuncionName(value = "getVram", type = Integer.class)
    protected int vram;
    @getterFuncionName(value = "getTDP",type = Integer.class)
    protected int TDP;
    @getterFuncionName(value = "getName",type = String.class)
    protected String name;
    @getterFuncionName(value = "getType", type = Enums.class)
    protected Object type;

    public Gpu(Object brands, int value, int vram, int TDP, String name){
        this.brands = brands;
        this.value = value;
        this.vram = vram;
        this.TDP = TDP;
        this.name = name;
    }

    public Enums.Manufacture getManufacture() {
        return manufacture;
    }

    public Object getBrands() {
        return brands;
    }

    public int getValue() {
        return value;
    }

    public int getVram() {
        return vram;
    }

    public int getTDP() {
        return TDP;
    }

    public String getName() {
        return name;
    }
}
