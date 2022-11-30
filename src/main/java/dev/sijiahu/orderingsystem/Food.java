package dev.sijiahu.orderingsystem;

public class Food {
    private final String type;
    private final String name;


    private final boolean multipleAllowed;
    private final boolean required;

    /**
     * alternate: if this food isn't ordered, give the alternate if not null
     */
    private final String alternate;
    private final boolean forceAlternative;

    public Food(String type,String name, boolean multipleAllowed, boolean required, String alternate, boolean forceAlternative) {
        this.type = type;
        this.name = name;

        this.multipleAllowed = multipleAllowed;
        this.required = required;
        this.alternate = alternate;
        this.forceAlternative = forceAlternative;
    }

    public Food(String type,String name, boolean multipleAllowed, boolean required) {
        this(type, name, multipleAllowed, required, null, false);
    }

    public String getName() {
        return name;
    }

    public boolean isMultipleAllowed() {
        return multipleAllowed;
    }

    public boolean isRequired() {
        return required;
    }

    public String getAlternate() {
        return alternate;
    }

    public boolean isForceAlternative() {
        return forceAlternative;
    }

    public String getType() {
        return type;
    }
}
