import java.beans.Customizer;

public class Main {

    public static void main(String[] args) {
        test1_MaxRentals();
        test2_ReturnNonExistent();
        test3_DuplicateAdd();
        test4_RemoveRentedOrNonExistent();
    }

    public static void test1_MaxRentals() {
        System.out.println("Test 1: Rent more than the allowed number of movies");
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addMovie("Inception", Genre.SCIENCE_FICTION, 2010, "Christopher Nolan", "Director of Inception");
        rentalSystem.addMovie("The Dark Knight", Genre.ACTION, 2008, "Christopher Nolan", "Director of The Dark Knight");
        rentalSystem.addMovie("Interstellar", Genre.SCIENCE_FICTION, 2014, "Christopher Nolan", "Director of Interstellar");
        rentalSystem.addMovie("Dunkirk", Genre.ACTION, 2017, "Christopher Nolan", "Director of Dunkirk");
        rentalSystem.addMovie("Tenet", Genre.ACTION, 2020, "Christopher Nolan", "Director of Tenet");
        rentalSystem.addMovie("Memento", Genre.DRAMA, 2000, "Christopher Nolan", "Director of Memento");
        rentalSystem.addMovie("Prestige", Genre.DRAMA, 2006, "Christopher Nolan", "Director of Prestige");

        // Rent 5 movies
        rentalSystem.rentMovie("John Doe", "12345", "Inception", 2010, "Christopher Nolan");
        rentalSystem.rentMovie("John Doe", "12345", "The Dark Knight", 2008, "Christopher Nolan");
        rentalSystem.rentMovie("John Doe", "12345", "Interstellar", 2014, "Christopher Nolan");
        rentalSystem.rentMovie("John Doe", "12345", "Dunkirk", 2017, "Christopher Nolan");
        rentalSystem.rentMovie("John Doe", "12345", "Tenet", 2020, "Christopher Nolan");

        // Try renting one more movie (should fail)
        rentalSystem.rentMovie("John Doe", "12345", "Memento", 2000, "Christopher Nolan");

        //Try renting one of the movies again (should fail)
        rentalSystem.rentMovie("John Doe", "12345", "Inception", 2010, "Christopher Nolan");

        rentalSystem.printMovies();
    }

    public static void test2_ReturnNonExistent() {
        System.out.println("\nTest 2: Return a movie that is not in the system");
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addMovie("Inception", Genre.SCIENCE_FICTION, 2010, "Christopher Nolan", "Director of Inception");
        rentalSystem.addMovie("The Dark Knight", Genre.ACTION, 2008, "Christopher Nolan", "Director of The Dark Knight");
        rentalSystem.addMovie("Interstellar", Genre.SCIENCE_FICTION, 2014, "Christopher Nolan", "Director of Interstellar");

        // Rent a movie
        rentalSystem.rentMovie("John Doe", "12345", "Inception", 2010, "Christopher Nolan");

        // Try to return a movie that is not in the system
        rentalSystem.returnMovie("12345", "Memento", 2000, "Christopher Nolan");

        // Return the rented movie
        rentalSystem.returnMovie("12345", "Inception", 2010, "Christopher Nolan");

        //Try to return the same movie again (should fail)
        rentalSystem.returnMovie("12345", "Inception", 2010, "Christopher Nolan");

        rentalSystem.printMovies();
    }

    public static void test3_DuplicateAdd() {
        System.out.println("\nTest 3: Add a duplicate movie");
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addMovie("Inception", Genre.SCIENCE_FICTION, 2010, "Christopher Nolan", "Director of Inception");
        rentalSystem.addMovie("Inception", Genre.SCIENCE_FICTION, 2010, "Christopher Nolan", "Director of Inception"); // Duplicate
        rentalSystem.addMovie("The Dark Knight", Genre.ACTION, 2008, "Christopher Nolan", "Director of The Dark Knight");
        rentalSystem.addMovie("The Dark Knight", Genre.DRAMA, 2008, "Christopher Nolan", "Director of The Dark Knight"); //same name, different genre, should fail

        rentalSystem.printMovies();
    }

    public static void test4_RemoveRentedOrNonExistent() {
        System.out.println("\nTest 4: Remove a rented or non-existent movie");
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addMovie("Inception", Genre.SCIENCE_FICTION, 2010, "Christopher Nolan", "Director of Inception");
        rentalSystem.addMovie("The Dark Knight", Genre.ACTION, 2008, "Christopher Nolan", "Director of The Dark Knight");
        rentalSystem.addMovie("Interstellar", Genre.SCIENCE_FICTION, 2014, "Christopher Nolan", "Director of Interstellar");

        // Rent a movie
        rentalSystem.rentMovie("John Doe", "12345", "Inception", 2010, "Christopher Nolan");

        // Try to remove the rented movie (should fail)
        rentalSystem.removeMovie("Inception", 2010, "Christopher Nolan");

        // Try to remove a non-existent movie
        rentalSystem.removeMovie("NonExistent Movie", 1999, "Unknown Director");

        // Return the rented movie
        rentalSystem.returnMovie("12345", "Inception", 2010, "Christopher Nolan");

        // Remove the returned movie
        rentalSystem.removeMovie("Inception", 2010, "Christopher Nolan");

        rentalSystem.printMovies();
    }
    public class RentalSystem{
        Movie Movies[] = new Movie[30];
        Customer Customers[] = new Customer[30];
        Director Directors[] = new Director[30];

