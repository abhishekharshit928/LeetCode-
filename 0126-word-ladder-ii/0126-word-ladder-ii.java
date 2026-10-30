
class Solution {

    Map<String, List<String>> parents = new HashMap<>();
    List<List<String>> ans = new ArrayList<>();
    String begin;

    public List<List<String>> findLadders(
        String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return ans;
        }

        begin = beginWord;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;

        // BFS: Build parent relationships
        while (!q.isEmpty() && !found) {

            int size = q.size();
            Set<String> usedOnLevel = new HashSet<>();

            for (int i = 0; i < size; i++) {

                String word = q.poll();
                char[] arr = word.toCharArray();

                for (int j = 0; j < arr.length; j++) {

                    char original = arr[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        if (ch == original) continue;

                        arr[j] = ch;
                        String newWord = new String(arr);

                        if (set.contains(newWord) &&
                            !visited.contains(newWord)) {

                            parents.putIfAbsent(
                                newWord, new ArrayList<>());

                            parents.get(newWord).add(word);

                            usedOnLevel.add(newWord);

                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }

                    arr[j] = original;
                }
            }

            // Add newly discovered words after the whole level
            for (String word : usedOnLevel) {
                visited.add(word);
                q.offer(word);
            }
        }

        // DFS: Reconstruct all shortest paths
        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, path);
        }

        return ans;
    }

    private void dfs(String word, List<String> path) {

        if (word.equals(begin)) {
            List<String> result = new ArrayList<>(path);
            Collections.reverse(result);
            ans.add(result);
            return;
        }

        if (!parents.containsKey(word)) {
            return;
        }

        for (String parent : parents.get(word)) {

            path.add(parent);

            dfs(parent, path);

            path.remove(path.size() - 1);
        }
    }
}
