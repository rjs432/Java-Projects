package lse;

import java.io.*;
import java.util.*;

/**
 * This class builds an index of keywords. Each keyword maps to a set of pages in
 * which it occurs, with frequency of occurrence in each page.
 *
 */
public class LittleSearchEngine {
    
    /**
     * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
     * an array list of all occurrences of the keyword in documents. The array list is maintained in 
     * DESCENDING order of frequencies.
     */
    HashMap<String,ArrayList<Occurrence>> keywordsIndex;
    
    /**
     * The hash set of all noise words.
     */
    HashSet<String> noiseWords;
    
    /**
     * Creates the keyWordsIndex and noiseWords hash tables.
     */
    public LittleSearchEngine() {
        keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
        noiseWords = new HashSet<String>(100,2.0f);
    }
    
    /**
     * Scans a document, and loads all keywords found into a hash table of keyword occurrences
     * in the document. Uses the getKeyWord method to separate keywords from other words.
     * 
     * @param docFile Name of the document file to be scanned and loaded
     * @return Hash table of keywords in the given document, each associated with an Occurrence object
     * @throws FileNotFoundException If the document file is not found on disk
     */
    public HashMap<String,Occurrence> loadKeywordsFromDocument(String docFile)  //20-apears to work
    throws FileNotFoundException {
        /** COMPLETE THIS METHOD
         if file no exist throw error
         traverse through the doc and use getKeyWord to see if the word is key word
         if key word add it to the hash table 
             increase value if seen more then once in the array list which stores the num of occurances
             insert with the txt file it comes from
         else continue till at end
          
          use buffer reader to make lines 
          **/
        HashMap<String,Occurrence> cc = new HashMap<String,Occurrence>();//putting the words into
        File doc = new File(docFile); //the file that will be scanned
        Scanner scan = new Scanner(doc); //scanner that will go through
        boolean flag = false; //flag for is null
        String words = "";
        String word = "";
        String[] split = null;
        
        while (scan.hasNext()) { //keep going till no more
             words = scan.nextLine();
             String w = words.trim();
             boolean t = w.isEmpty(); //tells you if the current words is empty
             
            if (IsNull(words) == true && t == true) {
                continue;
            }
            
            else if (IsNull(words) == false && t == false){    
                split = words.split(" "); //splits out all the spaces 
                
                int h = 0;
                while (h < split.length){ //go through the split words (split as in no spaces)
                    word = getKeyword(split[h]); //makes/checks if its a keyword
                    flag = IsNull(word);
                    
                    if (word == "") {
                        h++;
                        continue;
                    }
                    
                    else if (flag == false) {
                        if (cc.containsKey(word)){ //already in the map so up the freq
                            Occurrence temp = cc.get(word);
                            temp.frequency++; 
                            cc.put(word, temp); //puts it in map
                        }
                        else{ //not in map add for first time
                            Occurrence occ = new Occurrence (docFile, 1); 
                            cc.put(word, occ); //puts in map
                        }
                    }
                    h++;
                }
            }
        }
        scan.close();
        return cc;         
    }
    
