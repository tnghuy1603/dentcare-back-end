package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
public class CheckoutRequest {
    private Integer treatmentPlanId;
    private BigDecimal paidAmount;
    private String note;
    private String method;
}
