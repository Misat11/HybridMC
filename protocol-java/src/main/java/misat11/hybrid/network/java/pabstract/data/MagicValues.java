package misat11.hybrid.network.java.pabstract.data;

public interface MagicValues {

	public <T> T key(Class<T> keyType, Object value);

	public <T> T value(Class<T> valueType, Object key);
}
