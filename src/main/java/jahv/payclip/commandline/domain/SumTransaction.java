package jahv.payclip.commandline.domain;

import java.util.Objects;

public class SumTransaction {

    private int userId;
    private double sum;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumTransaction that = (SumTransaction) o;
        return userId == that.userId &&
                Double.compare(that.sum, sum) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, sum);
    }
}
