import java.util.ArrayList;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) {
 
         Scanner scanner = new Scanner(System.in);
         System.out.println("Welcome to the Movies Management System!.");
         int input, nextmovie = 0;
         String[] titles = new String[100];
         double[] ratings = new double[100];
         String[] directors = new String[100];
         while (true) {
             System.out.println("1. Add a new movie");
             System.out.println("2. Display all movies");
             System.out.println("3. Display movie rating");
             System.out.println("4. Find the best director");
             System.out.println("5. Exit");
             System.out.print("Please enter your choice: ");
             input = scanner.nextInt();
             if(input > 5 || input < 1) {
                 System.out.println("Invalid choice. Please try again.");
             }
             else if(input == 1) {
                 nextmovie = addmovie(nextmovie, titles, ratings, directors);
             }
             else if(input == 2) {
                 displayMovies(nextmovie, titles, ratings, directors);
             }
             else if(input == 3) {
 
             }
             else if(input == 4) {
                 bestAverage(ratings,directors, titles);
             }
             else if(input == 5) {
                 System.out.println("Exiting the program. Goodbye!");
                 break;
             }
         }
         scanner.close();
     }
 
 
     public static int addmovie(int nextmovie, String titles[], double ratings[], String directors[]) {
 
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter movie name: ");
         String title = scanner.nextLine();
         System.out.print("Enter rating: ");
         double rating = scanner.nextDouble();
         if (rating < 0 || rating > 10) {
 
             System.out.println("Invalid rating");
             return nextmovie;
         }
         System.out.print("Enter director name: ");
         String director = scanner.nextLine();
         titles[nextmovie] = title;
         ratings[nextmovie] = rating;
         directors[nextmovie] = director;
         return nextmovie + 1;
     }
     public static void displayMovies(int nextmovie ,String titles[], double ratings[], String directors[]) {
         int index = 0;
         while (index <= nextmovie) {
             System.out.print("Name: " + titles[index]);
             System.out.print(" rating: " + ratings[index]);
             System.out.println(" director: " + directors[index]);
         }
 
     }
     public void bestAverage(double []rating,String []actorName,String []movieName){
        if (movieName[0]==null){
            System.out.println("No movies are available")
        }
        int counter =0;
        int sum = 0;
        int max = 0;
        int average =0;
        String director;
        String  [] name1 == new String[100];
        for (int i=0; i<100;i++){
            name1[i]=actorName[i];
        }
        String name;
        for ( int i = 0; i < 100 ; i++){
            if (name1[i] =! null){
                name = name1[i];
        for (int j=i;j<100;j++){
         if (name1[j]==name){
            counter ++;
            sum +=rating[j];
            average = sum/counter;
            if (average>max){
                max =average;
                director = name;
            }
            name1[j] = null;
         }
        }
        sum = 0;
        counter =0;
        }
        }
        System.out.println("Best director:"name  "with an average rating of" max);
    }
}
 
 
 