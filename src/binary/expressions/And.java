// 315318766 Omer Bar

package binary.expressions;

import unary.expressions.Val;

import java.util.Map;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public class And extends BinaryExpression {

    /**
     * constructor for the And object.
     *
     * @param v1 - Expression
     * @param v2 - Expression
     */
    public And(Expression v1, Expression v2) {
        super(v1, v2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return super.getV1().evaluate(assignment) && super.getV2().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return super.getV1().evaluate() && super.getV2().evaluate();
    }


    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1, e2;
        e1 = super.getV1().assign(var, expression);
        e2 = super.getV2().assign(var, expression);
        if (this.toString().contains(var)) {
            return new And(e1, e2);
        }
        return this;
    }

    /*@Override
    public Expression assign(String var, Expression expression) {
        if (var == null || expression == null) {
            return this;
        }
        Expression e1 = super.getV1();
        Expression e2 = super.getV2();
        if (this.toString().contains(var)) {
            e1 = e1.assign(var, expression);
            e2 = e2.assign(var, expression);
            return new And(e1, e2);
        }
        return this;
    }*/


    @Override
    public String toString() {
        return "(" + super.getV1().toString() + " & " + super.getV2().toString() + ")";
    }

    @Override
    public Expression nandify() {
        return new Nand((new Nand(super.getV1().nandify(), super.getV2().nandify())),
                (new Nand(super.getV1().nandify(), super.getV2().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor((new Nor(super.getV1().norify(), super.getV1().norify())),
                (new Nor(super.getV2().norify(), super.getV2().norify())));
    }


    @Override
    public Expression simplify() {
        Expression e1 = super.getV1();
        Expression e2 = super.getV2();
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return super.getV1().simplify();
        }
        if (e1.simplify().toString().equals("T")) {
            // one of the expression is true need to return the other one x & true = x
            return e2.simplify();
        }
        if (e2.simplify().toString().equals("T")) {
            // one of the expression is true need to return the other one true & x = x
            return e1.simplify();
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need to return false, false & x = false
            return new Val(false);
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need to return false, x & false = false
            return new Val(false);
        }
        // there is (~(x) & x) return false.
        if(getV1().getVariables().equals(getV2().getVariables()) && getV1().getVariables().size() == 1
                && this.toString().contains("~"))
        {
            return new Val(false);
        }
        // none of the cases return new And.
        // if then next simplify is equals then the function stop the simplfy and returning the new And.
        if (oneStepSimplify(e1, e2).toString().equals(this.toString())) {
            return new And(e1.simplify(), e2.simplify());
        }
        // contiune to simplify.
        return new And(e1.simplify(), e2.simplify()).simplify();
    }

    /**
     * this function is kinda like the simplify function but these one don't continue to simplify the logic expression
     * when the simplify function returning the new And.
     *
     * @param e1 - Expression
     * @param e2 - Expression
     * @return Expression
     */
    private Expression oneStepSimplify(Expression e1, Expression e2) {
        if (stringToArraySorted(e1).equals(stringToArraySorted(e2))) {
            return super.getV1().simplify();
        }
        if (e1.simplify().toString().equals("T")) {
            // one of the expression is true need to return the other one x & true = x
            return e2.simplify();
        }
        if (e2.simplify().toString().equals("T")) {
            // one of the expression is true need to return the other one true & x = x
            return e1.simplify();
        }
        if (e1.simplify().toString().equals("F")) {
            // one of the expression is false need to return false, false & x = false
            return new Val(false);
        }
        if (e2.simplify().toString().equals("F")) {
            // one of the expression is false need to return false, x & false = false
            return new Val(false);
        }
        // none of the cases return new And.
        return new And(e1.simplify(), e2.simplify());
    }
}
