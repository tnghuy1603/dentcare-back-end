package advancedb.project.dentcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class CustomResponse {
    String message;
    List<Object> data;
}
