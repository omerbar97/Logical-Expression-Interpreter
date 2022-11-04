// 315318766 Omer Bar

package unary.expressions;

import binary.expressions.Expression;
import binary.expressions.Nand;
import binary.expressions.Nor;

import java.util.Map;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public class Not extends UnaryExpression {

    /**
     * Constructor for Not.
     *
     * @param v - Expression.
     */
    public Not(Expression v) {
        super(v);
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !super.getV().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !super.getV().evaluate();
    }

    @Override
    public String toString() {
        return "~(" + super.getV().toString() + ")";
    }

    @Override
    public Expression nandify() {
        return new Nand(super.getV().nandify(), super.getV().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(super.getV().norify(), super.getV().norify());
    }

    @Override
    public Expression simplify() {
        Expression e1 = super.getV();
        if (e1.simplify() != null) {
            e1 = e1.simplify();
        }
        try {
            return new Val(!(e1.evaluate())).simplify();
        } catch (Exception e) {
            return new Not(e1);
        }
    }

}

