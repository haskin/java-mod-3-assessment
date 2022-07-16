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
        @JsonSubTypes.Type(value = GoodTreatment.class, name = "good"),
        @JsonSubTypes.Type(value = BadTreatment.class, name = "bad"),
})
public interface Treatment {
    int getTreatmentValue();
}
