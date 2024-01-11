package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class ToothSelectionDto {
    private Integer toothId;
    private List<Integer> surfacesId;
}
