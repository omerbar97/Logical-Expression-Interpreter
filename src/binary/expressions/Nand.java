// 315318766 Omer Bar

package binary.expressions;

import unary.expressions.Not;
import unary.expressions.Val;

import java.util.Map;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public class Nand extends BinaryExpression {


    /**
     * Constructor for Nand.
     *
     * @param v1 - Expression.
     * @param v2 - Expression.
     */
    public Nand(Expression v1, Expression v2) {
        super(v1, v2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.getV1().evaluate(assignment) && super.getV2().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        // this uses an empty assignment means there is only T or F in the logic
        return !(super.getV1().evaluate() && super.getV2().evaluate());
    }


    @Override
    public String toString() {
        return "(" + this.getV1().toString() + " A " + this.getV2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1, e2;
        e1 = super.getV1().assign(var, expression);
        e2 = super.getV2().assign(var, expression);
        if (this.toString().contains(var)) {
            return new Nand(e1, e2);
        }
        return this;
    }

    @Override
    public Expression nandify() {
        return new Nand(super.getV1().nandify(), super.getV2().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor((new Nor((new Nor(super.getV1().norify(), super.getV1().norify())),
                (new Nor(super.getV2().norify(), super.getV2().norify())))),
                (new Nor((new Nor(super.getV1().norify(), super.getV1().norify())),
                        (new Nor(super.getV2().norify(), super.getV2().norify())))));
    }

    @Override
    public Expression simplify() {
        Expression e1 = super.getV1();
        Expression e2 = super.getV2();
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("T")) {
            // one of the expression is true need to return true & x = ~(x)
            return new Not(e2.simplify()).simplify();
        }
        if (e2.simplify().toString().equals("T")) {
            // one of the expression is true need to return x A true = ~(x)
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need to return true, false A x = true
            return new Val(true);
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need to return true, x A false = true
            return new Val(true);
        }
        if (oneStepSimplify(e1, e2).toString().equals(this.toString())) {
            return new Nand(e1.simplify(), e2.simplify());
        }
        return new Nand(e1.simplify(), e2.simplify()).simplify();
    }

    /**
     * this function is kinda like the simplify function but these one don't continue to simplify the logic expression
     * when the simplify function returning the new Nand.
     *
     * @param e1 - Expression
     * @param e2 - Expression
     * @return Expression
     */
    private Expression oneStepSimplify(Expression e1, Expression e2) {
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("T")) {
            // one of the expression is true need to return true & x = ~(x)
            return new Not(e2.simplify()).simplify();
        }
        if (e2.simplify().toString().equals("T")) {
            // one of the expression is true need to return x A true = ~(x)
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need to return true, false A x = true
            return new Val(true);
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need to return true, x A false = true
            return new Val(true);
        }
        return new Nand(e1.simplify(), e2.simplify());
    }
}

