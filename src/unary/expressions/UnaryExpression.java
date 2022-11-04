// 315318766 Omer Bar

package unary.expressions;

import binary.expressions.BaseExpression;
import binary.expressions.Expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public abstract class UnaryExpression extends BaseExpression {

    private final Expression v;

    /**
     * Constructor for UnaryExpression.
     *
     * @param v - Expression.
     */
    public UnaryExpression(Expression v) {
        this.v = v;
    }

    /**
     * Getter for Expression v.
     *
     * @return - Expression.
     */
    public Expression getV() {
        return this.v;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return null;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var == null) {
            return this;
        }
        Expression e = this.getV();
        if (this.toString().contains(var)) {
            e = e.assign(var, expression);
            return new Not(e);
        }
        return this;
    }

    @Override
    public List<String> getVariables() {
        List<String> lst1 = this.getV().getVariables();
        Set<String> set = new HashSet<>(lst1);
        // using set to eliminate duplicate items.
        return new ArrayList<String>(set);
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }
}
