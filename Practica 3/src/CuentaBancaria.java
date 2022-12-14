public class CuentaBancaria{
    String nombreEntidad;
    String IBAN;
    //Clase que contiene la entidad bancaria y el IBAN de la persona

    public CuentaBancaria(String nombreEntidad, String IBAN) {
        this.nombreEntidad = nombreEntidad;
        this.IBAN = IBAN;
    }


    public String getNombreEntidad() {
        return this.nombreEntidad;
    }

    public String getIBAN() {
        return this.IBAN;
    }


}
