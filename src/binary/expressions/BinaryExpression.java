// 315318766 Omer Bar

package binary.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public abstract class BinaryExpression extends BaseExpression {

    private final Expression v1;
    private final Expression v2;

    /**
     * counstrctor for BinaryExpression.
     *
     * @param v1 - Expression
     * @param v2 - Expression
     */
    public BinaryExpression(Expression v1, Expression v2) {
        this.v1 = v1;
        this.v2 = v2;
    }


    @Override
    public List<String> getVariables() {
        List<String> lst1 = this.getV1().getVariables();
        List<String> lst2 = this.getV2().getVariables();
        Set<String> set;
        // using set to eliminate duplicate items, using less memory to store the variables.
        if (lst1 != null) {
            if (lst2 != null) {
                lst1.addAll(lst2);
            }
            set = new HashSet<>(lst1);
            return new ArrayList<String>(set);
        }
        return new ArrayList<String>(new HashSet<>(lst2));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var == null || expression == null) {
            return this;
        }
        Expression e = this.getV1();
        if (this.toString().contains(var)) {
            e = e.assign(var, expression);
            return e;
        }
        return this;
    }

    /**
     * Getter for v1 Expression.
     *
     * @return - Expression.
     */
    protected Expression getV1() {
        return this.v1;
    }

    /**
     * Getter for v2 Expression.
     *
     * @return - Expression.
     */
    protected Expression getV2() {
        return this.v2;
    }

    /**
     * function that organize the string in ascii order for the Expression compare.
     *
     * @param e1 - Expression.
     * @return - Sorted String.
     */
    protected String stringToArraySorted(Expression e1) {
        char[] temp = e1.toString().toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }
}

