package Model;

public class AddBookIntException extends Exception{

    private String numberError;
    private String type;

    public AddBookIntException(String number, String type){
        numberError = number;
        this.type = type;
    }

    public String getMessage(){
        return "La "  + type + " : " + numberError + " n'est pas valide (doit etre un nombre entier)";
    }
}
