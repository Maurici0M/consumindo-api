package api.java.desafio.textcolors;

public class AnsiColors {
    //TEXT COLORS
    public String textColorReset(){
        return "\033[0m";
    }

    public String textYellow(String text) {
        return "\033[33m" + text + textColorReset();
    }

    public String textBlue(String text){
        return "\033[34m" + text + textColorReset();
    }

    public String textRed(String text){
        return "\033[31m" + text + textColorReset();
    }

    public String textCyan(String text){
        return "\033[36m" + text + textColorReset();
    }

    public String textGreen(String text){
        return "\033[36m" + text + textColorReset();
    }

    public String textMagenta(String text){
        return "\033[35m" + text + textColorReset();
    }

    public String textBlack(String text){
        return "\033[30m" + text + textColorReset();
    }

    public String textWhite(String text){
        return "\033[37m" + text + textColorReset();
    }

    public String textBrilhante(String text) {
        return "\033[1m" + text + textColorReset();
    }


}
