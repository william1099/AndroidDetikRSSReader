package william1099.com.design2;


public class objectItem2 {
    public int id;
    public String judul;
    public String gambar;
    public String deskripsi;
    public String link;
    public String tanggal;
    public String keterangan;

    public objectItem2(int id, String judul, String gambar, String desc, String link, String tgl, String ket) {
        this.id = id;
        this.judul = judul;
        this.gambar = gambar;
        this.deskripsi = desc;
        this.keterangan = ket;
        this.link = link;
        this.tanggal = tgl;
    }
}
