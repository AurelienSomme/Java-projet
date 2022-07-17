package Model;

public class AddBookStringBlankException extends Exception{
    private String type;

    public AddBookStringBlankException(String type){
        this.type = type;
    }

    public String getMessage(){
        return "Il faut un " + type;
    }
}
