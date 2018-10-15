package com.company;

import java.io.*;

public class Email {

    private String name;
    private String surname;
    private String fullEmail;

    public Email(String name, String surname) {

        if((name.matches("[a-zA-Z]*") && name.length() >= 2 && name.length() <= 40) && (surname.matches("[a-zA-Z]*")
                && surname.length() >= 2 && surname.length() <= 40)){
            this.name = name;
            this.surname = surname;
            this.fullEmail = makingAnEmail();
        }else {
            System.out.println("Invalid data, please try again!");
        }
    }

    public String getFullEmail() {
        return fullEmail;
    }

    private String makingAnEmail(){
        int howManyTimes = checkIfEmailExists(this.name + "." + this.surname);
        if(howManyTimes > 0){
            this.surname += (howManyTimes + 1);
        }

        String fullMail = (this.name + "." + this.surname + "@mex.com \n");
        writeWithFileWriter(fullMail);
        return fullMail;
    }

    private void writeWithFileWriter(String data){
        File file = new File("listOfEmails.txt");
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

    private int checkIfEmailExists(String name){
        int NumberOfExistingEmails = 0;
        try {
                String line = "";
                BufferedReader bReader = new BufferedReader(new FileReader("listOfEmails.txt"));
                while ((line = bReader.readLine()) != null) {
                    int posFound = line.indexOf(name);
                    if (posFound > - 1) {
                        NumberOfExistingEmails++;
                    }
                }
                bReader.close();
            }
            catch (IOException e) {
                System.out.println("Error: " + e.toString());
            }
            return NumberOfExistingEmails;
        }

}
