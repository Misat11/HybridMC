package misat11.hybrid.network.java.pabstract.data.game.entity.attribute;

import misat11.hybrid.network.java.pabstract.util.ObjectUtil;

public class AttributeModifier {
    private ModifierType type;
    private double amount;
    private ModifierOperation operation;

    public AttributeModifier(ModifierType type, double amount, ModifierOperation operation) {
        if(type == ModifierType.DYNAMIC) {
            throw new IllegalArgumentException("Cannot create a dynamic-typed modifier without a UUID.");
        }

        this.type = type;
        this.amount = amount;
        this.operation = operation;
    }

    public ModifierType getType() {
        return this.type;
    }

    public double getAmount() {
        return this.amount;
    }

    public ModifierOperation getOperation() {
        return this.operation;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof AttributeModifier)) return false;

        AttributeModifier that = (AttributeModifier) o;
        return this.type == that.type &&
                this.amount == that.amount &&
                this.operation == that.operation;
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.type, this.amount, this.operation);
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
