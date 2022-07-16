package patient;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.stream.IntStream;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = MutableHealth.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MutableHealth.class, name = "mutable"),
        @JsonSubTypes.Type(value = ImmutableHealth.class, name = "immutable")
})
public interface Health {
    void updateHealthIndex(int healthAddend);

    Integer getHealthIndex();

    void setHealthIndex(int healthIndex);

    // ========= 90% health
    public static String getHealthBar(int health) {
        StringBuilder equalsBar = new StringBuilder();
        IntStream.range(0, health / 10).mapToObj(i -> "=").forEach(equalsBar::append);
        return String.format("%s %s%% health", equalsBar.toString(), health);

    }
}
