// 315318766 Omer Bar

package unary.expressions;

import binary.expressions.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public class Val implements Expression {

    private final boolean value;

    /**
     * Constructor for Val.
     *
     * @param value - boolean.
     */
    public Val(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.value) {
            return "T";
        }
        return "F";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals("T") || var.equals("F")) {
            // in case the var is T or F just return the current expression, because you cannot assign value to T or F.
            return this;
        }
        if (this.toString().length() == 0 && var.length() == 0 || this.toString().equals(var)) {
            // empty string
            return expression;
        }
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
