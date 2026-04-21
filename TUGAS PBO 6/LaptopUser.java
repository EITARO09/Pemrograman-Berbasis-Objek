public class LaptopUser {
    Laptop laptop;

    public LaptopUser(Laptop laptop) {
        this.laptop = laptop;
    }

    void turnOnLaptop() {
        laptop.powerOn();
    }

    void turnOffLaptop() {
        laptop.powerOff();
    }

    void makeLaptopLouder() {
        laptop.volumeUp();
    }

    void makeLaptopSilent() {
        laptop.volumeDown();
    }
}