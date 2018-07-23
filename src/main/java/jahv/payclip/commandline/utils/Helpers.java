package jahv.payclip.commandline.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.exceptions.SerializeException;

import java.io.IOException;

public class Helpers {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static boolean isNumeric(String value) {
       return  value.matches("\\d+");
    }

    public static int intValue(String value) {
        return Integer.valueOf(value);
    }

    public static String deserializeTransactionPlain(Transaction transaction) {
        try {
            return objectMapper.writeValueAsString(transaction);
        } catch (IOException e) {
            throw new SerializeException("Error in Transaction deserialization");
        }
    }

    public static String deserializeTransaction(Object value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (IOException e) {
            throw new SerializeException("Error in Transaction deserialization");
        }
    }

    public static Transaction serializeTransaction(String transactionJson) {
        try {
            return objectMapper.readValue(transactionJson, Transaction.class);
        } catch (IOException e) {
            throw new SerializeException("Error in JSON serialization");
        }
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

}
