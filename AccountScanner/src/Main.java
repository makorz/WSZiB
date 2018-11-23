import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> rowsFromFile = new ArrayList<>();

        try {

            File file = new File("banks2");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                rowsFromFile.add(scanner.nextLine());
            }

            for (int i = 3; i < rowsFromFile.size(); i+=3){
                    rowsFromFile.remove(i);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }

        ArrayList<String> finalList = accountNumberMaker(rowsFromFile);

        for(String str:finalList)
            writeWithFileWriter(str + "\n");

    }

    public static ArrayList<String> accountNumberMaker(ArrayList<String> list) {

        ArrayList<String> accountsList = new ArrayList<>();

        for (int i = 0; i < list.size(); i+=3) {

            String singleAccount = "";
            int checkSum = 0;
            int position = 9;
            boolean illegible = false;

            for(int j = 0; j < list.get(i).length(); j+=3){

                String temp = list.get(i).substring(j,j+3) + list.get(i+1).substring(j,j+3) +
                        list.get(i+2).substring(j,j+3);
                singleAccount += whatsTheNumber(temp);

                if(whatsTheNumber(temp).matches("[0-9]")){
                    checkSum += Integer.parseInt(whatsTheNumber(temp)) * position;
                    position--;
                } else {
                    illegible = true;
                }
            }

            if(illegible){
                singleAccount += " ILL";
            } else if( checkSum % 11 != 0 ){
                singleAccount += " ERR";
            }
            accountsList.add(singleAccount);
        }
        return accountsList;
    }

    private static void writeWithFileWriter(String data){
        File file = new File("listOfAccounts.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.append(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String whatsTheNumber(String number) {

        String currentNumber = number;

        switch (currentNumber) {
            case " _ | ||_|":
                return "0";
            case "     |  |":
                return "1";
            case " _  _||_ ":
                return "2";
            case " _  _| _|":
                return "3";
            case "   |_|  |":
                return "4";
            case " _ |_  _|":
                return "5";
            case " _ |_ |_|":
                return "6";
            case " _   |  |":
                return "7";
            case " _ |_||_|":
                return "8";
            case " _ |_| _|":
                return "9";
            default:
                return "?";
        }
    }
}
