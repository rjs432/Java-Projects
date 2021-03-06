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

    // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

        return collage;

    // // WRITE YOUR CODE HERE
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
    //     int width = collageDimension * tileDimension; //this set the width to be the collageDimension X collageDimension of original
    //     int height = collageDimension * tileDimension; //this sets the height of original

    //     //Picture newimage = new Picture(collageDimension * tileDimension, collageDimension * tileDimension); //creates a new image that is the same w/h of the original
    //     Picture newimage = this.collage;
    //     Picture inputimage = new Picture(filename); // takes the image from file name and sets it to input image
        
    //     // scale filename image 
    //     int w = this.tileDimension * this.collageDimension; //takes
    //     int h = this.tileDimension * this.collageDimension; 
    //     Picture addedimage = new Picture(h, w);
    //      for (int col = 0; col < width; col++){
    //         for (int row = 0; row < height; row++){
    //             int scol = (col *inputimage.width()) / w; //inputimage.height()()
    //             int srow = (row * inputimage.height()) / h; //inputimage.height()
    //             Color color = inputimage.get(scol, srow);
    //             addedimage.set(col, row, color);
    //         }
    //     }    
            
    //     // now let's replace the image 
    //     for (int imagew = 0; imagew < collageDimension * tileDimension; imagew++){
    //         for (int imageh = 0; imageh < collageDimension * tileDimension; imageh++){
    //             for (int i = 0; i < collageDimension; i++){
    //                 for (int j = 0; j < collageDimension; j++){

    //                         boolean topleft = (((i * tileDimension*collageDimension) + imagew) /collageDimension) >= collageRow * tileDimension;
    //                         boolean topright = (((j * tileDimension*collageDimension) + imageh) /collageDimension ) >= collageCol * tileDimension;
    //                         boolean bottomleft = (((i * tileDimension*collageDimension) + imagew) /collageDimension )<= tileDimension + (collageRow * tileDimension);
    //                         boolean bottomright = (((j * tileDimension*collageDimension) + imageh) /collageDimension) <= tileDimension + (collageCol * tileDimension);

    //                         if ((topleft && topright && bottomright && bottomleft) == true) {
    //                            // System.out.println(" topleft is " + toplefti + " toprighti his " + toprighti + " botleft is " + bottomlefti + " botright is " + bottomrighti); 


    //                             int scalwt = ((i * tileDimension*collageDimension ) + imagew) / collageDimension ;
    //                             int scalht = ((j * tileDimension*collageDimension ) + imageh) / collageDimension ;

    //                             int color_scalew = (imagew * this.original.width()) / width; 
    //                             int color_scaleh = (imageh * this.original.height()) / height;  

    //                             Color color = addedimage.get(color_scaleh, color_scalew);
    //                             newimage.set(scalht, scalwt, color);
    //                            // System.out.println(" image w is " + imagew + " image his " + imageh + " scalewt is " + scalwt + " scaleht is " + scalht); 
 

    //                         } 
    //                 }
    //             }
    //         }
    //     }  
        
    //    this.collage = newimage; //this.
    //    //showCollagePicture(); 
    // }

    // WRITE YOUR CODE HERE
    
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */

    public void makeCollage () {
        //int width = collageDimension * tileDimension;
        //int height = collageDimension * tileDimension; 
        // Picture newimage = new Picture(collageDimension * tileDimension * collageDimension, collageDimension * tileDimension * collageDimension); 
       // Picture newimage = new Picture(collageDimension * tileDimension, collageDimension * tileDimension); 
        //this.original = this.collage; 
        // for (int imagew = 0; imagew < tileDimension * collageDimension; imagew++){
        //     for (int imageh = 0; imageh < tileDimension * collageDimension; imageh++){
        
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
                        // int scalwt = ((i * tileDimension*collageDimension) + imagew) / collageDimension;
                        // int scalht = ((j * tileDimension*collageDimension) + imageh) / collageDimension;

                        // // int color_scalew = (imagew * this.original.width()) / width; 
                        // // int color_scaleh = (imageh * this.original.height()) / height; 
                        // int color_scalew = (imagew * this.original.width()) / width; 
                        // int color_scaleh = (imageh * this.original.height()) / height; 

                        // Color color = this.original.get(color_scaleh, color_scalew);
                        // newimage.set(scalht, scalwt, color);
                        // //newimage.set(imageh, imagew, color);
                        //System.out.println(" image w is " + imagew + " image his " + imageh + " scalewt is " + scalwt + " scaleht is " + scalht); 
                    }
                }
            
        }
        // this.collage = newimage; 
        // showCollagePicture();
    
    
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
                        
                              //    int width = collageDimension * tileDimension;
      //   int height = collageDimension * tileDimension; 

      //   //Picture newimage = new Picture(collageDimension * tileDimension, collageDimension * tileDimension);  
      //   Picture newimage = this.collage;
      //   Picture inputimage = new Picture(this.original);
      //   // scale filename image 
      //   int w = this.tileDimension * this.collageDimension;
      //   int h = this.tileDimension * this.collageDimension; 
      //   Picture addedimage = new Picture(h, w);
      //    for (int col = 0; col < width; col++){
      //       for (int row = 0; row < height; row++){
      //           int scol = (col * inputimage.width()) / w;
      //           int srow = (row * inputimage.height()) / h;
      //           Color color = inputimage.get(scol, srow);

      //            if (component.equals("red")){//
      //               int r = color.getRed();//
      //               addedimage.set(col, row, new Color(r, 0, 0));//
      //               }
      //           else if (component.equals("green")){
      //               int g = color.getGreen();
      //               addedimage.set(col, row, new Color(0, g, 0));
      //           }
      //           else if (component.equals("blue")){
      //            int b = color.getBlue();
      //            addedimage.set(col, row, new Color(0, 0, b));
      //           }
      //          else{
      //           addedimage.set(col, row, color);
      //       }
      //    } 
      // }   
            
    //     // now let's replace the image 
    //     for (int imagew = 0; imagew < collageDimension * tileDimension; imagew++){
    //         for (int imageh = 0; imageh < collageDimension * tileDimension; imageh++){
    //             for (int i = 0; i < collageDimension; i++){
    //                 for (int j = 0; j < collageDimension; j++){
    //                          boolean topleft = (((i * tileDimension*collageDimension) + imagew) /collageDimension) >= collageRow * tileDimension;
    //                         boolean topright = (((j * tileDimension*collageDimension) + imageh) /collageDimension ) >= collageCol * tileDimension;
    //                         boolean bottomleft = (((i * tileDimension*collageDimension) + imagew) /collageDimension )<= tileDimension + (collageRow * tileDimension);
    //                         boolean bottomright = (((j * tileDimension*collageDimension) + imageh) /collageDimension) <= tileDimension + (collageCol * tileDimension);

    //                         if ((topleft && topright && bottomright && bottomleft) == true) {
    //                             //System.out.println(" topleft is " + toplefti + " toprighti his " + toprighti + " botleft is " + bottomlefti + " botright is " + bottomrighti); 


    //                             int scalwt = ((i * tileDimension*collageDimension) + imagew) / collageDimension;
    //                             int scalht = ((j * tileDimension*collageDimension) + imageh) / collageDimension;

    //                             int color_scalew = (imagew * this.original.width()) / width; 
    //                             int color_scaleh = (imageh * this.original.height()) / height;  

    //                             Color color = addedimage.get(color_scaleh, color_scalew);
    //                             newimage.set(scalht, scalwt, color);
    //                             //System.out.println(" image w is " + imagew + " image his " + imageh + " scalewt is " + scalwt + " scaleht is " + scalht); 
 

                            
    //                         }
    //                 }
    //             }
    //         }
    //     }  
    //     this.collage = newimage; 
    //     showCollagePicture(); 
    // }
        // now let's replace the image 
       
       

    // // WRITE YOUR CODE HERE
    //}

 //    /*
 //     * Greyscale tile at (collageCol, collageRow)
 //     * (see Week 9 slides, the code for luminance is at the book's website)
 //     *
 //     * @param collageCol tile column
 //     * @param collageRow tile row
    
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

 //        int width = collageDimension * tileDimension;
 //        int height = collageDimension * tileDimension; 

 //        //Picture newimage = new Picture(collageDimension * tileDimension, collageDimension * tileDimension); 
 //        Picture newimage = this.collage; 
 //        Picture inputimage = new Picture(this.original); 
        
 //        // scale filename image 
 //        int w = this.tileDimension * this.collageDimension;
 //        int h = this.tileDimension * this.collageDimension; 
 //        Picture addedimage = new Picture(h, w);
 //         for (int col = 0; col < width; col++){ //width
 //            for (int row = 0; row < height; row++){ //height
 //                int scol = (col * inputimage.width()) / w;
 //                int srow = (row * inputimage.height()) / h;
 //                Color color = inputimage.get(scol, srow);
 //                Color gray = Luminance.toGray(color);
 //                addedimage.set(col, row, gray);

                
 //            }

 //        }
            
            
 //        // now let's replace the image 
 //        for (int imagew = 0; imagew < collageDimension * tileDimension; imagew++){ //traverses through the original images width
 //            for (int imageh = 0; imageh < collageDimension * tileDimension; imageh++){ //traverses through the original images height
 //                for (int i = 0; i < collageDimension; i++){ //goes through the rows of pixels
 //                    for (int j = 0; j < collageDimension; j++){ //goes through the height of pixels
 //                             boolean topleft = (((i * tileDimension*collageDimension) + imagew) / collageDimension) >= collageRow * tileDimension;
 //                            boolean topright = (((j * tileDimension*collageDimension) + imageh) / collageDimension) >= collageCol * tileDimension;
 //                            boolean bottomleft = (((i * tileDimension*collageDimension) + imagew) / collageDimension)<= tileDimension + (collageRow * tileDimension);
 //                            boolean bottomright = (((j * tileDimension*collageDimension) + imageh) / collageDimension) <= tileDimension + (collageCol * tileDimension);


 //                            if ((topleft && topright && bottomright && bottomleft) == true) {
 //                                //System.out.println(" topleft is " + toplefti + " toprighti his " + toprighti + " botleft is " + bottomlefti + " botright is " + bottomrighti); 


 //                                int scalwt = ((i * tileDimension*collageDimension) + imagew) / collageDimension;
 //                                int scalht = ((j * tileDimension*collageDimension) + imageh) / collageDimension;

 //                                int color_scalew = (imagew * this.original.width()) / width; 
 //                                int color_scaleh = (imageh * this.original.height()) / height;  

 //                                Color color = addedimage.get(color_scaleh, color_scalew);
 //                                newimage.set(scalht, scalwt, color);
 //                                //System.out.println(" image w is " + imagew + " image his " + imageh + " scalewt is " + scalwt + " scaleht is " + scalht); 
 

                           
 //                            }
 //                    }
 //                }
 //            }
 //        }  
 //         this.collage = newimage; 
 //         showCollagePicture(); 
 //    }
    // // WRITE YOUR CODE HERE
    


    // Test client 
    public static void main (String[] args) {
        //ArtCollage art = new ArtCollage("Ariel.jpg");
    //art.showCollagePicture();

    // Creates a collage of 3x3 tiles. Each tile dimension is 200x200 pixels
        // ArtCollage art = new ArtCollage("Ariel.jpg", 200, 3);
        // art.makeCollage();
        // art.greyscaleTile(2, 0);
        //art.replaceTile("Flo.jpg",3,3);
       // art.colorizeTile("green", 0, 1);
        // Replace tile at col 1, row 1 with args[1] image
        // art.replaceTile("Flo.jpg",0,0);
        //art.showCollagePicture();

        // ArtCollage testCollage = new ArtCollage("Ariel.jpg", 200, 3);
        // testCollage.makeCollage();
        // testCollage.replaceTile("Flo.jpg",2,1);
        // testCollage.showCollagePicture();

        //ArtCollage testCollage = new ArtCollage("Ariel.jpg");
        //System.out.println(testCollage);
       // ArtCollage testCollage2 = new ArtCollage("Ariel.jpg", 200, 3);
        //System.out.println(testCollage2);
        // ArtCollage testCollage3 = new ArtCollage("Ariel.jpg"); 
        // testCollage3.replaceTile("Ariel.jpg", 10, 3);
        // System.out.println(testCollage3);
       
        // int td = 100; 
        // int cd = 3; 
        // ArtCollage testCollage3 = new ArtCollage("Ariel.jpg", td, cd);
        // testCollage3.original = new Picture("Flo.jpg"); 
        // testCollage3.collage = new Picture("Flo.jpg"); 
        //testCollage3.collage = new Picture(picDim, picDim); 
        // 

// ArtCollage art = new ArtCollage("Flo.jpg", 200, 3);
// art.makeCollage();
// //Colorize tile at col 2, row 2 to only show the red component
//      art.colorizeTile("blue",0,1);
//   art.showCollagePicture();

//         ArtCollage art = new ArtCollage("Ariel.jpg", 200, 4);
// art.makeCollage();
// // Converts the tile at col 1, row 0 from color to greyscale
// art.greyscaleTile(3, 3);


    //  ArtCollage art = new ArtCollage("Ariel.jpg", 200, 2);
    // art.makeCollage();
    // //     // Replace 3 tiles 
    // art.replaceTile("Flo.jpg",0,1);
    //  art.replaceTile("Ariel.jpg",1,0);
    //  art.replaceTile("Flo.jpg",1,1);
    // art.colorizeTile("green",0,0);
    //  art.showCollagePicture();


// ArtCollage art = new ArtCollage("Flo.jpg");
// art.makeCollage();
// art.showCollagePicture();
    }
}