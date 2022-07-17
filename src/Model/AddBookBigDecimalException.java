package Model;

public class AddBookBigDecimalException extends Exception{
    private String bigDecimalError;
    private String type;

    public AddBookBigDecimalException(String bigDecimal , String type){
        bigDecimalError = bigDecimal;
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "L'attribut " + type + " ne doit contenir que des chiffres (refusé : " + bigDecimalError + ")";
    }
}
