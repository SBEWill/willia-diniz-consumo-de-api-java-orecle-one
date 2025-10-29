package models;

public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String ddd;
    private String estado;

    public Cep(String cep, String logradouro, String bairro, String ddd, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.ddd = ddd;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cep{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", ddd='" + ddd + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

