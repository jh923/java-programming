

import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    private ArrayList answers = new ArrayList();

    /**
     * Main function, used to execute the program.
     * @param args none
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.build();
    }

    /**
     * Builds all the possible solutions and prints the results
     */
    public void build() {
        for (int i = 0; i < 3; i++) {                                       // 0 op 1
            for (int j = 0; j < 3; j++) {                                   // 1 op 2
                for (int k = 0; k < 3; k++) {                               // 2 op 3
                    for (int l = 0; l < 3; l++) {                           // 3 op 4
                        for (int m = 0; m < 3; m++) {                       // 4 op 5
                            for (int n = 0; n < 3; n++) {                   // 5 op 6
                                for (int o = 0; o < 3; o++) {               // 6 op 7
                                    for (int p = 0; p < 3; p++) {           // 7 op 8
                                        for (int q = 0; q < 3; q++) {       // 8 to 9
                                            solve(i, j, k, l, m, n, o, p, q);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        results();
    }

    /**
     * Converts ints from the build method into operators and applies them in order.
     * 0 -> "+", 1 -> "-" and 2 -> ""
     * @param a operator 1
     * @param b operator 2
     * @param c operator 3
     * @param d operator 4
     * @param e operator 5
     * @param f operator 6
     * @param g operator 7
     * @param h operator 8
     * @param i operator 9
     */
    public void solve(int a, int b, int c, int d, int e, int f, int g, int h, int i) {
        int result = 0;
        boolean add = true;
        String sum = "0";
        String concat = "";

        ArrayList<Integer> ops = new ArrayList<>();
        ops.addAll(Arrays.asList(a, b, c, d, e, f, g, h, i));

        for (int j = 0; j < 9; j++) {

            // Regular cases
            if (ops.get(j) == 0) {
                result += (j + 1);
                sum += " + " + (j + 1) + " ";
                add = true;
            } else if (ops.get(j) == 1) {
                result -= (j + 1);
                sum += " - " + (j + 1);
                add = false;
            }

            // Concatenation cases
            else if (ops.get(j) == 2) {

                // Correct the current value of result
                if (add)
                    result -= j;
                if (!add)
                    result += j;

                concat = "";

                // Keep on concatenating until another operator is reached
                while (ops.get(j) == 2 && j < 9) {
                    concat += Integer.toString(j);
                    concat += Integer.toString(j + 1);
                    sum = sum.replace(Integer.toString(j), "");
                    j++;
                    if (j == 9)
                        break;
                }

                // Remove duplicate values from concat
                for (int k = concat.length()-1; k > 1; k--)   {
                        if (concat.charAt(k) == concat.charAt(k - 1)) {
                            concat = concat.substring(0, k - 1) + concat.substring(k);
                            k--;
                        }
                }

                // Add or subtract the final concat value
                if (add) {
                    result += Integer.valueOf(concat);
                    sum += concat;

                } else if (!add) {
                    result -= Integer.valueOf(concat);
                    sum += concat;
                }

                // Adjust the value of j, to get values after the concatenation
                String fd = concat.toString();
                fd = fd.substring(fd.length()-1);
                if (Integer.valueOf(fd) != 9) {
                    j = j - (j - Integer.valueOf(fd)) - 1;
                }
            }
        }

        // Gather and format all the correct answers
        if (result == 100) {
            sum = sum.replace("0 + ", "");
            sum = sum.replace("0 - ", "");
            sum = sum.replace("0", "");
            if (sum.charAt(0) == ' ')   {
                sum = sum.substring(1);
            }

            answers.add(sum);
        }
    }

    /**
     * Prints a list of all the found formula within the scope that equals 100. One per line.
     */
    public void results()   {
        System.out.println("A list of all found answers");
        for (int i = 0; i < answers.size(); i++)    {
            System.out.println(answers.get(i));
        }
    }
}
