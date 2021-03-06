package misat11.hybrid.message;

/**
 * Message that contains a source sender.
 */
public interface SourceMessage extends Message {
    /**
     * Get sender's name.
     *
     * @return name of sender
     */
    String getSender();
}
