package my.apps.web;

import java.sql.Date;

public class Expences {

    private Long id;
    private String produs;
    private int cantitate;
    private Date data;
    private int pret;
    private String categorie;

    public Expences(String produs,int cantitate, Date data, int pret, String categorie) {
        this.produs = produs;
        this.cantitate = cantitate;
        this.data = data;
        this.pret = pret;
        this.categorie = categorie;
    }

    public String getProdus() {
        return produs;
    }
    public int getCantitate(){return cantitate;}

    public Date getData() {
        return data;
    }

    public int getPret() {
        return pret;
    }

    public String getCategorie() {
        return categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Expences{" +
                "produs='" + produs + '\'' +
                "cantitate'" + cantitate +'\''+
                ", data='" + data + '\'' +
                ", pret='" + pret + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
