import java.io.*;

//import Media.MediaType;

public class MediaFrontEnd {

    private MediaBackEnd mediaBackEnd;
    private BufferedReader inputScanner;

    public MediaFrontEnd(MediaBackEnd mediaBackEnd) {

        // Initialized variables to allow them to be called with 'this.'.
        this.mediaBackEnd = mediaBackEnd;

        System.out.println("Best Reads!");
        System.out.println("");
        System.out.println("A Light and Easy Application to explore reviewed Novels!");
        System.out.println("");
        System.out.println(" ___________________________________________________________");
        String readFileName = systemInput("| Welcome! Please make your Selection:                      |");
        while (readFileName.equals("") || !readFileName.equalsIgnoreCase("exit")) {
            readFileName = systemInput("\n" + "Please make a Selection: ");
        }
        // this.mediaBackEnd.saveFile();
    }

    public String systemInput(String userPrompts) {

        String systemInput = null;
        // Try and Catch utilised for exception catching for invalid BufferedReading.
        try {
            if (this.inputScanner == null)
                this.inputScanner = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(userPrompts);

            System.out.println(" ___________________________________________________________");
            System.out.println("| [0] View Media Entries                                     |");
            System.out.println("| [1] Search for Title Entry >> Must be exact Title name <<  |");
            System.out.println("| [2] Add a Media Record                                      |");
            System.out.println("| [3] Edit a Book Entry                                     |");
            System.out.println("| [4] Delete a Book Entry                                   |");
            System.out.println("| [5] Save a Book Entry                                     |");
            System.out.println("| [6] Expand your Book Entry Inventory                      |");
            System.out.println("| [exit] Exit                                               |");
            System.out.println(" ___________________________________________________________");

            systemInput = this.inputScanner.readLine();
            // if else statments for user inputs.
            if (systemInput.equals("0")) {
                this.viewBookTable();
            } else if (systemInput.equals("1")) {
                this.searchTitle();
            } else if (systemInput.equals("2")) {
                this.addMedia();
                // } else if (systemInput.equals("3")) {
                // this.editTitle();
                // } else if (systemInput.equals("4")) {
                // this.deleteEntry();
                // } else if (systemInput.equals("5")) {
                // this.saveToFile();
            } else if (systemInput.equals("6")) {
                // User Option to Expand Memory if Needed
                this.mediaBackEnd.expandMaxEntry();
                // } else if (systemInput.equalsIgnoreCase("exit")) {
                // System.out.println("");
                // System.out.println(">>TTYL, Good Bye<<");
                // }
            }

        } catch (Exception e) {
            // Throws format exception if nothing or incorrect primitive type is entered.
            System.out.println("");
            // String errorMessage = "Error: Incorrect Input";
            System.out.println(e.getMessage());

        }
        // Will repeat functions until conditions are met, from above selection.
        return systemInput;

    }

    public void viewBookTable() {

        String leftAlignFormat = "| %-20s| %-19s | %-13s|%n";

        System.out.format("+---------------------+---------------------+--------------+%n");
        System.out.format("| Book Title          | Book Page Number    | iSBN         |%n");
        System.out.format("+---------------------+---------------------+--------------+%n");

        int i = 0;
        while (i < this.mediaBackEnd.getMediaCounter()) {
            if (this.mediaBackEnd.getMedia()[i].getType() == Media.MediaType.BOOK) {
                Book temp = ((Book) this.mediaBackEnd.getMedia()[i]);
                System.out.format(leftAlignFormat, temp.title, temp.getPageCount(), temp.getISBN());
            }
            i++;
        }
        System.out.format("+---------------------+---------------------+--------------+%n");
        System.out.println("");
        System.out.println("");

    }

