/**
 * This class represents a personal identity numbers such as person numbers and coordination number, as well as an organisation number.
 */
public class PersonNumber {
    String year;
    String month;
    String date;
    String birthNr;
    String controlDigit;
    boolean isCoordinationNr;
    boolean isOrganisationNr;

    public PersonNumber(String year, String month, String date, String birthNr, String controlDigit, boolean isCoordinationNr, boolean isOrganisationNr) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.birthNr = birthNr;
        this.controlDigit = controlDigit;
        this.isCoordinationNr = isCoordinationNr;
        this.isOrganisationNr = isOrganisationNr;
    }

    /**
     * String representation of the person number object.
     * @return
     */
    @Override
    public String toString() {
        return year + month + date + birthNr + controlDigit;
    }
}