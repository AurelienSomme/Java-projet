package Model;

public class AddBookCodeException extends Exception{

    private String codeError;

    public AddBookCodeException(String code){
        codeError = code;
    }

    public String getMessage(){
        return "Le code : " + codeError + " n'est pas valide (ne doit contenir que des chiffres et etre unique)";
    }
}
