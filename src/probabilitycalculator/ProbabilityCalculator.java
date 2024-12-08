package probabilitycalculator;

import java.util.Set;

public class ProbabilityCalculator {

    //MAIN CALCULATOR FOR PROBABILITY EVENTS
   
    public static double atLeastOneOccurs(Set<String> eventA, Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(A U B) = P(A) + P(B) - P(A ∩ B)
            probability = (double) (eventA.size() + eventB.size() - intersection(eventA, eventB).size()) / 52;
        } else {
            // Without replacement: adjust the probabilities
            probability = (double) (eventA.size() + eventB.size() - intersection(eventA, eventB).size()) / 52;
           
        }
        return probability;
    }

    // both events (A and B) occur
    public static double bothOccur(Set<String> eventA, Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(A ∩ B) for replacement
            probability = (double) intersection(eventA, eventB).size() / 52;
        } else {
            // Without replacement: adjust for the changing sample space
            probability = (double) intersection(eventA, eventB).size() / 52;
            
        }
        return probability;
    }

    // exactly one of the events (A or B) occurs
    public static double exactlyOneOccurs(Set<String> eventA, Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(A ∪ B) - P(A ∩ B)
            probability = (double) (eventA.size() + eventB.size() - 2 * intersection(eventA, eventB).size()) / 52;
        } else {
            
            probability = (double) (eventA.size() + eventB.size() - 2 * intersection(eventA, eventB).size()) / 52;
            
        }
        return probability;
    }

    //  neither event (A nor B) occurs
    public static double neitherOccurs(Set<String> eventA, Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(not A and not B) = (52 - size of A - size of B + size of intersection) / 52
            probability = (double) (52 - eventA.size() - eventB.size() + intersection(eventA, eventB).size()) / 52;
        } else {
            
            probability = (double) (52 - eventA.size() - eventB.size() + intersection(eventA, eventB).size()) / 52;
         
        }
        return probability;
    }

    //event A does not occur
    public static double notOccur(Set<String> eventA, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(not A) = (52 - size of A) / 52
            probability = (double) (52 - eventA.size()) / 52;
        } else {
           
            probability = (double) (52 - eventA.size()) / 52;
           
        }
        return probability;
    }

    //  event B does not occur
    public static double notOccurB(Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(not B) = (52 - size of B) / 52
            probability = (double) (52 - eventB.size()) / 52;
        } else {
          
            probability = (double) (52 - eventB.size()) / 52;
           
        }
        return probability;
    }

    // A occurs given that B occurs
    public static double aGivenB(Set<String> eventA, Set<String> eventB, boolean withReplacement) {
        double probability;
        if (withReplacement) {
            // P(A | B) = P(A ∩ B) / P(B)
            probability = (double) intersection(eventA, eventB).size() / eventB.size();
        } else {
          
            probability = (double) intersection(eventA, eventB).size() / eventB.size();
          
        }
        return probability;
    }

    // Helper function 
    private static Set<String> intersection(Set<String> eventA, Set<String> eventB) {
        eventA.retainAll(eventB); 
        return eventA;
    }

    //format the probability as a percentage 
    public static String formatProbability(double probability) {
        return String.format("%.2f%%", probability * 100);
    }
}
