// class Solution {
//     public List<Integer> sequentialDigits(int low, int high) {
        
//     }
// }
import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        // Length of the numbers to generate
        for (int len = String.valueOf(low).length();
             len <= String.valueOf(high).length();
             len++) {

            for (int start = 0; start + len <= 9; start++) {
                int num = Integer.parseInt(digits.substring(start, start + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}