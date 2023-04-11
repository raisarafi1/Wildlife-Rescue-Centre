/**
 @authors Raisa Rafi, Rida Khan, Mohamed Ebdalla, Joshua Debele
 @version 1.4
 @since 1.0
 */

package edu.ucalgary.oop;

/**
 *  enum for the species: coyote, fox, porcupine, raccoon and beaver
 */
public enum Species {
    COYOTE {
        @Override
        public String toString() {
            return "coyote";
        }
    },
    FOX {
        @Override
        public String toString() {
            return "fox";
        }
    },
    PORCUPINE {
        @Override
        public String toString() {
            return "porcupine";
        }
    },
    RACCOON {
        @Override
        public String toString() {
            return "raccoon";
        }
    },
    BEAVER {
        @Override
        public String toString() {
            return "beaver";
        }
    };

    public abstract String toString();

    /**
     *
     * @param species
     * @return species dependent on the case
     */
    public static Species fromString(String species) {

        switch (species) {
            case "coyote":
                return Species.COYOTE;
            case "fox":
                return Species.FOX;
            case "porcupine":
                return Species.PORCUPINE;
            case "raccoon":
                return Species.RACCOON;
            case "beaver":
                return Species.BEAVER;
        }

        return null;
    }

}
