package jahv.payclip.commandline.dao.impl;

import jahv.payclip.commandline.dao.TransactionsDAO;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.exceptions.PayClipFileException;
import jahv.payclip.commandline.utils.Helpers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionsDAO {

    private static final String LINE_SEPARATOR = "\n";
    private static final String UTF_8 = "UTF-8";

    @Value("${file.name}")
    private String fileName;

    @Override
    public Transaction save(Transaction transaction) {
        File file = FileUtils.getFile(fileName);
        String transactionJson = Helpers.deserializeTransactionPlain(transaction);
        try {
            FileUtils.writeStringToFile(file, transactionJson + LINE_SEPARATOR, Charset.defaultCharset(), true);
        } catch (IOException e) {
            throw new PayClipFileException("Error writing transaction");
        }
        return transaction;
    }

    @Override
    public Transaction get(int userId, String transactionId) {
        List<String> transactions = this.getTransactions();
        for(String transaction : transactions) {
            if(StringUtils.isNotBlank(transaction)) {
                Transaction t = Helpers.serializeTransaction(transaction);
                if(userId == t.getUserId() && transactionId.equals(t.getTransactionId().toString())) {
                    return Helpers.serializeTransaction(transaction);
                }
            }
        }
        return null;
    }

    @Override
    public List<Transaction> get(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        List<String> transactionsLines = this.getTransactions();
        for(String transactionLine : transactionsLines) {
            if(StringUtils.isNotBlank(transactionLine)) {
                Transaction t = Helpers.serializeTransaction(transactionLine);
                if(userId == t.getUserId()) {
                    transactions.add(t);
                }
            }
        }
        return transactions;
    }

    private List<String> getTransactions() {
        List<String> transactions;
        File file = FileUtils.getFile(fileName);
        try {
            transactions = FileUtils.readLines(file, UTF_8);
        }catch (IOException e) {
            throw new PayClipFileException("Error reading transactions");
        }
        return transactions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
