public enum Notations {
    X('X'),
    O('O'),
    BLANK(' '),
    SPECIAL('*');

    private final char notation;

    Notations(char init) {
        this.notation = init;
    }

    public boolean isMarked() {
        return this != BLANK;
    }

    public char getMark() {
        return this.notation;
    }

    @Override
    public String toString() {
        return String.valueOf(notation);
    }
}
