package advancedb.project.dentcare.dto;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class CustomResponse {
    String message;
    List<Object> data;
}