        public void addMovie(String newtitle,Genre newgenre,int newyear,String newdirector,String newbiographia){
            int i = 0,flag;
            while(Movies[i] != null && i < 30){
                if(Movies[i].title.equals(newtitle)&& Movies[i].year == newyear && Movies[i].director.equals(newdirector)){
                    System.out.println("Movie is already in the system");
                    return;
                }
                i++;
            }
            if(i == 30){
                System.out.println("Cant add more movies System is full");
                return;
            }
            Movie newMovie = new Movie();
            newMovie.title = newtitle;
            newMovie.genre = newgenre;
            newMovie.year = newyear;
            i = 0;
            flag = 1;
            while (Directors[i] != null && i < 30){
                if(Directors[i].name == newdirector) {
                    flag = 0;
                    break;
                }
                i++;
            }
            if(flag == 1) {
                Directors[i] = new Director();
                Directors[i].name = newdirector;
                Directors[i].biographia = newbiographia;
            }
            newMovie.director = newdirector;
            Movies[i] = newMovie;
        }
        public void removeMovie(String title, int year, String director){
            int i = 0,flag = 1;
            while(flag == 1 && i < 30) {
                if (Movies[i].title.equals(title) && Movies[i].year == year && Movies[i].director.equals(director)) {
                    if(Movies[i].rating != 0){
                        System.out.println("Cannot remove a rented movie");
                        return;
                    }
                    while(i < 29)Movies[i] = Movies[i++];
                    Movies[i] = null;
                    return;
                }
                i++;
            }
            System.out.println("No such movie exists");
            return;
        }
        public void printMovies() {
            // print rented movies
            int i = 0,flag = 0;
            while (i < 30 && Movies[i] != null ) {
                if (Movies[i].rating > 0 && flag == 0) {
                    flag = 1;
                    System.out.print("Rented movies: ");
                    Movies[i].printMovie();
                }
                if (Movies[i].rating > 0 && flag == 1) {
                    Movies[i].printMovie();
                }
                i++;
            }
            if(flag == 0){
                System.out.println("No Rented movies");
            }
            i = 0;
            flag = 0;
            while (i< 30){
                if (Movies[i].rating == 0 && flag == 0) {
                    flag = 1;
                    System.out.print("Unrented movie: ");
                    Movies[i].printMovie();
                }
                if (Movies[i].rating == 0 && flag == 1) {
                    Movies[i].printMovie();
                }
                i++;
            }
            if(flag == 0) {
                System.out.println("No Unrented movies");
            }
        }
        public void rentMovie(String name ,String id,String movietitle,int movieyear,String moviedirector){
             int i = 0,flag = 0;
             while(Customers[i] != null && i < 30){
                 if(Customers[i].id.equals(id)){
                     flag = 1;
                     break;
                 }
                 i++;
             }
             if(flag == 0){
                 if(Customers[i] == null && i < 30){
                     Customers[i] = new Customer();
                     Customers[i].name = name;
                     Customers[i].id = id;
                 }
                 else {
                     System.out.println("No room for new customers");
                     return;
                 }
             }
             int j = 0;
             while(j < 5){
                 if(Customers[i].moviesrenting[j].title.equals(movietitle)){
                     if(Customers[i].moviesrenting[j].year == movieyear){
                         if(Customers[i].moviesrenting[j].director.equals(moviedirector)){
                             System.out.println("Customer already has this movie");
                         }
                     }
                 }
                 j++;
             }
             if(j == 5){
            System.out.println("The customer has reached the limit ");
             }
        //sersh for the movie in the system
             int k = 0;
             flag = 0;
             while(k < 30 && Movies[k] != null){
                if(Movies[k].title.equals(movietitle) && Movies[k].year == movieyear && Movies[k].director.equals(moviedirector)){
                   flag = 1;
                   break;
                }
                k++;
             }
             if(flag == 0){
                 System.out.println("No Such movie exists");
                 return;
             }
             Customers[i].moviesrenting[j] = Movies[k];
             Movies[k].rating++;
        }
        public void returnMovie(String id, String movietitle, int movieyear, String moviedirector){
            int i = 0,flag = 0;
            while(Customers[i] != null && i < 30){
                if(Customers[i].id.equals(id)){
                    flag = 1;
                    break;
                }
                i++;
            }
            if(flag == 0){
                System.out.println("Customer not found");
                return;
            }
            int j = 0;
            while(j < 5 && Customers[i].moviesrenting[j] != null){
                if(Customers[i].moviesrenting[j].title.equals(movietitle)){
                    if(Customers[i].moviesrenting[j].year == movieyear){
                        if(Customers[i].moviesrenting[j].director.equals(moviedirector)){
                            flag = 1;
                        }
                    }
                }
                if (flag == 1)break;
                j++;
            }
            if(flag == 0){
                System.out.println("Customer cannot return this movie");
            }
            Customers[i].moviesrenting[j].rating--;

            while(j < 4 && Customers[i].moviesrenting[j] != null){
                Customers[i].moviesrenting[j] = Customers[i].moviesrenting[j + 1];
                j++;
            }
            Customers[i].moviesrenting[j] = null;
            if(j > 0)return;
            while (Customers[i] != null && i < 29){
                Customers[i] = Customers[i + 1];
            }
            Customers[i] = null;
            return;
        }
    }
    public enum Genre {
        SCIENCE_FICTION,
        ACTION,
        DRAMA,
        HORROR,
        COMEDY,
        }

    class Director{
        public String name;
        public String biographia;
    }

    class Movie{
        public String title;
        public Genre genre;
        public int year;
        public String director;
        public int rating = 0;
        void printMovie() {
            System.out.println("Title: " + title);
            System.out.println("Genre: " + genre);
            System.out.println("Year: " + year);
            System.out.println("Director: " + director);
        }
    }

    class Customer{
        public String name;
        public String id;
        public Movie moviesrenting[] = new Movie[5];
        public void printcustomer() {
            System.out.println("Name: " + name);
            System.out.println("ID: " + id);
        }
    }
}