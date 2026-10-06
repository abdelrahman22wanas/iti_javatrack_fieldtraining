package chapter7;

public class AgeValidator {

    public void checkVotingAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age " + age + " is below the voting age of 18.");
        }
        System.out.println("Age " + age + " is eligible to vote.");
    }

    public void checkDrivingAge(int age) throws InvalidAgeException {
        if (age < 16) {
            throw new InvalidAgeException("Age " + age + " is below the driving age of 16.");
        }
        System.out.println("Age " + age + " is eligible to drive.");
    }

    public void checkRetirementAge(int age) throws InvalidAgeException {
        if (age < 60) {
            throw new InvalidAgeException("Age " + age + " is below the retirement age of 60.");
        }
        System.out.println("Age " + age + " is eligible to retire.");
    }
}
