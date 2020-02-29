package infra.exceptions;

public class ServiceUnavailableException extends Exception {

    public ServiceUnavailableException(String msg){
        super(msg);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getLocalizedMessage();
    }
}
