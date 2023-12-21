package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class UpdateMedicineRequest {
    private Integer medicineId;
    private String name;
    private String description;
    private LocalDate expireDate;
    private BigDecimal price;
}
