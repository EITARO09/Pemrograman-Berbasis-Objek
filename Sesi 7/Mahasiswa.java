public class Mahasiswa<T1, T2, T3> {
    // Deklarasi variabel dengan tipe generic
    private T1 nim;
    private T2 name;
    private T3 clas;

    // Setter
    public void setNim(T1 nim) {
        this.nim = nim;
    }

    public void setName(T2 name) {
        this.name = name;
    }

    public void setClas(T3 clas) {
        this.clas = clas;
    }

    // Getter
    public T1 getNim() {
        return nim;
    }

    public T2 getName() {
        return name;
    }

    public T3 getClas() {
        return clas;
    }
}