    public void addMedia() {

        if (!isDatabaseFull()) {
            System.out.println("What type of Media do you want to Create?");
            System.out.println(">> Press [1] for Book Entry <<");
            System.out.println(">> Press [2] for Song Entry <<");
            System.out.println(">> Press [3] for Movie Entry <<");

            try {
                String userInput = this.inputScanner.readLine();

                if (userInput.equals("1")) {
                    int i = 0;
                    Book tempBook = ((Book) this.mediaBackEnd.getMedia()[i]);

                    System.out.println("Please Enter Your Title:" + "\n");
                    String title = this.inputScanner.readLine();
                    if (!this.mediaBackEnd.SearchTitle(title)) {
                        System.out.println("Please Enter the Page Count: " + "\n");
                        int pageCount = Integer.parseInt(this.inputScanner.readLine());
                        System.out.println("PLease Enter the ISBN: " + "\n");
                        long iSBN = Long.parseLong(this.inputScanner.readLine());
                        System.out.println("");
                        System.out.println("############################");
                        System.out.println("Adding Your Title deets.....");
                        ((MediaBackEnd) this.mediaBackEnd).addBookType(title, pageCount, iSBN);
                        System.out.println("Book Title Added");
                        this.saveFile();

                    } else if (this.mediaBackEnd.SearchTitle(tempBook.title)) {
                        System.out.println("That Book Title Already Added!");

                    }

                } else if (userInput.equals("2")) {
                    int i = 2;
                    Song tempSong = ((Song) this.mediaBackEnd.getMedia()[i]);

                    System.out.println("Please Enter the Song Title:" + "\n");
                    String title = this.inputScanner.readLine();
                    if (!this.mediaBackEnd.SearchTitle(title)) {
                        System.out.println("Please Enter the Song Length: " + "\n");
                        float length = Float.parseFloat(this.inputScanner.readLine());
                        System.out.println("");
                        System.out.println("############################");
                        System.out.println("Adding Your Title deets.....");
                        ((MediaBackEnd) this.mediaBackEnd).addSongType(title, length);
                        System.out.println("Song Title Added");
                        this.saveFile();

                    } else if (this.mediaBackEnd.SearchTitle(tempSong.title)) {
                        System.out.println("That Song Already Exists!");

                    }

                } else if (userInput.equals("3")) {
                    int i = 1;
                    Movie tempMovie = ((Movie) this.mediaBackEnd.getMedia()[i]);

                    System.out.println("Please Enter the Movie Title:" + "\n");
                    String title = this.inputScanner.readLine();
                    if (!this.mediaBackEnd.SearchTitle(title)) {
                        System.out.println("Please Enter the Directors Name: " + "\n");
                        String directName = this.inputScanner.readLine();
                        System.out.println("Please Enter the Directors Name: " + "\n");
                        float length = Float.parseFloat(this.inputScanner.readLine());
                        System.out.println("");
                        System.out.println("Please Enter the Directors Name: " + "\n");
                        String movieComment = this.inputScanner.readLine();
                        System.out.println("");
                        System.out.println("############################");
                        System.out.println("Adding Your Title deets.....");
                        ((MediaBackEnd) this.mediaBackEnd).addMovieType(title, directName, length, movieComment);
                        ;
                        System.out.println("Movie Title Added");
                        this.saveFile();

                    } else if (this.mediaBackEnd.SearchTitle(tempMovie.title)) {
                        System.out.println("That Movie Already Exists!");

                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (isDatabaseFull()) {
            System.out.println("Your Database if Full, please increase your List Size!");

        }
    }

    public void saveFile() {

        System.out.println("Please enter the name of the file");
        try {
            String fileName = inputScanner.readLine();
            this.mediaBackEnd.saveFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchTitle() {

        System.out.println("Please enter Title");
        String title;
        try {
            title = this.inputScanner.readLine().toString();
            Boolean result = this.mediaBackEnd.SearchTitle(title);

            if (result)
                System.out.println("Record Found: " + title +" - " + this.mediaBackEnd.getMedia()[0].GetDescription());
            else
                System.out.println("No record Found.....");

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }

    }

    public boolean isDatabaseFull() {
        this.mediaBackEnd.isDatabaseFull();
        return false;
    }

}
