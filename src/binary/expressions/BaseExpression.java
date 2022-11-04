// 315318766 Omer Bar

package binary.expressions;

import java.util.List;
import java.util.Map;


/**
 * @author Omer Bar
 * @since 27.4.2022
 * @version jdk 17
 */
public abstract class BaseExpression implements Expression {


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return null;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return null;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

    @Override
    public Expression nandify() {
        return null;
    }

    @Override
    public Expression norify() {
        return null;
    }

    @Override
    public Expression simplify() {
        return null;
    }

}