    private boolean IsNull (String word) { //sees if null- used on lines 66,70,76
        boolean flag = false;
        if (word == null) {
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }

        
    
    /**
     * Merges the keywords for a single document into the master keywordsIndex
     * hash table. For each keyword, its Occurrence in the current document
     * must be inserted in the correct place (according to descending order of
     * frequency) in the same keyword's Occurrence list in the master hash table. 
     * This is done by calling the insertLastOccurrence method.
     *  
     * @param kws Keywords hash table for a document
     */
    public void mergeKeywords(HashMap<String,Occurrence> kws) { //25-
        /** COMPLETE THIS METHOD
        if exists in main 
        add the occurences to the end of the arraylist
        call insert last on it
        
        else 
        add in
         run load+ keywords then merge 
          **/  
    
        for (String key: kws.keySet()) { //key set return view of keys- we only need keys for here
            ArrayList<Occurrence> op = new ArrayList<Occurrence>(); 
            ArrayList<Occurrence> holder = new ArrayList<Occurrence>(); // temp that holds vals that are not in keywordsIndex 
             if (IsIn(key) == true){  //if in already
                op = keywordsIndex.get(key); //sets op to the key from keywordsIndex since already in
                Occurrence po = kws.get(key);
                op.add(po); //adds the occ at po to the list 
                insertLastOccurrence(op); //adds it in its position
                keywordsIndex.put(key, op); //puts it in the main list - merge
            }
            
            else if(IsIn(key) == false){// not in 
                Occurrence j = kws.get(key);
                holder.add(j);
                insertLastOccurrence(holder);
                keywordsIndex.put(key, holder);
            }
        }
    }
        
    private boolean IsIn(String key) { 
        boolean s = false;
     
        for (Map.Entry<String, ArrayList<Occurrence>>kgi : keywordsIndex.entrySet()) { //goes through to see if in 
            String h = kgi.getKey();
            if (key.equals(h)) {
                s = true; 
                break;
            }
            else {
                s = false;
            }
        }
        return s;
    }
        
        

        
    /**
     * Given a word, returns it as a keyword if it passes the keyword test,
     * otherwise returns null. A keyword is any word that, after being stripped of any
     * trailing punctuation(s), consists only of alphabetic letters, and is not
     * a noise word. All words are treated in a case-INsensitive manner.
     * 
     * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
     * NO OTHER CHARACTER SHOULD COUNT AS PUNCTUATION
     * 
     * If a word has multiple trailing punctuation characters, they must all be stripped
     * So "word!!" will become "word", and "word?!?!" will also become "word"
     * 
     * See assignment description for examples

     * @param word Candidate word
     * @return Keyword (word without trailing punctuation, LOWER CASE)
     */
    public String getKeyword(String word) { //13
        /** COMPLETE THIS METHOD
                         Pre-code
         for loop through the word and add all the letters to an array 
         if it reaches a char then skip and add next letter or skip till end
         covert the array back to string and convert it to lowercase
         then check if its not a noise word 
         if noise word dont return it else return it
         ***/

        String cleanWord ="";
        if (word == "") {
            cleanWord = null;
        }
        else {
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            
            if (word.charAt(i) == '.' || word.charAt(i) == ',' || word.charAt(i) == '?' ||word.charAt(i) == ':'|| word.charAt(i) == ';' || word.charAt(i) == '!') {
                continue; //go to next char 
            }
            else if (Character.isLetter(w)) {
                char h = word.charAt(i);
                String l = Character.toString(h);
                cleanWord = cleanWord.concat(l); //adds the letter to the end of the new string 
            }
            else {
                cleanWord = null; //means it has another char that makes it not only letters... so null
                break; //
            }
        }
            if (cleanWord != null) {
                cleanWord = cleanWord.toLowerCase(); //makes the string lowercase
                
                if(noiseWords.contains(cleanWord)) {
                cleanWord = null; //clean word is in the noise so it should be null
                }
            }
        }
        return cleanWord; //returns null or the word
    }



