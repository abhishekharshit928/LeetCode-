
class Solution {
        private void generate(int n, StringBuilder str,int leftBrace,int rightBrace,  List<String> ans){
        if(str.length() == 2*n){
            ans.add(str.toString());
            return ;
        }
       
       if(leftBrace < n){
        str.append('(');
        generate(n, str, leftBrace+1 , rightBrace, ans); 
        str.deleteCharAt(str.length() - 1);
       }


        if( rightBrace < leftBrace){
            str.append(')');
            generate(n, str,leftBrace , rightBrace+1, ans); 
            str.deleteCharAt(str.length() - 1);
        }

    }
    public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>(); 
     generate(n, new StringBuilder() ,0 , 0 , ans); 
     return ans;
    }
}