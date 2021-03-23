import java.io.*;
import java.util.*;

public class Graph {

    static String filename = "input.txt";
    static List<List<Integer>> graph = getTops();
    static Integer[] color = new Integer[getN() + 1];
    static boolean twoHalfs = true;


    public static void main(String[] args) throws IOException {

        System.out.println(graph.size());
        System.out.println(graph);

        Arrays.fill(color, 0);



        for (int i = 1; i <= getN(); i++) {
            if (color[i] == 0) {
                color[i] = 1;
                dfs(i);
            }
        }


        if (twoHalfs){
            writeFile("Y\n" + String.join(" ", getHalfs()[0].toString() ) + "\n0\n" + String.join(" ",getHalfs()[1].toString() ));
        }
        else {
            writeFile("N");
        }




    }

    public static void dfs(int pos) {

        for (int i = 0; i < graph.get(pos).size(); i++){
            if(color[graph.get(pos).get(i)] == 0){
                color[graph.get(pos).get(i)] = 3 - color[pos];
                System.out.println("Top: " + graph.get(pos).get(i).toString() +  "\tCOLOR: " + color[graph.get(pos).get(i)].toString());
                dfs(graph.get(pos).get(i));

            }
            else if (color[graph.get(pos).get(i)].equals(color[pos])){
                twoHalfs = false;
            }
        }
    }

    public static void writeFile(String str)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(str);

        writer.close();
    }


    static String[] getHalfs(){

        StringBuilder firstHalf = new StringBuilder();
        StringBuilder secondHalf = new StringBuilder();
        String[] res = new String[2];

        for (int i = 1; i < color.length; i++){
            if (color[i] ==1){
                firstHalf.append(i).append(" ");
            }
            else {
                secondHalf.append(i).append(" ");
            }
        }
        res[0] = firstHalf.toString();
        res[1] = secondHalf.toString();

        return res;


    }

    public static Integer getN() {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            return Integer.parseInt(myReader.nextLine());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<List<Integer>> getTops() {

        List<List<Integer>> result = new ArrayList<>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            Integer n = Integer.parseInt(myReader.nextLine());

            result.add(new ArrayList<>());

            while (myReader.hasNextLine()) {
                List<Integer> tops = new ArrayList<>();
                String data = myReader.nextLine();
                for( String top : data.split(" "))
                    if(Integer.parseInt(top) != 0){
                        tops.add(Integer.parseInt(top));
                    }
                result.add(tops);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }
}
