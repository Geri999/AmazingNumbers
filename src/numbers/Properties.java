package numbers;

import static numbers.Calculator.*;

public enum Properties {
    EVEN("even-odd", true) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsEven(number);
        }
    },
    ODD("even-odd", true) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsOdd(number);
        }
    },
    BUZZ("buzz", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsBuzz(number);
        }
    },
    DUCK("duck-spy", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsDuck(number);
        }
    },
    PALINDROMIC("palindromic", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsPalindromic(number);
        }
    },
    GAPFUL("gapful", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsGapful(number);
        }
    },
    SPY("duck-spy", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsSpy(number);
        }
    },
    SUNNY("sunny-square", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsSunny(number);
        }
    },
    SQUARE("sunny-square", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsPerfectSquare(number);
        }
    },
    JUMPING("jumping", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsJumping(number);
        }
    },

    HAPPY("happy-sad", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsHappy(number);
        }
    },

    SAD("happy-sad", false) {
        @Override
        boolean calculate(long number) {
            return checkIfNumberIsSad(number);
        }
    };

    final String mutuallyExclusiveProperties;
    final Boolean isMinusPropertyShouldBeMutuallyExclusiveProperties;

    Properties(String mutuallyExclusiveProperties, Boolean isMinusPropertyShouldBeMutuallyExclusiveProperties) {
        this.mutuallyExclusiveProperties = mutuallyExclusiveProperties;
        this.isMinusPropertyShouldBeMutuallyExclusiveProperties = isMinusPropertyShouldBeMutuallyExclusiveProperties;
    }

    abstract boolean calculate(long number);

    String nameLowerCase() {
        return this.name().toLowerCase();
    }
}
