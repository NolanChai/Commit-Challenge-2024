import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class fiveNumbers {
    public static void main(String[] args) throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

        System.out.print("Target Number: ");
        String targetNumString = reader.readLine();
        int targetNum = Integer.parseInt(targetNumString);
    
        System.out.print("Number 1: ");
        String num1String = reader.readLine();
        int num1 = Integer.parseInt(num1String);

        System.out.print("Number 2: ");
        String num2String = reader.readLine();
        int num2 = Integer.parseInt(num2String);
        
        System.out.print("Number 3: ");
        String num3String = reader.readLine();
        int num3 = Integer.parseInt(num3String);
        
        System.out.print("Number 4: ");
        String num4String = reader.readLine();
        int num4 = Integer.parseInt(num4String);
        
        System.out.print("Number 5: ");
        String num5String = reader.readLine();
        int num5 = Integer.parseInt(num5String);
        
        System.out.println("Solving " + targetNumString + ": [" + num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5 + "] ... ");

        fiveNumberSolver(targetNum, num1, num2, num3, num4, num5);
    }

    private static void fiveNumberSolver(int targetNum, int num1, int num2, int num3, int num4, int num5) {
        List<Rational> arr = new ArrayList<>();
        arr.add(new Rational(num1));
        arr.add(new Rational(num2));
        arr.add(new Rational(num3));
        arr.add(new Rational(num4));
        arr.add(new Rational(num5));
        List<String> log = new ArrayList<>();
        if (solverSubroutine(log, targetNum, arr)) {
            for (int i = log.size() - 1; i >= 0; i--) {
                System.out.println(log.get(i));
            }
            return;
        }
        System.out.println("This is impossible.");
    }

    private static boolean solverSubroutine(List<String> log, int target, List<Rational> arr) {
        if (arr.size() == 1) {
            Rational r = arr.get(0);
            if (r.isInt() && r.getNumerator() == target) {
                log.add(target + " = " + r.expression);
                return true;
            }
            return false;
        }

        int size = arr.size();
        // add
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                Rational r1 = arr.get(i);
                Rational r2 = arr.get(j);
                List<Rational> temp = new ArrayList<>(arr);
                temp.remove(r1);
                temp.remove(r2);

                Rational comb = r1.add(r2);
                temp.add(comb);
                if (solverSubroutine(log, target, temp)) {
                    log.add(r1 + " + " + r2 + " = " + comb);
                    return true;
                }
            }
        }
        
        // sub
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                Rational r1 = arr.get(i);
                Rational r2 = arr.get(j);
                List<Rational> temp = new ArrayList<>(arr);
                temp.remove(r1);
                temp.remove(r2);

                Rational comb = r1.sub(r2);
                temp.add(comb);
                if (solverSubroutine(log, target, temp)) {
                    log.add(r1 + " - " + r2 + " = " + comb);
                    return true;
                }
            }
        }

        // mult
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                Rational r1 = arr.get(i);
                Rational r2 = arr.get(j);
                List<Rational> temp = new ArrayList<>(arr);
                temp.remove(r1);
                temp.remove(r2);

                Rational comb = r1.mult(r2);
                temp.add(comb);
                if (solverSubroutine(log, target, temp)) {
                    log.add(r1 + " * " + r2 + " = " + comb);
                    return true;
                }
            }
        }

        // div
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                Rational r1 = arr.get(i);
                Rational r2 = arr.get(j);
                List<Rational> temp = new ArrayList<>(arr);
                temp.remove(r1);
                temp.remove(r2);

                Rational comb = r1.div(r2);
                temp.add(comb);
                if (solverSubroutine(log, target, temp)) {
                    log.add(r1 + " / " + r2 + " = " + comb);
                    return true;
                }
            }
        }

        return false;
    }
}

class Rational {
    int numerator = 0;
    int denominator = 1;
    int orderOfOperation = 0;
    String expression;
    Rational(int num) {
        numerator = num;
        expression = Integer.toString(numerator);
    }

    Rational(int numer, int denom) {
        if (numer == 0) return;
        int factor = gcd(numer, denom);
        numerator = numer / factor;
        denominator = denom / factor;
        expression = numerator + "/" + denominator;
    }

    Rational add(int num) {
        Rational comb = new Rational(num * denominator + numerator, denominator);
        comb.expression = getExpression(4) + " + " + num;
        comb.orderOfOperation = 4;
        return comb;
    }
    Rational add(Rational other) {
        Rational comb = new Rational(other.numerator * denominator + numerator * other.denominator, denominator * other.denominator);
        comb.expression = getExpression(4) + " + " + other.getExpression(4);
        comb.orderOfOperation = 4;
        return comb;
    }

    Rational sub(int num) {
        Rational comb = new Rational(numerator - num * denominator, denominator);
        comb.expression = getExpression(4) + " - " + num;
        comb.orderOfOperation = 3;
        return comb;
    }
    Rational sub(Rational other) {
        Rational comb = new Rational(numerator * other.denominator - other.numerator * denominator, denominator * other.denominator);
        comb.expression = getExpression(4) + " - " + other.getExpression(2);
        comb.orderOfOperation = 3;
        return comb;
    }

    Rational mult(int num) {
        Rational comb = new Rational(num * numerator, denominator);
        comb.expression = getExpression(2) + " * " + num;
        comb.orderOfOperation = 2;
        return comb;
    }
    Rational mult(Rational other) {
        Rational comb = new Rational(other.numerator * numerator, denominator * other.denominator);
        comb.expression = getExpression(2) + " * " + other.getExpression(2);
        comb.orderOfOperation = 2;
        return comb;
    }

    Rational div(int num) {
        Rational comb = new Rational(numerator, denominator * num);
        comb.expression = getExpression(2) + " / " + num;
        comb.orderOfOperation = 1;
        return comb;
    }
    Rational div(Rational other) {
        Rational comb = new Rational(numerator * other.denominator, denominator * other.numerator);
        comb.expression = getExpression(2) + " / " + other.getExpression(0);
        comb.orderOfOperation = 1;
        return comb;
    }

    boolean isInt() {
        return denominator == 1;
    }
    int getNumerator() {
        return numerator;
    }
    int getDenominator() {
        return denominator;
    }
    String getExpression(int order) {
        if (order >= orderOfOperation) return expression;
        else return "(" + expression + ")";
    }

    @Override
    public String toString() {
        if (denominator == 1) return Integer.toString(numerator);
        return numerator + "/" + denominator;
    }


    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
} 