public enum Item {
    KEY_1,
    KEY_2,
    KEY_3,
    KEY_4,
    KEY_5;

    public static Item itemForString(String string) {
        return switch (string) {
            case "KEY_1" -> KEY_1;
            case "KEY_2" -> KEY_2;
            case "KEY_3" -> KEY_3;
            case "KEY_4" -> KEY_4;
            case "KEY_5" -> KEY_5;
            default -> null;
        };
    }

}
