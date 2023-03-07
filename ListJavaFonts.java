import java.awt.GraphicsEnvironment;

/**
   From: https://alvinalexander.com/blog/post/jfc-swing/swing-faq-list-fonts-current-platform

*/
public class ListJavaFonts
{

  public static void main(String[] args)
  {
    String fonts[] = 
      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    for ( int i = 0; i < fonts.length; i++ )
    {
      System.out.println(fonts[i]);
    }
  }

}
