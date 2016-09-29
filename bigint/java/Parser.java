import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	private String raw_expression;
	private String expression;
	private ArrayList<String> RPn;
	private Natural value;
    private boolean verbose;

    public void make_calculation() {
        this.verbose = false;

        System.out.print("\n" +
"      _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  \n" +
"    (                                                                 )\n" +
"    (          INTERACTIVE INTERGER CALTULATOR by @matbur             )\n" +
"    (                                                                 )\n" +
"    (    Feel free to type a really big numbers                       )\n" +
"    (    You can use following operators: + - * / ( )                 )\n" +
"    (    Verbose mode is default 'off', to turn 'on' type: 0 [Enter]  )\n" +
"    (    Example: ( 12 + 2 ) * 3 [Enter]                              )\n" +
"    (    To exit just write: end [Enter]                              )\n" +
"    ( _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ )\n" +
"\n");

        int i = 0;
        Scanner read = new Scanner(System.in);

        while (true) {
            ++i;

            System.out.print(Integer.toString(i) + ")> ");
            this.raw_expression = read.nextLine();

            if (raw_expression.length() == 0) {
                --i;
                continue;
            }
            else if (Character.isLetter(raw_expression.charAt(0))) {
                System.out.println("\nThank you\n\n");
                break;
            }
            else if (raw_expression.charAt(0) == '0') {
                verbose = !verbose;
                System.out.print("verbose: ");

                if (verbose)
                    System.out.print("on\n");
                else
                    System.out.print("false\n");
            }

            if (verbose)
                System.out.println("raw: |" + this.raw_expression + "|");

            parse_raw_expression();

            if (verbose)
                System.out.println("exp: |" + this.expression + "|");

            parse_expression();

            if (verbose)
                System.out.println("RPn: " + this.RPn);

            parse_RPn();

            System.out.println("\t= " + value.toString());
        }
    }

	private void parse_raw_expression() {
        this.expression = "";
        char[] tab = raw_expression.toCharArray();

        for (char i: tab)
            if (i != ' ' && is_in(i, "1234567890+-*/()"))
                this.expression += i;

        if (this.expression.charAt(0) == '-')
            this.expression = '0' + this.expression;
    }

	private void parse_expression() {
        this.RPn = new ArrayList<>();
        ArrayList<Character> op_stack = new ArrayList<>();
        char[] tab = this.expression.toCharArray();

        for (int i = 0; i < tab.length; ++i) {
            if (Character.isDigit(tab[i])) {
                int j = 0; 
                while (i+j<tab.length && Character.isDigit(tab[i+j])) {
                    j++;
                }

                RPn.add(expression.substring(i, i+j));
                i += j-1;
            }
            else if (is_in(tab[i], "+-")) {
                if (op_stack.size() > 0 && op_stack.get(op_stack.size()-1) != '(' ){
                    RPn.add(String.valueOf(op_stack.get(op_stack.size()-1)));
                    op_stack.remove(op_stack.size()-1);
                }
                op_stack.add(tab[i]);
            }
            else if (is_in(tab[i], "*/")) {
                if (op_stack.size() > 0 && is_in(op_stack.get(op_stack.size()-1), "*/")){
                    RPn.add(String.valueOf(op_stack.get(op_stack.size()-1)));
                    op_stack.remove(op_stack.size()-1);
                }
                op_stack.add(tab[i]);
            }
            else if (tab[i] == '(')
                op_stack.add(tab[i]);
            else if (tab[i] == ')') {
                if (op_stack.get(op_stack.size()-1) != '(') {
                    RPn.add(String.valueOf(op_stack.get(op_stack.size()-1)));
                    op_stack.remove(op_stack.size()-1);
                }
                if (op_stack.get(op_stack.size()-1) == '(')
                    op_stack.remove(op_stack.size()-1);
            }
        }

        while (op_stack.size() > 0) {
            RPn.add(String.valueOf(op_stack.get(op_stack.size()-1)));
            op_stack.remove(op_stack.size()-1);
        }
    }

	private void parse_RPn() {
        ArrayList<BigInt> vec = new ArrayList<>();

        for (String i: RPn) {
            if (Character.isDigit(i.charAt(0)))
                vec.add(new BigInt(i));
            else {
                BigInt last = vec.get(vec.size()-1);
                vec.remove(vec.size()-1);

                switch (i.charAt(0)) {
                    case '+':
                        vec.set(vec.size()-1, vec.get(vec.size()-1).add(last));
                        break;
                    case '-':
                        vec.set(vec.size()-1, vec.get(vec.size()-1).sub(last));
                        break;
                    case '*':
                        vec.set(vec.size()-1, vec.get(vec.size()-1).mul(last));
                        break;
                    case '/':
                        vec.set(vec.size()-1, vec.get(vec.size()-1).div(last));
                        break;
                }
            }
        }
        this.value = vec.get(0);
    }

    static private boolean is_in(char c, String s) {
        char[] tab = s.toCharArray();
        for (char i: tab)
            if (i == c)
                return true;
        return false;
    }
}
