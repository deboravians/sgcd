package sgcd;

import java.io.Serializable;

public class Date implements Serializable {
    private int dia, mes, ano;
    Date(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
