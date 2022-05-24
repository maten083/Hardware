package GraphicsCard;

public abstract class Gpu {
    protected int ID;
    protected Enums.Manufacture manufacture;
    protected Enums.Brand brands;
    protected int value;
    protected int vram;
    protected int TDP;
    protected String name;
    public Gpu(Enums.Brand brands,int value,int vram,int TDP,String name){
        this.brands = brands;
        this.value = value;
        this.vram = vram;
        this.TDP = TDP;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public Enums.Manufacture getManufacture() {
        return manufacture;
    }

    public Enums.Brand getBrands() {
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
