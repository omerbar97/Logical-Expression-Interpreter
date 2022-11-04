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
public class Var implements Expression {

    private final String variable;

    /**
     * Constructor for Var.
     *
     * @param variable - String.
     */
    public Var(String variable) {
        this.variable = variable;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        }
        if (this.variable.equals("T")) {
            return true;
        }
        if (this.variable.equals("F")) {
            return false;
        }
        throw new Exception("Not all variables are initialized or the initialized of the variable was illegal");
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("the evaluate() function can only be used on logic expression without variables");
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new ArrayList<String>();
        l.add(this.variable);
        return l;
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if ((this.toString().length() == 0 && var.length() == 0) || (var != null && this.toString().equals(var))) {
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
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            return this;
        }
    }
}
