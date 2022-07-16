package doctor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = StableTreatment.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StableTreatment.class, name = "stable"),
})
public interface Treatment {
    int getTreatmentValue();
}
