package infra.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String msg){
        super(msg);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getLocalizedMessage();
    }
}
