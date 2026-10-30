class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
         Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();

        q.offer(beginWord);
        set.remove(beginWord);

        int count = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size ; i++){
                String word = q.poll();
                char[] arr = word.toCharArray();

                for(int j = 0 ; j < arr.length ; j++){
                    char org = arr[j];
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        if(ch == org) continue;
                        arr[j] = ch;
                        String newWord = new String(arr);
                        if(set.contains(newWord)){
                            if(newWord.equals(endWord)) return count + 1;
                            q.offer(newWord);
                            set.remove(newWord);
                        }
                    }
                    arr[j] = org;
                }
            }
            count++;
        }
        return 0;
    }
}