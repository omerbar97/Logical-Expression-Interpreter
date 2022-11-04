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
public class Nor extends BinaryExpression {


    /**
     * Constructor for Nor.
     *
     * @param v1 - Expression.
     * @param v2 - Expression.
     */
    public Nor(Expression v1, Expression v2) {
        super(v1, v2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.getV1().evaluate(assignment) || super.getV2().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        // this uses an empty assignment means there is only T or F in the logic
        return !(super.getV1().evaluate() || super.getV2().evaluate());
    }


    @Override
    public String toString() {
        return "(" + super.getV1().toString() + " V " + super.getV2().toString() + ")";
    }


    @Override
    public Expression nandify() {
        return new Nand((new Nand((new Nand(super.getV1().nandify(), super.getV1().nandify())),
                (new Nand(super.getV2().nandify(), super.getV2().nandify())))),
                (new Nand((new Nand(super.getV1().nandify(), super.getV1().nandify())),
                        (new Nand(super.getV2().nandify(), super.getV2().nandify())))));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1, e2;
        e1 = super.getV1().assign(var, expression);
        e2 = super.getV2().assign(var, expression);
        if (this.toString().contains(var)) {
            return new Nor(e1, e2);
        }
        return this;
    }

    @Override
    public Expression norify() {
        return new Nor(super.getV1().norify(), super.getV2().norify());
    }


    @Override
    public Expression simplify() {
        Expression e1 = super.getV1();
        Expression e2 = super.getV2();
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("T") || (getV2().simplify().toString().equals("T"))) {
            // one of the expression is true need to return false, x V true = false
            return new Val(false);
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need the other expression
            return new Not(e2.simplify()).simplify();
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need the other expression
            return new Not(e1.simplify()).simplify();
        }
        // returning new Or
        if (oneStepSimplify(e1, e2).toString().equals(this.toString())) {
            return new Nor(e1.simplify(), e2.simplify());
        }
        return new Nor(e1.simplify(), e2.simplify()).simplify();
    }

    /**
     * this function is kinda like the simplify function but these one don't continue to simplify the logic expression
     * when the simplify function returning the new Nor.
     *
     * @param e1 - Expression
     * @param e2 - Expression
     * @return Expression
     */
    private Expression oneStepSimplify(Expression e1, Expression e2) {
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return new Not(e1.simplify()).simplify();
        }
        if (e1.simplify().toString().equals("T") || (getV2().simplify().toString().equals("T"))) {
            // one of the expression is true need to return false, x V true = false
            return new Val(false);
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need the other expression
            return new Not(e2.simplify()).simplify();
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need the other expression
            return new Not(e1.simplify()).simplify();
        }
        // returning new Or
        return new Nor(e1.simplify(), e2.simplify());
    }
}