    /**
     * Inserts the last occurrence in the parameter list in the correct position in the
     * list, based on ordering occurrences on descending frequencies. The elements
     * 0..n-2 in the list are already in the correct order. Insertion is done by
     * first finding the correct spot using binary search, then inserting at that spot.
     * 
     * @param occs List of Occurrences
     * @return Sequence of mid point indexes in the input list checked by the binary search process,
     *         null if the size of the input list is 1. This returned array list is only used to test
     *         your code - it is not used elsewhere in the program.
     */
    public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) { //12
        /** COMPLETE THIS METHOD
         Do a binary search of the list to find the correct place to input
         if the size of list is 1 
             return null
         if the size is greater
             go through with binary search and store midpoints
         
          **/
    
        ArrayList<Integer> freq = new ArrayList<Integer>(); 
        ArrayList<Integer> frequenc = new ArrayList<Integer>();//add the freqs to
        ArrayList<Integer> binarySearch = new ArrayList<Integer>(); //add the mids to
        if (occs.size() <= 1 ) {
            return null;
        }
            for (int i = 0; i < occs.size(); i++ ) {
                Occurrence s = occs.get(i);
                frequenc.add(s.frequency);
            }
            
        int insert = frequenc.remove(frequenc.size()-1); //removes the last
        int left = 0;
        int right = frequenc.size()-1;
        int middle = -1;
            //simple binary search
            while (right >= left) {
            
                middle = (right + left) / 2;
                binarySearch.add(middle); 
                
            if (frequenc.get(middle) <  insert) {
                right = middle - 1;
            }
            else if (frequenc.get(middle) > insert ) {
                left = middle + 1;
            }
            else {
                break; 
        }
            }
        return binarySearch; 
    }
    

    /**
     * This method indexes all keywords found in all the input documents. When this
     * method is done, the keywordsIndex hash table will be filled with all keywords,
     * each of which is associated with an array list of Occurrence objects, arranged
     * in decreasing frequencies of occurrence.
     * 
     * @param docsFile Name of file that has a list of all the document file names, one name per line
     * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
     * @throws FileNotFoundException If there is a problem locating any of the input files on disk
     */
    public void makeIndex(String docsFile, String noiseWordsFile) 
    throws FileNotFoundException {
        
        // load noise words to hash table
        Scanner sc = new Scanner(new File(noiseWordsFile));
        while (sc.hasNext()) {
            String word = sc.next();
            noiseWords.add(word);
        }
        
        // index all keywords
        sc = new Scanner(new File(docsFile));
        while (sc.hasNext()) {
            String docFile = sc.next();
            HashMap<String,Occurrence> kws = loadKeywordsFromDocument(docFile);
            mergeKeywords(kws);
            
        }
        sc.close();
    }
    
    /**
     * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
     * document. Result set is arranged in descending order of document frequencies. 
     * 
     * Note that a matching document will only appear once in the result. 
     * 
     * Ties in frequency values are broken in favor of the first keyword. 
     * That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2 also with the same 
     * frequency f1, then doc1 will take precedence over doc2 in the result. 
     * 
     * The result set is limited to 5 entries. If there are no matches at all, result is null.
     * 
     * See assignment description for examples
     * 
     * @param kw1 First keyword
     * @param kw1 Second keyword
     * @return List of documents in which either kw1 or kw2 occurs, arranged in descending order of
     *         frequencies. The result size is limited to 5 documents. If there are no matches, 
     *         returns null or empty array list.
     */
    public ArrayList<String> top5search(String kw1, String kw2) { //30-  on one run it worked, test with 5 docs
        /** COMPLETE THIS METHOD **/


        ArrayList<Occurrence> k1 = new ArrayList<Occurrence>();
        if (keywordsIndex.containsKey(kw1) || keywordsIndex.containsKey(kw2)){
         for (Map.Entry<String,ArrayList<Occurrence>> ent :keywordsIndex.entrySet()) {
             if (ent.getKey().equals(kw1)|| ent.getKey().equals(kw2)) { //if the key equals either
                 k1.addAll(ent.getValue()); //add it to the list of occurrences 
             }        
         }
        }
        else {
            return null;
        }
        
        //sort
        int n = k1.size();
          for (int i = 0; i < n-1; i++) { 
                // Find the minimum element in unsorted array 
                int min = i; 
                for (int j = i+1; j < n; j++) {
                    if (k1.get(j).frequency < k1.get(min).frequency) { //play with =
                        min = j; 
                }
            }
                // Swap the found minimum element with the first element 
                Occurrence temp = k1.get(min); 
                k1.set(min, k1.get(i)); 
                k1.set(i, temp);
          }
          
          //get the 5 
          
          ArrayList<String> k2 = new ArrayList<>();
          for (int i = (k1.size()-1); i >= (k1.size()-5) && i >= 0; i--) {
              k2.add(k1.get(i).document);
          }
            

        return k2;
    
    }
}
