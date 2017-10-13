package api;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ScheduleRequest {

    @NotEmpty
    public String name;

    @NotNull
    public long time;

    @NotNull
    public BigDecimal price;

    public String description;

    public int servings;

    public boolean pickUp;

    public boolean sitDown;

    public String photo;
}
