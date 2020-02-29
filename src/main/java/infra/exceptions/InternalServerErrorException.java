package infra.exceptions;

public class InternalServerErrorException extends Exception {

    public InternalServerErrorException(String msg){
        super(msg);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getLocalizedMessage();
    }
}
