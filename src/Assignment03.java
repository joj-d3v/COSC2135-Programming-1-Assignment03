

public class Assignment03 {

    public Assignment03() {

    }

    public static void main(String[] args) {

        Media[] media = new Media[2];

        media[0] = new Book("Some isbn number");
        media[1] = new Song(100.001f);
      

        for(int i=0; i< media.length; i++)
        {
            //System.out.println(media[i].GetDescrition());
            if(media[i].GetType() == Media.MediaType.BOOK)
            {
                System.out.println("Book isbn " + ((Book)media[i]).GetISBN());
            }
            else if(media[i].GetType() == Media.MediaType.SONG)
            {
                System.out.println("Song length" + ((Song)media[i]).SongLengthInSeconds());
            }
        }

       /* System.out.println("What media you want?\n1 - Book\n2 - Song\n3 - Movie");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        if(choice == 1)
        {
            for(int i=0; i< media.length; i++)
            {
               if(media[i].GetType() == Media.MediaType.BOOK)
               {

               }
            }
        }*/

    }

}
