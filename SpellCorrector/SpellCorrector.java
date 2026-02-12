package spell;


import java.io.File;
import java.util.Scanner;


import java.io.IOException;

public class SpellCorrector implements ISpellCorrector {




    private Words dictionary = new Words();

    public SpellCorrector() {



    }

    

    public void useDictionary(String dictionaryFileName) throws IOException {


  
      Scanner in = new Scanner(new File(dictionaryFileName)).useDelimiter("[^a-zA-Z]+");



        while (in.hasNext()) {

            dictionary.add(in.next());
        }
    }



    public String suggestSimilarWord(String inputWord) {

        String mostSimilar = null;

        mostSimilar = dictionary.Control(inputWord.toLowerCase());

        if (mostSimilar == "") {
           mostSimilar = null;
        }        

        return mostSimilar;

    }
}
