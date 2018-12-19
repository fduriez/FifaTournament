public final class Message {
    public static final String message1 = "Mais voyons!!!";
    public static final String message2 = "La SCEP sidi";
    public static final String message3 = "Pas maintenant ce match";
    public static final String message4 = "Ce match se disputera plus tard";
    public static final String message5 = "ça joue avec mes bourses??";

    public static String getRandomMessage(){
        int number = (int)(Math.random() * 5);
        number++;
        switch (number) {
            case 1:
                return message1;
            case 2:
                return message2;
            case 3:
                return message3;
            case 4:
                return message4;
            case 5:
                return message5;
        }
        return "";
    }
}
