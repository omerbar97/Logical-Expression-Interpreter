// 315318766 Omer Bar

import binary.expressions.And;
import binary.expressions.Expression;
import binary.expressions.Or;
import binary.expressions.Xnor;
import binary.expressions.Xor;
import unary.expressions.Not;
import unary.expressions.Val;
import unary.expressions.Var;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27.4.2022
 */
public class main {

    /**
     * main method for testing expression.
     *
     * @param args - String from commandline.
     */

    //static variables
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Var> vars = new ArrayList<>();
    static ArrayList<Expression> expressions = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        //initializing the true and false value
        expressions.add(new Val(true));
        expressions.add(new Val(false));
        int result;
        // creating the menu
        while (true) {
            printMenu();
            try {
                result = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("-----Incorrect input-----");
                continue;
            }
            scanner.nextLine();
            switch (result) {
                case 1:
                    createVar();
                    break;
                case 2:
                    createExpression();
                    break;
                case 3:
                    evaluateExpression();
                    break;
                case 4:
                    simplifyExpression();
                    break;
                case 5:
                    nandifyExpression();
                    break;
                case 6:
                    norifyExpression();
                    break;
                case 7:
                    printAllVarsAndExpression();
                    break;

                default:
                    System.out.println("-----Incorrect input-----");
                    break;
            }
            System.out.println("-------------------------------------------------\n");
        }
    }

    /**
     * this function prints the program menu
     */
    private static void printMenu() {
        // this prints the menu
        System.out.println("1.Create a new variable\n" +
                "2.Create a new expression\n" +
                "3.Evaluate an expression\n" +
                "4.Simplify expression\n" +
                "5.Nandify expression\n" +
                "6.Norify expression\n" +
                "7.Print all expression\n");
    }

    /**
     * creating the basic form of logic variables
     */
    private static void createVar() {
        // going to add all to the vars list
        System.out.println("add as many variables as you wish for, just separate them with spaces.\n" +
                "be aware that you cannot put the letters: T, t, F, f. those letter for the boolean representation:");
        String result = scanner.nextLine();
        Scanner temp = new Scanner(result);
        int flag = 0;
        String tempRes;
        while (temp.hasNext()) {
            tempRes = temp.next();
            if (tempRes.equals("T") || tempRes.equals("t") || tempRes.equals("F") || tempRes.equals("f")) {
                continue;
            }
            for (Var v : vars) {
                // checking if the var already exist
                if (v.toString().equals(tempRes)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                vars.add(new Var(tempRes));
                continue;
            }
            flag = 0;
        }
    }

    /**
     * creating an expression can be either unary or binary
     */
    private static void createExpression() {
        System.out.println("choose the kind of expression you want to make: \n" +
                "1.Unary Expression\n" +
                "2.Binary Expression\n");
        int result;
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("-----Incorrect input-----");
            return;
        }
        switch (result) {
            case 1:
                createUnaryExpression();
                break;

            case 2:
                createBinaryExpression();
                break;

            default:
                System.out.println("-----Incorrect input-----");
        }
    }

    /**
     * creating the unary expression, the logic NOT ~()
     */
    private static void createUnaryExpression() {
        System.out.println("choose the vars you want (multi choose, separate them with spaces):");
        printAllVarsAndExpression();
        scanner.nextLine();
        String result = scanner.nextLine();
        Scanner sc = new Scanner(result);
        while (sc.hasNext()) {
            String temp = sc.next();
            int res;
            try {
                res = Integer.parseInt(temp) - 1;
            } catch (Exception e) {
                System.out.println("-----Incorrect input-----");
                return;
            }
            if (res >= vars.size()) {
                expressions.add(new Not(expressions.get(res - vars.size())));
            } else {
                expressions.add(new Not(vars.get(res)));
            }
        }
    }

    /**
     * creating all the 4 binary expression in this program: and, or, xnor, xor
     */
    private static void createBinaryExpression() {
        System.out.println("choose a single expression to make:");
        printAllBinaryExpression();
        int result;
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("-----Incorrect input-----");
            return;
        }
        String res;
        scanner.nextLine();
        System.out.println("choose 2 vars\\expression to make:");
        printAllVarsAndExpression();
        res = scanner.nextLine();
        Scanner sc = new Scanner(res);
        int e1, e2;
        try {
            e1 = Integer.parseInt(sc.next()) - 1;
            e2 = Integer.parseInt(sc.next()) - 1;
        } catch (Exception e) {
            System.out.println("-----Incorrect input-----");
            return;
        }
        Expression ee1, ee2;
        if (e1 >= vars.size()) {
            ee1 = expressions.get(e1 - vars.size());
        } else {
            ee1 = vars.get(e1);
        }
        if (e2 >= vars.size()) {
            ee2 = expressions.get(e2 - vars.size());
        } else {
            ee2 = vars.get(e2);
        }
        switch (result) {
            case 1:
                // creating AND expression
                expressions.add(new And(ee1, ee2));
                break;
            case 2:
                // creating OR expression
                expressions.add(new Or(ee1, ee2));
                break;
            case 3:
                // creating XNOR expression
                expressions.add(new Xnor(ee1, ee2));
                break;
            case 4:
                // creating XOR expression
                expressions.add(new Xor(ee1, ee2));
                break;

        }
    }

    /**
     * printing the vars and expressions
     */
    private static void printAllVarsAndExpression() {
        int i, j;
        for (i = 0; i < vars.size(); i++) {
            System.out.println((i + 1) + ". " + vars.get(i) + "  (Vars)");
        }
        j = i + 1;
        for (i = 0; i < expressions.size(); i++) {
            if (expressions.get(i).getClass().equals(Val.class)) {
                System.out.println((j + i) + ". " + expressions.get(i) + "  (boolean value)");
            } else {
                System.out.println((j + i) + ". " + expressions.get(i) + "  (Expressions)");
            }
        }
    }

    /**
     * prints the binary expression menu
     */
    private static void printAllBinaryExpression() {
        System.out.println("1. And\n" +
                "2. Or\n" +
                "3. Xnor\n" +
                "4. Xor\n");
    }

    /**
     * prints only the expression, not including the True and False.
     */
    private static void printAllExpressions() {
        int i;
        for (i = 0; i < expressions.size(); i++) {
            if (expressions.get(i).getClass().equals(Val.class)) {
                continue;
            } else {
                System.out.println((i + 1) + ". " + expressions.get(i) + "  (Expressions)");
            }
        }
    }

    /**
     * the function that evaluate an expression, you choose one of them and then puts value in the variables and
     * it will calculate it.
     *
     * @throws Exception when not all variables was initialized.
     */
    private static void evaluateExpression() throws Exception {
        if (expressions.size() <= 2) {
            return;
            // no expression to evaluate
        }
        System.out.println("choose one expression to evaluate:");
        printAllExpressions();
        int res = scanner.nextInt();
        scanner.nextLine();
        List<String> tempExpression = expressions.get(res - 1).getVariables();
        Map<String, Boolean> assignment = new TreeMap<>();
        String ans;
        for (String s : tempExpression) {
            while (true) {
                System.out.print("Please put value in \"" + s + "\" (true | false): ");
                ans = scanner.nextLine();
                ans = ans.toLowerCase(Locale.ROOT);
                if (ans.equals("true")) {
                    assignment.put(s, true);
                    break;
                } else if (ans.equals("false")) {
                    assignment.put(s, false);
                    break;
                } else {
                    System.out.println("only true or false value! try again.");
                    continue;
                }
            }
        }
        boolean evaluate = expressions.get(res - 1).evaluate(assignment);
        System.out.println("the result of the expression: " + expressions.get(res - 1) + " is :::: " + evaluate);
    }

    /**
     * this simplfiy the expression as far as it can, NOT expression won't be simplified till the end.
     */
    private static void simplifyExpression() {
        if (expressions.size() <= 2) {
            return;
            // no expression to evaluate
        }
        System.out.println("choose one expression to simplify:");
        printAllExpressions();
        int res = scanner.nextInt();
        scanner.nextLine();
        Expression ex = expressions.get(res - 1).simplify();
        System.out.println("the simplify form of " + expressions.get(res - 1) + " is :::: " + ex.toString());
    }

    /**
     * creating an nandify expression, using only NOT AND.
     */
    private static void nandifyExpression() {
        if (expressions.size() <= 2) {
            return;
            // no expression to evaluate
        }
        System.out.println("choose one expression to nandify:");
        printAllExpressions();
        int res = scanner.nextInt();
        scanner.nextLine();
        Expression ex = expressions.get(res - 1).nandify();
        System.out.println("the nandify form of " + expressions.get(res - 1) + " is :::: " + ex.toString());
    }

    /**
     * creating an norify expression, using only NOT OR.
     */
    private static void norifyExpression() {
        if (expressions.size() <= 2) {
            return;
            // no expression to evaluate
        }
        System.out.println("choose one expression to norfiy:");
        printAllExpressions();
        int res = scanner.nextInt();
        scanner.nextLine();
        Expression ex = expressions.get(res - 1).norify();
        System.out.println("the norfiy form of " + expressions.get(res - 1) + " is :::: " + ex.toString());
    }
}

