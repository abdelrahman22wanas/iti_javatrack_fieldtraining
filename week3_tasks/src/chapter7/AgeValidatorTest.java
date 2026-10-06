package chapter7;

public class AgeValidatorTest {

    public static void main(String[] args) {
        AgeValidator validator = new AgeValidator();

        try {
            validator.checkVotingAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Voting age check finished.\n");
        }

        try {
            validator.checkDrivingAge(17);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Driving age check finished.\n");
        }

        try {
            validator.checkRetirementAge(45);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Retirement age check finished.\n");
        }
    }
}
