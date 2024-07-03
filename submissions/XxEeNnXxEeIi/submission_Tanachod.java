import java.util.*;

public class Test 
{
    private boolean isPrime(int number) 
    {
        if (number <= 1) 
        {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) 
        {
            if (number % i == 0) 
            {
                return false;
            }
        }
        return true; 
    }

    public List<int[]> summaryPrime(int[] array, int result) 
    {
        List<Integer> primes = new ArrayList<>();

        for (int number : array) 
        {
            if (isPrime(number)) 
            {
                primes.add(number);
            }
        }

        List<int[]> pairs = new ArrayList<>();
        
        for (int i = 0; i < primes.size(); i++) 
        {
            for (int j = i; j < primes.size(); j++) 
            {  
                if (primes.get(i) + primes.get(j) == result) 
                {
                    pairs.add(new int[]{primes.get(i), primes.get(j)});
                }
            }
        }
        return pairs;
    }
    
    public void printSumaryPrime(int[] array, int result, List<int[]> listofsum)
    {
    	System.out.println("Input array: " + Arrays.toString(array));
        System.out.println("Target result: " + result);
    	
    	System.out.print("[");
        
        for (int i = 0; i < listofsum.size(); i++) 
        {
            System.out.print("[" + listofsum.get(i)[0] + ", " + listofsum.get(i)[1] + "]");
            
            if (i < listofsum.size() - 1) 
            {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private boolean isPangram(String string) 
    {
        HashSet<Character> alphabetSet = new HashSet<>();
        string = string.toLowerCase(); 

        for (char ch : string.toCharArray()) 
        {
            if (ch >= 'a' && ch <= 'z') 
            {
                alphabetSet.add(ch);
            }
        }

        return alphabetSet.size() == 26; 
    }

    public void showPangram(String string) 
    {
        if (isPangram(string)) 
        {
            String[] words = string.split("\\s+");
            String longestWord = "";

            for (String word : words) 
            {
                if (word.length() > longestWord.length()) 
                {
                    longestWord = word;
                }
            }
            
            System.out.println("Input tense: " + string);
            System.out.println("It Pangram!, คำที่ยาวที่สุด คือ: " + longestWord);
        } else 
        
        {
            System.out.println("It is not a pangram");
        }
    }
    
    public int findShortestPath(List<int[]> edges, int start, int end) 
    {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) 
        {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(start, 0);

        while (!pq.isEmpty()) 
        {
            int[] current = pq.poll();
            int node = current[0];
            int currentDist = current[1];

            if (node == end) 
            {
                return currentDist;
            }

            if (currentDist > dist.getOrDefault(node, Integer.MAX_VALUE)) 
            {
                continue;
            }

            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) 
            {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDist = currentDist + weight;

                if (newDist < dist.getOrDefault(nextNode, Integer.MAX_VALUE)) 
                {
                    dist.put(nextNode, newDist);
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }

        return -1; 
    }

    public void printShortestPath(List<int[]> edges, int start, int end) 
    {
        System.out.print("Input edges: [");
        
        for (int i = 0; i < edges.size(); i++) 
        {
            System.out.print(Arrays.toString(edges.get(i)));
            if (i < edges.size() - 1) {
                System.out.print(", ");
            }
        }
        
        System.out.println("]");
        System.out.println("Start node: " + start);
        System.out.println("End node: " + end);

        int shortestPathLength = findShortestPath(edges, start, end);
        
        if (shortestPathLength == -1) 
       {
            System.out.println("There is no path from " + start + " to " + end);
        } else 
        
        {
            System.out.println("The shortest path from " + start + " to " + end + " is: " + shortestPathLength);
        }
    }

    public static void main(String[] args) 
    {
    	System.out.println("ผู้จัดทำ: ธนโชติ ปัญจพรรุ่งโรจน์\n");
    	
    	Test test = new Test();
        
        // Test 1
        System.out.println("โจทย์ 1 : การหาค่าผสมที่เป็นไปได้ของเลขชุดที่ให้ผลรวมเป็นจำนวนเฉพาะ");
        int[] array = {3, 5, 7, 9, 11, 13};
        int result = 10;
        List<int[]> pairs = test.summaryPrime(array, result);
        test.printSumaryPrime(array, result, pairs);
        System.out.println("");
        
        // Test 2
        System.out.println("โจทย์ 2 : การหาคำที่ใช้ตัวอักษรทุกตัวในภาษาอังกฤษอย่างน้อยหนึ่งครั้ง (Pangram)");
        String testString = "The quick brown fox jumps over the lazy dog";
        test.showPangram(testString);
        System.out.println("");
        
        // Test 3
        System.out.println("โจทย์ 3 : การหาทางเดินที่สั้นที่สุดในกราฟ (Shortest Path in Graph)");
        List<int[]> edges = Arrays.asList
        (
            new int[]{1, 2, 1},
            new int[]{2, 3, 2},
            new int[]{1, 3, 4},
            new int[]{3, 4, 1}
        );
        
       int start = 1;
       int end = 4;
       test.printShortestPath(edges, start, end);
       System.out.println("");
    }
}

