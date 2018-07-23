package jahv.payclip.commandline.exceptions;

public class ArgumentsException extends RuntimeException {

    public ArgumentsException(String message) {
        System.out.println(message);
    }
}
