import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class validates the personal identity numbers and uses the Luhn algorithm to calculate the control digit.
 */
public class Validate {

    /**
     * validates the input number by checking that it follows the right structure.
     * @param personNumber the person number to be validated
     * @throws PersonNumberException
     */
    public void validityCheck(String personNumber) throws PersonNumberException {
        String century;

        if(personNumber.length() > 11) {
            century = personNumber.substring(0, 2);
            personNumber = personNumber.substring(2);
        } else {
            century = findCentury(personNumber);
        }

        if(personNumber.contains("-") || personNumber.contains("+")) {
            personNumber = personNumber.replace("-", "");
            personNumber = personNumber.replace("+", "");
        }

        try {
            if(personNumber.matches("\\d+") && personNumber.length() == 10) {
                String year = personNumber.substring(0, 2);
                String fullYear = century + year;
                String month = personNumber.substring(2, 4);
                String date = personNumber.substring(4, 6);
                String birthNr = personNumber.substring(6, 9);
                String controlDigit = personNumber.substring(9);

                boolean isOrganisationNr = Integer.valueOf(month) > 19;
                boolean isCoordinationNr = Integer.valueOf(date) > 60;

                if (!isOrganisationNr && !isCoordinationNr) {
                    try {
                        checkValidBirthdate(fullYear, month, date);
                    } catch (DateTimeException dte) {
                        throw new PersonNumberException(fullYear + month + date + " is not a valid birthdate");
                    }
                }
                PersonNumber prn = new PersonNumber(year, month, date, birthNr, controlDigit, isCoordinationNr, isOrganisationNr);
                calculateControlDigit(prn);
            } else {
                throw new PersonNumberException(personNumber + " is not a valid number format");
            }
        } catch(PersonNumberException prne) {
            throw new PersonNumberException(prne.getMessage());
        }
    }

    /**
     * Finds the century of the person number.
     * @param personNumber
     * @return
     */
    private String findCentury(String personNumber) {
        int currentYear = LocalDate.now().getYear();
        int currentCentury = ((currentYear - currentYear % 100) / 100);

        if(personNumber.contains("+")) {
            currentCentury -= 1;
        }
        return String.valueOf(currentCentury);
    }

    /**
     * Checks if the birthdate is a valid date.
     * @param fullYear the year
     * @param month the month
     * @param date the date
     */
    public void checkValidBirthdate(String fullYear, String month, String date) {
        if (Integer.valueOf(fullYear) > LocalDate.now().getYear()) {
            fullYear = fullYear.replace("20", "19");
        }
        LocalDate currentBirthDate = LocalDate.of(Integer.valueOf(fullYear), Integer.valueOf(month), Integer.valueOf(date));
    }

    /**
     * Calculates the control digit by using the Luhn algorithm
     * @param prn
     * @throws PersonNumberException
     */
    public void calculateControlDigit(PersonNumber prn) throws PersonNumberException {
        String data = prn.year + prn.month + prn.date + prn.birthNr;
        int multiplier = 2;
        int sum = 0;
        int product = 0;
        int i = 0;

        while(i < data.length()) {
            product = Character.getNumericValue(data.charAt(i)) * multiplier;
            if(product > 9) {
                product -= 9;
            }
            sum += product;
            multiplier = 3 - multiplier;
            i++;
        }
        int result = (10 - (sum %  10)) % 10;
        if(result != Integer.valueOf(prn.controlDigit)) {
            throw new PersonNumberException("The control digit for number " + prn + " is INVALID");
        }
        System.out.println("The control digit for number " + prn + " is VALID");
    }
}