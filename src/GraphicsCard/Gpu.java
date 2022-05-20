package GraphicsCard;

public abstract class Gpu {
    protected Enums.Manufacture manufacture;
    protected Enums.Brand brands;
    protected int value;
    protected int vram;
    protected String name;
    public Gpu(Enums.Manufacture manufacture,Enums.Brand brand,int value,int vram,String name){
        this.manufacture = manufacture;
        this.brands = brands;
        this.value = value;
        this.vram = vram;
        this.name = name;
    }
    
}
