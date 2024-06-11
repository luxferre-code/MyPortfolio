package fr.valentinthuillier.portfolio;

public class CV {
    
    private CV() {
        //Do nothing
    }

    public static String toHTML() {
        /*
         * Permet de visualiser le CV en HTML
         */
        return "<object data=\"contents/cv.pdf\" type=\"application/pdf\" id=\"cv\">\n" +
                "  <p>Impossible d'afficher le CV</p>\n" +
                "</object>";
    }

}
