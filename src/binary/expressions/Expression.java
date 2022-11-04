// 315318766 Omer Bar

package binary.expressions;

import java.util.List;
import java.util.Map;


/**
 * @author Omer Bar
 * @since 27.4.2022
 * @version jdk 17
 */
public interface Expression {

    /**
     * with given map of value to string the function is calculating the logic expression.
     * @param assignment - MAP
     * @return - Boolean
     * @throws Exception - in case not all of the variables are valued then the function throw Exception.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * should be used on expression with only true or false values.
     * @return Boolean
     * @throws Exception - in case not all of the variables are true or false type the function throw Exception.
     */
    Boolean evaluate() throws Exception;

    // Returns a list of the variables in the expression.

    /**
     * creating a list of all the variables in the logic expression.
     * @return List of String
     */
    List<String> getVariables();

    /**
     * returning a nice string representation of the expression.
     * @return - String
     */
    String toString();

    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).

    /**
     * Returns a new expression in which all occurrences of the variable.
     * var a re replaced with the provided expression (Does not modify the current expression).
     * @param var - String
     * @param expression - Expression to assign.
     * @return Expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     * @return - Expression.
     */
    Expression nandify();


    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     * @return - Expression.
     */
    Expression norify();


    /**
     * Returned a simplified version of the current expression.
     * @return - Expression.
     */
    Expression simplify();

}