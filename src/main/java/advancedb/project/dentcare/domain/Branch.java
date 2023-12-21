package advancedb.project.dentcare.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "branch")
    @JsonBackReference
    private List<User> staffs;
    @OneToMany(mappedBy = "branch")
    @JsonBackReference
    private List<User> dentists;
}
