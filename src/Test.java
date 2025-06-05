//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class Test {
//    public static void main(String[] args) {
//        int[][] n = new int[50][50];
//
//        int maxWorldCol = 50, maxWorldRow = 50;
//        int col = 0, row = 0;
//        InputStream is = Test.class.getResourceAsStream("/maps/map02.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        while(col < maxWorldCol && row < gp.maxWorldRow) {
//            String line = br.readLine(); // Read a line in txt file
//            while (col < gp.maxWorldCol) {
//                String[] numbers = line.split(" ");
//
//                int num = Integer.parseInt(numbers[col]);
//
//                n[col][row] = num;
//                col++;
//            }
//            if (col == gp.maxWorldCol) {
//                col = 0;
//                row++;
//            }
//        }
//        br.close();
//    }
//}
