package misat11.hybrid.network.java.p404.data.message;

public enum ChatFormat {
    BOLD,
    UNDERLINED,
    STRIKETHROUGH,
    ITALIC,
    OBFUSCATED;

    public static ChatFormat byName(String name) {
        name = name.toLowerCase();
        for(ChatFormat format : values()) {
            if(format.toString().equals(name)) {
                return format;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}