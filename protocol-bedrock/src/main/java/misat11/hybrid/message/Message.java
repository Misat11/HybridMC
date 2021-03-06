package misat11.hybrid.message;

public interface Message {
    String getMessage();

    Type getType();

    boolean needsTranslating();

    enum Type {
        RAW,
        CHAT,
        TRANSLATION,
        POPUP,
        JUKEBOX_POPUP,
        TIP,
        SYSTEM,
        WHISPER,
        ANNOUNCEMENT
    }
}
