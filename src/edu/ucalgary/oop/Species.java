package edu.ucalgary.oop;

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

}
