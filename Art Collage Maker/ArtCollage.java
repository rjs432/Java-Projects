/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
       tileDimension = 100; //set default values of tileDimension to 100
       collageDimension = 4; //sets default values of collageDimension to 4 

       int width = tileDimension * collageDimension; 
       int height = tileDimension * collageDimension;
       original = new Picture(filename); 
       collage = new Picture (width, height);

        for (int col = 0; col < width; col++){
            for (int row = 0; row < height; row++){
                int scol = (col * original.width()) / width;
                int srow = (row * original.height()) / height;
                Color color = original.get(scol, srow);
                collage.set(col, row, color);
            }
       }
     //collage.show();
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        collageDimension = cd; //set default values of collageDimension to cd
        tileDimension = td; //set default values of tileDimension to td

        int width = tileDimension * collageDimension;
        int height = tileDimension * collageDimension;
        original = new Picture(filename); 
        collage = new Picture (width, height); //3
         for (int col = 0; col < width; col++){
            for (int row = 0; row < height; row++){
                int scol = (col * original.width()) / width;
                int srow = (row * original.height()) / height;
                Color color = original.get(scol, srow);
                collage.set(col, row, color);
            }
        }
     //collage.show();
    }
    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

       return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

       return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

        return original;

 
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

        return collage;

  
   }
    
 //    /*
 //     * Display the original image
 //     * Assumes that original has been initialized
 //     */
    public void showOriginalPicture() {
        original.show();
  }
    

 //    /*
 //     * Display the collage image
 //     * Assumes that collage has been initialized
 //     */
    public void showCollagePicture() {
         collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture replacement = new Picture(filename);

        for (int i = 0; i < collageDimension; i++){
            for (int j = 0; j < collageDimension; j++){
                for (int col = i * tileDimension; col < i * tileDimension + tileDimension; col++){
                    for (int row = j * tileDimension; row < j * tileDimension + tileDimension; row++){
                        if ( i == collageCol){
                            if (j == collageRow){
                                int replacementCol = ((col - i * tileDimension) * replacement.width()) / tileDimension;
                                int replacementRow = ((row - j * tileDimension) * replacement.height()) / tileDimension;
                                Color color = replacement.get(replacementCol, replacementRow);
                                collage.set(col, row, color);
                            }
                        }
                    }
                }
            }
        }
    }
   
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */

    public void makeCollage () {
      
        
        for (int i = 0; i < collageDimension; i++){
            for (int j = 0; j < collageDimension; j++){
                for (int col = i * tileDimension; col < i * tileDimension + tileDimension; col++){
                    for (int row = j * tileDimension; row < j * tileDimension + tileDimension; row++){
                                int originalCol = ((col - i * tileDimension) * original.width()) / tileDimension;
                                int originalRow = ((row - j * tileDimension) * original.height()) / tileDimension;
                                Color color = original.get(originalCol, originalRow);
                                collage.set(col, row, color);
                            }
                        }
               
                    }
                }
            
        }
    
    
    
 //     * Colorizes the tile at (collageCol, collageRow) with component 
 //     * (see Week 9 slides, the code for color separation is at the 
 //     *  book's website)
 //     *
 //     * @param component is either red, blue or green
 //     * @param collageCol tile column
 //     * @param collageRow tile row
     
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        //set(int col, int row, Color color    
 for (int i = 0; i < collageDimension; i++){
            for (int j = 0; j < collageDimension; j++){
                for (int col = i * tileDimension; col < i * tileDimension + tileDimension; col++){
                    for (int row = j * tileDimension; row < j * tileDimension + tileDimension; row++){
                        Color color = collage.get(col, row);
                        if (collageCol == i && collageRow == j && component.equals("red")){//
                        int r = color.getRed();//
                        collage.set(col, row, new Color(r, 0, 0));//
                    }
                        if (collageCol == i && collageRow == j && component.equals("green")){
                        int g = color.getGreen();
                        collage.set(col, row, new Color(0, g, 0));
                    }
                        if (collageCol == i && collageRow == j && component.equals("blue")){
                        int b = color.getBlue();
                        collage.set(col, row, new Color(0, 0, b));
                }
            }
          
      }  
      }
      }
      } 
                        
                         
    
    public void greyscaleTile (int collageCol, int collageRow) {
        for (int i = 0; i < collageDimension; i++){
            for (int j = 0; j < collageDimension; j++){
                for (int col = i * tileDimension; col < i * tileDimension + tileDimension; col++){
                    for (int row = j * tileDimension; row < j * tileDimension + tileDimension; row++){
                        if ( i == collageCol){
                            if (j == collageRow){
                                Color color = collage.get(col, row);
                                Color gray = Luminance.toGray(color);
                                collage.set(col, row, gray);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void main (String[] args) {
    }
}
